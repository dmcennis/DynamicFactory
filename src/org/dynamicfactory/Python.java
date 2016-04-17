package org.dynamicfactory;

/**
 * Created by dmcennis on 4/17/2016.
 */
public class Python {
    static public String join(Object[] array,String join){
        if((array==null)||(array.length==0)){
            return "";
        }else{
            StringBuffer buff = new StringBuffer(array[0].toString());
            for(int i=1;i<array.length;++i){
                buff.append(join).append(array[i].toString());
            }
            return buff.toString();
        }
    }
}
