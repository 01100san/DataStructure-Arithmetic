package com.mysite;

import org.junit.Test;

import java.io.DataOutput;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName: TestHashCode
 * Package: com.mysite
 * Description
 *
 * @Author zhl
 * @Create 2023/12/29 18:13
 * version 1.0
 */
public class TestHashCode {
    @Test
    public void test1(){
        Float f = 10.6F;
        Double d = 10.6;

        int code = Float.floatToIntBits(f);
        long bits = Double.doubleToLongBits(d);
        System.out.println(code);
        System.out.println(bits);

        System.out.println(Integer.toBinaryString(code));
        //System.out.println(Integer.toBinaryString(9));

    }
    @Test
    public void test3(){
        String str = "jack";    //3254239   3254239

        int len = str.length();
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            hashCode = hashCode * 31 + c;   //jvm底层会进行优化
            //hashCode = (hashCode << 5 )- hashCode + c;
        }
        System.out.println(hashCode);
        System.out.println(str.hashCode());
    }
    @Test
    public void test4(){
        Integer a = 110;
        Float b = 10.6f;
        Long c = 1333L;
        Double d = 10.6;
        String e = "jack";
        System.out.println(Integer.hashCode(a));//110
        System.out.println(a.hashCode());       //110
        System.out.println(Float.hashCode(b));  //1093245338
        System.out.println(b.hashCode());       //1093245338
        System.out.println(Long.hashCode(c));   //1333
        System.out.println(c.hashCode());       //1333
        System.out.println(Double.hashCode(d)); //1930821632
        System.out.println(d.hashCode());       //1930821632
        System.out.println(e.hashCode());       //3254239
    }
    @Test
    public void test5(){
        Person p1 = new Person(10,10.3f,"java");
        Person p2 = new Person(10,10.3f,"java");
        System.out.println(p1.hashCode());  //721748895  -> -475620225
        System.out.println(p2.hashCode());  //1642534850 -> -475620225

        Map<Object, Object> map = new HashMap<>();
        map.put(p1,"abc");
        map.put(p2,"def");
        System.out.println(map.size());

        //System.out.println(map.get(p1).hashCode()); //96354
        //System.out.println(map.get(p2).hashCode()); //99333
    }
}
