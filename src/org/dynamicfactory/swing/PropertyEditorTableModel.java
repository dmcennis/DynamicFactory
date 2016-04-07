package org.dynamicfactory.swing;

import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.property.InvalidObjectTypeException;
import org.dynamicfactory.property.Property;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class PropertyEditorTableModel extends DefaultTableModel{
    private PropertiesInternal model;

    public PropertyEditorTableModel(PropertiesInternal props){
        model = props;
    }

    @Override
    public Vector getDataVector() {
        return super.getDataVector();
    }

    @Override
    public void setDataVector(Vector dataVector, Vector columnIdentifiers) {
        super.setDataVector(dataVector, columnIdentifiers);
    }

    @Override
    public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers) {
        super.setDataVector(dataVector, columnIdentifiers);
    }

    @Override
    public void addRow(Vector rowData) {
        try {
            if ((rowData != null) && (rowData.size() == 2) && (rowData.get(0) instanceof String)) {
                if ((model.quickCheck((String) rowData.get(0), rowData.get(1).getClass()))) {
                    model.add((String) rowData.get(0), rowData.get(1));
                }
                model.set((String) rowData.get(0), rowData.get(1).getClass(), rowData.get(1));
            }
        }catch (InvalidObjectTypeException e){
            // showErrorDialog
        }
    }

    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
    }

    @Override
    public void insertRow(int row, Vector rowData) {
        super.insertRow(row, rowData);
    }

    @Override
    public void insertRow(int row, Object[] rowData) {
        super.insertRow(row, rowData);
    }

    @Override
    public int getRowCount() {
        return super.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return super.getColumnCount();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return super.isCellEditable(row, column);
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return super.getValueAt(row, column);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
    }

    @Override
    public int findColumn(String columnName) {
        return super.findColumn(columnName);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    public void add(ParameterInternal parameter) {
        try{
            model.add(parameter);
        }catch(InvalidObjectTypeException e){
            // show error dialog
        }
    }

    public PropertiesInternal prototype(Properties props) {
        return model.prototype(props);
    }

    public void remove(String type) {
        model.remove(type);
    }

    public PropertiesInternal prototype() {
        return model.prototype();
    }

    public void set(String type, Class c, List value, String description, String longDescription) {
        try {
            model.set(type, c, value, description, longDescription);
        } catch (InvalidObjectTypeException e) {
            //show error dialog
        }
    }

    public boolean quickCheck(String s, Class type) {
        return model.quickCheck(s, type);
    }

    public boolean check(Properties props) {
        return model.check(props);
    }

    public void set(String type, Class c, List value, String description) {
        try {
            model.set(type, c, value, description);
        } catch (InvalidObjectTypeException e) {
            // showerrordialog
        }
    }

    public void add(String name, Class type, Object value) {
        try {
            model.add(name, type, value);
        } catch (InvalidObjectTypeException e) {
            //showerrordialog
        }
    }

    public SyntaxObject getDefaultRestriction() {
        return model.getDefaultRestriction();
    }

    public void setDefaultRestriction(SyntaxObject restriction) {
        model.setDefaultRestriction(restriction);
    }

    public List<Parameter> get() {
        return model.get();
    }

    public void set(Property value) {
        try {
            model.set(value);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public void add(String type, Object value) {
        try {
            model.add(type, value);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public Object quickGet(String s) {
        return model.quickGet(s);
    }

    public void set(ParameterInternal parameter) {
        try {
            model.set(parameter);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public void set(String type, Class c, Object value) {
        try {
            model.set(type, c, value);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public void set(String type, Class c, Object value, String description) {
        try {
            model.set(type, c, value, description);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public void set(String type, Class c, List value) {
        try {
            model.set(type, c, value);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public void set(String type, Object value) {
        try {
            model.set(type, value);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public void set(String type, List value) {
        try {
            model.set(type, value);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public ParameterInternal get(String string) {
        return model.get(string);
    }

    public PropertiesInternal merge(Properties right) {
        return model.merge(right);
    }

    public void set(String type, List value, String description) {
        try {
            model.set(type, value, description);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public void replace(Parameter type) {
        model.replace(type);
    }

    public void set(String type, List value, String description, String longDescription) {
        try {
            model.set(type, value, description, longDescription);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public boolean checkQuick(String s, Class type) {
        return model.checkQuick(s, type);
    }

    public void add(String type, Class c, List value) {
        try {
            model.add(type, c, value);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public void set(String type, Class c, Object value, String description, String longDescription) {
        try {
            model.set(type, c, value, description, longDescription);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public PropertiesInternal mergeDefaults(Properties right) {
        try {
            return model.mergeDefaults(right);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
            return model;
        }
    }

    public Object getQuick(String s) {
        return model.getQuick(s);
    }

    public int compareTo(Properties o) {
        return model.compareTo(o);
    }

    public void clear() {
        model.clear();
    }

    public void add(String type, List value) {
        try {
            model.add(type, value);
        } catch (InvalidObjectTypeException e) {
            // showErrorDialog
        }
    }

    public boolean check(Parameter type) {
        return model.check(type);
    }
}
