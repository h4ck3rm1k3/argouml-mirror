// $Id$
/*******************************************************************************
 * Copyright (c) 2007-2011 Tom Morris and other contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Tom Morris - initial framework 
 *    Bob Tarling
 *******************************************************************************/
package org.argouml.model.euml;

import java.util.Collection;
import java.util.List;

import org.argouml.model.Model;
import org.argouml.model.StateMachinesHelper;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Vertex;

/**
 * The implementation of the StateMachinesHelper for EUML2.
 */
class StateMachinesHelperEUMLImpl implements StateMachinesHelper {

    /**
     * The model implementation.
     */
    private EUMLModelImplementation modelImpl;

    /**
     * Constructor.
     * 
     * @param implementation
     *            The ModelImplementation.
     */
    public StateMachinesHelperEUMLImpl(EUMLModelImplementation implementation) {
        modelImpl = implementation;
    }

    public void addDeferrableEvent(Object state, Object deferrableEvent) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void addSubvertex(Object handle, Object subvertex) {
        if (handle instanceof Region
                && subvertex instanceof Vertex) {
            ((Vertex) subvertex).setContainer((Region) handle);
            return;
        }
        throw new IllegalArgumentException("handle: " + handle //$NON-NLS-1$
                + " or subvertex: " + subvertex); //$NON-NLS-1$
    }

    public Object findNamespaceForEvent(Object trans, Object model) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public Object findOperationByName(Object trans, String opname) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public Collection getAllPossibleStatemachines(Object model,
            Object oSubmachineState) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public Collection getAllPossibleSubvertices(Object oState) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public Collection getAllSubStates(Object compState) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public Object getDestination(Object trans) {
        if (trans instanceof Transition) {
            return ((Transition) trans).getTarget();
        }
        throw new IllegalArgumentException();
    }

    public Collection getOutgoingStates(Object ostatevertex) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public String getPath(Object o) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }
    
    public List getRegions(Object handle) {
        if (handle instanceof StateMachine) {
            return ((StateMachine) handle).getRegions();
        } else if (handle instanceof State) {
            return ((State) handle).getRegions();
        }
        throw new IllegalArgumentException(
                "getRegions call not valid for a " + handle); //$NON-NLS-1$
    }

    public Object getSource(Object trans) {
        if (trans instanceof Transition) {
            return ((Transition) trans).getTarget();
        }
        throw new IllegalArgumentException();
    }

    public Object getStateMachine(Object handle) {
        if (handle == null) {
            throw new IllegalArgumentException(
                    "bad argument to getStateMachine() - " //$NON-NLS-1$
                    + handle);
        }
        Object container =
            modelImpl.getFacade().getModelElementContainer(handle);
        while (container != null) {
            if (Model.getFacade().isAStateMachine(container)) {
                return container;
            }
            container =
                modelImpl.getFacade()
                    .getModelElementContainer(container);
        }
        /* In this case, either the container was not set,
         * or it was not contained in a statemachine.
         */
        return null;
    }

    public Object getStatebyName(String path, Object container) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public Object getTop(Object sm) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();
    }

    public boolean isAddingStatemachineAllowed(Object context) {
        return (context instanceof Classifier);
    }

    public boolean isTopState(Object o) {
        // TODO: This needs to be double checked. - tfm
        return o instanceof State && ((State) o).getOwner() == null;
    }

    public void removeDeferrableEvent(Object state, Object deferrableEvent) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void removeSubvertex(Object handle, Object subvertex) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setBound(Object handle, int bound) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setChangeExpression(Object handle, Object value) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setConcurrent(Object handle, boolean concurrent) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setContainer(Object handle, Object region) {
        if (!(handle instanceof Vertex)) {
            throw new IllegalArgumentException(
                    "Expected a vertext, got a " + handle); //$NON-NLS-1$
        }
        
        if (region instanceof State) {
            List<Region> regions = ((State) region).getRegions();
            if (regions.isEmpty()) {
                region = Model.getUmlFactory().buildNode(
                        Model.getMetaTypes().getRegion(), region);
            } else {
                region = regions.get(0);
            }
        }
                        
        if (region == null || region instanceof Region) {
            ((Vertex) handle).setContainer((Region) region);
            return;
        }
                        
        throw new IllegalArgumentException(
                "Expected a State or Region, got a " + handle); //$NON-NLS-1$
    }

    public void setContext(Object statemachine, Object modelElement) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setDoActivity(Object handle, Object value) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setEffect(Object handle, Object value) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setEntry(Object handle, Object value) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setEventAsTrigger(Object transition, Object event) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setExit(Object handle, Object value) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setExpression(Object handle, Object value) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setGuard(Object handle, Object guard) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setInternalTransitions(Object handle, Collection intTrans) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setReferenceState(Object o, String referenced) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setSource(Object handle, Object state) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setState(Object handle, Object element) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setStateMachine(Object handle, Object stm) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setStatemachineAsSubmachine(Object oSubmachineState,
            Object oStatemachine) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setSubvertices(Object handle, Collection subvertices) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setTrigger(Object handle, Object event) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

    public void setWhen(Object handle, Object value) {
        // TODO: Auto-generated method stub
        throw new NotYetImplementedException();

    }

}
