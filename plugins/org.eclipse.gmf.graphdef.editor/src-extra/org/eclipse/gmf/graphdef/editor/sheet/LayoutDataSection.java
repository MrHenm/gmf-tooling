/*
 * Copyright (c) 2008 Borland Software Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Artem Tikhomirov (Borland) - initial API and implementation
 *     Alexander Shatalin (Borland) - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.sheet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.gmfgraph.Alignment;
import org.eclipse.gmf.gmfgraph.BorderLayoutData;
import org.eclipse.gmf.gmfgraph.CustomClass;
import org.eclipse.gmf.gmfgraph.CustomLayoutData;
import org.eclipse.gmf.gmfgraph.GMFGraphFactory;
import org.eclipse.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.gmf.gmfgraph.GridLayoutData;
import org.eclipse.gmf.gmfgraph.Layoutable;
import org.eclipse.gmf.gmfgraph.XYLayoutData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class LayoutDataSection extends AbstractPropertySection implements ChangeTracker, Listener {

	private org.eclipse.emf.common.notify.Adapter[] myModelListeners;

	private boolean myIsCommit;
	private boolean myIsRefresh;
	private Object myInput;

	private Group myLayoutDataKindRadios;
	private Group myBorderLayoutDataDetails;
	private Group myGridLayoutDataDetails;
	private Group myXyLayoutDataDetails;
	private Group myCustomLayoutDataDetails;
	private Button myR1;
	private Button myR2;
	private Button myR3;
	private Button myR4;
	private Button myR5;
	private Button myBorderLayoutDataIsVertical;
	private Combo myBorderLayoutDataAlignment;
	private Button myGridLayoutDataGrabExcessHorizontalSpace;
	private Group myGridLayoutDataAlignment;
	private Button myGridLayoutDataGrabExcessVerticalSpace;
	private Spinner myGridLayoutDataHorizontalIndent;
	private Group myGridLayoutDataSpan;
	private Group myGridLayoutDataSizeHint;
	private Spinner myXyLayoutDataX;
	private Spinner myXyLayoutDataWidth;
	private Spinner myXyLayoutDataY;
	private Spinner myXyLayoutDataHeight;
	private Text myCustomLayoutQualifiedClassName;
	private Combo myGridLayoutDataVerticalAlignment;
	private Combo myGridLayoutDataHorizontalAlignment;
	private Spinner myGridLayoutDataVerticalSpan;
	private Spinner myGridLayoutDataHorizontalSpan;
	private Spinner myGridLayoutDataSizeHintHorizontal;
	private Spinner myGridLayoutDataSizeHintVertical;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage page) {
		super.createControls(parent, page);
		getWidgetFactory().paintBordersFor(parent);
		myLayoutDataKindRadios = createGroup(parent, "Layout Data");
		myR1 = getWidgetFactory().createButton(myLayoutDataKindRadios, "Border Layout Data", SWT.RADIO);
		myR2 = getWidgetFactory().createButton(myLayoutDataKindRadios, "Grid Layout Data", SWT.RADIO);
		myR3 = getWidgetFactory().createButton(myLayoutDataKindRadios, "XY Layout Data", SWT.RADIO);
		myR4 = getWidgetFactory().createButton(myLayoutDataKindRadios, "Custom Layout Data", SWT.RADIO);
		myR5 = getWidgetFactory().createButton(myLayoutDataKindRadios, "None", SWT.RADIO);
		myLayoutDataKindRadios.setLayout(new org.eclipse.swt.layout.FillLayout(org.eclipse.swt.SWT.VERTICAL));
		myBorderLayoutDataDetails = createGroup(parent, "Details");
		myBorderLayoutDataIsVertical = getWidgetFactory().createButton(myBorderLayoutDataDetails, "Is Vertical", SWT.CHECK);
		createLabel(myBorderLayoutDataDetails, "Alignment");
		myBorderLayoutDataAlignment = new Combo(myBorderLayoutDataDetails, SWT.FLAT | SWT.READ_ONLY);
		myBorderLayoutDataAlignment.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		getWidgetFactory().adapt(myBorderLayoutDataAlignment, false, false);
		myBorderLayoutDataDetails.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING).span(2, 1).applyTo(myBorderLayoutDataIsVertical);
		myGridLayoutDataDetails = createGroup(parent, "Details");
		myGridLayoutDataGrabExcessHorizontalSpace = getWidgetFactory().createButton(myGridLayoutDataDetails, "Grab Excess Horizontal Space", SWT.CHECK);
		myGridLayoutDataAlignment = createGroup(myGridLayoutDataDetails, "Alignment");
		createLabel(myGridLayoutDataAlignment, "Vertical");
		myGridLayoutDataVerticalAlignment = new Combo(myGridLayoutDataAlignment, SWT.FLAT | SWT.READ_ONLY);
		myGridLayoutDataVerticalAlignment.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		getWidgetFactory().adapt(myGridLayoutDataVerticalAlignment, false, false);
		createLabel(myGridLayoutDataAlignment, "Horizontal");
		myGridLayoutDataHorizontalAlignment = new Combo(myGridLayoutDataAlignment, SWT.FLAT | SWT.READ_ONLY);
		myGridLayoutDataHorizontalAlignment.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		getWidgetFactory().adapt(myGridLayoutDataHorizontalAlignment, false, false);
		myGridLayoutDataAlignment.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		myGridLayoutDataGrabExcessVerticalSpace = getWidgetFactory().createButton(myGridLayoutDataDetails, "Grab Excess Vertical Space", SWT.CHECK);
		createLabel(myGridLayoutDataDetails, "Horizontal Indent");
		myGridLayoutDataHorizontalIndent = new Spinner(myGridLayoutDataDetails, SWT.FLAT);
		myGridLayoutDataHorizontalIndent.setMinimum(0);
		myGridLayoutDataHorizontalIndent.setMaximum(2147483647);
		myGridLayoutDataHorizontalIndent.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myGridLayoutDataSpan = createGroup(myGridLayoutDataDetails, "Span");
		createLabel(myGridLayoutDataSpan, "Vertical");
		myGridLayoutDataVerticalSpan = new Spinner(myGridLayoutDataSpan, SWT.FLAT);
		myGridLayoutDataVerticalSpan.setMinimum(1);
		myGridLayoutDataVerticalSpan.setMaximum(2147483647);
		myGridLayoutDataVerticalSpan.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myGridLayoutDataSpan, "Horizontal");
		myGridLayoutDataHorizontalSpan = new Spinner(myGridLayoutDataSpan, SWT.FLAT);
		myGridLayoutDataHorizontalSpan.setMinimum(1);
		myGridLayoutDataHorizontalSpan.setMaximum(2147483647);
		myGridLayoutDataHorizontalSpan.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myGridLayoutDataSpan.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		myGridLayoutDataSizeHint = createGroup(myGridLayoutDataDetails, "SizeHint");
		createLabel(myGridLayoutDataSizeHint, "Horizontal");
		myGridLayoutDataSizeHintHorizontal = new Spinner(myGridLayoutDataSizeHint, SWT.FLAT);
		myGridLayoutDataSizeHintHorizontal.setMinimum(-1);
		myGridLayoutDataSizeHintHorizontal.setMaximum(2147483647);
		myGridLayoutDataSizeHintHorizontal.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myGridLayoutDataSizeHint, "Vertical");
		myGridLayoutDataSizeHintVertical = new Spinner(myGridLayoutDataSizeHint, SWT.FLAT);
		myGridLayoutDataSizeHintVertical.setMinimum(-1);
		myGridLayoutDataSizeHintVertical.setMaximum(2147483647);
		myGridLayoutDataSizeHintVertical.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myGridLayoutDataSizeHint.setLayout(new org.eclipse.swt.layout.GridLayout(2, false));
		myGridLayoutDataDetails.setLayout(new org.eclipse.swt.layout.GridLayout(3, false));
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING).span(2, 1).applyTo(myGridLayoutDataGrabExcessHorizontalSpace);
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING).span(1, 3).applyTo(myGridLayoutDataAlignment);
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING).span(2, 1).applyTo(myGridLayoutDataGrabExcessVerticalSpace);
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING).span(2, 1).applyTo(myGridLayoutDataSpan);
		org.eclipse.jface.layout.GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.BEGINNING).applyTo(myGridLayoutDataSizeHint);
		myXyLayoutDataDetails = createGroup(parent, "Details");
		createLabel(myXyLayoutDataDetails, "X");
		myXyLayoutDataX = new Spinner(myXyLayoutDataDetails, SWT.FLAT);
		myXyLayoutDataX.setMinimum(0);
		myXyLayoutDataX.setMaximum(2147483647);
		myXyLayoutDataX.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myXyLayoutDataDetails, "Width");
		myXyLayoutDataWidth = new Spinner(myXyLayoutDataDetails, SWT.FLAT);
		myXyLayoutDataWidth.setMinimum(0);
		myXyLayoutDataWidth.setMaximum(2147483647);
		myXyLayoutDataWidth.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myXyLayoutDataDetails, "Y");
		myXyLayoutDataY = new Spinner(myXyLayoutDataDetails, SWT.FLAT);
		myXyLayoutDataY.setMinimum(0);
		myXyLayoutDataY.setMaximum(2147483647);
		myXyLayoutDataY.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		createLabel(myXyLayoutDataDetails, "Height");
		myXyLayoutDataHeight = new Spinner(myXyLayoutDataDetails, SWT.FLAT);
		myXyLayoutDataHeight.setMinimum(0);
		myXyLayoutDataHeight.setMaximum(2147483647);
		myXyLayoutDataHeight.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER); // @see #145837
		myXyLayoutDataDetails.setLayout(new org.eclipse.swt.layout.GridLayout(4, false));
		myCustomLayoutDataDetails = createGroup(parent, "Details");
		createLabel(myCustomLayoutDataDetails, "Qualified Class Name");
		myCustomLayoutQualifiedClassName = getWidgetFactory().createText(myCustomLayoutDataDetails, null);
		myCustomLayoutDataDetails.setLayout(new org.eclipse.swt.layout.FillLayout(org.eclipse.swt.SWT.VERTICAL));

		parent.setLayout(new org.eclipse.swt.layout.FormLayout());
		org.eclipse.swt.layout.FormData parentFD;
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(0, 10);
		myLayoutDataKindRadios.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myLayoutDataKindRadios, 20, org.eclipse.swt.SWT.RIGHT);
		myBorderLayoutDataDetails.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myLayoutDataKindRadios, 20, org.eclipse.swt.SWT.RIGHT);
		myGridLayoutDataDetails.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myLayoutDataKindRadios, 20, org.eclipse.swt.SWT.RIGHT);
		myXyLayoutDataDetails.setLayoutData(parentFD);
		parentFD = new org.eclipse.swt.layout.FormData();
		parentFD.left = new org.eclipse.swt.layout.FormAttachment(myLayoutDataKindRadios, 20, org.eclipse.swt.SWT.RIGHT);
		myCustomLayoutDataDetails.setLayoutData(parentFD);
		// TODO myBorderLayoutDataAlignment.setItems(VALUES.toString().toArray());
		for (org.eclipse.emf.common.util.Enumerator e : Alignment.VALUES) {
			myBorderLayoutDataAlignment.add(e.getName());
		}
		// TODO myGridLayoutDataVerticalAlignment.setItems(VALUES.toString().toArray());
		for (org.eclipse.emf.common.util.Enumerator e : Alignment.VALUES) {
			myGridLayoutDataVerticalAlignment.add(e.getName());
		}
		// TODO myGridLayoutDataHorizontalAlignment.setItems(VALUES.toString().toArray());
		for (org.eclipse.emf.common.util.Enumerator e : Alignment.VALUES) {
			myGridLayoutDataHorizontalAlignment.add(e.getName());
		}

		for (Text t : new Text[] { myCustomLayoutQualifiedClassName }) {
			t.addListener(SWT.Modify, this);
			t.addListener(SWT.FocusOut, this);
			t.addListener(SWT.KeyDown, this);
		}
		for (Spinner s : new Spinner[] { myGridLayoutDataHorizontalIndent, myXyLayoutDataX, myXyLayoutDataWidth, myXyLayoutDataY, myXyLayoutDataHeight, myGridLayoutDataVerticalSpan,
				myGridLayoutDataHorizontalSpan, myGridLayoutDataSizeHintHorizontal, myGridLayoutDataSizeHintVertical }) {
			s.addListener(SWT.Modify, this);
			s.addListener(SWT.FocusOut, this);
		}
		for (Widget w : new Widget[] { myR1, myR2, myR3, myR4, myR5, myBorderLayoutDataIsVertical, myGridLayoutDataGrabExcessHorizontalSpace, myGridLayoutDataGrabExcessVerticalSpace,
				myBorderLayoutDataAlignment, myGridLayoutDataVerticalAlignment, myGridLayoutDataHorizontalAlignment }) {
			w.addListener(SWT.Selection, this);
		}
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Object newInput = null;
		if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
			newInput = unwrap(((IStructuredSelection) selection).getFirstElement());
		}
		if (newInput != myInput) {
			if (myInput != null) {
				detach();
			}
			myInput = newInput;
			if (newInput != null) {
				attach();
			}
		}
	}

	protected void commit() {

		if (myBorderLayoutDataDetails.isVisible()) {
			getInput().setLayoutData(GMFGraphFactory.eINSTANCE.createBorderLayoutData());
			((BorderLayoutData) getInput().getLayoutData()).setVertical(myBorderLayoutDataIsVertical.getSelection());
			((BorderLayoutData) getInput().getLayoutData()).setAlignment(Alignment.get(myBorderLayoutDataAlignment.getSelectionIndex()));
		}
		if (myGridLayoutDataDetails.isVisible()) {
			getInput().setLayoutData(GMFGraphFactory.eINSTANCE.createGridLayoutData());
			((GridLayoutData) getInput().getLayoutData()).setGrabExcessHorizontalSpace(myGridLayoutDataGrabExcessHorizontalSpace.getSelection());
			((GridLayoutData) getInput().getLayoutData()).setGrabExcessVerticalSpace(myGridLayoutDataGrabExcessVerticalSpace.getSelection());
			((GridLayoutData) getInput().getLayoutData()).setVerticalAlignment(Alignment.get(myGridLayoutDataVerticalAlignment.getSelectionIndex()));
			((GridLayoutData) getInput().getLayoutData()).setHorizontalAlignment(Alignment.get(myGridLayoutDataHorizontalAlignment.getSelectionIndex()));
			((GridLayoutData) getInput().getLayoutData()).setVerticalSpan(myGridLayoutDataVerticalSpan.getSelection());
			((GridLayoutData) getInput().getLayoutData()).setHorizontalSpan(myGridLayoutDataHorizontalSpan.getSelection());
			((GridLayoutData) getInput().getLayoutData()).setHorizontalIndent(myGridLayoutDataHorizontalIndent.getSelection());
			if (((GridLayoutData) getInput().getLayoutData()).getSizeHint() == null) {
				((GridLayoutData) getInput().getLayoutData()).setSizeHint(GMFGraphFactory.eINSTANCE.createDimension());
			}
			((GridLayoutData) getInput().getLayoutData()).getSizeHint().setDx(myGridLayoutDataSizeHintHorizontal.getSelection());
			((GridLayoutData) getInput().getLayoutData()).getSizeHint().setDy(myGridLayoutDataSizeHintVertical.getSelection());
		}
		if (myXyLayoutDataDetails.isVisible()) {
			getInput().setLayoutData(GMFGraphFactory.eINSTANCE.createXYLayoutData());
			if (((XYLayoutData) getInput().getLayoutData()).getTopLeft() == null) {
				((XYLayoutData) getInput().getLayoutData()).setTopLeft(GMFGraphFactory.eINSTANCE.createPoint());
			}
			if (((XYLayoutData) getInput().getLayoutData()).getSize() == null) {
				((XYLayoutData) getInput().getLayoutData()).setSize(GMFGraphFactory.eINSTANCE.createDimension());
			}
			((XYLayoutData) getInput().getLayoutData()).getTopLeft().setX(myXyLayoutDataX.getSelection());
			((XYLayoutData) getInput().getLayoutData()).getTopLeft().setY(myXyLayoutDataY.getSelection());
			((XYLayoutData) getInput().getLayoutData()).getSize().setDx(myXyLayoutDataWidth.getSelection());
			((XYLayoutData) getInput().getLayoutData()).getSize().setDy(myXyLayoutDataHeight.getSelection());
		}
		if (myCustomLayoutDataDetails.isVisible()) {
			getInput().setLayoutData(GMFGraphFactory.eINSTANCE.createCustomLayoutData());
			((CustomClass) getInput().getLayoutData()).setQualifiedClassName(/*Bridge.fieldGet(myCustomLayoutQualifiedClassName)*/myCustomLayoutQualifiedClassName.getText());
		}
		if (myR5.getSelection()) {
			getInput().setLayoutData(null);
		}

	}

	@Override
	public void refresh() {
		myIsRefresh = true;

		if (getInput().getLayoutData() instanceof BorderLayoutData) {
			if (getInput().getLayoutData() != null) {
				myBorderLayoutDataIsVertical.setSelection(((BorderLayoutData) getInput().getLayoutData()).isVertical());
				myBorderLayoutDataAlignment.select(((BorderLayoutData) getInput().getLayoutData()).getAlignment().getValue());
			}
			myR1.setSelection(true);
			myBorderLayoutDataDetails.setVisible(true);
		} else {
			myR1.setSelection(false);
			myBorderLayoutDataDetails.setVisible(false);
		}
		if (getInput().getLayoutData() instanceof GridLayoutData) {
			if (getInput().getLayoutData() != null) {
				myGridLayoutDataGrabExcessHorizontalSpace.setSelection(((GridLayoutData) getInput().getLayoutData()).isGrabExcessHorizontalSpace());
				myGridLayoutDataGrabExcessVerticalSpace.setSelection(((GridLayoutData) getInput().getLayoutData()).isGrabExcessVerticalSpace());
				myGridLayoutDataVerticalAlignment.select(((GridLayoutData) getInput().getLayoutData()).getVerticalAlignment().getValue());
				myGridLayoutDataHorizontalAlignment.select(((GridLayoutData) getInput().getLayoutData()).getHorizontalAlignment().getValue());
				myGridLayoutDataVerticalSpan.setSelection(((GridLayoutData) getInput().getLayoutData()).getVerticalSpan());
				myGridLayoutDataHorizontalSpan.setSelection(((GridLayoutData) getInput().getLayoutData()).getHorizontalSpan());
				myGridLayoutDataHorizontalIndent.setSelection(((GridLayoutData) getInput().getLayoutData()).getHorizontalIndent());
			}
			if (getInput().getLayoutData() != null && ((GridLayoutData) getInput().getLayoutData()).getSizeHint() != null) {
				myGridLayoutDataSizeHintHorizontal.setSelection(((GridLayoutData) getInput().getLayoutData()).getSizeHint().getDx());
				myGridLayoutDataSizeHintVertical.setSelection(((GridLayoutData) getInput().getLayoutData()).getSizeHint().getDy());
			}
			myR2.setSelection(true);
			myGridLayoutDataDetails.setVisible(true);
		} else {
			myR2.setSelection(false);
			myGridLayoutDataDetails.setVisible(false);
		}
		if (getInput().getLayoutData() instanceof XYLayoutData) {
			if (getInput().getLayoutData() != null && ((XYLayoutData) getInput().getLayoutData()).getTopLeft() != null) {
				myXyLayoutDataX.setSelection(((XYLayoutData) getInput().getLayoutData()).getTopLeft().getX());
				myXyLayoutDataY.setSelection(((XYLayoutData) getInput().getLayoutData()).getTopLeft().getY());
			}
			if (getInput().getLayoutData() != null && ((XYLayoutData) getInput().getLayoutData()).getSize() != null) {
				myXyLayoutDataWidth.setSelection(((XYLayoutData) getInput().getLayoutData()).getSize().getDx());
				myXyLayoutDataHeight.setSelection(((XYLayoutData) getInput().getLayoutData()).getSize().getDy());
			}
			myR3.setSelection(true);
			myXyLayoutDataDetails.setVisible(true);
		} else {
			myR3.setSelection(false);
			myXyLayoutDataDetails.setVisible(false);
		}
		if (getInput().getLayoutData() instanceof CustomLayoutData) {
			if (getInput().getLayoutData() != null) {
				myCustomLayoutQualifiedClassName.setText(((CustomClass) getInput().getLayoutData()).getQualifiedClassName());/*Bridge.fieldSet(myCustomLayoutQualifiedClassName, ((CustomClass) getInput().getLayoutData()).getQualifiedClassName());*/
			}
			myR4.setSelection(true);
			myCustomLayoutDataDetails.setVisible(true);
		} else {
			myR4.setSelection(false);
			myCustomLayoutDataDetails.setVisible(false);
		}
		if (getInput().getLayoutData() == null) {
			myR5.setSelection(true);
		} else {
			myR5.setSelection(false);
		}

		myIsRefresh = false;
	}

	@Override
	public void aboutToBeHidden() {
		if (myInput != null) {
			detach();
		}
	}

	public void handleEvent(Event event) {
		if (myIsRefresh) {
			return;
		}

		if (event.type == SWT.Modify) {
			// XXX also override isDirty to compare values if they
			// match model's and to clear dirty state in case like aaa^H^H^H 
			markDirty();
		} else if (event.type == SWT.FocusOut) {
			applyChanges();
		} else if (event.type == SWT.KeyDown) {
			if (event.keyCode == SWT.ESC) {
				discardChanges();
			} else if (event.keyCode == SWT.CR) {
				applyChanges();
			}
		}
		if (event.type == SWT.Selection) {
			if (myBorderLayoutDataIsVertical == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			} else if (myGridLayoutDataGrabExcessHorizontalSpace == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			} else if (myGridLayoutDataGrabExcessVerticalSpace == event.widget) {
				applyChanges(); // Commit; View to Model
				// Optimization? Instead of full refresh, just dependant widgets should get updated
			}
			if (myR1 == event.widget) {
				if (myR1.getSelection()) {
					myBorderLayoutDataDetails.setVisible(true);
					myGridLayoutDataDetails.setVisible(false);
					myXyLayoutDataDetails.setVisible(false);
					myCustomLayoutDataDetails.setVisible(false);
					applyChanges(); // Commit; View to Model
					if (getInput().getLayoutData() != null) {
						myBorderLayoutDataIsVertical.setSelection(((BorderLayoutData) getInput().getLayoutData()).isVertical());
						myBorderLayoutDataAlignment.select(((BorderLayoutData) getInput().getLayoutData()).getAlignment().getValue());
					}
				} else {
					myBorderLayoutDataDetails.setVisible(false);
				}
			} else if (myR2 == event.widget) {
				if (myR2.getSelection()) {
					myGridLayoutDataDetails.setVisible(true);
					myBorderLayoutDataDetails.setVisible(false);
					myXyLayoutDataDetails.setVisible(false);
					myCustomLayoutDataDetails.setVisible(false);
					applyChanges(); // Commit; View to Model
					if (getInput().getLayoutData() != null) {
						myGridLayoutDataGrabExcessHorizontalSpace.setSelection(((GridLayoutData) getInput().getLayoutData()).isGrabExcessHorizontalSpace());
						myGridLayoutDataGrabExcessVerticalSpace.setSelection(((GridLayoutData) getInput().getLayoutData()).isGrabExcessVerticalSpace());
						myGridLayoutDataVerticalAlignment.select(((GridLayoutData) getInput().getLayoutData()).getVerticalAlignment().getValue());
						myGridLayoutDataHorizontalAlignment.select(((GridLayoutData) getInput().getLayoutData()).getHorizontalAlignment().getValue());
						myGridLayoutDataVerticalSpan.setSelection(((GridLayoutData) getInput().getLayoutData()).getVerticalSpan());
						myGridLayoutDataHorizontalSpan.setSelection(((GridLayoutData) getInput().getLayoutData()).getHorizontalSpan());
						myGridLayoutDataHorizontalIndent.setSelection(((GridLayoutData) getInput().getLayoutData()).getHorizontalIndent());
					}
					if (getInput().getLayoutData() != null && ((GridLayoutData) getInput().getLayoutData()).getSizeHint() != null) {
						myGridLayoutDataSizeHintHorizontal.setSelection(((GridLayoutData) getInput().getLayoutData()).getSizeHint().getDx());
						myGridLayoutDataSizeHintVertical.setSelection(((GridLayoutData) getInput().getLayoutData()).getSizeHint().getDy());
					}
				} else {
					myGridLayoutDataDetails.setVisible(false);
				}
			} else if (myR3 == event.widget) {
				if (myR3.getSelection()) {
					myXyLayoutDataDetails.setVisible(true);
					myBorderLayoutDataDetails.setVisible(false);
					myGridLayoutDataDetails.setVisible(false);
					myCustomLayoutDataDetails.setVisible(false);
					applyChanges(); // Commit; View to Model
					if (getInput().getLayoutData() != null && ((XYLayoutData) getInput().getLayoutData()).getTopLeft() != null) {
						myXyLayoutDataX.setSelection(((XYLayoutData) getInput().getLayoutData()).getTopLeft().getX());
						myXyLayoutDataY.setSelection(((XYLayoutData) getInput().getLayoutData()).getTopLeft().getY());
					}
					if (getInput().getLayoutData() != null && ((XYLayoutData) getInput().getLayoutData()).getSize() != null) {
						myXyLayoutDataWidth.setSelection(((XYLayoutData) getInput().getLayoutData()).getSize().getDx());
						myXyLayoutDataHeight.setSelection(((XYLayoutData) getInput().getLayoutData()).getSize().getDy());
					}
				} else {
					myXyLayoutDataDetails.setVisible(false);
				}
			} else if (myR4 == event.widget) {
				if (myR4.getSelection()) {
					myCustomLayoutDataDetails.setVisible(true);
					myBorderLayoutDataDetails.setVisible(false);
					myGridLayoutDataDetails.setVisible(false);
					myXyLayoutDataDetails.setVisible(false);
					applyChanges(); // Commit; View to Model
					if (getInput().getLayoutData() != null) {
						myCustomLayoutQualifiedClassName.setText(((CustomClass) getInput().getLayoutData()).getQualifiedClassName());/*Bridge.fieldSet(myCustomLayoutQualifiedClassName, ((CustomClass) getInput().getLayoutData()).getQualifiedClassName());*/
					}
				} else {
					myCustomLayoutDataDetails.setVisible(false);
				}
			} else if (myR5 == event.widget) {
				if (myR5.getSelection()) {
					myBorderLayoutDataDetails.setVisible(false);
					myGridLayoutDataDetails.setVisible(false);
					myXyLayoutDataDetails.setVisible(false);
					myCustomLayoutDataDetails.setVisible(false);
					applyChanges(); // Commit; View to Model
				}
			}
			if (myBorderLayoutDataAlignment == event.widget || myGridLayoutDataVerticalAlignment == event.widget || myGridLayoutDataHorizontalAlignment == event.widget) {
				applyChanges();
			}

		}
	}

	public void modelChanged(Notification msg) {
		if (!myIsCommit && Display.getCurrent() != null) {
			refresh();
		}
	}

	private void markDirty() {
		// NO-OP, need that to share Update::handleEvent template with IFormPart which has same method
		// Perhaps, clients may find this method useful for some purpose?
	}

	protected void applyChanges() {
		try {
			myIsCommit = true;
			commit();
		} finally {
			myIsCommit = false;
		}
	}

	protected void discardChanges() {
		refresh();
	}

	protected Object unwrap(Object element) {
		// TODO may need to adapt selected element to smth else,
		// do it here
		return element;
	}

	private void attach() {
		myModelListeners = new org.eclipse.emf.common.notify.Adapter[] { new AttachAdapter(GMFGraphPackage.eINSTANCE.getLayoutable_LayoutData(), new ChangeTracker() {

			public void modelChanged(org.eclipse.emf.common.notify.Notification n) {
				// FIXME enable/disable widget(s) -- HOWEVER, need access to Binding/Widget here, so can't share the template with e.g. Alex's ItemProviders
			}
		}, new FeatureTracker(this, GMFGraphPackage.eINSTANCE.getBorderLayoutData_Vertical(), GMFGraphPackage.eINSTANCE.getBorderLayoutData_Alignment(), GMFGraphPackage.eINSTANCE
				.getGridLayoutData_GrabExcessHorizontalSpace(), GMFGraphPackage.eINSTANCE.getGridLayoutData_GrabExcessVerticalSpace(), GMFGraphPackage.eINSTANCE.getGridLayoutData_VerticalAlignment(),
				GMFGraphPackage.eINSTANCE.getGridLayoutData_HorizontalAlignment(), GMFGraphPackage.eINSTANCE.getGridLayoutData_VerticalSpan(), GMFGraphPackage.eINSTANCE
						.getGridLayoutData_HorizontalSpan(), GMFGraphPackage.eINSTANCE.getGridLayoutData_HorizontalIndent(), GMFGraphPackage.eINSTANCE.getCustomClass_QualifiedClassName()),
				new AttachAdapter(GMFGraphPackage.eINSTANCE.getGridLayoutData_SizeHint(), new FeatureTracker(this, GMFGraphPackage.eINSTANCE.getDimension_Dx(), GMFGraphPackage.eINSTANCE
						.getDimension_Dy())), new AttachAdapter(GMFGraphPackage.eINSTANCE.getXYLayoutData_TopLeft(), new FeatureTracker(this, GMFGraphPackage.eINSTANCE.getPoint_X(),
						GMFGraphPackage.eINSTANCE.getPoint_Y())), new AttachAdapter(GMFGraphPackage.eINSTANCE.getXYLayoutData_Size(), new FeatureTracker(this, GMFGraphPackage.eINSTANCE
						.getDimension_Dx(), GMFGraphPackage.eINSTANCE.getDimension_Dy()))) };
		getInput().eAdapters().addAll(java.util.Arrays.asList(myModelListeners));

	}

	private void detach() {
		if (myModelListeners != null) {
			getInput().eAdapters().removeAll(java.util.Arrays.asList(myModelListeners));
			myModelListeners = null;
		}

	}

	protected Layoutable getInput() {
		// TODO implement;
		return (Layoutable) myInput;
	}

	private org.eclipse.swt.widgets.Label createLabel(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Label l = new org.eclipse.swt.widgets.Label(parent, SWT.NONE);
		if (label != null)
			l.setText(label);
		getWidgetFactory().adapt(l, false, false);
		return l;
	}

	private org.eclipse.swt.widgets.Group createGroup(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Group g = new org.eclipse.swt.widgets.Group(parent, SWT.SHADOW_NONE);
		if (label != null)
			g.setText(label);
		getWidgetFactory().adapt(g, false, false);
		getWidgetFactory().paintBordersFor(g);
		return g;
	}

}
