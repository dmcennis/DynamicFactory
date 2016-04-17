package org.dynamicfactory.swing;

import org.dynamicfactory.descriptors.ParameterFactory;
import org.dynamicfactory.descriptors.ParameterInternal;
import org.dynamicfactory.swing.classEditors.*;
import org.dynamicfactory.swing.classEditors.Renderer;


import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class PropertyEditorTable extends JTable {

    PropertyEditorTableModel model;

    Vector<Renderer> rendererList = new Vector<Renderer>();

    Vector<Vector<Editor>> editorList = new Vector<Vector<Editor>>();

    public PropertyEditorTable(PropertyEditorTableModel data){
        model = data;
        for(ParameterInternal param : model.get()){
            Renderer r = RendererFactory.getInstance().create(param.getParameterClass().getSimpleName()+"Renderer");
            rendererList.add(r);
            Vector<Editor> valueList = new Vector<>();
            for(Object o : param.getValue()) {
                Editor e = EditorFactory.getInstance().create(param.getParameterClass().getSimpleName() + "Editor");
                valueList.add(e);
            }
        }
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
        if(column==0){
            return false;
        }else{
            return model.isCellEditable(row,column);
        }
    }

    @Override
    public void addColumn(TableColumn aColumn) {
        ;
    }

    @Override
    public void removeColumn(TableColumn aColumn) {
        ;
    }

    @Override
    public void moveColumn(int column, int targetColumn) {
        ;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        if(column==0){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: Attempting to edit parameter names that are not supposed to be directly editable");
        }else{
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"DEVELOPER: The values column can only be edited by modifying the internal JTable of the rendered component. The table must be non-editable as a consequence.");
        }
        return new StringEditor(model, ParameterFactory.newInstance().create("ERROR",String.class),0);
    }
}
