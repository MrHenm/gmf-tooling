/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.bridge.genmodel;

import org.eclipse.gmf.codegen.gmfgen.DefaultSizeAttributes;
import org.eclipse.gmf.codegen.gmfgen.FigureViewmap;
import org.eclipse.gmf.codegen.gmfgen.GMFGenFactory;
import org.eclipse.gmf.codegen.gmfgen.ResizeConstraints;
import org.eclipse.gmf.codegen.gmfgen.StyleAttributes;
import org.eclipse.gmf.codegen.gmfgen.Viewmap;
import org.eclipse.gmf.codegen.gmfgen.ViewmapLayoutType;
import org.eclipse.gmf.gmfgraph.Canvas;
import org.eclipse.gmf.gmfgraph.Compartment;
import org.eclipse.gmf.gmfgraph.Connection;
import org.eclipse.gmf.gmfgraph.DiagramLabel;
import org.eclipse.gmf.gmfgraph.Dimension;
import org.eclipse.gmf.gmfgraph.Direction;
import org.eclipse.gmf.gmfgraph.Figure;
import org.eclipse.gmf.gmfgraph.FigureHandle;
import org.eclipse.gmf.gmfgraph.FlowLayout;
import org.eclipse.gmf.gmfgraph.Layout;
import org.eclipse.gmf.gmfgraph.Layoutable;
import org.eclipse.gmf.gmfgraph.Node;
import org.eclipse.gmf.gmfgraph.XYLayout;
import org.eclipse.gmf.gmfgraph.util.GMFGraphSwitch;

/**
 * @author artem
 */
public class DefaultViewmapProducer extends ViewmapProducer {
	private final LayoutTypeSwitch myLayoutTypeSwitch = new LayoutTypeSwitch();
	
	public DefaultViewmapProducer() {
	}

	public Viewmap create(Canvas canvasElement) {
		FigureViewmap v = GMFGenFactory.eINSTANCE.createFigureViewmap();
		v.setFigureQualifiedClassName("org.eclipse.draw2d.FreeformLayer");
		return v;
	}

	public Viewmap create(Node node) {
		FigureViewmap v = GMFGenFactory.eINSTANCE.createFigureViewmap();
		v.setFigureQualifiedClassName("org.eclipse.draw2d.RoundedRectangle");
		return v;
	}

	public Viewmap create(Connection link) {
		FigureViewmap v = GMFGenFactory.eINSTANCE.createFigureViewmap();
		v.setFigureQualifiedClassName("org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx");
		return v;
	}

	public Viewmap create(Compartment compartment) {
		FigureViewmap v = GMFGenFactory.eINSTANCE.createFigureViewmap();
		// ShapeCompartmentFigure | NestedResizableCompartmentFigure
		v.setFigureQualifiedClassName("XXX");
		return v;
	}

	public Viewmap create(DiagramLabel label) {
		FigureViewmap v = GMFGenFactory.eINSTANCE.createFigureViewmap();
		v.setFigureQualifiedClassName("org.eclipse.draw2d.Label");
		return v;
	}

	protected final void setupResizeConstraints(Viewmap viewmap, Node diagramNode){
		Direction direction = diagramNode.getResizeConstraint();
		if (direction == null || direction.getValue() == Direction.NSEW){
			return;
		}
		ResizeConstraints constraints = GMFGenFactory.eINSTANCE.createResizeConstraints();
		constraints.setResizeHandles(direction.getValue());
		viewmap.getAttributes().add(constraints);
	}
	
	protected final void setupLayoutType(Viewmap viewmap, Node diagramNode){
		FigureHandle figure = diagramNode.getFigure();
		if (false == figure instanceof Layoutable){
			return;
		}
		ViewmapLayoutType type = myLayoutTypeSwitch.getLayoutType(((Layoutable) figure).getLayout());
		viewmap.setLayoutType(type);
	}
	
	protected final void setupStyleAttributes(Viewmap viewmap, FigureHandle handle){
		if (viewmap == null || false == handle instanceof Figure){
			return;
		}
		Figure figure = (Figure)handle;
		StyleAttributes attributes = GMFGenFactory.eINSTANCE.createStyleAttributes();
		boolean fixedSomething = false;
		if (figure.getFont() != null){
			attributes.setFixedFont(true);
			fixedSomething = true;
		}
		if (figure.getForegroundColor() != null){
			attributes.setFixedForeground(true);
			fixedSomething = true;
		}
		if (figure.getBackgroundColor() != null){
			attributes.setFixedBackground(true);
			fixedSomething = true;
		}
		
		if (fixedSomething){
			viewmap.getAttributes().add(attributes);
		}
	}
	
	/**
	 * Intentionally limited to Node's.
	 * It does not make sense to setup default size for labels, compartments, etc.
	 */
	protected final void setupDefaultSize(Viewmap viewmap, Node node) {
		FigureHandle handle = node.getFigure();
		if (handle instanceof Figure){
			Figure figure = (Figure)handle;
			Dimension prefSize = figure.getPreferredSize();
			if (prefSize != null){
				DefaultSizeAttributes defaultSize = GMFGenFactory.eINSTANCE.createDefaultSizeAttributes();
				defaultSize.setHeight(prefSize.getDy());
				defaultSize.setWidth(prefSize.getDx());
				viewmap.getAttributes().add(defaultSize);
			}
		}
	}

	private static class LayoutTypeSwitch extends GMFGraphSwitch {
		
		public ViewmapLayoutType getLayoutType(Layout layout){
			return layout == null ? ViewmapLayoutType.UNKNOWN_LITERAL : (ViewmapLayoutType)doSwitch(layout);
		}
		
		public Object caseLayout(Layout object) {
			return ViewmapLayoutType.UNKNOWN_LITERAL;
		}
		
		public Object caseFlowLayout(FlowLayout layout) {
			return layout.isForceSingleLine() ? ViewmapLayoutType.TOOLBAR_LAYOUT_LITERAL : ViewmapLayoutType.FLOW_LAYOUT_LITERAL;
		}
		
		public Object caseXYLayout(XYLayout object) {
			return ViewmapLayoutType.XY_LAYOUT_LITERAL;
		}
	}
}
