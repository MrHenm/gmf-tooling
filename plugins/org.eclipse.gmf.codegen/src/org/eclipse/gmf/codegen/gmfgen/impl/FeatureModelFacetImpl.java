/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen.impl;

import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.gmf.codegen.gmfgen.FeatureModelFacet;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;

import org.eclipse.gmf.codegen.gmfgen.LabelModelFacet;
import org.eclipse.gmf.codegen.gmfgen.LinkModelFacet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Model Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.FeatureModelFacetImpl#getViewPattern <em>View Pattern</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.FeatureModelFacetImpl#getEditPattern <em>Edit Pattern</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.FeatureModelFacetImpl#getMetaFeature <em>Meta Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureModelFacetImpl extends EObjectImpl implements FeatureModelFacet {
	/**
	 * The default value of the '{@link #getViewPattern() <em>View Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String VIEW_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getViewPattern() <em>View Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewPattern()
	 * @generated
	 * @ordered
	 */
	protected String viewPattern = VIEW_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditPattern() <em>Edit Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditPattern() <em>Edit Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditPattern()
	 * @generated
	 * @ordered
	 */
	protected String editPattern = EDIT_PATTERN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMetaFeature() <em>Meta Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetaFeature()
	 * @generated
	 * @ordered
	 */
	protected GenFeature metaFeature = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureModelFacetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GMFGenPackage.eINSTANCE.getFeatureModelFacet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getViewPattern() {
		return viewPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewPattern(String newViewPattern) {
		String oldViewPattern = viewPattern;
		viewPattern = newViewPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.FEATURE_MODEL_FACET__VIEW_PATTERN, oldViewPattern, viewPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditPattern() {
		return editPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditPattern(String newEditPattern) {
		String oldEditPattern = editPattern;
		editPattern = newEditPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.FEATURE_MODEL_FACET__EDIT_PATTERN, oldEditPattern, editPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenFeature getMetaFeature() {
		if (metaFeature != null && metaFeature.eIsProxy()) {
			InternalEObject oldMetaFeature = (InternalEObject)metaFeature;
			metaFeature = (GenFeature)eResolveProxy(oldMetaFeature);
			if (metaFeature != oldMetaFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GMFGenPackage.FEATURE_MODEL_FACET__META_FEATURE, oldMetaFeature, metaFeature));
			}
		}
		return metaFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenFeature basicGetMetaFeature() {
		return metaFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetaFeature(GenFeature newMetaFeature) {
		GenFeature oldMetaFeature = metaFeature;
		metaFeature = newMetaFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.FEATURE_MODEL_FACET__META_FEATURE, oldMetaFeature, metaFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getSourceTypes() {
		EList sources = new BasicEList();
		if (getMetaFeature() != null) {
			sources.add(getMetaFeature().getGenClass());
		}
		return sources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getTargetTypes() {
		EList targets = new BasicEList();
		if (getMetaFeature() != null) {
			targets.add(getMetaFeature().getTypeGenClass());
		}
		return targets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GMFGenPackage.FEATURE_MODEL_FACET__VIEW_PATTERN:
				return getViewPattern();
			case GMFGenPackage.FEATURE_MODEL_FACET__EDIT_PATTERN:
				return getEditPattern();
			case GMFGenPackage.FEATURE_MODEL_FACET__META_FEATURE:
				if (resolve) return getMetaFeature();
				return basicGetMetaFeature();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GMFGenPackage.FEATURE_MODEL_FACET__VIEW_PATTERN:
				setViewPattern((String)newValue);
				return;
			case GMFGenPackage.FEATURE_MODEL_FACET__EDIT_PATTERN:
				setEditPattern((String)newValue);
				return;
			case GMFGenPackage.FEATURE_MODEL_FACET__META_FEATURE:
				setMetaFeature((GenFeature)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case GMFGenPackage.FEATURE_MODEL_FACET__VIEW_PATTERN:
				setViewPattern(VIEW_PATTERN_EDEFAULT);
				return;
			case GMFGenPackage.FEATURE_MODEL_FACET__EDIT_PATTERN:
				setEditPattern(EDIT_PATTERN_EDEFAULT);
				return;
			case GMFGenPackage.FEATURE_MODEL_FACET__META_FEATURE:
				setMetaFeature((GenFeature)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GMFGenPackage.FEATURE_MODEL_FACET__VIEW_PATTERN:
				return VIEW_PATTERN_EDEFAULT == null ? viewPattern != null : !VIEW_PATTERN_EDEFAULT.equals(viewPattern);
			case GMFGenPackage.FEATURE_MODEL_FACET__EDIT_PATTERN:
				return EDIT_PATTERN_EDEFAULT == null ? editPattern != null : !EDIT_PATTERN_EDEFAULT.equals(editPattern);
			case GMFGenPackage.FEATURE_MODEL_FACET__META_FEATURE:
				return metaFeature != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == LinkModelFacet.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == LabelModelFacet.class) {
			switch (derivedFeatureID) {
				case GMFGenPackage.FEATURE_MODEL_FACET__VIEW_PATTERN: return GMFGenPackage.LABEL_MODEL_FACET__VIEW_PATTERN;
				case GMFGenPackage.FEATURE_MODEL_FACET__EDIT_PATTERN: return GMFGenPackage.LABEL_MODEL_FACET__EDIT_PATTERN;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == LinkModelFacet.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == LabelModelFacet.class) {
			switch (baseFeatureID) {
				case GMFGenPackage.LABEL_MODEL_FACET__VIEW_PATTERN: return GMFGenPackage.FEATURE_MODEL_FACET__VIEW_PATTERN;
				case GMFGenPackage.LABEL_MODEL_FACET__EDIT_PATTERN: return GMFGenPackage.FEATURE_MODEL_FACET__EDIT_PATTERN;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (viewPattern: ");
		result.append(viewPattern);
		result.append(", editPattern: ");
		result.append(editPattern);
		result.append(')');
		return result.toString();
	}

} //FeatureModelFacetImpl
