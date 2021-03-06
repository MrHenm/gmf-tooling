package org.eclipse.gmf.internal.xpand.migration.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmf.internal.xpand.RootManager;
import org.eclipse.gmf.internal.xpand.build.WorkspaceResourceManager;
import org.eclipse.gmf.internal.xpand.expression.AnalysationIssue;
import org.eclipse.gmf.internal.xpand.migration.MigrationException;
import org.eclipse.gmf.internal.xpand.migration.XpandMigrationFacade;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class MigrateXpandResource implements IObjectActionDelegate {

	private Shell shell;

	private IFile selectedFile;

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	public void run(IAction action) {
		if (!MessageDialog.openConfirm(shell, "Migrate xpand resource", "Migration action will replace existing xpand resource with new OCL/QVTO based one. Do you want to owerwrite it?")) {
			return;
		}
		try {
			InputStream inputStream = getUpdatedResourceContent(selectedFile.getCharset());
			if (inputStream == null) {
				return;
			}
			selectedFile.setContents(inputStream, true, true, null);
		} catch (CoreException e) {
			showError("Migration problems", "Following exception appears:\n\n" + e.getMessage());
		}
	}

	private InputStream getUpdatedResourceContent(String charset) {
		RootManager rootManager = new RootManager(selectedFile.getProject());
		String templateFullName = rootManager.getTemplateFullName(selectedFile);
		if (templateFullName == null) {
			showError("Incorrect xtend resource", "Unable to locate proper xpand root for this xtend resource");
			return null;
		}
		WorkspaceResourceManager resourceManager = rootManager.getResourceManager(selectedFile);
		XpandMigrationFacade migrationFacade = new XpandMigrationFacade(resourceManager, templateFullName);
		try {
			String updatedResourceContent = migrationFacade.migrateXpandResource();
			return new ByteArrayInputStream(updatedResourceContent.getBytes(charset));
		} catch (MigrationException e) {
			reportMigrationException(e);
		} catch (UnsupportedEncodingException e) {
			showError("Unsupported encoding", "Specified encoding \"" + charset + "\" is not supported by the platform: " + e.getMessage());
		}
		return null;
	}

	private void reportMigrationException(MigrationException e) {
		switch (e.getType()) {
		case ANALYZATION_PROBLEMS:
			StringBuilder sb = new StringBuilder("Following analyzation problems present:\n\n");
			for (AnalysationIssue issue : e.getIssues()) {
				sb.append(issue.toString());
				sb.append("\n");
			}
			showError("Unable to load xtend resource", sb.toString());
			return;
		default:
			showError("Migration exception", "Migration exception appears:\n" + e.getMessage());
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1 && structuredSelection.getFirstElement() instanceof IFile) {
				selectedFile = (IFile) structuredSelection.getFirstElement();
				action.setEnabled(true);
				return;
			}
		}
		selectedFile = null;
		action.setEnabled(false);
	}

	private void showError(String title, String contents) {
		MessageDialog.openError(shell, title, contents);
	}

}
