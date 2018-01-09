package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.Creatable;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * Created by dmcennis on 4/7/2016.
 */
public interface Renderer extends TableCellRenderer, Creatable<Renderer> {

}
