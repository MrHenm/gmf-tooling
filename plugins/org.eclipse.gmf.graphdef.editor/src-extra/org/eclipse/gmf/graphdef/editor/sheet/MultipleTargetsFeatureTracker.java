/**
 * Copyright (c) 2008 Borland Software Corp.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alexander Shatalin (Borland) - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.sheet;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class MultipleTargetsFeatureTracker extends AdapterImpl {

	private final EStructuralFeature[] myFeatures;

	private final ChangeTracker myListener;
	
	private final Collection<Notifier> myTargets = new ArrayList<Notifier>();

	public MultipleTargetsFeatureTracker(ChangeTracker listener, EStructuralFeature... features) {
		assert listener != null && features != null;
		myListener = listener;
		myFeatures = features;
	}
	
	@Override
	public void setTarget(Notifier newTarget) {
		if (newTarget instanceof EObject) {
			EClass metaClass = ((EObject) newTarget).eClass();
			for (EStructuralFeature sf : myFeatures) {
				if (sf.getEContainingClass().isSuperTypeOf(metaClass)) {
					super.setTarget(newTarget);
					myTargets.add(newTarget);
					break;
				}
			}
			return;
		}
		super.setTarget(newTarget);
	}
	
	@Override
	public void unsetTarget(Notifier oldTarget) {
		if (oldTarget != null) {
			myTargets.remove(oldTarget);
		}
		super.unsetTarget(oldTarget);
	}

	@Override
	public void notifyChanged(Notification msg) {
		if (msg.isTouch() || !hasTarget(msg.getNotifier())) {
			return;
		}
		for (EStructuralFeature sf : myFeatures) {
			if (sf.equals(msg.getFeature())) {
				myListener.modelChanged(msg);
				break;
			}
		}
	}
	
	private boolean hasTarget(Object notificationSource) {
		return notificationSource != null && myTargets.contains(notificationSource);
	}

}
