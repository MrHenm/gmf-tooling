package org.eclipse.gmf.codegen.templates.parts;

import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.codegen.util.*;

public class LinkLabelTextEditPartGenerator
{
  protected static String nl;
  public static synchronized LinkLabelTextEditPartGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    LinkLabelTextEditPartGenerator result = new LinkLabelTextEditPartGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL;
  protected final String TEXT_3 = NL + "import org.eclipse.gmf.runtime.diagram.ui.editparts.TextCompartmentEditPart;" + NL + "import org.eclipse.gmf.runtime.notation.View;";
  protected final String TEXT_4 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_5 = " extends TextCompartmentEditPart {" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_6 = "(View view) {" + NL + "\t\tsuper(view);" + NL + "\t}";
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_9 = " getParser() {" + NL + "\t\tif (parser == null) {" + NL + "\t\t\tparser = new ";
  protected final String TEXT_10 = "(";
  protected final String TEXT_11 = ".eINSTANCE.get";
  protected final String TEXT_12 = "().getEStructuralFeature(\"";
  protected final String TEXT_13 = "\"));";
  protected final String TEXT_14 = NL + "\t\t\t((";
  protected final String TEXT_15 = ") parser).setViewPattern(\"";
  protected final String TEXT_16 = "\");";
  protected final String TEXT_17 = NL + "\t\t\t((";
  protected final String TEXT_18 = ") parser).setEditPattern(\"";
  protected final String TEXT_19 = "\");";
  protected final String TEXT_20 = NL + "\t\t}" + NL + "\t\treturn parser;" + NL + "\t}";
  protected final String TEXT_21 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected boolean isEditable() {" + NL + "\t\treturn false;" + NL + "\t}";
  protected final String TEXT_22 = NL + "}";
  protected final String TEXT_23 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
GenLinkLabel genLabel = (GenLinkLabel) argument;
GenDiagram genDiagram = genLabel.getDiagram();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(genDiagram.getEditPartsPackageName());
    stringBuffer.append(TEXT_2);
    ImportUtil importManager = new ImportUtil(genDiagram.getEditPartsPackageName());
    stringBuffer.append(TEXT_3);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genLabel.getTextEditPartClassName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genLabel.getTextEditPartClassName());
    stringBuffer.append(TEXT_6);
    
String accessorName;
if (genLabel.getLink().getModelFacet() instanceof TypeLinkModelFacet) {
	TypeLinkModelFacet modelFacet = (TypeLinkModelFacet) genLabel.getLink().getModelFacet();
	accessorName = modelFacet.getMetaClass().getClassifierAccessorName();
} else {
	FeatureModelFacet modelFacet = (FeatureModelFacet) genLabel.getLink().getModelFacet();
	accessorName = modelFacet.getMetaFeature().getGenClass().getClassifierAccessorName();
}

    stringBuffer.append(TEXT_7);
    
{
	GenPackage genPackage = genDiagram.getDomainMetaModel();
	String semanticPackageInterfaceName = importManager.getImportedName(genPackage.getQualifiedPackageInterfaceName());
	FeatureModelFacet modelFacet = (FeatureModelFacet) genLabel.getModelFacet();
	String featureName = modelFacet.getMetaFeature().getName();
	String parserClassName = importManager.getImportedName(genDiagram.getStructuralFeatureParserQualifiedClassName());

    stringBuffer.append(TEXT_8);
    stringBuffer.append(importManager.getImportedName("org.eclipse.gmf.runtime.common.ui.services.parser.IParser"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(parserClassName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(semanticPackageInterfaceName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(accessorName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(featureName);
    stringBuffer.append(TEXT_13);
    	if (modelFacet.getViewPattern() != null && modelFacet.getViewPattern().length() != 0) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(parserClassName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(modelFacet.getViewPattern());
    stringBuffer.append(TEXT_16);
    
	}
	if (modelFacet.getEditPattern() != null && modelFacet.getEditPattern().length() != 0) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(parserClassName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(modelFacet.getEditPattern());
    stringBuffer.append(TEXT_19);
    	}
    stringBuffer.append(TEXT_20);
    	if (genLabel.isReadOnly()) {
    stringBuffer.append(TEXT_21);
    
	}
}

    stringBuffer.append(TEXT_22);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_23);
    return stringBuffer.toString();
  }
}
