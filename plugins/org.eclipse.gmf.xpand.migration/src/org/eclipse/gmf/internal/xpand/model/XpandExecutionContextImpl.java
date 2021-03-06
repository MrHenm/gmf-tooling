/*
 * <copyright>
 *
 * Copyright (c) 2005-2008 Sven Efftinge and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sven Efftinge - Initial API and implementation
 *     Alexander Shatalin (Borland)
 *
 * </copyright>
 */
package org.eclipse.gmf.internal.xpand.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.internal.xpand.BufferOutput;
import org.eclipse.gmf.internal.xpand.ResourceManager;
import org.eclipse.gmf.internal.xpand.ResourceMarker;
import org.eclipse.gmf.internal.xpand.expression.ExecutionContextImpl;
import org.eclipse.gmf.internal.xpand.expression.PolymorphicResolver;
import org.eclipse.gmf.internal.xpand.expression.SyntaxConstants;
import org.eclipse.gmf.internal.xpand.expression.TypeNameUtil;
import org.eclipse.gmf.internal.xpand.expression.Variable;
import org.eclipse.gmf.internal.xpand.migration.Activator;

/**
 * *
 * 
 * @author Sven Efftinge *
 */
public class XpandExecutionContextImpl extends ExecutionContextImpl implements XpandExecutionContext {

    protected final Output output;
    
    protected final ProtectedRegionResolver protectedRegionResolver;
    
    private final List<XpandAdvice> registeredAdvices = new ArrayList<XpandAdvice>();

    public XpandExecutionContextImpl(ResourceManager resourceManager, Output output, ProtectedRegionResolver prs) {
        this (resourceManager, output, prs, null);
    }
    
    public XpandExecutionContextImpl(ResourceManager resourceManager, EPackage... fallbackVisibleModels) {
    	super(resourceManager, fallbackVisibleModels);
    	this.output = new BufferOutput(new StringBuilder());
    	this.protectedRegionResolver = null;
    }
    
    public XpandExecutionContextImpl(ResourceManager resourceManager, Output output, ProtectedRegionResolver prs, Collection<Variable> globalVars) {
        super(resourceManager, globalVars);
        this.output = output;
        this.protectedRegionResolver = prs;
    }

    protected XpandExecutionContextImpl (ResourceManager resourceManager, ResourceMarker currentResource, Collection<Variable> vars, 
            Collection<Variable> globalVars, Output output, ProtectedRegionResolver protectedRegionResolver) {
        super(resourceManager, currentResource, vars, globalVars);
        this.output = output;
        this.protectedRegionResolver = protectedRegionResolver;
    }

    /*copy constructor*/
    protected XpandExecutionContextImpl(XpandExecutionContextImpl original) {
    	super(original);
    	this.output = original.output;
    	this.protectedRegionResolver = original.protectedRegionResolver;
    }
    

    @Override
    public XpandExecutionContextImpl cloneContext() {
        final XpandExecutionContextImpl result = new XpandExecutionContextImpl(this);
        result.registeredAdvices.addAll(registeredAdvices); //todo: [aha] before I refactored, there was an assignment in this place. Is this modification correct?
        return result;
    }

    public XpandDefinition findDefinition(final String name, final EClassifier target, final EClassifier[] paramTypes) {
        String templateName;
        boolean localCall = name.indexOf(SyntaxConstants.NS_DELIM) < 0;
        if (localCall) {
        	templateName = ((XpandResource) currentResource()).getFullyQualifiedName();	//need an enclosing resource in case of composite
        } else {
        	templateName = TypeNameUtil.withoutLastSegment(name);
        }
        XpandResource tpl = findTemplate(templateName);
        if (tpl == null) {
        	if (localCall) {
        		tpl = (XpandResource) currentResource();
        	} else {
        		return null;
        	}
		}
        final XpandExecutionContext ctx = (XpandExecutionContext) cloneWithResource(tpl);
        XpandDefinition def = findDefinition(tpl.getDefinitions(), name, target, paramTypes, ctx);
        if (def == null) {
        	return null;
        }
        XpandAdvice[] advicesInResource = tpl.getAdvices();
        for (int x = advicesInResource.length - 1; x >= 0; x--) {
            final XpandAdvice adv = advicesInResource[x];
            if (adv.matches(def, this)) {
                def = new AdvicedDefinition(adv, def);
            }
        }
        for (int x = registeredAdvices.size() - 1; x >= 0; x--) {
            final XpandAdvice adv = registeredAdvices.get(x);
            if (adv.matches(def, this)) {
                def = new AdvicedDefinition(adv, def);
            }
        }
        return def;
    }

	public void registerAdvices(final String fullyQualifiedName) {
        final XpandResource tpl = findTemplate(fullyQualifiedName);
        if (tpl == null) {
			throw new RuntimeException("Couldn't find template : " + fullyQualifiedName);
		}
        final XpandAdvice[] as = tpl.getAdvices();
        for (final XpandAdvice advice : as) {
            if (registeredAdvices.contains(advice)) {
                Activator.logWarn("advice " + advice.toString() + " allready registered!");
            } else {
                registeredAdvices.add(advice);
            }
        }
    }

    public ProtectedRegionResolver getProtectedRegionResolver() {
        return protectedRegionResolver;
    }

    public Output getOutput() {
        return output;
    }

    @Override
    protected String[] getImportedNamespaces() {
    	if (currentResource() instanceof XpandResource) {
    		return ((XpandResource) currentResource()).getImportedNamespaces();
    	}
    	return super.getImportedNamespaces();
    }

    @Override
    protected String[] getImportedExtensions() {
    	if (currentResource() instanceof XpandResource) {
    		return ((XpandResource) currentResource()).getImportedExtensions();
    	}
    	return super.getImportedExtensions();
    }

    public XpandResource findTemplate(final String templateName) {
    	if (getResourceManager() == null) {
    		return null;
    	}
    	// XXX findTemplate needs kinda file uri, while metamodel import needs nsURI
    	final List<String> possibleNames = getPossibleNames(templateName, getImportedNamespaces());
        for (String name : possibleNames) {
            final XpandResource tpl = getResourceManager().loadXpandResource(name);
            if (tpl != null) {
//            	installAspectsFor(templateName);
				return tpl;
			}
        }
        return null;
    }

//    private void installAspectsFor(String templateName) {
//    	String aspectsTemplateName = "aspects" + SyntaxConstants.NS_DELIM + templateName;
//    	XpandResource aspects = getResourceManager().loadXpandResource(aspectsTemplateName);
//    	if (aspects != null) {
//    		registeredAdvices.addAll(Arrays.asList(aspects.getAdvices()));
//    	}
//	}

	private List<String> getPossibleNames(final String name, final String[] importedNs) {
        final String typeName = TypeNameUtil.getTypeName(name);
        final String typesMetamodelName = TypeNameUtil.getMetaModelName(name);
        final String collectionTypeName = TypeNameUtil.getCollectionTypeName(name);

        final List<String> result = new ArrayList<String>();
        result.add(name);
        
        for (final String string : importedNs) {
            final StringBuffer s = new StringBuffer();
            if (collectionTypeName != null) {
                s.append(collectionTypeName).append("[");
            }
            if (typesMetamodelName != null) {
                s.append(typesMetamodelName).append("!");
            }
            s.append(string).append(SyntaxConstants.NS_DELIM).append(typeName);
            if (collectionTypeName != null) {
                s.append("]");
            }
            result.add(s.toString());
        }
        return result;
    }

    /**
     * resolves the correct definition (using parametric polymorphism)
	 * XXX: get rid of the ctx argument and redeclare as non-static?
     * @param definitions
     * @param target
     * @param paramTypes
     * @return
     */
    private static XpandDefinition findDefinition(final XpandDefinition[] definitions, final String name, final EClassifier target,
            EClassifier[] paramTypes, final XpandExecutionContext ctx) {
        if (paramTypes == null) {
            paramTypes = new EClassifier[0];
        }
        final String unqualifiedName = TypeNameUtil.getLastSegment(name);
        // XXX Instead of using map as a mere pair storage, do it like Extension does with init(ctx)
        // to resolve and keep typed arguments
        HashMap<XpandDefinition, List<EClassifier>> resolvedDefs = new HashMap<XpandDefinition, List<EClassifier>>();
        for (final XpandDefinition def : definitions) {
        	if (!def.getName().equals(unqualifiedName)) {
        		continue;
        	}
            if (def.getParams().length == paramTypes.length) {
                final LinkedList<EClassifier> defsParamTypes = new LinkedList<EClassifier>();
                EClassifier t = null;
                boolean complete = true;
                for (int j = 0; (j < paramTypes.length) && complete; j++) {
                    t = ctx.getTypeForName(def.getParams()[j].getType().getValue());
                    if (t == null) {
                        complete = false;
                    }
                    defsParamTypes.add(t);
                }
                t = ctx.getTypeForName(def.getTargetType());
                if (t == null) {
                    complete = false;
                } else {
                	defsParamTypes.addFirst(t);
                }
                if (complete) {
                	resolvedDefs.put(def, defsParamTypes);
                }
            }
        }
		return PolymorphicResolver.filterDefinition(resolvedDefs, target, Arrays.asList(paramTypes));
    }
}
