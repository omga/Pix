package com.pix;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Andrew on 23.04.14.
 */
public class TestShit {
    public static void main(String ...args){
        int i=127;
        byte b=127;
        byte bbb,bb=5;
        LinkedList<Integer> ll=new LinkedList<Integer>();
        ll.add(i);i++;
        ll.add((int)bb);
        Iterator<Integer> it=ll.descendingIterator();
        MyIterator<Integer> mit= new MyIterator<Integer>(ll);
       // System.out.println(ll.get(2)==null);
        while (mit.hasNext())
            System.out.println("Well hello "+mit.next());
        long longgg = 309999994999L;
        int v1=2099999999;
        v1+=longgg;
        Date date= GregorianCalendar.getInstance().getTime();

        System.out.println(date+"|||||||"+ new Timestamp(date.getTime()));


        System.out.println(v1 );

    }



}
