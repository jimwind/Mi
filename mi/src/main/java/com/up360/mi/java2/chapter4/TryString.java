package com.up360.mi.java2.chapter4;

/***
 * StringBuffer 线程安全
 *
 */

public class TryString {
    public static void main(String[] args){
        String s = "abc";
        s.concat("def");

        System.out.println(s);

        StringBuffer aString = new StringBuffer("A switch in time!");
        System.out.println(aString.length());
        System.out.println(aString.capacity());

        StringBuffer newString = new StringBuffer(50);
        System.out.println(newString.length());
        System.out.println(newString.capacity());

        newString.ensureCapacity(80);//80 > 50
        System.out.println(newString.length());
        System.out.println(newString.capacity());//50 * 2 + 2

        newString.append("Am I the first?");
        System.out.println(newString);
        System.out.println(newString.length());
        System.out.println(newString.capacity());

        StringBuffer buf = new StringBuffer("Many hands make light word");
        buf.insert(4, " old");
        System.out.println(buf);

        StringBuilder sb = new StringBuilder();
        sb.append("abc");
        sb.append("def");


    }
}
