package org.eclipse.gmf.codegen.templates.editor;

import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.common.codegen.*;

public class NavigatorGrGenerator
{
  protected static String nl;
  public static synchronized NavigatorGrGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    NavigatorGrGenerator result = new NavigatorGrGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "/*" + NL + " *";
  protected final String TEXT_3 = NL + " */";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_6 = " extends ";
  protected final String TEXT_7 = " implements ";
  protected final String TEXT_8 = " {" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_9 = " myAdapterFactoryLabelProvider = new ";
  protected final String TEXT_10 = "(";
  protected final String TEXT_11 = ".getInstance().getItemProvidersAdapterFactory());" + NL + "" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_12 = " getImage(Object element) {" + NL + "\t\tif (element instanceof ";
  protected final String TEXT_13 = ") {" + NL + "\t\t\t";
  protected final String TEXT_14 = " view = (";
  protected final String TEXT_15 = ") element;" + NL + "\t\t\t";
  protected final String TEXT_16 = " eObject = view.getElement();" + NL + "\t\t\treturn myAdapterFactoryLabelProvider.getImage(eObject);" + NL + "\t\t}" + NL + "\t\treturn super.getImage(element);" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String getText(Object element) {" + NL + "\t\tif (element instanceof ";
  protected final String TEXT_17 = ") {" + NL + "\t\t\t";
  protected final String TEXT_18 = " view = (";
  protected final String TEXT_19 = ") element;" + NL + "\t\t\t";
  protected final String TEXT_20 = " eObject = view.getElement();" + NL + "\t\t\treturn myAdapterFactoryLabelProvider.getText(eObject);" + NL + "\t\t}" + NL + "\t\treturn super.getText(element);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void init(";
  protected final String TEXT_21 = " aConfig) {" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void restoreState(";
  protected final String TEXT_22 = " aMemento) {" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void saveState(";
  protected final String TEXT_23 = " aMemento) {" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String getDescription(Object anElement) {" + NL + "\t\treturn null;" + NL + "\t}" + NL + "" + NL + "}";
  protected final String TEXT_24 = NL;

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
    stringBuffer.append(copyrightText.replaceAll("\n", "\n *"));
    stringBuffer.append(TEXT_3);
    }
    importManager.emitPackageStatement(stringBuffer);
    stringBuffer.append(TEXT_4);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genNavigator.getLabelProviderClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(importManager.getImportedName("org.eclipse.jface.viewers.LabelProvider"));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.navigator.ICommonLabelProvider"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(importManager.getImportedName(genNavigator.getEditorGen().getPlugin().getActivatorQualifiedClassName()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(importManager.getImportedName("org.eclipse.swt.graphics.Image"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.notation.View"));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.navigator.ICommonContentExtensionSite"));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.IMemento"));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(importManager.getImportedName("org.eclipse.ui.IMemento"));
    stringBuffer.append(TEXT_23);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}
