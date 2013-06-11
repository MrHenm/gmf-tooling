/**
 * Copyright (c) 2006-2013 Borland Software Corporation and others
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
package xpt.diagram.edithelpers;

import com.google.inject.Inject
import org.eclipse.gmf.codegen.gmfgen.SpecializationType
import xpt.Common

public class EditHelperAdvice {
	@Inject extension Common;

	def EditHelperAdvice(SpecializationType it) '''
		«copyright(diagramElement.diagram.editorGen)»
		package «diagramElement.getDiagram().editHelpersPackageName»;
		
		public class «editHelperAdviceClassName» extends «superClass()» {
			
			«additions()»
		}
	'''

	def additions(SpecializationType it) '''«stampXtend2(diagramElement.diagram)»'''

	def extendsClause(SpecializationType it) '''extends «superClass()»'''

	def superClass(SpecializationType it) '''org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice'''

}
