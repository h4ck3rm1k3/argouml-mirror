// Copyright (c) 1995, 1996 Regents of the University of California.
// All rights reserved.
//
// This software was developed by the Arcadia project
// at the University of California, Irvine.
//
// Redistribution and use in source and binary forms are permitted
// provided that the above copyright notice and this paragraph are
// duplicated in all such forms and that any documentation,
// advertising materials, and other materials related to such
// distribution and use acknowledge that the software was developed
// by the University of California, Irvine.  The name of the
// University may not be used to endorse or promote products derived
// from this software without specific prior written permission.
// THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR
// IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
// WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.

// File: DesignMaterial.java
// Classes: DesignMaterial
// Original Author: jrobbins@ics.uci.edu
// $Id$

package uci.argo.kernel;

import java.util.*;
import uci.ui.*;
import uci.util.*;

/** Abstract class to represent the materials being used in
 *  design. The nature of these materials is domain-dependent.  For
 *  example, in the domain of software architecture the design
 *  materials are software components and connectors. There is not much
 *  that can be said about generic design materials at this level of
 *  abstraction. But the Argo kernel provides for all DesignMaterials
 *  to (1) be Observable, (2) have Properties (in addition
 *  to instance variables supplied in subclasses) that can be shown in
 *  a property sheet, (3) ask Agency to critique them, (4) keep a
 *  list of the ToDoItem's that are generated from that critiquing,
 *  (5) be notified when the user selects them in an editor, (6)
 *  highlight themselves to draw the designer's attention. <p>
 *
 *  Needs-More-Work: should I implement virtual copying? or
 *  instance-based inheritiance of properties? How are properties
 *  shared (it at all)?
 *
 *  For examples of subclasing see the package jargo.softarch.  For
 *  instructions on how to define subclasses for DesignMaterials in
 *  your domain, see the Argo cookbook entry for <A
 *  HREF="../cookbook.html#define_design_material">define_design_material</a>
 *  <p>
 *
 * @see jargo.softarch.C2BrickDM
 * @see jargo.softarch.C2CompDM
 * @see jargo.softarch.C2ConnDM
 * @see Design
 * @see jargo.ui.UiPropertyBrowser */

public abstract class DesignMaterial extends Observable
implements java.io.Serializable {

  ////////////////////////////////////////////////////////////////
  // constants

  public static final String HIGHLIGHT = "Highlight";
  public static final String UNHIGHLIGHT = "Unhighlight";

  ////////////////////////////////////////////////////////////////
  // instance variables

  /** Observers of this object that should be saved and loaded with
   *  this object. */
  private Vector _persistObservers = null;

  /** ToDoList items that are on the designers ToDoList because
   *  of this material. */
  private ToDoList _pendingItems = new ToDoList();

  /** Other DesignMaterial's that contain this one. */
  private Vector _parents = new Vector();

  /** "Soft" Properties that this DesignMaterial may have, but we do
   *  not want to allocate an instance variable to them. */
  public Hashtable _props = new Hashtable();

  ////////////////////////////////////////////////////////////////
  // constructors

  /** Construct a new DesignMaterial with empty Properties. */
  public DesignMaterial() {  }

  /** Construct a new DesignMaterial with the given Properties. */
  public DesignMaterial(Hashtable ht) {
    // Needs-More-Work: construct a new Hashtable that "inherits"
  }

  ////////////////////////////////////////////////////////////////
  // accessors

  /** Make the given Design be the parent of this DesignMaterial. */
  void addParent(Design d) {
    _parents.removeElement(d); /* behave like a set */
    _parents.addElement(d);
  }

  /** Remove the given parent. For example, this could be called when
   * a DesignMaterial is removed from a Design. */
  void removeParent(Design d) { _parents.removeElement(d); }

  /** Enumerate over all my parents. For now most DesignMaterial's
   * have just one parent, but I think this might be useful in the
   * future. */
  public Enumeration parents() { return _parents.elements(); }

  /** Reply the value of one property, if not found reply
  * defaultValue. Even if subclasses store some properties in instance
  * variables, they should implement this method to give acces to
  * those instance variables by name. */
  public Object getProperty(String key, Object defaultValue) {
    return get(key, defaultValue);
  }

  /** Reply the value of one property, if not found reply null. */
  public Object getProperty(String k) { return get(k); }

  public Object get(String k) { return _props.get(k); }

  public Object get(String key, Object defaultValue) {
    Object res = get(key);
    if (res == null) res = defaultValue;
    return res;
  }

  /** Reply the value an int property, if not found reply 0. */
  public int getIntProperty(String k) {
    return getIntProperty(k, 0);
  }

  /** Reply the value an int property, if not found reply defaultValue. */
  public int getIntProperty(String k, int defaultValue) {
    Object o = getProperty(k);
    if (o instanceof Integer) return ((Integer)o).intValue();
    return defaultValue;
  }


  /** Reply the value an boolean property, if not found reply false. */
  public boolean getBoolProperty(String k) {
    return getBoolProperty(k, false);
  }

  /** Reply the value an int property, if not found reply defaultValue. */
  public boolean getBoolProperty(String k, boolean defaultValue) {
    Object o = getProperty(k);
    if (o instanceof Boolean) return ((Boolean)o).booleanValue();
    return defaultValue;
  }

  /** Set the value of a property. */
  public boolean put(String k, Object v) {
    if (v == null || k == null) return false;
    _props.put(k, v);
    changedProperty(k);
    return true;
  }

  public void put(Hashtable ht) {
    Enumeration keys = ht.keys();
    while (keys.hasMoreElements()) {
      String k = (String) keys.nextElement();
      Object v = ht.get(k);
      put(k, v);
    }
  }

  /** Convenient function for setting the value of boolean
   * properties. */
  public boolean put(String k, boolean v) {
    return put(k, v ? Boolean.TRUE : Boolean.FALSE);
  }

  /** Convenient function for setting the value of boolean
   * properties. */
  public boolean put(String k, int v) { return put(k, new Integer(v)); }

  public void setProperty(String k, Object v) {
    if (getProperty(k) != null) put(k, v);
  }

  public void setProperty(String k, int v) {
    if (getProperty(k) != null) put(k, new Integer(v));
  }

  public void setProperty(String k, boolean v) {
    if (getProperty(k) != null) put(k, new Boolean(v));
  }

  public boolean define(String k, Object v) {
    if (getProperty(k) == null) return put(k, v);
    return false;
  }

  public boolean canPut(String key) { return true; }
  public Enumeration keysIn(String cat) {
    if (cat.equals("Model")) return _props.keys();
    else return EnumerationEmpty.theInstance();
  }

  /** Set the value of a property iff it is not already set. */
  public void define(String k, boolean v) {
    if (getProperty(k) == null) put(k, v);
  }

  /** Set the value of a property iff it is not already set. */
  public void define(String k, int v) {
    if (getProperty(k) == null) put(k, v);
  }

  /** Make the given property undefined. */
  public Object removeProperty(String k) { return _props.remove(k); }

  /** Convenient function for getting the value of boolean
   * properties. */
  public boolean getBoolean(String k) {
    return getProperty(k, "false").equals("true");
  }

  ////////////////////////////////////////////////////////////////
  // critiquing

  /** Critique this DesignMaterial and post ToDoItem's to the
   * Designer's list. By default this is done by asking Agency. */
  public void critique(Designer dsgr) { Agency.applyAllCritics(this, dsgr); }

  /** Remove all the ToDoItem's generated by this DesignMaterial from
   * the ToDoList of the given Designer. For example, this could be
   * called when the DesignMaterial is deleted from the design
   * document. */
  public void removePendingItems(Designer dsgr) {
    dsgr.removeToDoItems(_pendingItems);
    _pendingItems.removeAllElements();
  }

  /** Remove this DesignMaterial from the design document and free up
   * memory and other resources as much as possible. */
  public void dispose() {
    Enumeration pars = parents();
    while (pars.hasMoreElements()) {
      Design d = (Design)pars.nextElement();
      d.removeElement(this);
    }
    removePendingItems(Designer.theDesigner());
  }

  ////////////////////////////////////////////////////////////////
  // user interface

  /** When a critic produces a ToDoItem, both the Designer and the
   * "offending" DesignMaterial's are notified. By default the
   * ToDoItem is added to the list of generated ToDoItem's. Subclasses
   * may, for example, visually change their appearance to indicate
   * the presence of an error. One paper called this 'clarifiers'. <p>
   *
   * Needs-More-Work: do I need a Clarifier object in the framework? */
  public void inform(ToDoItem item) { _pendingItems.addElement(item); }

  /** This DesignMaterial has been selected in some editor.
   * @see jargo.ui.UiToDoList */
  public void select() { highlight(); }

  /** This DesignMaterial has been deselected in some editor.
   * @see jargo.ui.UiToDoList */
  public void deselect() { unhighlight(); }

  /** Draw the Designer's attention to this DesignMaterial in all
   * views. */
  public void highlight() {
    setChanged();
    notifyObservers(HIGHLIGHT);
  }
  public void highlight(ToDoItem item) { highlight(); }

  /** Stop drawing the Designer's attention to this DesignMaterial. */
  public void unhighlight() {
    setChanged();
    notifyObservers(UNHIGHLIGHT);
  }

  public void unhighlight(ToDoItem item) { unhighlight(); }


  ////////////////////////////////////////////////////////////////
  // notifications and updates

  public void changedProperty(String key) {
    setChanged();
    notifyObservers(key);
  }

  public void addPersistantObserver(Observer o) {
    if (_persistObservers == null) _persistObservers = new Vector();
    _persistObservers.removeElement(o);
    _persistObservers.addElement(o);
  }

  public void removePersistObserver(Observer o) {
    _persistObservers.removeElement(o);
  }

  public void notifyPersistantObservers(Object arg) {
    if (_persistObservers == null) return;
    Enumeration eachObs = _persistObservers.elements();
    while (eachObs.hasMoreElements()) {
      Observer obs = (Observer) eachObs.nextElement();
      obs.update(this, arg);
    }
  }

  public void notifyObservers(Object arg) {
    super.notifyObservers(arg);
    notifyPersistantObservers(arg);
  }

} /* end class DesignMaterial */
