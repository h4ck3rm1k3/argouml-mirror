// Source file: Foundation/Core/ModelElement.java

package uci.uml.Foundation.Core;

import java.util.*;
import java.beans.PropertyVetoException;
import uci.uml.Foundation.Data_Types.*;
import uci.uml.Foundation.Extension_Mechanisms.*;
import uci.uml.Behavioral_Elements.State_Machines.*;
import uci.uml.Behavioral_Elements.Collaborations.*;
import uci.uml.Model_Management.*;


public class ModelElementImpl extends ElementImpl implements ModelElement {
  public static final int MAX_STEREOTYPE = 10;
  
  //- public Namespace _namespace;
  public ElementOwnership _elementOwnership;
  //% public Constraint _constraint[];
  public Vector _constraint;
  //% public Dependency _provision[];
  public Vector _provision;
  //% public TaggedValue _taggedValue[];
  public Vector _taggedValue;
  //% public Dependency _requirement[];
  public Vector _requirement;
  //    public ViewElement _view[];
  //    public Binding _binding;
  //% public ModelElement _templateParameter[];
  public Vector _templateParameter;
  public ModelElement _template;
  //    public Component _implementation[];
  //% public Stereotype _stereotype[] = new Stereotype[MAX_STEREOTYPE];
  public Vector _stereotype;
  //% public StateMachine _behavior[];
  public Vector _behavior;
  //    public Partition _partition;
  //% public Collaboration _collaboration[];
  public Vector _collaboration;
  //% public Package _package[];
  //- public Vector _package;
  public Vector _elementReference;
  
  public ModelElementImpl() { }
  public ModelElementImpl(Name name) { super(name); }
  public ModelElementImpl(String nameStr) { super(new Name(nameStr)); }
  
  //-   public Namespace getNamespace() { return _namespace; }
  //-   public void setNamespace(Namespace x) {
  //-     _namespace = x;
  //-   }
  public ElementOwnership getElementOwnership() { return _elementOwnership; }
  public void setElementOwnership(ElementOwnership x)
  throws PropertyVetoException {
    fireVetoableChange("elementOwnership", _elementOwnership, x);
    _elementOwnership = x;
  }

  public Vector getConstraint() { return _constraint; }
  public void setConstraint(Vector x)
  throws PropertyVetoException {
    fireVetoableChange("constraint", _constraint, x);
    _constraint = x;
  }
  public void addConstraint(Constraint x)
  throws PropertyVetoException {
    fireVetoableChange("constraint", _constraint, x);
    if (_constraint == null) _constraint = new Vector();
    _constraint.addElement(x);
  }
  public void removeConstraint(Constraint x)
  throws PropertyVetoException {
    fireVetoableChange("constraint", _constraint, x);
    _constraint.removeElement(x);
  }

  public Vector getProvision() { return _provision; }
  public void setProvision(Vector x)
  throws PropertyVetoException {
    fireVetoableChange("provision", _provision, x);
    _provision = x;
  }
  public void addProvision(Dependency x)
  throws PropertyVetoException {
    fireVetoableChange("provision", _provision, x);
    if (_provision == null) _provision = new Vector();
    _provision.addElement(x);
  }
  public void removeProvision(Dependency x)
  throws PropertyVetoException {
    fireVetoableChange("provision", _provision, x);
    _provision.removeElement(x);
  }

  public Vector getRequirement() { return _requirement; }
  public void setRequirement(Vector x)
  throws PropertyVetoException {
    fireVetoableChange("requirement", _requirement, x);
    _requirement = x;
  }
  public void addRequirement(Dependency x)
  throws PropertyVetoException {
    fireVetoableChange("requirement", _requirement, x);
    if (_requirement == null) _requirement = new Vector();
    _requirement.addElement(x);
  }
  public void removeRequirement(Dependency x)
  throws PropertyVetoException {
    fireVetoableChange("requirement", _requirement, x);
    _requirement.removeElement(x);
  }

  public Vector getTemplateParameter() {
    return _templateParameter;
  }
  public void setTemplateparameter(Vector x)
  throws PropertyVetoException {
    fireVetoableChange("templateParameter", _templateParameter, x);
    _templateParameter = x;
  }
  public void addTemplateparameter(ModelElement x)
  throws PropertyVetoException {
    fireVetoableChange("templateParameter", _templateParameter, x);
    if (_templateParameter == null) _templateParameter = new Vector();
    _templateParameter.addElement(x);
  }
  public void removeTemplateparameter(ModelElement x)
  throws PropertyVetoException {
    fireVetoableChange("templateParameter", _templateParameter, x);
    _templateParameter.removeElement(x);
  }

  public ModelElement getTemplate() { return _template; }
  public void setTemplate(ModelElement x)
  throws PropertyVetoException {
    fireVetoableChange("template", _template, x);
    _template = x;
  }

  //-   public Vector getPackage() { return _package; }
  //-   public void setPackage(Vector x) {
  //-     _package = x;
  //-   }
  //-   public void addPackage(Package x) {
  //-     if (_package == null) _package = new Vector();
  //-     _package.addElement(x);
  //-   }
  //-   public void removePackage(Package x) {
  //-     _package.removeElement(x);
  //-   }
  public Vector getElementReference() { return _elementReference; }
  public void setElementReference(Vector x)
  throws PropertyVetoException {
    fireVetoableChange("elementReference", _elementReference, x);
    _elementReference = x;
  }
  public void addElementReference(ElementReference x)
  throws PropertyVetoException {
    fireVetoableChange("elementReference", _elementReference, x);
    if (_elementReference == null) _elementReference = new Vector();
    _elementReference.addElement(x);
  }
  public void removeElementReference(ElementReference x)
  throws PropertyVetoException {
    fireVetoableChange("elementReference", _elementReference, x);
    _elementReference.removeElement(x);
  }

  public Vector getStereotype() { return _stereotype; }
  public void setStereotype(Vector x)
  throws PropertyVetoException {
    fireVetoableChange("stereotype", _stereotype, x);
    _stereotype = x;
  }
  public void addStereotype(Stereotype x)
  throws PropertyVetoException {
    fireVetoableChange("stereotype", _stereotype, x);
    if (_stereotype == null) _stereotype = new Vector();
    _stereotype.addElement(x);
  }
  public void removeStereotype(Stereotype x)
  throws PropertyVetoException {
    fireVetoableChange("stereotype", _stereotype, x);
    _stereotype.removeElement(x);
  }
  public boolean containsStereotype(Stereotype x) {
    if (_stereotype == null) return false;
    return _stereotype.contains(x);
  }
  
  public Vector getBehavior() { return _behavior; }
  public void setBehavior(Vector x)
  throws PropertyVetoException {
    fireVetoableChange("behavior", _behavior, x);
    _behavior = x;
  }
  public void addBehavior(StateMachine x)
  throws PropertyVetoException {
    fireVetoableChange("behavior", _behavior, x);
    if (_behavior == null) _behavior = new Vector();
    _behavior.addElement(x);
  }
  public void removeBehavior(StateMachine x)
  throws PropertyVetoException {
    fireVetoableChange("behavior", _behavior, x);
    _behavior.removeElement(x);
  }

  ////////////////////////////////////////////////////////////////
  // debugging

  public String dbgStereotypes() {
    String s = "";
    java.util.Enumeration enum;
    Vector v;
    if ((v = getStereotype()) != null) {
      enum = v.elements();
      while (enum.hasMoreElements())
	s += ((Stereotype)enum.nextElement()).dbgString();
    }
    return s;
  }

  public String dbgTaggedValues() {
    String s = "";
    java.util.Enumeration enum;
    Vector v;
    boolean first = true;
    if ((v = getTaggedValue()) != null) {
      enum = v.elements();
      while (enum.hasMoreElements()) {
	if (!first) s += ", ";
	s += ((TaggedValue)enum.nextElement()).dbgString();
	first = false;
      }
    }
    return s;
  }

}
