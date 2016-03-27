/**
 * GraphCompositeQuery
 * Created Jan 5, 2009 - 7:46:57 PM
 * Copyright Daniel McEnnis, see license.txt
 */
/*
 *   This file is part of GraphRAT.
 *
 *   GraphRAT is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   GraphRAT is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with GraphRAT.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dynamicfactory.propertyQuery;

import org.dynamicfactory.Creatable;
import org.dynamicfactory.descriptors.*;
import org.dynamicfactory.descriptors.Properties;
import org.dynamicfactory.property.Property;
import org.dynamicfactory.property.PropertyFactory;

import java.util.*;


/**
 *
 * @author Daniel McEnnis
 */
public class AndQuery implements PropertyQuery {

    PropertiesInternal properties;

    LinkedList<PropertyQuery> entries = new LinkedList<PropertyQuery>();

    public AndQuery(){
        properties = PropertiesFactory.newInstance().create();
        ParameterInternal param = ParameterFactory.newInstance().create("Children",PropertyQuery.class,"List of PropertyQuery objects to be combined with a logical AND");
        properties.add(param);
    }

    @Override
    public AndQuery build(Properties props) {

        if(props.quickCheck("Children",PropertyQuery.class)){
            return build(props.get("Children").getValue());
        }else{
            return this;
        }
    }


    @Override
    public AndQuery prototype() {
        return this;
    }

    @Override
    public AndQuery prototype(Properties props) {
        return (new AndQuery()).build(props);
    }

    public boolean execute(Property p) {
        if (entries.size() == 0) {
            return true;
        }
        Iterator<PropertyQuery> it = entries.iterator();
        while (it.hasNext()) {
            if(!it.next().execute(p)){
                return false;
            }
        }
        return true;
    }

    public AndQuery build(Collection<PropertyQuery> source) {
        entries.addAll(source);
        return this;
    }

    public int compareTo(Object o) {
        if (o.getClass().getName().contentEquals(this.getClass().getName())) {
            AndQuery query = (AndQuery) o;
            Iterator left = this.entries.iterator();
            Iterator right = query.entries.iterator();
            while (left.hasNext() && right.hasNext()) {
                int compare = ((Comparable) left.next()).compareTo((Comparable) right.next());
                if (compare != 0) {
                    return compare;
                }
            }
            if (left.hasNext()) {
                return -1;
            } else if (right.hasNext()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return this.getClass().getName().compareTo(o.getClass().getName());
        }
    }

//    public class AndIterator<Type extends Comparable> implements Iterator<Type> {
//
//        Vector<Iterator<Type>> iterators = new Vector<Iterator<Type>>();
//        Vector<Type> nextEntries = new Vector<Type>();
//        Type next = null;
//        boolean remaining = true;
//        SortedSet<Type> restriction;
//
//        public AndIterator(SortedSet<Type> r) {
//            Iterator it = entries.iterator();
//            restriction = r;
//            while (it.hasNext()) {
//                iterators.add((Iterator<Type>) executeIterator(it.next()));
//            }
//            for (int i = 0; i < iterators.size(); ++i) {
//                if (iterators.get(i).hasNext()) {
//                    nextEntries.set(i, iterators.get(i).next());
//                } else {
//                    remaining = false;
//                }
//            }
//        }
//
//        public boolean hasNext() {
//            if (remaining) {
//                boolean done = false;
//                next = nextEntries.get(0);
//                while (!done) {
//                    done = true;
//                    for (int i = 0; i < nextEntries.size(); ++i) {
//                        if (nextEntries.get(i).compareTo(next) < 0) {
//                            while (nextEntries.get(i).compareTo(next) < 0) {
//                                if (iterators.get(i).hasNext()) {
//                                    nextEntries.set(i, iterators.get(i).next());
//                                    done = false;
//                                } else {
//                                    remaining = false;
//                                    return false;
//                                }
//                            }
//                        } else if (nextEntries.get(i).compareTo(next) > 0) {
//                            next = nextEntries.get(i);
//                            done = false;
//                        }
//                    }
//                    if (done && (restriction!=null) && !restriction.contains(next)) {
//                        done = false;
//                        if (iterators.get(0).hasNext()) {
//                            nextEntries.set(0, iterators.get(0).next());
//                            next = nextEntries.get(0);
//                        } else {
//                            remaining = false;
//                            return false;
//                        }
//                    }
//                }
//                return done;
//            } else {
//                return false;
//            }
//        }
//
//        public Type next() {
//            if (this.hasNext()) {
//                if (this.hasNext()) {
//                    Type ret = next;
//                    if (iterators.get(0).hasNext()) {
//                        nextEntries.set(0, iterators.get(0).next());
//                    } else {
//                        remaining = false;
//                    }
//                    return ret;
//                }
//            }
//            return null;
//        }
//
//        public void remove() {
//            ;
//        }
//    }


}
