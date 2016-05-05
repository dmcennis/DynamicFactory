package org.dynamicfactory.descriptors;

import org.dynamicfactory.swing.PropertyEditorTable;
import org.dynamicfactory.swing.PropertyEditorTableModel;
import org.dynamicfactory.swing.classEditors.Renderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dmcennis on 5/5/2016.
 */
public class PropertiesRenderer implements Renderer {
    PropertyEditorTable table;

    PropertyEditorTableModel model;

    public PropertiesRenderer(PropertyEditorTableModel parent, PropertiesInternal data){
        model = new PropertyEditorTableModel(data);
        table = new PropertyEditorTable(model);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return table;
    }

    @Override
    public Renderer prototype() {
        return new PropertiesRenderer(new PropertyEditorTableModel(PropertiesFactory.newInstance().create()),PropertiesFactory.newInstance().create());
    }

    @Override
    public Renderer prototype(Properties props) {
        return new PropertiesRenderer(new PropertyEditorTableModel(PropertiesFactory.newInstance().create(props)),PropertiesFactory.newInstance().create(props));
    }
}
