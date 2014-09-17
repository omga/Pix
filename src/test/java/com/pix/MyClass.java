package com.pix;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Andrew on 02.05.14.
 */
public class MyClass {

    public static String stringToModify="123,1232,,1,,,23123,65,,3443,,,4355,,,,3452,12321455,,234234,,,45432114,,34,,,,4542233";

    public static void main(String[] args) {
        HashSet<String> hs= new HashSet<String>();
        LinkedList<String> ll=new LinkedList<String>();
        StringBuilder sb=new StringBuilder();
        for(String s:stringToModify.split(",")){
            sb.append(s);

            if(!s.equals("")){sb.append(",");hs.add(s);ll.add(s);}
        }
        System.out.println(sb);
        System.out.println(hs.toString());
        Iterator<String> iterator=hs.iterator();
        System.out.println(iterator.next());
        System.out.println(ll.toString());
        iterator=ll.descendingIterator();
        System.out.println(iterator.next());
        System.out.println(ll.subList(ll.size()-5,ll.size()).toString());




    }

}