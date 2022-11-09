package com.allwayup.util;

import java.lang.reflect.Field;

/**
 * 解析Json专用Class类
 */
public class Generic2<T> {

    private Class<T> mainClass;

    private Field field;

    private String className;

    public void classGeneric() {
        if (field != null) {
            this.className = field.getGenericType().getTypeName();
            this.mainClass = (Class<T>) field.getType();
            return;
        }
        if (mainClass != null) {
            this.className = mainClass.toGenericString().substring(mainClass.toGenericString().lastIndexOf(" ") + 1);
        }
    }

    public void genericClass() {
        if (className == null) {
            return;
        }
        if (!className.contains(".")) {
            mainClass = (Class<T>) Object.class;
            return;
        }
        int index = className.indexOf("<");
        try {
            if (index < 0) {
                Class.forName(className);
            } else {
                Class.forName(className.substring(0, index));
            }
        } catch (Exception ignored) {
        }
    }

    private Generic2(Class<T> mainClass) {
        this.mainClass = mainClass;
        classGeneric();
    }

    private Generic2(Field field) {
        this.field = field;
        classGeneric();
    }

    private Generic2(String className) {
        this.className = className;
        genericClass();
    }

    public static <T>Generic2<T> create(Class<T> mainClass) {
        return new Generic2<T>(mainClass);
    }

    public static <T>Generic2<T> create(Field field) {
        return new Generic2<T>(field);
    }

    public static <T>Generic2<T> create(String className) {
        return new Generic2<T>(className);
    }

    public Generic2<T> getGeneric() {
        String gcStr = genericStr(this.className);
        if (this.className.equals(gcStr)) {
            return null;
        }
        return Generic2.create(genericStr(this.className));
    }

    public static String genericStr(String className) {
        if (className == null) {
            return null;
        }
        boolean nf = true;
        int st = 0;
        String r = "";
        byte[] bs = className.getBytes();
        byte b;
        for (int i = bs.length - 1; i >= 0; i--) {
            b = bs[i];
            if (b == '>') {
                st++;
                if (nf) {
                    r = "";
                    nf = false;
                    continue;
                }
            } else if (b == '<') {
                st--;
                if (st == 0) {
                    break;
                }
            }
            if (st == 1 && (',' == b)) {
                break;
            }
            r = (char) b + r;
        }
        if (nf) {
            return null;
        }
        return r.trim();
    }

    public static String genericStr2(String className) {
        if (className == null) {
            return null;
        }
        boolean nf = true;
        int st = 0;
        String r = "";
        byte[] bs = className.getBytes();
        for (byte b : bs) {
            if (b == '<') {
                st++;
                if (nf) {
                    r = "";
                    nf = false;
                    continue;
                }
            } else if (b == '>') {
                st--;
                if (st == 0) {
                    break;
                }
            }
            if (st == 1 && (',' == b)) {
                r = "";
                continue;
            }
            r += (char) b;
        }
        if (nf) {
            return null;
        }
        return r.trim();
    }

    public Class<T> getMainClass() {
        return this.mainClass;
    }

    public Field getField() {
        return this.field;
    }

    public String getClassName() {
        return this.className;
    }
}
