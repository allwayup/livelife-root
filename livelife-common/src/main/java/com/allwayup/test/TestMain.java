package com.allwayup.test;

import cn.hutool.json.JSONArray;
import com.allwayup.entity.Cls01;
import com.allwayup.entity.Cls02;
import com.allwayup.util.Generic;
import com.allwayup.util.Generic2;
import com.allwayup.util.JsonUtil2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestMain {

    public static void main(String[] args) throws Exception {
        testCv();
    }

    public static void testCv() throws Exception {
        Field list = Cls01.class.getDeclaredField("list");
        JSONArray objects = new JSONArray("[\"a\", \"b\"]");
        String[] o = JsonUtil2.convertArray(objects, Generic2.create(list));
        for (String s : o) {
            System.out.println(s);
        }
    }

    public static void testSf() throws Exception {
        Field veryComplex = Cls01.class.getDeclaredField("gcd");
        String typeName = veryComplex.getGenericType().getTypeName();
        long t = 0;
        t = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Generic.genericStr2(typeName);
        }
        t = System.currentTimeMillis() - t;
        System.out.println(t);
        t = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Generic.genericStr(typeName);
        }
        t = System.currentTimeMillis() - t;
        System.out.println(t);
    }

    public static void testGcStr() throws Exception {
        Field veryComplex = Cls01.class.getDeclaredField("gcd");
        String typeName = veryComplex.getGenericType().getTypeName();
        System.out.println("tyn: " + typeName);
        System.out.println();
        long t1 = System.currentTimeMillis();
        String s1 = Generic.genericStr(typeName);
        long t2 = System.currentTimeMillis();
        String s2 = Generic.genericStr(typeName);
        long t3 = System.currentTimeMillis();
        System.out.println("倒序: " + (t2 - t1) + "" + s1);
        System.out.println("正序: " + (t3 - t2) + "" + s2);
        System.out.println();
    }

    public static void testIdStr() throws Exception {
        List<Map<List<String>, List<Map<String, List<String>>>>> veryComplex = new ArrayList<>();
        Class<? extends List> aClass = veryComplex.getClass();
        Class<?> cls = Cls02.class;
        System.out.println(aClass.toGenericString().substring(aClass.toGenericString().lastIndexOf(" ") + 1));
    }

    public static void testFieldGo() throws Exception {
        Field veryComplex = Cls01.class.getDeclaredField("veryComplex");
        System.out.println(veryComplex.getType());
        String typeName = veryComplex.getGenericType().getTypeName();
        System.out.println(typeName);

    }
}
