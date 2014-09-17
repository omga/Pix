package com.pix;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew on 07.05.14.
 */
public class MyIterator<E> {
    LinkedList<E> list;
    int index=0;
    public MyIterator(LinkedList<E> list){
        this.list=list;

    }
    public boolean hasNext(){

        return list.size()>index;
    }
    public E next(){
        return list.get(index++);

    }

}
