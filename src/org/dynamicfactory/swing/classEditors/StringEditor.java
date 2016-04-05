package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class StringEditor extends DefaultTableCellRenderer implements Editor{

    ParameterInternal param;

    boolean editable = true;
    public StringEditor(){
        param = ParameterFactory.newInstance().create("None",String.class);
    }

    public StringEditor(ParameterInternal p){
        param = p;
    }

    public StringEditor(Parameter p){
        param = (ParameterInternal)p;
        editable = false;
    }

    @Override
    public StringEditor prototype() {
        return new StringEditor();
    }

    @Override
    public StringEditor prototype(Properties props) {
        if((props!=null)&&(props.get().size()>0)){
            return new StringEditor(props.get().get(0));
        }
        return new StringEditor(props.get().get(0));
    }

}
