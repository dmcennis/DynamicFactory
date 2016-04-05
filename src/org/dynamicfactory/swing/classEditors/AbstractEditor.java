package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Created by dmcennis on 4/4/2016.
 */
public abstract class AbstractEditor extends DefaultTableCellRenderer implements Editor {
    protected ParameterInternal param;

    protected Parameter nonEditable;

    boolean editable;

    protected void install(Properties p){
        if((p!=null)&&(p.get().size()>0)){

        }
    }

    @Override
    public JToolTip createToolTip() {
        return super.createToolTip();
    }

    @Override
    public JPopupMenu getComponentPopupMenu() {
        return super.getComponentPopupMenu();
    }
}
