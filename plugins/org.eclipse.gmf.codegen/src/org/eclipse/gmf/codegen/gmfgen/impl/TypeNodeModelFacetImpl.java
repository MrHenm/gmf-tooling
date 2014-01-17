/**
 */
package org.eclipse.gmf.codegen.gmfgen.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenChildContainer;
import org.eclipse.gmf.codegen.gmfgen.GenMultiFacetedNode;
import org.eclipse.gmf.codegen.gmfgen.TypeNodeModelFacet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Node Model Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.TypeNodeModelFacetImpl#getMultiFacetedNode <em>Multi Faceted Node</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.TypeNodeModelFacetImpl#getCreateCommandClassName <em>Create Command Class Name</em>}</li>
 *   <li>{@link org.eclipse.gmf.codegen.gmfgen.impl.TypeNodeModelFacetImpl#getContainers <em>Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeNodeModelFacetImpl extends TypeModelFacetImpl implements TypeNodeModelFacet {
	/**
	 * The default value of the '{@link #getCreateCommandClassName() <em>Create Command Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateCommandClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATE_COMMAND_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreateCommandClassName() <em>Create Command Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateCommandClassName()
	 * @generated
	 * @ordered
	 */
	protected String createCommandClassName = CREATE_COMMAND_CLASS_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContainers() <em>Containers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<GenChildContainer> containers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeNodeModelFacetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GMFGenPackage.eINSTANCE.getTypeNodeModelFacet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenMultiFacetedNode getMultiFacetedNode() {
		if (eContainerFeatureID() != GMFGenPackage.TYPE_NODE_MODEL_FACET__MULTI_FACETED_NODE) return null;
		return (GenMultiFacetedNode)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreateCommandClassName() {
		return createCommandClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateCommandClassName(String newCreateCommandClassName) {
		String oldCreateCommandClassName = createCommandClassName;
		createCommandClassName = newCreateCommandClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GMFGenPackage.TYPE_NODE_MODEL_FACET__CREATE_COMMAND_CLASS_NAME, oldCreateCommandClassName, createCommandClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenChildContainer> getContainers() {
		if (containers == null) {
			containers = new EObjectResolvingEList<GenChildContainer>(GenChildContainer.class, this, GMFGenPackage.TYPE_NODE_MODEL_FACET__CONTAINERS);
		}
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreateCommandQualifiedClassName() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__MULTI_FACETED_NODE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, GMFGenPackage.TYPE_NODE_MODEL_FACET__MULTI_FACETED_NODE, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__MULTI_FACETED_NODE:
				return eBasicSetContainer(null, GMFGenPackage.TYPE_NODE_MODEL_FACET__MULTI_FACETED_NODE, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__MULTI_FACETED_NODE:
				return eInternalContainer().eInverseRemove(this, GMFGenPackage.GEN_MULTI_FACETED_NODE__ADDITIONAL_MODEL_FACETS, GenMultiFacetedNode.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__MULTI_FACETED_NODE:
				return getMultiFacetedNode();
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__CREATE_COMMAND_CLASS_NAME:
				return getCreateCommandClassName();
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__CONTAINERS:
				return getContainers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__CREATE_COMMAND_CLASS_NAME:
				setCreateCommandClassName((String)newValue);
				return;
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__CONTAINERS:
				getContainers().clear();
				getContainers().addAll((Collection<? extends GenChildContainer>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__CREATE_COMMAND_CLASS_NAME:
				setCreateCommandClassName(CREATE_COMMAND_CLASS_NAME_EDEFAULT);
				return;
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__CONTAINERS:
				getContainers().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__MULTI_FACETED_NODE:
				return getMultiFacetedNode() != null;
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__CREATE_COMMAND_CLASS_NAME:
				return CREATE_COMMAND_CLASS_NAME_EDEFAULT == null ? createCommandClassName != null : !CREATE_COMMAND_CLASS_NAME_EDEFAULT.equals(createCommandClassName);
			case GMFGenPackage.TYPE_NODE_MODEL_FACET__CONTAINERS:
				return containers != null && !containers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (createCommandClassName: ");
		result.append(createCommandClassName);
		result.append(')');
		return result.toString();
	}

} //TypeNodeModelFacetImpl
