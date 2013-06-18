/**
 * Copyright (c) 2007, 2009, 2013 Borland Software Corporation and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 * 	  Michael Golubev (Montages) - #386838 - migrate to Xtend2
 */
package xpt.providers

import com.google.inject.Inject
import xpt.Common
import org.eclipse.gmf.codegen.gmfgen.GenDiagram
import xpt.editor.VisualIDRegistry
import xpt.editor.ValidationMarker

/**
 * FIXME: [MG] monolithic template with most of the code "same-generated".
 * Unfortunately, a lot of the logic is based around «IF editorGen.application == null» and we don't have a good ways to deal with taht in GMFT-runtimw
 */
class ValidationDecoratorProvider {
	@Inject extension Common;
	
	@Inject VisualIDRegistry xptVisualIDRegistry;
	@Inject ValidationMarker xptValidationMarker; 
	
def ValidationDecoratorProvider(GenDiagram it) '''
«copyright(editorGen)»
package «providersPackageName»;

«generatedClassComment»
public class «validationDecoratorProviderClassName»
		extends org.eclipse.gmf.runtime.common.core.service.AbstractProvider
		implements org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider {

	«generatedMemberComment»
	private static final String KEY = "validationStatus"; «nonNLS(1)»
	«IF editorGen.application == null»

	«generatedMemberComment»
	private static final String MARKER_TYPE = «editorGen.plugin.getActivatorQualifiedClassName()».ID +
			".«getValidationDiagnosticMarkerType()»"; «nonNLS(1)»

	«generatedMemberComment»
	private static MarkerObserver fileObserver;
	«ENDIF»

	«generatedMemberComment»
	private static java.util.Map/*<String, List<IDecorator>>*/ allDecorators = new java.util.HashMap();

	«generatedMemberComment»
	public void createDecorators(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget decoratorTarget) {
		org.eclipse.gef.EditPart editPart = (org.eclipse.gef.EditPart) decoratorTarget.getAdapter(org.eclipse.gef.EditPart.class);
		if (editPart instanceof org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart ||
				editPart instanceof org.eclipse.gef.editparts.AbstractConnectionEditPart) {
			Object model = editPart.getModel();
			if ((model instanceof org.eclipse.gmf.runtime.notation.View)) {
				org.eclipse.gmf.runtime.notation.View view = (org.eclipse.gmf.runtime.notation.View) model;
				if (!(view instanceof org.eclipse.gmf.runtime.notation.Edge) && !view.isSetElement()) {
					return;
				}
			}
			org.eclipse.gef.EditDomain ed = editPart.getViewer().getEditDomain();
			if (!(ed instanceof org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain)) {
				return;
			}
			if (((org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain) ed).getEditorPart() instanceof
					«editorGen.editor.getQualifiedClassName()») {
				decoratorTarget.installDecorator(KEY, new StatusDecorator(decoratorTarget));
			}
		}
	}

	«generatedMemberComment»
	public boolean provides(org.eclipse.gmf.runtime.common.core.service.IOperation operation) {
		if (!(operation instanceof org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation)) {
			return false;
		}
		org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget decoratorTarget =
				((org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation) operation).getDecoratorTarget();
		org.eclipse.gmf.runtime.notation.View view = (org.eclipse.gmf.runtime.notation.View) decoratorTarget.getAdapter(
				org.eclipse.gmf.runtime.notation.View.class);
		return view != null && «VisualIDRegistry::modelID(it)».equals(«xptVisualIDRegistry.getModelIDMethodCall(it)»(view));
	}

	«generatedMemberComment»
	public static void refreshDecorators(org.eclipse.gmf.runtime.notation.View view) {
		refreshDecorators(org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.getIdStr(view), view.getDiagram());
	}

	«generatedMemberComment»
	private static void refreshDecorators(String viewId, org.eclipse.gmf.runtime.notation.Diagram diagram) {
		final java.util.List decorators = viewId != null ? (java.util.List) allDecorators.get(viewId) : null;
		if (decorators == null || decorators.isEmpty() || diagram == null) {
			return;
		}
		final org.eclipse.gmf.runtime.notation.Diagram fdiagram = diagram;
		org.eclipse.ui.PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

			public void run() {
				try {
					org.eclipse.emf.transaction.util.TransactionUtil.getEditingDomain(fdiagram).runExclusive(new Runnable() {

						public void run() {
							for (java.util.Iterator it = decorators.iterator(); it.hasNext();) {
								org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator decorator =
										(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator) it.next();
								decorator.refresh();
							}
						}
					});
				} catch (Exception e) {
					«editorGen.plugin.getActivatorQualifiedClassName()».getInstance().logError(
							"Decorator refresh failure", e); «nonNLS(1)»
				}
			}
		});
	}

	«generatedMemberComment»
	public static class StatusDecorator extends org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator {

		«generatedMemberComment»
		private String viewId;

		«generatedMemberComment»
		public StatusDecorator(org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget decoratorTarget) {
			super(decoratorTarget);
			try {
				final org.eclipse.gmf.runtime.notation.View view = (org.eclipse.gmf.runtime.notation.View) getDecoratorTarget().getAdapter(
						org.eclipse.gmf.runtime.notation.View.class);
				org.eclipse.emf.transaction.util.TransactionUtil.getEditingDomain(view).runExclusive(new Runnable() {

					public void run() {
						StatusDecorator.this.viewId = view != null ?
								org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.getIdStr(view) : null;
					}
				});
			} catch (Exception e) {
				«editorGen.plugin.getActivatorQualifiedClassName()».getInstance().logError(
						"ViewID access failure", e); «nonNLS(1)»			
			}
		}

		«generatedMemberComment»
		public void refresh() {
			removeDecoration();
			org.eclipse.gmf.runtime.notation.View view = (org.eclipse.gmf.runtime.notation.View) getDecoratorTarget().getAdapter(
					org.eclipse.gmf.runtime.notation.View.class);
			if (view == null || view.eResource() == null) {
				return;
			}
			org.eclipse.gef.EditPart editPart = (org.eclipse.gef.EditPart) getDecoratorTarget().getAdapter(org.eclipse.gef.EditPart.class);
			if (editPart == null || editPart.getViewer() == null) {
				return;
			}

			// query for all the validation markers of the current resource
			«IF editorGen.application == null»
			String elementId = org.eclipse.gmf.runtime.diagram.core.util.ViewUtil.getIdStr(view);
			if (elementId == null) {
				return;
			}
			int severity = org.eclipse.core.resources.IMarker.SEVERITY_INFO;
			org.eclipse.core.resources.IMarker foundMarker = null;
			org.eclipse.core.resources.IResource resource = org.eclipse.emf.workspace.util.WorkspaceSynchronizer.getFile(view.eResource());
			if (resource == null || !resource.exists()) {
				return;
			}
			org.eclipse.core.resources.IMarker[] markers = null;
			try {
				markers = resource.findMarkers(MARKER_TYPE, true, org.eclipse.core.resources.IResource.DEPTH_INFINITE);
			} catch (org.eclipse.core.runtime.CoreException e) {
				«editorGen.plugin.getActivatorQualifiedClassName()».getInstance().logError(
						"Validation markers refresh failure", e); «nonNLS(1)»
			}
			«ELSE»
			int severity = org.eclipse.core.runtime.IStatus.INFO;
			«xptValidationMarker.qualifiedClassName(it)» foundMarker = null;
			«xptValidationMarker.qualifiedClassName(it)»[] markers =
					«xptValidationMarker.qualifiedClassName(it)».getMarkers(editPart.getViewer(), viewId);
			«ENDIF»
			if (markers == null || markers.length == 0) {
				return;
			}
			org.eclipse.draw2d.Label toolTip = null;
			for (int i = 0; i < markers.length; i++) {
				«IF editorGen.application == null»
				org.eclipse.core.resources.IMarker marker = markers[i];
				String attribute = marker.getAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID, ""); «nonNLS(1)»
				if (attribute.equals(elementId)) {
					int nextSeverity = marker.getAttribute(org.eclipse.core.resources.IMarker.SEVERITY,
							org.eclipse.core.resources.IMarker.SEVERITY_INFO);					
				«ELSE»
				«xptValidationMarker.qualifiedClassName(it)» marker = markers[i];
				int nextSeverity = marker.getStatusSeverity();
				«ENDIF»
					org.eclipse.swt.graphics.Image nextImage = getImage(nextSeverity);	 
					if (foundMarker == null) {				
						foundMarker = marker;
						toolTip = new org.eclipse.draw2d.Label(
							«IF editorGen.application == null»
								marker.getAttribute(org.eclipse.core.resources.IMarker.MESSAGE, ""), «nonNLS(1)»
							«ELSE»
								marker.getMessage(),
							«ENDIF»
								nextImage);						
					} else {
						if (toolTip.getChildren().isEmpty()) {
							org.eclipse.draw2d.Label comositeLabel = new org.eclipse.draw2d.Label();
							org.eclipse.draw2d.FlowLayout fl = new org.eclipse.draw2d.FlowLayout(false);
							fl.setMinorSpacing(0);
							comositeLabel.setLayoutManager(fl);
							comositeLabel.add(toolTip);
							toolTip = comositeLabel;
						}
						toolTip.add(new org.eclipse.draw2d.Label(
							«IF editorGen.application == null»
								marker.getAttribute(org.eclipse.core.resources.IMarker.MESSAGE, ""),  «nonNLS(1)»
							«ELSE»
								marker.getMessage(),
							«ENDIF»
								nextImage));
					}
					severity = (nextSeverity > severity) ? nextSeverity : severity;					
				«IF editorGen.application == null»
				}
				«ENDIF»
			}
			if (foundMarker == null) {
				return;
			}

			// add decoration
			if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
				if (view instanceof org.eclipse.gmf.runtime.notation.Edge) {
					setDecoration(getDecoratorTarget().addConnectionDecoration(getImage(severity), 50, true));										
				} else {
					int margin = -1;
					if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
						margin = org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil.getMapMode(
							((org.eclipse.gef.GraphicalEditPart) editPart).getFigure()).DPtoLP(margin);
					}
					setDecoration(getDecoratorTarget().addShapeDecoration(getImage(severity),
							org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.NORTH_EAST, margin, true));										
				}
				getDecoration().setToolTip(toolTip);
			}
		}

		«generatedMemberComment»
		private org.eclipse.swt.graphics.Image getImage(int severity) {
			String imageName = org.eclipse.ui.ISharedImages.IMG_OBJS_ERROR_TSK;
			switch (severity) {
			«IF editorGen.application == null»
			case org.eclipse.core.resources.IMarker.SEVERITY_ERROR:
				imageName = org.eclipse.ui.ISharedImages.IMG_OBJS_ERROR_TSK;
				break;
			case org.eclipse.core.resources.IMarker.SEVERITY_WARNING:
				imageName = org.eclipse.ui.ISharedImages.IMG_OBJS_WARN_TSK;
				break;
			«ELSE»
			case org.eclipse.core.runtime.IStatus.ERROR:
				imageName = org.eclipse.ui.ISharedImages.IMG_OBJS_ERROR_TSK;
				break;
			case org.eclipse.core.runtime.IStatus.WARNING:
				imageName = org.eclipse.ui.ISharedImages.IMG_OBJS_WARN_TSK;
				break;
			«ENDIF»
			default:
				imageName = org.eclipse.ui.ISharedImages.IMG_OBJS_INFO_TSK;
			}
			return org.eclipse.ui.PlatformUI.getWorkbench().getSharedImages().getImage(imageName);
		}

		«generatedMemberComment»
		public void activate() {
			if (viewId == null) {
				return;
			}

			// add self to global decorators registry
			java.util.List list = (java.util.List) allDecorators.get(viewId);
			if (list == null) {
				list = new java.util.ArrayList(2);
				list.add(this);
				allDecorators.put(viewId, list);
			} else if (!list.contains(this)) {
				list.add(this);
			}
			«IF editorGen.application == null»

			// start listening to changes in resources
			org.eclipse.gmf.runtime.notation.View view = (org.eclipse.gmf.runtime.notation.View) getDecoratorTarget().getAdapter(
					org.eclipse.gmf.runtime.notation.View.class);
			if (view == null) {
				return;
			}
			org.eclipse.gmf.runtime.notation.Diagram diagramView = view.getDiagram();
			if (diagramView == null) {
				return;
			}
			if (fileObserver == null) {
				org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager.getInstance().addFileObserver(
						fileObserver = new MarkerObserver(diagramView));
			}
			«ENDIF»
		}

		«generatedMemberComment»
		public void deactivate() {
			if (viewId == null) {
				return;
			}

			// remove self from global decorators registry
			java.util.List list = (java.util.List) allDecorators.get(viewId);
			if (list != null) {
				list.remove(this);
				if (list.isEmpty()) {
					allDecorators.remove(viewId);
				}
			}
			«IF editorGen.application == null»

			// stop listening to changes in resources if there are no more decorators
			if (fileObserver != null && allDecorators.isEmpty()) {
				org.eclipse.gmf.runtime.common.ui.resources.FileChangeManager.getInstance().removeFileObserver(fileObserver);
				fileObserver = null;
			}
			«ENDIF»
			super.deactivate();
		}
	}
	«IF editorGen.application == null»

	«generatedMemberComment»
	static class MarkerObserver implements org.eclipse.gmf.runtime.common.ui.resources.IFileObserver {

		«generatedMemberComment»
		private org.eclipse.gmf.runtime.notation.Diagram diagram;

		«generatedMemberComment»
		private MarkerObserver(org.eclipse.gmf.runtime.notation.Diagram diagram) {
			this.diagram = diagram;
		}

		«generatedMemberComment»
		public void handleFileRenamed(org.eclipse.core.resources.IFile oldFile, org.eclipse.core.resources.IFile file) {
		}

		«generatedMemberComment»
		public void handleFileMoved(org.eclipse.core.resources.IFile oldFile, org.eclipse.core.resources.IFile file) {
		}

		«generatedMemberComment»
		public void handleFileDeleted(org.eclipse.core.resources.IFile file) {
		}

		«generatedMemberComment»
		public void handleFileChanged(org.eclipse.core.resources.IFile file) {
		}

		«generatedMemberComment»
		public void handleMarkerAdded(org.eclipse.core.resources.IMarker marker) {
			if (marker.getAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID, null) != null) {
				handleMarkerChanged(marker);
			}
		}

		«generatedMemberComment»
		public void handleMarkerDeleted(org.eclipse.core.resources.IMarker marker, java.util.Map attributes) {
			String viewId = (String) attributes.get(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID);
			refreshDecorators(viewId, diagram);
		}

		«generatedMemberComment»
		public void handleMarkerChanged(org.eclipse.core.resources.IMarker marker) {
			if (!MARKER_TYPE.equals(getType(marker))) {
				return;
			}
			String viewId = marker.getAttribute(
					org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID, ""); «nonNLS(1)»
			refreshDecorators(viewId, diagram);
		}

		«generatedMemberComment»
		private String getType(org.eclipse.core.resources.IMarker marker) {
			try {
				return marker.getType();
			} catch (org.eclipse.core.runtime.CoreException e) {
				«editorGen.plugin.getActivatorQualifiedClassName()».getInstance().logError(
						"Validation marker refresh failure", e); «nonNLS(1)»
				return ""; «nonNLS(1)»
			}
		}
	}
	«ENDIF»
	«additions(it)»
}
'''

	def additions(GenDiagram it) ''''''

}
