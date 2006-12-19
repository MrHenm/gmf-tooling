package org.eclipse.gmf.codegen.templates.lite.navigator;

import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.common.codegen.*;

public class NavigatorLinkHelperGenerator
{
  protected static String nl;
  public static synchronized NavigatorLinkHelperGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    NavigatorLinkHelperGenerator result = new NavigatorLinkHelperGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "/*" + NL + " * ";
  protected final String TEXT_3 = NL + " */";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_6 = " implements ";
  protected final String TEXT_7 = " {" + NL;
  protected final String TEXT_8 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */\t" + NL + "\tpublic ";
  protected final String TEXT_9 = " findSelection(";
  protected final String TEXT_10 = " anInput) {" + NL + "\t\treturn ";
  protected final String TEXT_11 = ".EMPTY;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */\t" + NL + "\tpublic void activateEditor(";
  protected final String TEXT_12 = " aPage, ";
  protected final String TEXT_13 = " aSelection) {" + NL + "\t\tif (aSelection == null || aSelection.isEmpty()) {" + NL + "\t\t\treturn;" + NL + "\t\t}" + NL + "\t\tif (false == aSelection.getFirstElement() instanceof ";
  protected final String TEXT_14 = ") {" + NL + "\t\t\treturn;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_15 = " navigatorItem = (";
  protected final String TEXT_16 = ") aSelection.getFirstElement();" + NL + "\t\t";
  protected final String TEXT_17 = " navigatorView = null;" + NL + "\t\tif (navigatorItem instanceof ";
  protected final String TEXT_18 = ") {" + NL + "\t\t\tnavigatorView = ((";
  protected final String TEXT_19 = ") navigatorItem).getView();" + NL + "\t\t} else if (navigatorItem instanceof ";
  protected final String TEXT_20 = ") {" + NL + "\t\t\t";
  protected final String TEXT_21 = " group = (";
  protected final String TEXT_22 = ") navigatorItem;" + NL + "\t\t\tif (group.getParent() instanceof ";
  protected final String TEXT_23 = ") {" + NL + "\t\t\t\tnavigatorView = ((";
  protected final String TEXT_24 = ") group.getParent()).getView();" + NL + "\t\t\t}";
  protected final String TEXT_25 = " else if (group.getParent() instanceof ";
  protected final String TEXT_26 = ") {" + NL + "\t\t\t\tnavigatorView = (";
  protected final String TEXT_27 = ") ((";
  protected final String TEXT_28 = ") group.getParent()).getAdapter(";
  protected final String TEXT_29 = ".class);" + NL + "\t\t\t}";
  protected final String TEXT_30 = NL + "\t\t}" + NL + "\t\tif (navigatorView == null) {" + NL + "\t\t\treturn;" + NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_31 = " editorInput = new ";
  protected final String TEXT_32 = "(navigatorView.getDiagram());" + NL + "\t\t";
  protected final String TEXT_33 = " editor = aPage.findEditor(editorInput);" + NL + "\t\tif (editor == null) {" + NL + "\t\t\treturn;" + NL + "\t\t}" + NL + "\t\taPage.bringToTop(editor);" + NL + "\t\tif (editor instanceof ";
  protected final String TEXT_34 = ") {" + NL + "\t\t\t";
  protected final String TEXT_35 = " diagramEditor = (";
  protected final String TEXT_36 = ") editor;" + NL + "\t\t\t";
  protected final String TEXT_37 = " resourceSet = diagramEditor.getEditingDomain().getResourceSet();" + NL + "\t\t\t";
  protected final String TEXT_38 = " selectedView = resourceSet.getEObject(";
  protected final String TEXT_39 = ".getURI(navigatorView), false);" + NL + "\t\t\tif (selectedView == null) {" + NL + "\t\t\t\treturn;" + NL + "\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_40 = " graphicalViewer = (";
  protected final String TEXT_41 = ") diagramEditor.getAdapter(";
  protected final String TEXT_42 = ".class);" + NL + "\t\t\t";
  protected final String TEXT_43 = " selectedEditPart = (";
  protected final String TEXT_44 = ") graphicalViewer.getEditPartRegistry().get(selectedView);" + NL + "\t\t\tif (selectedEditPart != null) {" + NL + "\t\t\t\tgraphicalViewer.select(selectedEditPart);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "}";
  protected final String TEXT_45 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
GenNavigator genNavigator = (GenNavigator) ((Object[]) argument)[0];
GenDiagram genDiagram = genNavigator.getEditorGen().getDiagram();
final ImportAssistant importManager = (ImportAssistant) ((Object[]) argument)[1];

    stringBuffer.append(TEXT_1);
    
String copyrightText = genDiagram.getEditorGen().getCopyrightText();
if (copyrightText != null && copyrightText.trim().length() > 0) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(copyrightText.replaceAll("\n", "\n * "));
    stringBuffer.append(TEXT_3);
    }
    importManager.emitPackageStatement(stringBuffer);
    stringBuffer.append(TEXT_4);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genNavigator.getLinkHelperClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.navigator.ILinkHelper"));
    stringBuffer.append(TEXT_7);
    
/**
 * Uncomment this inclide ore remove corresponding file on resolving the following
 * request: https://bugs.eclipse.org/bugs/show_bug.cgi?id=159739
 *
	
< %@ include file="navigatorLinkHelper_findSelection.jetinc" % >
 *
 */

    stringBuffer.append(TEXT_8);
    stringBuffer.append(importManager.getImportedName("org.eclipse.jface.viewers.IStructuredSelection"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.IEditorInput"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(importManager.getImportedName("org.eclipse.jface.viewers.StructuredSelection"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.IWorkbenchPage"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(importManager.getImportedName("org.eclipse.jface.viewers.IStructuredSelection"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(importManager.getImportedName(genNavigator.getAbstractNavigatorItemQualifiedClassName()));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(importManager.getImportedName(genNavigator.getAbstractNavigatorItemQualifiedClassName()));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(importManager.getImportedName(genNavigator.getAbstractNavigatorItemQualifiedClassName()));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(importManager.getImportedName(genNavigator.getNavigatorItemQualifiedClassName()));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(importManager.getImportedName(genNavigator.getNavigatorItemQualifiedClassName()));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(importManager.getImportedName(genNavigator.getNavigatorGroupQualifiedClassName()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(importManager.getImportedName(genNavigator.getNavigatorGroupQualifiedClassName()));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(importManager.getImportedName(genNavigator.getNavigatorGroupQualifiedClassName()));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(importManager.getImportedName(genNavigator.getNavigatorItemQualifiedClassName()));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(importManager.getImportedName(genNavigator.getNavigatorItemQualifiedClassName()));
    stringBuffer.append(TEXT_24);
    
if (false && genDiagram.generateShortcutIcon()) {	//no shortcuts so far in lite
	
    stringBuffer.append(TEXT_25);
    stringBuffer.append(importManager.getImportedName("org.eclipse.core.runtime.IAdaptable"));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(importManager.getImportedName("org.eclipse.core.runtime.IAdaptable"));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_29);
    
}
    stringBuffer.append(TEXT_30);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.lite.parts.DiagramEditorInput"));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.lite.parts.DiagramEditorInput"));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.IEditorPart"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.lite.parts.DiagramEditor"));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.lite.parts.DiagramEditor"));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.lite.parts.DiagramEditor"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.resource.ResourceSet"));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.GraphicalViewer"));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.GraphicalViewer"));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.GraphicalViewer"));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.EditPart"));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gef.EditPart"));
    stringBuffer.append(TEXT_44);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_45);
    return stringBuffer.toString();
  }
}
