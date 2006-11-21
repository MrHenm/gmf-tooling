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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
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
	private static final String DOMAIN_EXT = ".taipan"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static final String DIAGRAM_EXT = ".taipan_diagram"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public TaiPanCreationWizardPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}

	/**
	 * @generated
	 */
	public URI getDiagramURI() {
		IPath path = getFilePath();
		if (path.getFileExtension() == null) {
			path = path.addFileExtension("taipan_diagram"); //$NON-NLS-1$
		}
		return URI.createPlatformResourceURI(path.toString());
	}

	/**
	 * @generated
	 */
	public URI getModelURI() {
		IPath path = getFilePath();
		path = path.removeFileExtension().addFileExtension("taipan"); //$NON-NLS-1$
		return URI.createPlatformResourceURI(path.toString());
	}

	/**
	 * @generated
	 */
	protected IPath getFilePath() {
		IPath path = getContainerFullPath();
		if (path == null) {
			path = new Path(""); //$NON-NLS-1$
		}
		String fileName = getFileName();
		if (fileName != null) {
			path = path.append(fileName);
		}
		return path;
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
		if (fileName != null && !fileName.endsWith(DIAGRAM_EXT)) {
			fileName += DIAGRAM_EXT;
		}
		return fileName;
	}

	/**
	 * @generated
	 */
	private String getUniqueFileName(IPath containerPath, String fileName) {
		String newFileName = fileName;
		IPath diagramFilePath = containerPath.append(newFileName + DIAGRAM_EXT);
		IPath modelFilePath = containerPath.append(newFileName + DOMAIN_EXT);
		int i = 1;
		while (exists(diagramFilePath) || exists(modelFilePath)) {
			i++;
			newFileName = fileName + i;
			diagramFilePath = containerPath.append(newFileName + DIAGRAM_EXT);
			modelFilePath = containerPath.append(newFileName + DOMAIN_EXT);
		}
		return newFileName;
	}

	/**
	 * @generated
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		IPath path = getContainerFullPath();
		if (path != null) {
			String fileName = getUniqueFileName(path, getDefaultFileName());
			setFileName(fileName);
		} else {
			setFileName(getDefaultFileName());
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
			fileName = fileName.substring(0, fileName.length() - DIAGRAM_EXT.length()) + DOMAIN_EXT;
			IPath path = getContainerFullPath();
			if (path == null) {
				path = new Path(""); //$NON-NLS-1$
			}
			path = path.append(fileName);
			if (exists(path)) {
				setErrorMessage("Model file already exists: " + path.lastSegment());
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean exists(IPath path) {
		return ResourcesPlugin.getWorkspace().getRoot().exists(path);
	}
}
