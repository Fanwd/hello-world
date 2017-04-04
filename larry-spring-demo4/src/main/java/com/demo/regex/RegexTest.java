package com.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guoxiaomin on 2017/4/4.
 */
public class RegexTest {

    public static void main(String[] args){

        String str = "hi hi fwd, him music!";
//        Pattern pat = Pattern.compile("hi{3}");
//        Matcher mat = pat.matcher(str);
//        boolean flat = Pattern.matches("hi{2}", str);
//        System.out.println(flat);
        Pattern pat = Pattern.compile("\\bhi\\b");
        Matcher mat = pat.matcher(str);
        while (mat.find()) {
            String g = mat.group();
            System.out.println("g:" + g);
        }
    }
}
