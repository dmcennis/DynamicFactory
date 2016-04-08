package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Parameter;
import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by dmcennis on 4/4/2016.
 */
public abstract class AbstractRenderer extends DefaultTableCellRenderer implements Renderer {
    protected ParameterInternal param;

    JTable container=null;

    boolean editable;

    protected void install(Properties p){
        if((p!=null)&&(p.get().size()>0)){

        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(param.getValue().size()>1) {
            if ((container == null) || (container.getRowCount() != param.getValue().size())) {
                container = new JTable();
                for (int i = 0; i < param.getValue().size(); ++i) {
                    container.add(getRenderer(i));
                }
                return container;
            } else {
                for (int i = 0; i < param.getValue().size(); ++i) {
                    container.setValueAt(getRenderer(i), i, 0);
                }
                return container;
            }
        }else if(param.getValue().size()==1){
            return getRenderer(0);
        }
        return new JTextField("Empty Parameter");
    }

    protected abstract Component getRenderer(int index);

    @Override
    public JToolTip createToolTip() {
        return super.createToolTip();
    }

    @Override
    public JPopupMenu getComponentPopupMenu() {
        return super.getComponentPopupMenu();
    }


}
