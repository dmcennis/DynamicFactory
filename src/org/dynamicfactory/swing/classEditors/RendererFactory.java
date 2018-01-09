package org.dynamicfactory.swing.classEditors;

import org.dynamicfactory.AbstractFactory;

/**
 * Created by dmcennis on 4/4/2016.
 */
public class RendererFactory extends AbstractFactory<Renderer>{
    private static RendererFactory ourInstance = new RendererFactory();

    public static RendererFactory getInstance() {
        return ourInstance;
    }

    private RendererFactory() {
//        map.put("NumericRenderer",new IntegerRenderer());
        map.put("StringRenderer",new StringRenderer());
        map.put("IntegerRenderer", new IntegerRenderer());
        map.put("FloatingRenderer",new FloatingRenderer());
    }

    public RendererFactory prototype(){
        return getInstance();
    }
}
