/**
 * Copyright (c) 2006 Eclipse.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    vano - initial API and implementation
 */
package org.eclipse.gmf.mappings.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.gmf.mappings.CanvasMapping;
import org.eclipse.gmf.mappings.ChildReference;
import org.eclipse.gmf.mappings.CompartmentMapping;
import org.eclipse.gmf.mappings.LabelMapping;
import org.eclipse.gmf.mappings.LinkMapping;
import org.eclipse.gmf.mappings.Mapping;
import org.eclipse.gmf.mappings.MappingEntry;
import org.eclipse.gmf.mappings.NodeMapping;
import org.eclipse.gmf.mappings.NodeReference;
import org.eclipse.gmf.mappings.TopNodeReference;

public class FilterUtil {

	private static Comparator EOBJECTS_COMPARATOR = new EObjectsComparator();

	public static List sort(Collection eObjects) {
		List result = new ArrayList(eObjects);
		Collections.sort(result, EOBJECTS_COMPARATOR);
		return result;
	}

	public static List filterByModel(Collection eClasses, CanvasMapping canvasMapping) {
		return sort(getValidEClassesFrom(eClasses, canvasMapping.getDomainModel()));
	}

	public static List filterByContainmentFeature(Collection eClasses, MappingEntry mappingEntry) {
		EClass superType = null;
		CanvasMapping canvasMapping = null;
		if (mappingEntry instanceof NodeMapping) {
			NodeReference nodeReference = (NodeReference) mappingEntry.eContainer();
			EReference modelReference = nodeReference.getChildrenFeature() != null ? nodeReference.getChildrenFeature() : nodeReference.getContainmentFeature();
			if (modelReference != null) {
				superType = modelReference.getEReferenceType();
			}
			canvasMapping = getMapping(nodeReference).getDiagram();
		} else if (mappingEntry instanceof LinkMapping) {
			if (((LinkMapping) mappingEntry).getContainmentFeature() != null) {
				superType = ((LinkMapping) mappingEntry).getContainmentFeature().getEReferenceType();
			}
			canvasMapping = ((Mapping) mappingEntry.eContainer()).getDiagram();
		}
		return sort(getSubtypesOf(getValidEClassesFrom(eClasses, canvasMapping != null ? canvasMapping.getDomainModel() : null), superType));
	}

	public static List filterByContainerMetaclass(Collection eReferences, LinkMapping mappingEntry) {
		EClass containerMetaClass = mappingEntry.getDomainMetaElement();
		return sort(getEStructuralFeaturesOf(eReferences, containerMetaClass));
	}

	public static List filterByContainerMetaclass(Collection eAttributes, LabelMapping labelMapping) {
		EClass containerMetaClass = labelMapping.getMapEntry().getDomainMetaElement();
		return sort(getEStructuralFeaturesOf(eAttributes, containerMetaClass));
	}
	
	public static List filterByContainerMetaclass(Collection eAttributes, MappingEntry mappingEntry) {
		EClass containerMetaClass = mappingEntry.getDomainMetaElement();
		return sort(getEStructuralFeaturesOf(eAttributes, containerMetaClass));
	}

	public static List filterByContainerMetaclass(Collection eReferences, NodeReference nodeReference, boolean containmentOnly) {
		EClass containerMetaClass = null;
		if (nodeReference instanceof ChildReference) {
			containerMetaClass = ((ChildReference) nodeReference).getParentNode().getDomainMetaElement();
		} else if (nodeReference instanceof TopNodeReference) {
			CanvasMapping diagram = ((Mapping) nodeReference.eContainer()).getDiagram();
			if (diagram != null) {
				containerMetaClass = diagram.getDomainMetaElement();
			}
		}
		return sort(getEReferences(getEStructuralFeaturesOf(eReferences, containerMetaClass), containmentOnly));
	}

	public static List filterByReferenceType(Collection eReferences, LinkMapping linkMapping) {
		EClass referenceType = linkMapping.getDomainMetaElement();
		return sort(getEReferences(getEReferencesOfType(eReferences, referenceType), true));
	}

	public static Collection filterByNodeMapping(Collection childReferences, CompartmentMapping mapping) {
		return sort(getChildReferencesOf(childReferences, mapping.getParentNode()));
	}

	private static Collection getSubtypesOf(Collection eClasses, EClass superType) {
		if (superType == null) {
			return eClasses;
		}
		for (Iterator it = eClasses.iterator(); it.hasNext();) {
			EClass nextEClass = (EClass) it.next();
			if (nextEClass != null && !superType.isSuperTypeOf(nextEClass)) {
				it.remove();
			}
		}
		return eClasses;
	}

	private static Collection getEReferences(Collection eReferences, boolean containmentOnly) {
		if (!containmentOnly) {
			return eReferences;
		}
		for (Iterator it = eReferences.iterator(); it.hasNext();) {
			EReference nextReference = (EReference) it.next();
			if (nextReference != null && !nextReference.isContainment()) {
				it.remove();
			}
		}
		return eReferences;
	}

	private static Collection getEStructuralFeaturesOf(Collection structuralFeatures, EClass featureContainerEClass) {
		Collection result = getValidEStructuralFeatures(structuralFeatures);
		if (featureContainerEClass == null) {
			return result;
		}
		for (Iterator it = result.iterator(); it.hasNext();) {
			EStructuralFeature nextFeature = (EStructuralFeature) it.next();
			if (nextFeature != null && !nextFeature.getEContainingClass().isSuperTypeOf(featureContainerEClass)) {
				it.remove();
			}
		}
		return result;
	}

	private static Collection getEReferencesOfType(Collection references, EClass referenceType) {
		Collection result = getValidEStructuralFeatures(references);
		if (referenceType == null) {
			return result;
		}
		for (Iterator it = result.iterator(); it.hasNext();) {
			EReference nextFeature = (EReference) it.next();
			if (nextFeature != null && !nextFeature.getEReferenceType().isSuperTypeOf(referenceType)) {
				it.remove();
			}
		}
		return result;
	}

	private static Collection getValidEStructuralFeatures(Collection structuralFeatures) {
		Collection result = getValidEObjects(structuralFeatures);
		for (Iterator it = result.iterator(); it.hasNext();) {
			EStructuralFeature nextFeature = (EStructuralFeature) it.next();
			if (nextFeature != null && (nextFeature.getEContainingClass() == null)) {
				it.remove();
			}
		}
		return result;
	}

	private static Collection getValidEClassesFrom(Collection eClasses, EPackage ePackage) {
		Collection result = getValidEObjects(eClasses);
		if (ePackage == null) {
			return result;
		}
		for (Iterator it = result.iterator(); it.hasNext();) {
			EClass nextEClass = (EClass) it.next();
			if (nextEClass == null) {
				continue;
			}
			EPackage eClassPackage = nextEClass.getEPackage();
			for (; eClassPackage != null && eClassPackage != ePackage; eClassPackage = eClassPackage.getESuperPackage()) {
			}
			if (eClassPackage == null) {
				it.remove();
			}
		}
		return result;
	}

	private static Collection getValidEObjects(Collection eObjects) {
		List result = new ArrayList();
		for (Iterator it = eObjects.iterator(); it.hasNext();) {
			EObject nextEObject = (EObject) it.next();
			if (nextEObject != null && (nextEObject.eContainer() == null)) {
				continue;
			}
			result.add(nextEObject);
		}
		return result;
	}

	private static Mapping getMapping(NodeReference nodeReference) {
		if (nodeReference instanceof TopNodeReference) {
			return (Mapping) nodeReference.eContainer();
		}
		return getMapping((NodeReference) ((ChildReference) nodeReference).getParentNode().eContainer());
	}

	private static Collection getChildReferencesOf(Collection childReferences, NodeMapping parentNode) {
		List result = new ArrayList();
		for (Iterator it = childReferences.iterator(); it.hasNext();) {
			ChildReference nextChildReference = (ChildReference) it.next();
			if (nextChildReference != null && nextChildReference.getParentNode() == parentNode) {
				result.add(nextChildReference);
			}
		}
		return result;
	}

	private static class EObjectsComparator implements Comparator {

		public int compare(Object o1, Object o2) {
			if (o1 instanceof EObject && o2 instanceof EObject) {
				EObject firstEObject = (EObject) o1;
				EObject secondEObject = (EObject) o2;
				return compareLabels(firstEObject, secondEObject);
			}
			return compareObjects(o1, o2);
		}

		private int compareObjects(Object o1, Object o2) {
			if (o1 == null) {
				return o2 == null ? 0 : 1;
			} else {
				return o2 == null ? -1 : 0;
			}
		}

		private int compareLabels(EObject firstEObject, EObject secondEObject) {
			String firstLabel = getLabel(firstEObject);
			String secondLabel = getLabel(secondEObject);
			return firstLabel == null ? (secondLabel == null ? 0 : 1) : (secondLabel == null ? -1 : firstLabel.compareTo(secondLabel));
		}

		private String getLabel(EObject eObject) {
			AdapterFactory adapterFactory = getAdapterFactory(eObject);
			if (adapterFactory == null) {
				return "";
			}
			IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject, IItemLabelProvider.class);
			return itemLabelProvider != null ? itemLabelProvider.getText(eObject) : eObject == null ? "" : eObject.toString();
		}

		private AdapterFactory getAdapterFactory(EObject eObject) {
			EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
			if (editingDomain instanceof AdapterFactoryEditingDomain) {
				return ((AdapterFactoryEditingDomain) editingDomain).getAdapterFactory();
			}
			return null;
		}

	}

}
