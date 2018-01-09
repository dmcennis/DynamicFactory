package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.propertyQuery.StringQuery;
import org.dynamicfactory.swing.PropertyEditorTableModel;

import java.util.logging.Logger;

/**
 * Created by dmcennis on 4/13/2016.
 */
public class IntegerEditor extends TextFieldEditorObject<Integer> {
    static final SyntaxObject isInteger = SyntaxCheckerFactory.newInstance().create(1,1,(new StringQuery()).buildQuery("\\-?[0-9]+",false, StringQuery.Operation.MATCHES),String.class);

    public IntegerEditor(PropertyEditorTableModel m, Parameter p, int i) {
        super(m, p, i);
    }

    public IntegerEditor(PropertyEditorTableModel m, ParameterInternal p, int i) {
        super(m, p, i);
    }

    public IntegerEditor(PropertyEditorTableModel m, Properties p, int i) {
        super(m, p, i);
    }

    public IntegerEditor() {
        super();
    }

    @Override
    protected Integer parse(String object) {
        return Integer.parseInt(object);
    }

    @Override
    protected boolean parsingCheck(String object) {
        if(!isInteger.check(param.getType(),object)){
            return false;
        }
        return true;
    }

    @Override
    public Editor prototype(Properties props) {
        return new IntegerEditor(getModel(),param,index);
    }

    @Override
    public Editor prototype(PropertyEditorTableModel model, ParameterInternal parameter, int index, Properties propertiesStringEditor) {
        return new IntegerEditor(model,parameter,index);
    }
}
