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
package org.eclipse.gmf.examples.taipan.gmf.editor.part;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * @generated
 */
public class TaiPanCreationWizardPage extends WizardNewFileCreationPage {

	/**
	 * @generated
	 */
	public TaiPanCreationWizardPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}

	/**
	 * @generated
	 */
	protected String getDefaultFileName() {
		return "default"; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public String getFileName() {
		String fileName = super.getFileName();
		if (fileName != null) {
			fileName = getDiagramFileCreator().appendExtensionToFileName(fileName);
		}
		return fileName;
	}

	/**
	 * @generated
	 */
	public TaiPanDiagramFileCreator getDiagramFileCreator() {
		return TaiPanDiagramFileCreator.getInstance();
	}

	/**
	 * @generated
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		IPath path = getContainerFullPath();
		if (path != null) {
			String fileName = getDiagramFileCreator().getUniqueFileName(path, getDefaultFileName());
			setFileName(fileName);
		}
		setPageComplete(validatePage());
	}

	/**
	 * @generated
	 */
	protected boolean validatePage() {
		if (super.validatePage()) {
			String fileName = getFileName();
			if (fileName == null) {
				return false;
			}
			// appending file extension to correctly process file names including "." symbol
			IPath path = getContainerFullPath().append(getDiagramFileCreator().appendExtensionToFileName(fileName));
			path = path.removeFileExtension().addFileExtension("taipan"); //$NON-NLS-1$
			if (TaiPanDiagramFileCreator.exists(path)) {
				setErrorMessage("Model file already exists: " + path.lastSegment());
				return false;
			}
			return true;
		}
		return false;
	}
}
