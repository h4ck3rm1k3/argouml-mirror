// Copyright (c) 1996-98 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation for educational, research and non-profit
// purposes, without fee, and without a written agreement is hereby granted,
// provided that the above copyright notice and this paragraph appear in all
// copies. Permission to incorporate this software into commercial products may
// be obtained by contacting the University of California. David F. Redmiles
// Department of Information and Computer Science (ICS) University of
// California Irvine, California 92697-3425 Phone: 714-824-3823. This software
// program and documentation are copyrighted by The Regents of the University
// of California. The software program and documentation are supplied "as is",
// without any accompanying services from The Regents. The Regents do not
// warrant that the operation of the program will be uninterrupted or
// error-free. The end-user understands that the program was developed for
// research purposes and is advised not to rely exclusively on the program for
// any reason. IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY
// PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES,
// INCLUDING LOST PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS
// DOCUMENTATION, EVEN IF THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY
// DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE
// SOFTWARE PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
// ENHANCEMENTS, OR MODIFICATIONS.

// File: UMLClassDiagram.java
// Classes: UMLClassDiagram
// Original Author: jrobbins@ics.uci.edy
// $Id$


package uci.uml.visual;

import java.util.*;
import java.awt.*;
import com.sun.java.swing.*;

import uci.gef.*;
import uci.graph.*;
import uci.ui.*;
import uci.uml.ui.*;
import uci.uml.Model_Management.*;
import uci.uml.Foundation.Core.*;


public class UMLClassDiagram extends UMLDiagram {

  ////////////////
  // actions for toolbar
  // needs-more-work: should these be static?


  protected static Action _actionSelect =
  new CmdSetMode(ModeSelect.class, "Select");

  protected static Action _actionClass = 
  new CmdCreateNode(MMClass.class, "Class");

  protected static Action _actionInterface =
  new CmdCreateNode(Interface.class, "Interface");

  protected static Action _actionAssoc =
  new CmdSetMode(ModeCreateEdge.class,
		 "edgeClass", Association.class,
		 "Association");

  protected static Action _actionGeneralize =
  new CmdSetMode(ModeCreateEdge.class,
		 "edgeClass", Generalization.class,
		 "Generalization");

  protected static Action _actionRectangle =
  new CmdSetMode(ModeCreateFigRect.class, "Rectangle");

  protected static Action _actionRRectangle =
  new CmdSetMode(ModeCreateFigRRect.class, "RRect");

  protected static Action _actionCircle =
  new CmdSetMode(ModeCreateFigCircle.class, "Circle");

  protected static Action _actionLine =
  new CmdSetMode(ModeCreateFigLine.class, "Line");

  protected static Action _actionText =
  new CmdSetMode(ModeCreateFigText.class, "Text");

  protected static Action _actionPoly =
  new CmdSetMode(ModeCreateFigPoly.class, "Polygon");

  protected static Action _actionInk =
  new CmdSetMode(ModeCreateFigInk.class, "Ink");


  ////////////////////////////////////////////////////////////////
  // contructors

  public UMLClassDiagram(Model m) {
    super(m);
    ClassDiagramGraphModel gm = new ClassDiagramGraphModel();
    gm.setModel(m);
    setGraphModel(gm);
    LayerPerspective lay = new LayerPerspective(m.getName().getBody(), gm);
    setLayer(lay);
    ClassDiagramRenderer rend = new ClassDiagramRenderer(); // singleton
    lay.setGraphNodeRenderer(rend);
    lay.setGraphEdgeRenderer(rend);
  }


  /** initialize the toolbar for this diagram type */
  protected void initToolBar() {
    _toolBar = new ToolBar();
    _toolBar.add(Actions.Cut);
    _toolBar.add(Actions.Copy);
    _toolBar.add(Actions.Paste);
    _toolBar.addSeparator();

    _toolBar.add(_actionSelect);
    _toolBar.addSeparator();

    _toolBar.add(_actionClass);
    _toolBar.add(_actionAssoc);
    _toolBar.add(_actionGeneralize);
    _toolBar.addSeparator();

    _toolBar.add(_actionRectangle);
    _toolBar.add(_actionRRectangle);
    _toolBar.add(_actionCircle);
    _toolBar.add(_actionLine);
    _toolBar.add(_actionText);
    _toolBar.add(_actionPoly);
    _toolBar.add(_actionInk);
  }
  
} /* end class UMLClassDiagram */
