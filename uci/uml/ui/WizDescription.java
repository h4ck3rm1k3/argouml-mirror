// Copyright (c) 1996-98 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation for educational, research and non-profit
// purposes, without fee, and without a written agreement is hereby granted,
// provided that the above copyright notice and this paragraph appear in all
// copies. Permission to incorporate this software into commercial products
// must be negotiated with University of California. This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "as is",
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




package uci.uml.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.sun.java.swing.*;
import com.sun.java.swing.event.*;
import com.sun.java.swing.tree.*;

import uci.ui.ToolBar;
import uci.util.*;
import uci.argo.kernel.*;


public class WizDescription extends WizStep {

  ////////////////////////////////////////////////////////////////
  // instance variables

  JTextArea _description = new JTextArea();


  public WizDescription() {
    super();
    System.out.println("making WizDescription");

    _description.setLineWrap(true);
    _description.setWrapStyleWord(true);

    _mainPanel.setLayout(new BorderLayout());
    _mainPanel.add(new JScrollPane(_description), BorderLayout.CENTER);
  }

  public void setTarget(Object item) {
    super.setTarget(item);
    if (_target == null) {
      _description.setText("No ToDoItem selected");
    }
    else if (_target instanceof ToDoItem) {
      ToDoItem tdi = (ToDoItem) _target;
      _description.setEnabled(true);
      _description.setText(tdi.getDescription());
      _description.setCaretPosition(0);
    }
    else {
      _description.setText("needs-more-work");
      return;
    }
  }



} /* end class WizDescription */
