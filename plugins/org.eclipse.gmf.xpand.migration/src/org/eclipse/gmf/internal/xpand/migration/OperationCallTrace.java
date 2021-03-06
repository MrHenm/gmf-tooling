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
package org.eclipse.gmf.internal.xpand.migration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.gmf.internal.xpand.BuiltinMetaModel;
import org.eclipse.gmf.internal.xpand.expression.ExecutionContext;
import org.eclipse.gmf.internal.xpand.expression.ast.DeclaredParameter;
import org.eclipse.gmf.internal.xpand.xtend.ast.Extension;
import org.eclipse.gmf.internal.xpand.xtend.ast.JavaExtensionStatement;

public class OperationCallTrace extends ExpressionAnalyzeTrace {

	public enum Type {
		UNDESOLVED_PARAMETER_TYPE, UNDESOLVED_TARGET_TYPE, STATIC_EXTENSION_REF, OPERATION_REF, EXTENSION_REF, IMPLICIT_COLLECT_OPERATION_REF, IMPLICIT_COLLECT_EXTENSION_REF
	}

	private Type type;

	private EOperation operation;

	private EClassifier targetType;

	private List<EClassifier> paramTypes;

	private String nativeLibraryName;

	private boolean staticQvtoCall = true;

	/**
	 * Migrating operation call as contextual only if there is another one
	 * with same name and number of parameters in the _SAME_ file. 
	 *
	 * No generic solution for externally added polymorphyc extensions for now
	 */
	public static boolean isStaticQvtoCall(ExecutionContext ctx, Extension extension) {
		if (extension.getFormalParameters().size() < 1) {
			return true;
		}
		Set<Extension> allExtensions = new HashSet<Extension>(ctx.getAllExtensions());
		for (Iterator<Extension> it = allExtensions.iterator(); it.hasNext();) {
			Extension nextExtension = it.next();
			if (!extension.getFileName().equals(nextExtension.getFileName()) || !extension.getName().equals(nextExtension.getName())
					|| extension.getParameterTypes().size() != nextExtension.getParameterTypes().size() || extension.isPrivate() ^ nextExtension.isPrivate()) {
				it.remove();
				continue;
			}
		}
		return allExtensions.size() == 1;
	}

	public static String getNativeLibraryName(Extension extension) {
		if (extension instanceof JavaExtensionStatement) {
			return JavaExtensionDescriptor.getNativeLibraryName((JavaExtensionStatement) extension);
		}
		return null;
	}

	public static List<EClassifier> getParamTypes(EOperation op) {
		EList<EParameter> parameters = op.getEParameters();
		List<EClassifier> result = new ArrayList<EClassifier>();
		for (int i = 0; i < parameters.size(); i++) {
			result.add(BuiltinMetaModel.getTypedElementType(parameters.get(i)));
		}
		return result;
	}

	public static List<EClassifier> getParamTypes(Extension f, ExecutionContext ctx) {
		List<DeclaredParameter> formalParameters = f.getFormalParameters();
		List<EClassifier> result = new ArrayList<EClassifier>();
		for (int i = 0; i < formalParameters.size(); i++) {
			result.add(ctx.getTypeForName(formalParameters.get(i).getType().getValue()));
		}
		return result;
	}

	public OperationCallTrace(Type type) {
		this(null, null, null, type);
	}

	public OperationCallTrace(EClassifier result, List<EClassifier> paramTypes, String nativeLibraryName, Type type, boolean isStaticQvtoCall) {
		this(result, paramTypes, nativeLibraryName, type);
		this.staticQvtoCall = isStaticQvtoCall;
	}

	public OperationCallTrace(EClass result, List<EClassifier> paramTypes, EClassifier targetType, String nativeLibraryName, boolean isStaticQvtoCall) {
		this(result, paramTypes, nativeLibraryName, Type.IMPLICIT_COLLECT_EXTENSION_REF, isStaticQvtoCall);
		this.targetType = targetType;
	}

	public OperationCallTrace(EClassifier result, List<EClassifier> paramTypes, EClassifier targetType, EOperation operation, Type type) {
		this(result, paramTypes, null, type);
		this.targetType = targetType;
		this.operation = operation;
	}

	public OperationCallTrace(EClassifier result, List<EClassifier> paramTypes, EClassifier targetType, EOperation operation) {
		this(result, paramTypes, targetType, operation, OperationCallTrace.Type.OPERATION_REF);
		this.paramTypes = paramTypes;
	}

	private OperationCallTrace(EClassifier result, List<EClassifier> paramTypes, String nativeLibraryName, Type type) {
		super(result);
		this.paramTypes = paramTypes;
		this.type = type;
		this.nativeLibraryName = nativeLibraryName;
	}

	public Type getType() {
		return type;
	}

	/**
	 * @return EOperation referenced by this OperationCall or null if getType()
	 *         != (OPERATION_REF || IMPLICIT_COLLECT_OPERATION_REF)
	 */
	public EOperation getEOperation() {
		return operation;
	}

	/**
	 * @return EClassifier representing the type of OperationCall target or null
	 *         if getType() != (OPERATION_REF || IMPLICIT_COLLECT_OPERATION_REF
	 *         || IMPLICIT_COLLECT_EXTENSION_REF)
	 */
	public EClassifier getTargetType() {
		return targetType;
	}

	public List<EClassifier> getParamTypes() {
		return paramTypes;
	}

	public String getNativeLibraryName() {
		return nativeLibraryName;
	}

	/**
	 * Reasonable is getType() == STATIC_EXTENSION_REF || EXTENSION_REF ||
	 * IMPLICIT_COLLECT_EXTENSION_REF
	 */
	public boolean isStaticQvtoCall() {
		return staticQvtoCall;
	}

}
