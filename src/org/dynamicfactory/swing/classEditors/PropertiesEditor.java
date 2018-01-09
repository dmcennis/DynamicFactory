package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.descriptors.PropertiesFactory;
import org.dynamicfactory.swing.PropertyEditorTable;
import org.dynamicfactory.swing.PropertyEditorTableModel;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import java.awt.*;
import java.util.EventObject;

/**
 * Created by dmcennis on 5/5/2016.
 */
public class PropertiesEditor implements Editor{

    PropertyEditorTable editor;

    boolean isEditable = false;

    public PropertiesEditor(PropertyEditorTable editor){
        this.editor = editor;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return editor.getCellEditor(row,column).getTableCellEditorComponent(table,value,isSelected,row,column);
    }

    @Override
    public Object getCellEditorValue() {
        return editor.getModel();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean stopCellEditing() {
        return false;
    }

    @Override
    public void cancelCellEditing() {

    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        //editor.addVetoableChangeListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
//        editor.removeVetoableChangeListener(l);
    }

    @Override
    public Editor prototype() {
        return new PropertiesEditor(new PropertyEditorTable(new PropertyEditorTableModel(PropertiesFactory.newInstance().create())));
    }

    @Override
    public Editor prototype(Properties props) {
        return new PropertiesEditor(new PropertyEditorTable(new PropertyEditorTableModel(PropertiesFactory.newInstance().create(props))));
    }

    @Override
    public Editor prototype(PropertyEditorTableModel model, ParameterInternal parameter, int index, Properties propertiesStringEditor) {
        return new PropertiesEditor(new PropertyEditorTable(model));
    }
}
