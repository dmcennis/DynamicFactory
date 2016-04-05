package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.descriptors.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class NumericEditor extends DefaultTableCellRenderer implements Editor{
    @Override
    public Editor prototype() {
        return null;
    }

    @Override
    public Editor prototype(Properties props) {
        return null;
    }
}
