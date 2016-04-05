package org.dynamicfactory.swing;

import org.dynamicfactory.descriptors.*;
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
        super.addRow(rowData);
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
        model.add(parameter);
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
        model.set(type, c, value, description, longDescription);
    }

    public boolean quickCheck(String s, Class type) {
        return model.quickCheck(s, type);
    }

    public boolean check(Properties props) {
        return model.check(props);
    }

    public void set(String type, Class c, List value, String description) {
        model.set(type, c, value, description);
    }

    public void add(String name, Class type, Object value) {
        model.add(name, type, value);
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
        model.set(value);
    }

    public void add(String type, Object value) {
        model.add(type, value);
    }

    public Object quickGet(String s) {
        return model.quickGet(s);
    }

    public void set(ParameterInternal parameter) {
        model.set(parameter);
    }

    public void set(String type, Class c, Object value) {
        model.set(type, c, value);
    }

    public void set(String type, Class c, Object value, String description) {
        model.set(type, c, value, description);
    }

    public void set(String type, Class c, List value) {
        model.set(type, c, value);
    }

    public void set(String type, Object value) {
        model.set(type, value);
    }

    public void set(String type, List value) {
        model.set(type, value);
    }

    public ParameterInternal get(String string) {
        return model.get(string);
    }

    public PropertiesInternal merge(Properties right) {
        return model.merge(right);
    }

    public void set(String type, List value, String description) {
        model.set(type, value, description);
    }

    public void replace(Parameter type) {
        model.replace(type);
    }

    public void set(String type, List value, String description, String longDescription) {
        model.set(type, value, description, longDescription);
    }

    public boolean checkQuick(String s, Class type) {
        return model.checkQuick(s, type);
    }

    public void add(String type, Class c, List value) {
        model.add(type, c, value);
    }

    public void set(String type, Class c, Object value, String description, String longDescription) {
        model.set(type, c, value, description, longDescription);
    }

    public PropertiesInternal mergeDefaults(Properties right) {
        return model.mergeDefaults(right);
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
        model.add(type, value);
    }

    public boolean check(Parameter type) {
        return model.check(type);
    }
}
