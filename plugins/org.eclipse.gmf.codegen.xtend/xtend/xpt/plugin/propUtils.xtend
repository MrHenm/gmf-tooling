package xpt.plugin

import com.google.inject.Inject
import org.eclipse.gmf.codegen.gmfgen.GenDiagram

@com.google.inject.Singleton class propUtils {
	
	@Inject extension xpt.diagram.preferences.extensions
	
	def i18n_diagramPreferences(GenDiagram it)'''
		«it.i18n»
	'''
}