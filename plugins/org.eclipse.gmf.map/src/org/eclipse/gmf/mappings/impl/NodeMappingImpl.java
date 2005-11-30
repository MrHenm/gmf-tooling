/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.mappings.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.gmf.diadef.Node;
import org.eclipse.gmf.mappings.Constraint;
import org.eclipse.gmf.mappings.ElementInitializer;
import org.eclipse.gmf.mappings.GMFMapPackage;
import org.eclipse.gmf.mappings.NodeMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class NodeMappingImpl extends AbstractNodeMappingImpl implements NodeMapping {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GMFMapPackage.eINSTANCE.getNodeMapping();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case GMFMapPackage.NODE_MAPPING__CHILD_MAPPINGS:
					return ((InternalEList)getChildMappings()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case GMFMapPackage.NODE_MAPPING__DOMAIN_SPECIALIZATION:
					return basicSetDomainSpecialization(null, msgs);
				case GMFMapPackage.NODE_MAPPING__DOMAIN_INITIALIZER:
					return basicSetDomainInitializer(null, msgs);
				case GMFMapPackage.NODE_MAPPING__CHILD_MAPPINGS:
					return ((InternalEList)getChildMappings()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case GMFMapPackage.NODE_MAPPING__DIAGRAM_NODE:
				if (resolve) return getDiagramNode();
				return basicGetDiagramNode();
			case GMFMapPackage.NODE_MAPPING__DOMAIN_META_ELEMENT:
				if (resolve) return getDomainMetaElement();
				return basicGetDomainMetaElement();
			case GMFMapPackage.NODE_MAPPING__DOMAIN_SPECIALIZATION:
				return getDomainSpecialization();
			case GMFMapPackage.NODE_MAPPING__DOMAIN_INITIALIZER:
				return getDomainInitializer();
			case GMFMapPackage.NODE_MAPPING__CONTAINMENT_FEATURE:
				if (resolve) return getContainmentFeature();
				return basicGetContainmentFeature();
			case GMFMapPackage.NODE_MAPPING__EDIT_FEATURE:
				if (resolve) return getEditFeature();
				return basicGetEditFeature();
			case GMFMapPackage.NODE_MAPPING__CHILD_MAPPINGS:
				return getChildMappings();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case GMFMapPackage.NODE_MAPPING__DIAGRAM_NODE:
				setDiagramNode((Node)newValue);
				return;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_META_ELEMENT:
				setDomainMetaElement((EClass)newValue);
				return;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_SPECIALIZATION:
				setDomainSpecialization((Constraint)newValue);
				return;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_INITIALIZER:
				setDomainInitializer((ElementInitializer)newValue);
				return;
			case GMFMapPackage.NODE_MAPPING__CONTAINMENT_FEATURE:
				setContainmentFeature((EReference)newValue);
				return;
			case GMFMapPackage.NODE_MAPPING__EDIT_FEATURE:
				setEditFeature((EAttribute)newValue);
				return;
			case GMFMapPackage.NODE_MAPPING__CHILD_MAPPINGS:
				getChildMappings().clear();
				getChildMappings().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case GMFMapPackage.NODE_MAPPING__DIAGRAM_NODE:
				setDiagramNode((Node)null);
				return;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_META_ELEMENT:
				setDomainMetaElement((EClass)null);
				return;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_SPECIALIZATION:
				setDomainSpecialization((Constraint)null);
				return;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_INITIALIZER:
				setDomainInitializer((ElementInitializer)null);
				return;
			case GMFMapPackage.NODE_MAPPING__CONTAINMENT_FEATURE:
				setContainmentFeature((EReference)null);
				return;
			case GMFMapPackage.NODE_MAPPING__EDIT_FEATURE:
				setEditFeature((EAttribute)null);
				return;
			case GMFMapPackage.NODE_MAPPING__CHILD_MAPPINGS:
				getChildMappings().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case GMFMapPackage.NODE_MAPPING__DIAGRAM_NODE:
				return diagramNode != null;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_META_ELEMENT:
				return domainMetaElement != null;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_SPECIALIZATION:
				return domainSpecialization != null;
			case GMFMapPackage.NODE_MAPPING__DOMAIN_INITIALIZER:
				return domainInitializer != null;
			case GMFMapPackage.NODE_MAPPING__CONTAINMENT_FEATURE:
				return containmentFeature != null;
			case GMFMapPackage.NODE_MAPPING__EDIT_FEATURE:
				return editFeature != null;
			case GMFMapPackage.NODE_MAPPING__CHILD_MAPPINGS:
				return childMappings != null && !childMappings.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //NodeMappingImpl
