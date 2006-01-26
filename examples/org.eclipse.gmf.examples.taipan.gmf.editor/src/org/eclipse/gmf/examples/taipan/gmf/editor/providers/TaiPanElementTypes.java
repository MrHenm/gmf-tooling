/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitri Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.examples.taipan.gmf.editor.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IMetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.MetamodelType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.gmf.examples.taipan.TaiPanFactory;
import org.eclipse.gmf.examples.taipan.TaiPanPackage;

import org.eclipse.gmf.examples.taipan.gmf.editor.part.TaiPanDiagramEditorPlugin;

import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelper;

/**
 * @generated
 */
public class TaiPanElementTypes {

	/**
	 * @generated
	 */
	private TaiPanElementTypes() {
	}

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			element = ((EStructuralFeature) element).getEContainingClass();
		}
		if (element instanceof EClass) {
			return TaiPanDiagramEditorPlugin.getInstance().getItemImageDescriptor(TaiPanFactory.eINSTANCE.create((EClass) element));
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (Aquatory_79 == type) {
			return TaiPanPackage.eINSTANCE.getAquatory();
		} else if (Item_2001 == type) {
			return TaiPanPackage.eINSTANCE.getItem();
		} else if (Port_1001 == type) {
			return TaiPanPackage.eINSTANCE.getPort();
		} else if (Ship_1002 == type) {
			return TaiPanPackage.eINSTANCE.getShip();
		} else if (ShipDestination_3001 == type) {
			return TaiPanPackage.eINSTANCE.getShip_Destination();
		} else if (Route_3002 == type) {
			return TaiPanPackage.eINSTANCE.getRoute();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public static final IMetamodelType Aquatory_79 = new MetamodelType("Aquatory_79", null, "Aquatory", TaiPanPackage.eINSTANCE.getAquatory(), new NullEditHelper());

	/**
	 * @generated
	 */
	public static final IMetamodelType Item_2001 = new MetamodelType("Item_2001", null, "Item", TaiPanPackage.eINSTANCE.getItem(), new NullEditHelper());

	/**
	 * @generated
	 */
	public static final IMetamodelType Port_1001 = new MetamodelType("Port_1001", null, "Port", TaiPanPackage.eINSTANCE.getPort(), new NullEditHelper());

	/**
	 * @generated
	 */
	public static final IMetamodelType Ship_1002 = new MetamodelType("Ship_1002", null, "Ship", TaiPanPackage.eINSTANCE.getShip(), new NullEditHelper());

	/**
	 * @generated
	 */
	public static final IMetamodelType ShipDestination_3001 = new MetamodelType("ShipDestination_3001", null, "ShipDestination", null, new NullEditHelper());

	/**
	 * @generated
	 */
	public static final IMetamodelType Route_3002 = new MetamodelType("Route_3002", null, "Route", TaiPanPackage.eINSTANCE.getRoute(), new NullEditHelper());

	/**
	 * @generated
	 */
	public static void register() {
		ElementTypeRegistry.getInstance().register(Aquatory_79);
		ElementTypeRegistry.getInstance().register(Item_2001);
		ElementTypeRegistry.getInstance().register(Port_1001);
		ElementTypeRegistry.getInstance().register(Ship_1002);
		ElementTypeRegistry.getInstance().register(ShipDestination_3001);
		ElementTypeRegistry.getInstance().register(Route_3002);
	}

	/**
	 * @generated
	 */
	public static class NullEditHelper extends AbstractEditHelper {

		/**
		 * @generated
		 */
		public ICommand getEditCommand(IEditCommandRequest req) {
			if (!(req instanceof ConfigureRequest)) {
				System.err.println("unserved request " + req);
			}
			return null;
		}
	}
}
