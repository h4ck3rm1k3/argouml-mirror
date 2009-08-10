// $Id: FigInclude.java 17045 2009-04-05 16:52:52Z mvw $
// Copyright (c) 1996-2009 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.diagram.uml2;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.argouml.uml.diagram.DiagramSettings;
import org.argouml.uml.diagram.ui.FigEdgeModelElement;
import org.argouml.uml.diagram.ui.FigSingleLineText;
import org.argouml.uml.diagram.ui.PathItemPlacement;
import org.tigris.gef.presentation.ArrowHeadGreater;
import org.tigris.gef.presentation.Fig;


/**
 * A fig for use with include relationships on use case diagrams.<p>
 *
 * Realised as a dotted line with an open arrow head and the label
 * <<include>> alongside a la stereotype.<p>
 *
 * @author Jeremy Bennett
 */
public class FigInclude2 extends FigEdgeModelElement {

    /**
     * Serial version - generated by Eclipse for rev. 1.14
     */
    private static final long serialVersionUID = 6199364390483239154L;

    /*
     * Text fig to hold the <<include>> label
     */
    private FigSingleLineText label;
    
    private ArrowHeadGreater endArrow = new ArrowHeadGreater();

    private void initialize() {
        // The <<include>> label.
        // It's not a true stereotype, so don't use the stereotype support
        label.setTextColor(TEXT_COLOR);
        label.setTextFilled(false);
        label.setFilled(false);
        label.setLineWidth(0);
        label.setEditable(false);
        label.setText("<<include>>");
        
        addPathItem(label, new PathItemPlacement(this, label, 50, 10));
        
        // Make the line dashed

        setDashed(true);

        // Add an arrow with an open arrow head

        setDestArrowHead(endArrow);

        // Make the edge go between nearest points

        setBetweenNearestPoints(true);    
    }

    /**
     * Construct a fig owned by the given UML element with the provided render
     * settings.
     * @param edge owning UML element
     * @param settings rendering settings
     */
    public FigInclude2(Object edge, DiagramSettings settings) {
        super(edge, settings);
        label = new FigSingleLineText(edge, new Rectangle(X0, Y0 + 20, 90, 20), 
                settings, false);
        initialize();
    }
    
    /**
     * <p>Set a new fig to represent this edge.</p>
     *
     * <p>We invoke the superclass accessor. Then change aspects of the
     *   new fig that are not as we want. In this case to use dashed lines.</p>
     *
     * @param f  The fig to use.
     */
    @Override
    public void setFig(Fig f) {
        super.setFig(f);
        setDashed(true);
    }

    /**
     * <p>Define whether the given fig can be edited (it can't).</p>
     *
     * @param f  The fig about which the enquiry is being made. Ignored in this
     *           implementation.
     *
     * @return   <code>false</code> under all circumstances.
     */
    @Override
    protected boolean canEdit(Fig f) {
        return false;
    }

    /*
     * @see org.tigris.gef.presentation.Fig#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
        endArrow.setLineColor(getLineColor());
        super.paint(g);
    }
}

