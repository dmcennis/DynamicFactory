package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.SyntaxCheckerFactory;
import org.dynamicfactory.descriptors.SyntaxObject;
import org.dynamicfactory.propertyQuery.StringQuery;

/**
 * Created by dmcennis on 4/13/2016.
 */
public class FloatingEditor {
    static final SyntaxObject isFloatingPoint = SyntaxCheckerFactory.newInstance().create(1,1,(new StringQuery()).buildQuery("[0-9]+(\\.[0-9]+)?(e[0-9]+(\\.[0-9]+)?))?",false, StringQuery.Operation.MATCHES),String.class);

}
