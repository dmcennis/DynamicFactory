package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.propertyQuery.StringQuery;
import org.dynamicfactory.swing.PropertyEditorTableModel;

/**
 * Created by dmcennis on 4/13/2016.
 */
public class FloatingEditor extends TextFieldEditorObject<Double>{
    static final SyntaxObject isFloatingPoint = SyntaxCheckerFactory.newInstance().create(1,1,(new StringQuery()).buildQuery("\\-?[0-9]+(\\.[0-9]+)?(e\\-?[0-9]+(\\.[0-9]+)?))?",false, StringQuery.Operation.MATCHES),String.class);

    public FloatingEditor(PropertyEditorTableModel m, Parameter p, int i) {
        super(m, p, i);
    }

    public FloatingEditor(PropertyEditorTableModel m, ParameterInternal p, int i) {
        super(m, p, i);
    }

    public FloatingEditor(PropertyEditorTableModel m, Properties p, int i) {
        super(m, p, i);
    }

    public FloatingEditor() {
        super();
    }

    @Override
    protected Double parse(String object) {
        return Double.parseDouble(object);
    }

    @Override
    protected boolean parsingCheck(String object) {
        return isFloatingPoint.check(param.getType(),object);
    }

    @Override
    public Editor prototype(PropertyEditorTableModel ref, ParameterInternal param, int index, Properties props) {
        return new FloatingEditor(ref,param,index);
    }
}
