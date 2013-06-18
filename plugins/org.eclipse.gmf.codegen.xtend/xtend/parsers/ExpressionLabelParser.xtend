/**
 * Copyright (c) 2010, 2013 Artem Tikhomirov and others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (independent) - Initial API and implementation
 *    Michael Golubev (Montages) - #386838 - migrate to Xtend2
 */
package parsers

import com.google.inject.Inject
import impl.parsers.expression_qvto
import xpt.CodeStyle
import xpt.Common
import xpt.Common_qvto

class ExpressionLabelParser {
	@Inject extension Common;
	@Inject extension Common_qvto;

	@Inject extension expression_qvto;
	@Inject impl.parsers.ExpressionLabelParser xptImplExpressionLabelParser;
	@Inject CodeStyle xptCodeStyle;

	def Main(org.eclipse.gmf.codegen.gmfgen.ExpressionLabelParser it) '''
		«copyright(it.holder.editorGen)»
		package «withoutLastSegment(it.qualifiedClassName)»;
		
		«generatedClassComment»
		public class «lastSegment(it.qualifiedClassName)» «extendsList(it)» «implementsList(it)» {
			«xptImplExpressionLabelParser.fields(it)»
			«xptImplExpressionLabelParser.constructor(it, lastSegment(it.qualifiedClassName))»
			«body(it)»
			«additions(it)»
		}
	'''

	def additions(org.eclipse.gmf.codegen.gmfgen.ExpressionLabelParser it) ''''''

	def extendsList(org.eclipse.gmf.codegen.gmfgen.ExpressionLabelParser it) // 
	'''«IF isParserViewExpressionDefinedAndOcl(it)»extends org.eclipse.gmf.tooling.runtime.parsers.ExpressionLabelParserBase«ENDIF»'''

	def implementsList(org.eclipse.gmf.codegen.gmfgen.ExpressionLabelParser it) // 
	'''«IF !isParserViewExpressionDefinedAndOcl(it)»implements org.eclipse.gmf.runtime.common.ui.services.parser.IParser«ENDIF»'''

	def body(org.eclipse.gmf.codegen.gmfgen.ExpressionLabelParser it) '''

	«IF isParserViewExpressionDefinedAndOcl(it)»
		«generatedMemberComment»
		«xptCodeStyle.overrideC(it.holder.editorGen.diagram)»
		protected String getExpressionBody() {
			return «xptImplExpressionLabelParser.getExpression(viewExpression.provider, it, viewExpression)».body();
		}
	«ELSE»
		«generatedMemberComment»
		public String getPrintString(org.eclipse.core.runtime.IAdaptable element, int flags) {
			«xptImplExpressionLabelParser.accessViewExpression(it)»
		}

		«generatedMemberComment»
		public boolean isAffectingEvent(Object event, int flags) {
			// XXX Any event is recognized as important, unless there's a way to extract this information from expression itself.
			// TODO analyze expressions (e.g. using OCL parser) to find out structural features in use  
			return true;
		}
	«ENDIF»

	«generatedMemberComment»
	public String getEditString(org.eclipse.core.runtime.IAdaptable element, int flags) {
		«xptImplExpressionLabelParser.accessEditExpression(it)»
	}

	«generatedMemberComment»
	public org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus isValidEditString(org.eclipse.core.runtime.IAdaptable element, String editString) {
		«xptImplExpressionLabelParser.accessValidateExpression(it)»
	}

	«generatedMemberComment»
	public org.eclipse.gmf.runtime.common.core.command.ICommand getParseCommand(org.eclipse.core.runtime.IAdaptable element, final String newString, int flags) {
		final org.eclipse.emf.ecore.EObject target = (org.eclipse.emf.ecore.EObject) element.getAdapter(org.eclipse.emf.ecore.EObject.class);
		if (!validateValues(target, newString)) {
			return org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
		}
		org.eclipse.emf.transaction.TransactionalEditingDomain editingDomain = org.eclipse.emf.transaction.util.TransactionUtil.getEditingDomain(target);
		if (editingDomain == null) {
			return org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
		}
		org.eclipse.core.resources.IFile affectedFile = org.eclipse.emf.workspace.util.WorkspaceSynchronizer.getFile(target.eResource());
		return new org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand(editingDomain, "Set Values", affectedFile == null ? null : java.util.Collections.singletonList(affectedFile)) { «nonNLS(
		1)» 
			protected org.eclipse.gmf.runtime.common.core.command.CommandResult doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor monitor, org.eclipse.core.runtime.IAdaptable info) throws org.eclipse.core.commands.ExecutionException {
				return new org.eclipse.gmf.runtime.common.core.command.CommandResult(updateValues(target, newString));
			}
		};
	}

	«generatedMemberComment»
	public org.eclipse.jface.text.contentassist.IContentAssistProcessor getCompletionProcessor(org.eclipse.core.runtime.IAdaptable element) {
		return null;
	}
	
	«generatedMemberComment»
	private boolean validateValues(org.eclipse.emf.ecore.EObject target, String newString) {
		// TODO implement as needed«/* with default 'true' I assume isValidEditString() has been called prior to getParseCommand and generally I don't need to do anything else here */»
		return true;
	}

	«generatedMemberComment»
	private org.eclipse.core.runtime.IStatus updateValues(org.eclipse.emf.ecore.EObject target, String newString) throws org.eclipse.core.commands.ExecutionException {
		// TODO implement this method
		// DO NOT FORGET to remove @generated tag or mark method @generated NOT
		throw new org.eclipse.core.commands.ExecutionException("Please implement parsing and value modification"); 
	}
	
	«xptImplExpressionLabelParser.extraMethods(it)»
	'''
}
