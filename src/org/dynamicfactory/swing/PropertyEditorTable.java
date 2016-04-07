package org.dynamicfactory.swing;

import org.dynamicfactory.swing.classEditors.EditorFactory;
import org.dynamicfactory.swing.classEditors.RendererFactory;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class PropertyEditorTable extends JTable{

    PropertyEditorTableModel model;

    public PropertyEditorTable(PropertyEditorTableModel data){
        model = data;
    }

    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        return RendererFactory.getInstance().create(columnClass.getName());
    }

    @Override
    public TableCellEditor getDefaultEditor(Class<?> columnClass) {
        return EditorFactory.getInstance().create(columnClass.getName());
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
    }

    @Override
    public void addColumn(TableColumn aColumn) {
        super.addColumn(aColumn);
    }

    @Override
    public void removeColumn(TableColumn aColumn) {
        super.removeColumn(aColumn);
    }

    @Override
    public void moveColumn(int column, int targetColumn) {
        super.moveColumn(column, targetColumn);
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        return super.getCellEditor(row, column);
    }
}
