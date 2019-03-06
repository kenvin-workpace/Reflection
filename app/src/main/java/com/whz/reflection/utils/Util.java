package com.whz.reflection.utils;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

import com.whz.reflection.module.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 2018/5/9
 */
public class Util {

    /**
     * 获取所有类
     */
    public static Person simpel_1() {
        try {
            //获取Person类的Class对象
            Class<Person> aClass = Person.class;
            //通过Class对象反射获取Person类的构造方法
            Constructor<Person> constructor = aClass.getConstructor(String.class, int.class);
            //调用构造方法获取Person实例
            Person person = constructor.newInstance("whz", 10);
            return person;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取类信息
     */
    public static String simpel_2() {
        try {
            StringBuffer sb = new StringBuffer();

            Class<HashMap> aClass = HashMap.class;
            //获取类名
            String canonicalName = aClass.getCanonicalName();
            sb.append("canonicalName:").append(canonicalName).append("\n");
            //获取类限定符
            String modifiers = Modifier.toString(aClass.getModifiers());
            sb.append("modifiers:").append(modifiers).append("\n");
            //获取类泛型信息
            TypeVariable<Class<HashMap>>[] typeParameters = aClass.getTypeParameters();
            sb.append("typeParameters:");
            if (typeParameters.length != 0) {
                for (TypeVariable tv : typeParameters) {
                    sb.append(tv.getName()).append(" ");
                }
            } else {
                sb.append("No Type Parameters");
            }
            sb.append("\n");
            //获取类实现的所有接口
            Type[] genericInterfaces = aClass.getGenericInterfaces();
            sb.append("genericInterfaces:");
            if (genericInterfaces.length != 0) {
                for (Type t : genericInterfaces) {
                    sb.append(t.toString()).append(" ");
                }
            } else {
                sb.append("No Implemented Interfaces");
            }
            sb.append("\n");
            //获取类的注解(只能获取到 RUNTIME 类型的注解)
            Annotation[] annotations = aClass.getAnnotations();
            sb.append("annotations:");
            if (annotations.length != 0) {
                for (Annotation at : annotations) {
                    sb.append(at.toString()).append(" ");
                }
            } else {
                sb.append("No Annotations");
            }
            sb.append("\n");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * getDeclaredField(String name)
     * 获取指定的变量（只要是声明的变量都能获得，包括 private）
     * <p>
     * getField(String name)
     * 获取指定的变量（只能获得 public 的）
     * <p>
     * getDeclaredFields()
     * 获取所有声明的变量（包括 private）
     * <p>
     * getFields()
     * 获取所有的 public 变量
     */
    public static String simpel_3() {
        try {
            StringBuffer sb = new StringBuffer();
            //获取Person类的Class对象
            Class<Person> personClass = Person.class;
            //获取指定的变量
            Field[] declaredFields = personClass.getDeclaredFields();
            for (Field field : declaredFields) {
                //获取名称
                sb.append(field.getName()).append(" ");
                //获取类型
                sb.append(field.getType()).append(" ");
                //获取修饰符
                sb.append(Modifier.toString(field.getModifiers())).append(" ");
                //获取注解
                Annotation[] annotations = field.getAnnotations();
                if (annotations.length != 0) {
                    for (Annotation ann : annotations) {
                        sb.append(ann.toString()).append(" ");
                    }
                } else {
                    sb.append("No Annotation");
                }
                sb.append("\n\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取对象属性，并修改
     */
    public static String simpel_4() {
        try {
            StringBuffer sb = new StringBuffer();
            //创建Person对象
            Person person = new Person("whz", 10);
            //获取Person类的Class对象
            Class<? extends Person> personClass = person.getClass();

            //获取name
            Field nameField = personClass.getDeclaredField("name");
            //private需要设置setAccessible
            nameField.setAccessible(true);
            String name = (String) nameField.get(person);
            sb.append("name:").append(name);

            sb.append("\n");

            //获取age
            Field ageField = personClass.getDeclaredField("age");
            int age = (int) ageField.get(person);
            sb.append("age:").append(age);

            sb.append("\n");

            //修改属性值
            nameField.set(person, "wanghz");
            ageField.set(person, 11);
            sb.append(person.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Method
     */
    public static void simpel_5() {
        try {
            //获取Person类的Class对象
            Class<Person> personClass = Person.class;
            //通过Class对象反射获取Person类的构造方法
            Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
            //调用构造方法获取Person实例
            Person person = constructor.newInstance("whz", 10);

            //调用无参方法
            Method getName = personClass.getMethod("getName");
            getName.invoke(person);

            //调用定项参数方法
            Method setName = personClass.getMethod("setName", String.class);
            setName.invoke(person, "wanghongzhen");

            //调用不定项参数方法,不定项参数可以当成数组来处理
            Method setEat = personClass.getMethod("setEat", new Class[]{String[].class});
            setEat.invoke(person, (Object) new String[]{"whz_1", "whz_2"});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Methods
     */
    public static String simpel_6() {
        try {
            StringBuffer sb = new StringBuffer();

            //获取Person类的Class对象
            Class<Person> personClass = Person.class;
            //获取Person类所有方法
            Method[] declaredMethods = personClass.getDeclaredMethods();
            if (declaredMethods.length != 0) {
                for (Method m : declaredMethods) {
                    sb.append(m.getName()).append("\n");
                }
            } else {
                sb.append("No methods").append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数组使用
     */
    public static String simpel_7() {
        try {
            StringBuffer sb = new StringBuffer();
            //设置一维数组
            Object instance_1 = Array.newInstance(int.class, 2);
            Array.set(instance_1, 0, 1);
            Array.set(instance_1, 1, 2);
            //获取一维数组值
            sb
                    .append("instance_1:")
                    .append(Array.get(instance_1, 0))
                    .append(Array.get(instance_1, 1))
                    .append("\n");


            //设置二维数组
            Object instance_2 = Array.newInstance(int.class, 2, 2);
            Object row0 = Array.get(instance_2, 0);
            Object row1 = Array.get(instance_2, 1);

            Array.set(row0, 0, 1);
            Array.set(row0, 1, 2);
            Array.set(row1, 0, 3);
            Array.set(row1, 1, 4);

            //获取二维数组值
            sb
                    .append("instance_2:")
                    .append(Array.get(row0, 0))
                    .append(Array.get(row0, 1))
                    .append(Array.get(row1, 0))
                    .append(Array.get(row1, 1))
                    .append("\n");

            //或
            Object instance_3 = Array.newInstance(int.class, 2);
            Object row00 = Array.newInstance(int.class, 2);
            Object row01 = Array.newInstance(int.class, 2);

            Array.set(row00, 0, 1);
            Array.set(row00, 1, 2);
            Array.set(row01, 0, 3);
            Array.set(row01, 1, 4);

            Array.set(instance_3, 0, row00);
            Array.set(instance_3, 1, row01);

            //获取二维数组值
            sb
                    .append("instance_3:")
                    .append(Array.get(row00, 0))
                    .append(Array.get(row00, 1))
                    .append(Array.get(row01, 0))
                    .append(Array.get(row01, 1))
                    .append("\n");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数组使用
     */
    public static String simpel_8(Context ctx) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StringBuffer sb = new StringBuffer();
                //通过Class对象反射获取Person类的构造方法
                FingerprintManager fm = (FingerprintManager) ctx.getSystemService(Context.FINGERPRINT_SERVICE);
                Class<? extends FingerprintManager> aClass = fm.getClass();
                Method method_1 = aClass.getMethod("getEnrolledFingerprints");
                List method_1_list = (List) method_1.invoke(fm);
                sb.append("指纹数量：").append(method_1_list.size());
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "不支持查询";
    }
}
