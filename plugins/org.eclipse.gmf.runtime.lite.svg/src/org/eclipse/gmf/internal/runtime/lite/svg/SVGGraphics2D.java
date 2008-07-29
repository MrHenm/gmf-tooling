/**
 * Copyright (c) 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 */
package org.eclipse.gmf.internal.runtime.lite.svg;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;

import org.apache.batik.ext.awt.g2d.DefaultGraphics2D;
import org.apache.batik.ext.awt.g2d.GraphicContext;
import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class SVGGraphics2D extends DefaultGraphics2D {

	private final AffineTransform IDENTITY = new AffineTransform();
	private final Stroke DEFAULT_STROKE = new BasicStroke();

	private Graphics swtGraphics;
	private org.eclipse.swt.graphics.Font currentFont;
	private org.eclipse.swt.graphics.Color currentColor;
	private GraphicsConfiguration gconfig; // Used to create proper font metrics

	{
		BufferedImage bi = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		gconfig = bi.createGraphics().getDeviceConfiguration();
	}

	public GraphicsConfiguration getDeviceConfiguration() {
		return gconfig;
	}

	public SVGGraphics2D(Graphics swtGraphics) {
		super(true);
		gc = new GraphicContext();
		this.swtGraphics = swtGraphics;
	}

	public void dispose() {
		if (currentColor != null) {
			currentColor.dispose();
			currentColor = null;
		}
		if (currentFont != null) {
			currentFont.dispose();
			currentFont = null;
		}
		swtGraphics = null;
	}

	protected Device getDevice() {
		return Display.getCurrent();
	}

	protected final boolean configureGraphics() {
		//swtGraphics.pushState();
		boolean supported = true;
		supported &= configureStroke(getStroke());
		supported &= configureTransformation(getTransform());
		supported &= configureClipping(getClip());
		supported &= configureComposite(getComposite());
		supported &= configurePaintMode(getPaint());
		supported &= configureRenderingHints();
		return supported;
	}

	private boolean configureRenderingHints() {
		Object antiAlias = getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		if (antiAlias != null) {
			if (antiAlias.equals(RenderingHints.VALUE_ANTIALIAS_ON))
				swtGraphics.setAntialias(SWT.ON);
			else if (antiAlias.equals(RenderingHints.VALUE_ANTIALIAS_OFF))
				swtGraphics.setAntialias(SWT.OFF);
			else if (antiAlias.equals(RenderingHints.VALUE_ANTIALIAS_DEFAULT))
				swtGraphics.setAntialias(SWT.DEFAULT);
		}

		Object textAntiAlias = getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		if (textAntiAlias != null) {
			if (textAntiAlias.equals(RenderingHints.VALUE_TEXT_ANTIALIAS_ON))
				swtGraphics.setTextAntialias(SWT.ON);
			else if (textAntiAlias.equals(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF))
				swtGraphics.setTextAntialias(SWT.OFF);
			else if (textAntiAlias.equals(RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT))
				swtGraphics.setTextAntialias(SWT.DEFAULT);
		}

		Object interpolation = getRenderingHint(RenderingHints.KEY_INTERPOLATION);
		if (interpolation != null) {
			if (interpolation.equals(RenderingHints.VALUE_INTERPOLATION_BICUBIC))
				swtGraphics.setInterpolation(SWT.HIGH);
			else if (interpolation.equals(RenderingHints.VALUE_INTERPOLATION_BILINEAR))
				swtGraphics.setInterpolation(SWT.LOW);
			else if (interpolation.equals(RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR))
				swtGraphics.setInterpolation(SWT.NONE);
		}
		return true;
	}

	private boolean configureStroke(Stroke s) {
		if (s instanceof BasicStroke) {
			BasicStroke basicStroke = (BasicStroke) s;
			switch (basicStroke.getEndCap()) {
			case BasicStroke.CAP_BUTT:
				swtGraphics.setLineCap(SWT.CAP_FLAT);
				break;
			case BasicStroke.CAP_ROUND:
				swtGraphics.setLineCap(SWT.CAP_ROUND);
				break;
			case BasicStroke.CAP_SQUARE:
				swtGraphics.setLineCap(SWT.CAP_SQUARE);
				break;
			default:
				return false;
			}

			if (basicStroke.getDashArray() != null) {
				swtGraphics.setLineStyle(SWT.LINE_CUSTOM);
				float[] currentDash = basicStroke.getDashArray();
				int[] dash = new int[currentDash.length];
				for (int i = 0; i < currentDash.length; i++)
					dash[i] = Math.round(currentDash[i]);
				swtGraphics.setLineDash(dash);
			} else {
				swtGraphics.setLineStyle(SWT.LINE_SOLID);
				swtGraphics.setLineDash(null);
			}

			switch (basicStroke.getLineJoin()) {
			case BasicStroke.JOIN_BEVEL:
				swtGraphics.setLineJoin(SWT.JOIN_BEVEL);
				break;
			case BasicStroke.JOIN_MITER:
				swtGraphics.setLineJoin(SWT.JOIN_MITER);
				break;
			case BasicStroke.JOIN_ROUND:
				swtGraphics.setLineJoin(SWT.JOIN_ROUND);
				break;
			default:
				return false;
			}

			// since we don't have precision of less then 1 pixel in
			// swt, if the image is defined as very small in the original
			// file, then the linewidth gets scaled out of proportion.
			if (basicStroke.getLineWidth() < 1 && basicStroke.getLineWidth() > 0) {
				throw new UnsupportedOperationException();
			}
			swtGraphics.setLineWidth(Math.round(basicStroke.getLineWidth()));
			return true;
		}

		return false;
	}

	private boolean configurePaintMode(Paint p) {
		if (p instanceof Color) {
			Color c = (Color) p;
			if (c.getAlpha() != 255) {
				// swt graphics doesn't support blitting onto transparency mask
				// explicitly.
				throw new UnsupportedOperationException();
			}
			RGB rgb = new RGB(c.getRed(), c.getGreen(), c.getBlue());
			if (currentColor != null) {
				currentColor.dispose();
			}
			currentColor = new org.eclipse.swt.graphics.Color(null, rgb.red, rgb.green, rgb.blue);
			swtGraphics.setBackgroundColor(currentColor);
			swtGraphics.setForegroundColor(currentColor);
			return true;
		}
		return false;
	}

	private boolean configureComposite(Composite c) {
		if (c instanceof AlphaComposite) {
			AlphaComposite ac = (AlphaComposite) c;

			// swt graphics doesn't support blitting onto transparency mask
			// explicitly.
			if (ac.getAlpha() != 1.0)
				throw new UnsupportedOperationException();

			swtGraphics.setAlpha(Math.round(ac.getAlpha() * 255));
			if (ac.getRule() == AlphaComposite.SRC_OVER) {
				return true;
			}
		}

		return false;
	}

	private boolean configureTransformation(AffineTransform cxPrime) {
		swtGraphics.rotate((float) getRotation(cxPrime));
		swtGraphics.translate((float) cxPrime.getTranslateX(), (float) cxPrime.getTranslateY());
		return true;
	}

	private static double getRotation(final AffineTransform tr) {
		final int flip = getFlip(tr);
		if (flip != 0) {
			final double scaleX = getScaleX0(tr);
			final double scaleY = getScaleY0(tr) * flip;
			return Math.atan2(tr.getShearY() / scaleY - tr.getShearX() / scaleX, tr.getScaleY() / scaleY + tr.getScaleX() / scaleX);
		}
		return Double.NaN;
	}

	private static int getFlip(final AffineTransform tr) {
		final int scaleX = sgn(tr.getScaleX());
		final int scaleY = sgn(tr.getScaleY());
		final int shearX = sgn(tr.getShearX());
		final int shearY = sgn(tr.getShearY());
		if (scaleX == scaleY && shearX == -shearY)
			return +1;
		if (scaleX == -scaleY && shearX == shearY)
			return -1;
		return 0;
	}

	private static int sgn(double value) {
		return value > 0 ? 1 : value < 0 ? -1 : 0;
	}

	private static double getScaleX0(final AffineTransform tr) {
		final double scale = tr.getScaleX();
		final double shear = tr.getShearX();
		if (shear == 0)
			return Math.abs(scale);
		if (scale == 0)
			return Math.abs(shear);
		return Math.hypot(scale, shear);
	}

	private static double getScaleY0(final AffineTransform tr) {
		final double scale = tr.getScaleY();
		final double shear = tr.getShearY();
		if (shear == 0)
			return Math.abs(scale);
		if (scale == 0)
			return Math.abs(shear);
		return Math.hypot(scale, shear);
	}

	private boolean configureClipping(Shape clipShape) {
		Path path = getPath(getDevice(), clipShape);
		if (path != null) {
			swtGraphics.setClip(path);
		}
		return true;
	}

	protected final void resetGraphics() {
		configureTransformation(IDENTITY);
		configurePaintMode(Color.WHITE);
		configureStroke(DEFAULT_STROKE);
		configureComposite(AlphaComposite.SrcOver);
		if (currentColor != null) {
			currentColor.dispose();
			currentColor = null;
		}
		if (currentFont != null) {
			currentFont.dispose();
			currentFont = null;
		}
		swtGraphics.setClip((Path) null);
		//swtGraphics.popState();
	}

	public void draw(Shape s) {
		if (!configureGraphics()) {
			throw new UnsupportedOperationException();
		}
		Path path = getPath(getDevice(), s);
		if (path != null) {
			swtGraphics.drawPath(path);
		}
		resetGraphics();
	}

	public void fill(Shape s) {
		if (!configureGraphics()) {
			throw new UnsupportedOperationException();
		}
		Path path = getPath(getDevice(), s);
		if (path != null) {
			swtGraphics.fillPath(path);
		}
		resetGraphics();
	}

	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		if (img instanceof BufferedImage && configureGraphics()) {
			BufferedImage bufImg = (BufferedImage) img;
			drawAWTImage(bufImg, 0, 0, bufImg.getWidth(), bufImg.getHeight(), x, y, bufImg.getWidth(), bufImg.getHeight());
		} else {
			throw new UnsupportedOperationException();
		}
		resetGraphics();
		return true;
	}

	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		if (img instanceof BufferedImage && configureGraphics()) {
			BufferedImage bufImg = (BufferedImage) img;
			drawAWTImage((BufferedImage) img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), x, y, width, height);
		} else {
			throw new UnsupportedOperationException();
		}
		resetGraphics();
		return true;
	}

	private void drawAWTImage(BufferedImage awtImage, int x, int y, int width, int height, int tx, int ty, int twidth, int theight) {
		org.eclipse.swt.graphics.Image swtImage = toSWT(getDevice(), awtImage);
		swtGraphics.drawImage(swtImage, x, y, width, height, tx, ty, twidth, theight);
		swtImage.dispose();
	}

	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		throw new UnsupportedOperationException();
	}

	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		throw new UnsupportedOperationException();
	}

	public void drawString(AttributedCharacterIterator iterator, float x, float y) {
		throw new UnsupportedOperationException();
	}

	public void drawString(String s, float x, float y) {
		throw new UnsupportedOperationException();
	}

	private static Path getPath(Device device, Shape s) {
		PathIterator pi = s.getPathIterator(null);
		Path path = new Path(device);
		float seg[] = new float[6];
		int segType = 0;
		while (!pi.isDone()) {
			segType = pi.currentSegment(seg);
			switch (segType) {
			case PathIterator.SEG_LINETO:
				path.lineTo(seg[0], seg[1]);
				break;
			case PathIterator.SEG_CLOSE:
				path.close();
				break;
			case PathIterator.SEG_MOVETO:
				path.moveTo(seg[0], seg[1]);
				break;
			case PathIterator.SEG_QUADTO:
				path.quadTo(seg[0], seg[1], seg[2], seg[3]);
				break;
			case PathIterator.SEG_CUBICTO:
				path.cubicTo(seg[0], seg[1], seg[2], seg[3], seg[4], seg[5]);
				break;
			default:
				throw new Error();
			}
			pi.next();
		}
		return path;
	}

	/**
	 * Converts an AWT based buffered image into an SWT <code>Image</code>. This
	 * will always return an <code>Image</code> that has 24 bit depth regardless
	 * of the type of AWT buffered image that is passed into the method.
	 * 
	 * @param awtImage
	 *            the {@link java.awt.image.BufferedImage} to be converted to an
	 *            <code>Image</code>
	 * @return an <code>Image</code> that represents the same image data as the
	 *         AWT <code>BufferedImage</code> type.
	 */
	public static org.eclipse.swt.graphics.Image toSWT(Device device, BufferedImage awtImage) {
		// We can force bitdepth to be 24 bit because BufferedImage getRGB
		// allows us to always
		// retrieve 24 bit data regardless of source color depth.
		PaletteData palette = new PaletteData(0xFF0000, 0xFF00, 0xFF);
		ImageData swtImageData = new ImageData(awtImage.getWidth(), awtImage.getHeight(), 24, palette);
		// Ensure scansize is aligned on 32 bit.
		int scansize = (((awtImage.getWidth() * 3) + 3) * 4) / 4;
		WritableRaster alphaRaster = awtImage.getAlphaRaster();
		byte[] alphaBytes = new byte[awtImage.getWidth()];
		for (int y = 0; y < awtImage.getHeight(); y++) {
			int[] buff = awtImage.getRGB(0, y, awtImage.getWidth(), 1, null, 0, scansize);
			swtImageData.setPixels(0, y, awtImage.getWidth(), buff, 0);
			if (alphaRaster != null) {
				int[] alpha = alphaRaster.getPixels(0, y, awtImage.getWidth(), 1, (int[]) null);
				for (int i = 0; i < awtImage.getWidth(); i++) {
					alphaBytes[i] = (byte) alpha[i];
				}
				swtImageData.setAlphas(0, y, awtImage.getWidth(), alphaBytes, 0);
			}
		}
		return new org.eclipse.swt.graphics.Image(device, swtImageData);
	}
}
