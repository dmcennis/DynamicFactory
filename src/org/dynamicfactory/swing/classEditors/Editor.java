package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.Creatable;
import org.dynamicfactory.descriptors.Properties;

import javax.swing.table.TableCellRenderer;

/**
 * Created by dmcennis on 4/4/2016.
 */
public interface Editor extends TableCellRenderer, Creatable<Editor> {
    @Override
    Editor prototype();

    @Override
    Editor prototype(Properties props);
}
