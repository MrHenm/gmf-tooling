/*
 *  Copyright (c) 2006, 2009 Borland Software Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      Borland Software Corporation - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.gmf.graphdef.editor.edit.parts.Ellipse3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureDescriptorEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Label3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polygon3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polyline3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Rectangle3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangle3EditPart;
import org.eclipse.gmf.graphdef.editor.part.GMFGraphDiagramUpdater;
import org.eclipse.gmf.graphdef.editor.part.GMFGraphNodeDescriptor;
import org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class FigureGalleryFiguresCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated
	 */
	Set myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		List result = new LinkedList();
		for (Iterator it = GMFGraphDiagramUpdater.getFigureGalleryFigures_7008SemanticChildren(viewObject).iterator(); it.hasNext();) {
			result.add(((GMFGraphNodeDescriptor) it.next()).getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection semanticChildren, final View view) {
		int visualID = GMFGraphVisualIDRegistry.getVisualID(view);
		switch (visualID) {
		case FigureDescriptorEditPart.VISUAL_ID:
		case Rectangle3EditPart.VISUAL_ID:
		case Ellipse3EditPart.VISUAL_ID:
		case RoundedRectangle3EditPart.VISUAL_ID:
		case Polyline3EditPart.VISUAL_ID:
		case Polygon3EditPart.VISUAL_ID:
		case Label3EditPart.VISUAL_ID:
			if (!semanticChildren.contains(view.getElement())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * @generated
	 */
	protected Set getFeaturesToSynchronize() {
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet();
			myFeaturesToSynchronize.add(GMFGraphPackage.eINSTANCE.getFigureGallery_Descriptors());
			myFeaturesToSynchronize.add(GMFGraphPackage.eINSTANCE.getFigureGallery_Figures());
		}
		return myFeaturesToSynchronize;
	}

}
