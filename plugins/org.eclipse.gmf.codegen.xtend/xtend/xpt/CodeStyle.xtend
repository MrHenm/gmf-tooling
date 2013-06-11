package xpt

import com.google.inject.Inject
import org.eclipse.gmf.codegen.gmfgen.GenCommonBase
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator

class CodeStyle {
	@Inject extension GenEditorGenerator_qvto

	def overrideC(GenCommonBase xptSelf) ''' 
		«IF xptSelf.jdkComplianceLevel() > 4»
			@Override
		«ENDIF»
	'''

	def overrideI(GenCommonBase xptSelf) '''
		«IF xptSelf.jdkComplianceLevel() > 5»
			@Override
		«ENDIF»
	'''

	def SuppressWarnings(GenEditorGenerator xptSelf, String warnToken) '''«SuppressWarnings(xptSelf.diagram, warnToken)»'''

	def SuppressWarnings(GenCommonBase xptSelf, String warnToken) '''
		«IF xptSelf.jdkComplianceLevel() > 4»
			@SuppressWarnings(«warnToken»)
		«ENDIF»
	'''
}
