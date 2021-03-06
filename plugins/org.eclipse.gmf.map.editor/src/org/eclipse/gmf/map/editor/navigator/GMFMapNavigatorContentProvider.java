/*
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.map.editor.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import org.eclipse.gmf.map.editor.edit.parts.CanvasMappingEditPart;
import org.eclipse.gmf.map.editor.edit.parts.ChildReferenceEditPart;
import org.eclipse.gmf.map.editor.edit.parts.ChildReferenceOwnedChildEditPart;
import org.eclipse.gmf.map.editor.edit.parts.ChildReferenceReferencedChildEditPart;
import org.eclipse.gmf.map.editor.edit.parts.CompartmentMappingChildrenEditPart;
import org.eclipse.gmf.map.editor.edit.parts.CompartmentMappingEditPart;
import org.eclipse.gmf.map.editor.edit.parts.DesignLabelMapping2EditPart;
import org.eclipse.gmf.map.editor.edit.parts.DesignLabelMappingEditPart;
import org.eclipse.gmf.map.editor.edit.parts.FeatureLabelMapping2EditPart;
import org.eclipse.gmf.map.editor.edit.parts.FeatureLabelMappingEditPart;
import org.eclipse.gmf.map.editor.edit.parts.LabelMapping2EditPart;
import org.eclipse.gmf.map.editor.edit.parts.LabelMappingEditPart;
import org.eclipse.gmf.map.editor.edit.parts.LinkMappingEditPart;
import org.eclipse.gmf.map.editor.edit.parts.MappingEditPart;
import org.eclipse.gmf.map.editor.edit.parts.NodeMappingEditPart;
import org.eclipse.gmf.map.editor.edit.parts.TopNodeReferenceEditPart;
import org.eclipse.gmf.map.editor.edit.parts.TopNodeReferenceOwnedChildEditPart;

import org.eclipse.gmf.map.editor.part.GMFMapVisualIDRegistry;

import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.jface.viewers.Viewer;

import org.eclipse.ui.IMemento;

import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class GMFMapNavigatorContentProvider implements ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	public void dispose() {
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof GMFMapAbstractNavigatorItem) {
			GMFMapAbstractNavigatorItem abstractNavigatorItem = (GMFMapAbstractNavigatorItem) parentElement;
			if (!MappingEditPart.MODEL_ID.equals(abstractNavigatorItem.getModelID())) {
				return EMPTY_ARRAY;
			}

			if (abstractNavigatorItem instanceof GMFMapNavigatorItem) {
				GMFMapNavigatorItem navigatorItem = (GMFMapNavigatorItem) abstractNavigatorItem;
				switch (navigatorItem.getVisualID()) {
				case CanvasMappingEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(TopNodeReferenceEditPart.VISUAL_ID), navigatorItem));
					return result.toArray();
				}
				case NodeMappingEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(FeatureLabelMappingEditPart.VISUAL_ID), navigatorItem));
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(DesignLabelMappingEditPart.VISUAL_ID), navigatorItem));
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(LabelMappingEditPart.VISUAL_ID), navigatorItem));
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(ChildReferenceEditPart.VISUAL_ID), navigatorItem));
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(CompartmentMappingEditPart.VISUAL_ID), navigatorItem));
					GMFMapNavigatorGroup incominglinks = new GMFMapNavigatorGroup("incoming links", "icons/incomingLinksNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					incominglinks.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(TopNodeReferenceOwnedChildEditPart.VISUAL_ID), false, incominglinks));
					incominglinks.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(ChildReferenceOwnedChildEditPart.VISUAL_ID), false, incominglinks));
					incominglinks.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(ChildReferenceReferencedChildEditPart.VISUAL_ID), false, incominglinks));
					if (!incominglinks.isEmpty()) {
						result.add(incominglinks);
					}
					return result.toArray();
				}
				case LinkMappingEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(FeatureLabelMapping2EditPart.VISUAL_ID), navigatorItem));
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(DesignLabelMapping2EditPart.VISUAL_ID), navigatorItem));
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(LabelMapping2EditPart.VISUAL_ID), navigatorItem));
					return result.toArray();
				}
				case TopNodeReferenceEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					GMFMapNavigatorGroup outgoinglinks = new GMFMapNavigatorGroup("outgoing links", "icons/outgoingLinksNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					outgoinglinks.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(TopNodeReferenceOwnedChildEditPart.VISUAL_ID), true, outgoinglinks));
					if (!outgoinglinks.isEmpty()) {
						result.add(outgoinglinks);
					}
					return result.toArray();
				}
				case ChildReferenceEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					GMFMapNavigatorGroup outgoinglinks = new GMFMapNavigatorGroup("outgoing links", "icons/outgoingLinksNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					outgoinglinks.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(ChildReferenceOwnedChildEditPart.VISUAL_ID), true, outgoinglinks));
					outgoinglinks.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(ChildReferenceReferencedChildEditPart.VISUAL_ID), true, outgoinglinks));
					GMFMapNavigatorGroup incominglinks = new GMFMapNavigatorGroup("incoming links", "icons/incomingLinksNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					incominglinks.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(CompartmentMappingChildrenEditPart.VISUAL_ID), false, incominglinks));
					if (!outgoinglinks.isEmpty()) {
						result.add(outgoinglinks);
					}
					if (!incominglinks.isEmpty()) {
						result.add(incominglinks);
					}
					return result.toArray();
				}
				case CompartmentMappingEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					GMFMapNavigatorGroup outgoinglinks = new GMFMapNavigatorGroup("outgoing links", "icons/outgoingLinksNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					outgoinglinks.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(CompartmentMappingChildrenEditPart.VISUAL_ID), true, outgoinglinks));
					if (!outgoinglinks.isEmpty()) {
						result.add(outgoinglinks);
					}
					return result.toArray();
				}
				case MappingEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(CanvasMappingEditPart.VISUAL_ID), navigatorItem));
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(NodeMappingEditPart.VISUAL_ID), navigatorItem));
					result.addAll(getChildByType(navigatorItem.getView().getChildren(), GMFMapVisualIDRegistry.getType(LinkMappingEditPart.VISUAL_ID), navigatorItem));
					GMFMapNavigatorGroup links = new GMFMapNavigatorGroup("links", "icons/linksNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					links.addChildren(getViewByType(navigatorItem.getView().getDiagram().getEdges(), GMFMapVisualIDRegistry.getType(TopNodeReferenceOwnedChildEditPart.VISUAL_ID), links));
					links.addChildren(getViewByType(navigatorItem.getView().getDiagram().getEdges(), GMFMapVisualIDRegistry.getType(ChildReferenceOwnedChildEditPart.VISUAL_ID), links));
					links.addChildren(getViewByType(navigatorItem.getView().getDiagram().getEdges(), GMFMapVisualIDRegistry.getType(ChildReferenceReferencedChildEditPart.VISUAL_ID), links));
					links.addChildren(getViewByType(navigatorItem.getView().getDiagram().getEdges(), GMFMapVisualIDRegistry.getType(CompartmentMappingChildrenEditPart.VISUAL_ID), links));
					if (!links.isEmpty()) {
						result.add(links);
					}
					return result.toArray();
				}
				case TopNodeReferenceOwnedChildEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					GMFMapNavigatorGroup target = new GMFMapNavigatorGroup("target", "icons/linkTargetNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					target.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(NodeMappingEditPart.VISUAL_ID), true, target));
					GMFMapNavigatorGroup source = new GMFMapNavigatorGroup("source", "icons/linkSourceNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					source.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(TopNodeReferenceEditPart.VISUAL_ID), false, source));
					if (!target.isEmpty()) {
						result.add(target);
					}
					if (!source.isEmpty()) {
						result.add(source);
					}
					return result.toArray();
				}
				case ChildReferenceOwnedChildEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					GMFMapNavigatorGroup target = new GMFMapNavigatorGroup("target", "icons/linkTargetNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					target.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(NodeMappingEditPart.VISUAL_ID), true, target));
					GMFMapNavigatorGroup source = new GMFMapNavigatorGroup("source", "icons/linkSourceNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					source.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(ChildReferenceEditPart.VISUAL_ID), false, source));
					if (!target.isEmpty()) {
						result.add(target);
					}
					if (!source.isEmpty()) {
						result.add(source);
					}
					return result.toArray();
				}
				case ChildReferenceReferencedChildEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					GMFMapNavigatorGroup target = new GMFMapNavigatorGroup("target", "icons/linkTargetNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					target.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(NodeMappingEditPart.VISUAL_ID), true, target));
					GMFMapNavigatorGroup source = new GMFMapNavigatorGroup("source", "icons/linkSourceNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					source.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(ChildReferenceEditPart.VISUAL_ID), false, source));
					if (!target.isEmpty()) {
						result.add(target);
					}
					if (!source.isEmpty()) {
						result.add(source);
					}
					return result.toArray();
				}
				case CompartmentMappingChildrenEditPart.VISUAL_ID: {
					Collection result = new ArrayList();
					GMFMapNavigatorGroup target = new GMFMapNavigatorGroup("target", "icons/linkTargetNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					target.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(ChildReferenceEditPart.VISUAL_ID), true, target));
					GMFMapNavigatorGroup source = new GMFMapNavigatorGroup("source", "icons/linkSourceNavigatorGroup.gif", MappingEditPart.MODEL_ID, navigatorItem);
					source.addChildren(getConnectedViews(navigatorItem.getView(), GMFMapVisualIDRegistry.getType(CompartmentMappingEditPart.VISUAL_ID), false, source));
					if (!target.isEmpty()) {
						result.add(target);
					}
					if (!source.isEmpty()) {
						result.add(source);
					}
					return result.toArray();
				}
				}
			} else if (abstractNavigatorItem instanceof GMFMapNavigatorGroup) {
				GMFMapNavigatorGroup group = (GMFMapNavigatorGroup) parentElement;
				return group.getChildren();
			}
		} else if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) GMFEditingDomainFactory.INSTANCE.createEditingDomain();
			editingDomain.setResourceToReadOnlyMap(new HashMap() {

				public Object get(Object key) {
					if (!containsKey(key)) {
						put(key, Boolean.TRUE);
					}
					return super.get(key);
				}
			});
			ResourceSet resourceSet = editingDomain.getResourceSet();

			URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString());
			Resource resource = resourceSet.getResource(fileURI, true);

			Collection result = new ArrayList();
			result.addAll(getViewByType(resource.getContents(), MappingEditPart.MODEL_ID, file));
			return result.toArray();
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		if (element instanceof GMFMapAbstractNavigatorItem) {
			GMFMapAbstractNavigatorItem abstractNavigatorItem = (GMFMapAbstractNavigatorItem) element;
			if (!MappingEditPart.MODEL_ID.equals(abstractNavigatorItem.getModelID())) {
				return null;
			}
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	private Collection getViewByType(Collection childViews, String type, Object parent) {
		Collection result = new ArrayList();
		for (Iterator it = childViews.iterator(); it.hasNext();) {
			Object next = it.next();
			if (false == next instanceof View) {
				continue;
			}
			View nextView = (View) next;
			if (type.equals(nextView.getType())) {
				result.add(new GMFMapNavigatorItem(nextView, parent));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection getChildByType(Collection childViews, String type, Object parent) {
		Collection result = new ArrayList();
		List children = new ArrayList(childViews);
		for (int i = 0; i < children.size(); i++) {
			if (false == children.get(i) instanceof View) {
				continue;
			}
			View nextChild = (View) children.get(i);
			if (type.equals(nextChild.getType())) {
				result.add(new GMFMapNavigatorItem(nextChild, parent));
			} else if (!stopGettingChildren(nextChild, type)) {
				children.addAll(nextChild.getChildren());
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean stopGettingChildren(View child, String type) {
		return false;
	}

	/**
	 * @generated
	 */
	private Collection getConnectedViews(View rootView, String type, boolean isOutTarget, Object parent) {
		Collection result = new ArrayList();
		List connectedViews = new ArrayList();
		connectedViews.add(rootView);
		Set visitedViews = new HashSet();
		for (int i = 0; i < connectedViews.size(); i++) {
			View nextView = (View) connectedViews.get(i);
			if (visitedViews.contains(nextView)) {
				continue;
			}
			visitedViews.add(nextView);
			if (type.equals(nextView.getType()) && nextView != rootView) {
				result.add(new GMFMapNavigatorItem(nextView, parent));
			} else {
				if (isOutTarget && !stopGettingOutTarget(nextView, rootView, type)) {
					connectedViews.addAll(nextView.getSourceEdges());
					if (nextView instanceof Edge) {
						connectedViews.add(((Edge) nextView).getTarget());
					}
				}
				if (!isOutTarget && !stopGettingInSource(nextView, rootView, type)) {
					connectedViews.addAll(nextView.getTargetEdges());
					if (nextView instanceof Edge) {
						connectedViews.add(((Edge) nextView).getSource());
					}
				}
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean stopGettingInSource(View nextView, View rootView, String type) {
		return !isOneHopConnection(nextView, rootView);
	}

	/**
	 * @generated
	 */
	private boolean stopGettingOutTarget(View nextView, View rootView, String type) {
		return !isOneHopConnection(nextView, rootView);
	}

	/**
	 * @generated
	 */
	private boolean isOneHopConnection(View targetView, View sourceView) {
		if (sourceView == targetView) {
			return true;
		}
		if (sourceView instanceof Node) {
			return targetView instanceof Edge;
		}
		if (sourceView instanceof Edge) {
			return targetView instanceof Node;
		}
		return false;
	}

}
