package com.allwayup.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    /**
     * 0: convertSingle, 1: convertArray, 2: convertCollection, 3: convertMa 4: convertObj
     */
    public final static int[] typeInt = {0, 1, 2, 3, 4};

    /**
     * 基本数据类型
     */
    public final static List<?> basicClasses = Arrays.asList(boolean.class, byte.class, char.class, short.class, int.class, long.class, float.class, double.class, Boolean.class, Byte.class, Character.class, Short.class, Integer.class, Long.class, Float.class, Double.class, String.class);

    public static Class<?> genericCollection(Class<?> tClass) {
        TypeVariable<? extends Class<?>>[] typeParameters = tClass.getTypeParameters();
        TypeVariable<? extends Class<?>> variable = null;
        if (typeParameters.length > 0) {
            variable = typeParameters[0];
        }
        if (variable == null || !variable.getName().contains(".")) {
            return Object.class;
        } else {
            return variable.getClass();
        }
    }

    public static void generic(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType) field.getGenericType();
            System.out.println(genericSuperclass.getActualTypeArguments()[0]);
            for (Type actualTypeArgument : genericSuperclass.getActualTypeArguments()) {
                System.out.println(actualTypeArgument.getTypeName());
            }
        }
    }

    /**
     * Json转单值
     */
    public static <T> T convertSingle(JSONObject jsonObject, Class<T> tClass) {
        T t;
        try {
            t = jsonObject.toBean(tClass);
        } catch (Exception e) {
            return null;
        }
        return t;
    }

    /**
     * Json转数组
     */
    public static <T> T convertArray(JSONArray jsonArray, Class<T> tClass) {
        if (jsonArray == null || jsonArray.size() < 1) {
            return null;
        }
        T t = null;
        try {
            Class<?> componentType = tClass.getComponentType();
            Object[] array = (Object[]) Array.newInstance(componentType, jsonArray.size());
            for (int i = 0; i < jsonArray.size(); i++) {
                Object obj = null;
                switch (typeInt(componentType)) {
                    case 0:
                        obj = convertSingle(jsonArray.getJSONObject(i), componentType);
                        break;
                    case 1:
                        obj = convertArray(jsonArray.getJSONArray(i), componentType);
                        break;
                    case 2:
                        obj = convertCollection(jsonArray.getJSONArray(i), componentType);
                        break;
                    case 3:
                        obj = convertMap(jsonArray.getJSONObject(i), componentType);
                        break;
                    case 4:
                        obj = convertObj(jsonArray.getJSONObject(i), componentType);
                        break;
                }
                array[i] = obj;
            }
            t = (T) array;
        } catch (Exception e) {
            return null;
        }
        return t;
    }

    /**
     * Json转集合
     */
    public static <T> T convertCollection(JSONArray jsonArray, Class<T> tClass) {
        T t = null;
        try {
            Collection collection = (Collection) tClass.getDeclaredConstructor().newInstance();
            for (int i = 0; i < jsonArray.size(); i++) {
                Object obj = null;
//                switch (typeInt(tClass)) {
//                    case 0:
//                        obj = convertSingle(jsonArray.getJSONObject(i), componentType);
//                        break;
//                    case 1:
//                        obj = convertArray(jsonArray.getJSONArray(i), componentType);
//                        break;
//                    case 2:
//                        obj = convertCollection(jsonArray.getJSONArray(i), componentType);
//                        break;
//                    case 3:
//                        obj = convertMap(jsonArray.getJSONObject(i), componentType);
//                        break;
//                    case 4:
//                        obj = convertObj(jsonArray.getJSONObject(i), componentType);
//                        break;
//                }
                collection.add(obj);
            }
            t = (T) collection;
        } catch (Exception e) {
            return null;
        }
        return t;
    }

    /**
     * Json转Map
     */
    public static <T> T convertMap(JSONObject jsonObject, Class<T> tClass) {
        T t = null;
        try {

        } catch (Exception e) {
            return null;
        }
        return t;
    }

    /**
     * Json转对象
     */
    public static <T> T convertObj(JSONObject jsonObject, Class<T> tClass) {
        T t = null;
        try {

        } catch (Exception e) {
            return null;
        }
        return t;
    }


    /**
     * mainClass语法定义上能否继承或实现character,也就是能否包含character特性
     */
    public static boolean withCharacter(Class<?> mainClass, Class<?> character) {
        if (mainClass.isAssignableFrom(Object.class)) {
            return false;
        } else if (character.isInterface()) {
            return true;
        } else if (character.isLocalClass()) {
            return mainClass.isLocalClass() || Modifier.isAbstract(mainClass.getModifiers());
        } else if (character.isEnum()) {
            return character.isEnum() || mainClass.isInterface();
        } else if (Modifier.isAbstract(character.getModifiers())) {
            return Modifier.isAbstract(character.getModifiers()) || mainClass.isLocalClass();
        }
        return false;
    }

    /**
     * 向上追溯所有接口
     */
    public static boolean hasInterface(Class<?> mainClass, Class<?> character) {
        if (mainClass.isAssignableFrom(character)) {
            return true;
        }
        for (Class<?> anInterface : mainClass.getInterfaces()) {
            if (hasInterface(anInterface, character)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 向上追溯所有接口和继承类
     */
    public static boolean hasCharacterClass(Class<?> mainClass, Class<?> character) {
        if (mainClass.isAssignableFrom(Object.class)) {
            return false;
        }
        if (hasInterface(mainClass, character)) {
            return true;
        }
        return hasCharacterClass(mainClass.getSuperclass(), character);
    }

    /**
     * 是否继承或实现character,也就是否包含了character特性
     */
    public static boolean hasCharacter(Class<?> mainClass, Class<?> character) {
        if (!withCharacter(mainClass, character)) {
            return false;
        }
        if (mainClass.isEnum() || mainClass.isInterface()) {
            return hasInterface(mainClass, character);
        } else {
            return hasCharacterClass(mainClass, character);
        }
    }

    /**
     *
     */

    /**
     * 判断需要转译的类型
     */
    public static int typeInt(Class<?> tClass) {
        if (basicClasses.contains(tClass)) {
            return typeInt[0];
        } else if (tClass.isArray()) {
            return typeInt[1];
        } else if (hasCharacter(tClass, Collection.class)) {
            return typeInt[2];
        } else if (hasCharacter(tClass, Map.class)) {
            return typeInt[3];
        } else {
            return typeInt[4];
        }
    }

    /**
     * Json转对象
     */
    public static <T> T convertObj(String json, Class<T> tClass) {
        T t = null;
        try {
            switch (typeInt(tClass)) {
                case 0:
                    t = convertSingle(new JSONObject(json), tClass);
                    break;
                case 1:
                    t = convertArray(new JSONArray(json), tClass);
                    break;
                case 2:
                    t = convertCollection(new JSONArray(json), tClass);
                    break;
                case 3:
                    t = convertMap(new JSONObject(json), tClass);
                    break;
                case 4:
                    t = convertObj(new JSONObject(json), tClass);
                    break;
            }
        } catch (Exception e) {
            return null;
        }
        return t;
    }

}
