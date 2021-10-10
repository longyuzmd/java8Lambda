package com.atguigu.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 内置了四大核心函数式接口
 *
 * Consumer<T> : 消费性接口
 *      void accept(T t)
 *
 * Supplier<T> : 供给型接口
 *      T get();
 *
 * Function<T,R> : 函数式接口
 *      R apply(T t)
 *
 * Predicate<T> : 断言型接口
 *      boolean test(T t);
 */
public class TestLambda3 {

    // Consumer
    @Test
    public void test1(){
        happy(10000, m -> System.out.println(m));
    }

    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }

    // Supplier<T> 供给
    @Test
    public void test2(){
        String s = supplierTest(() -> new String("hello!"));
        System.out.println(s);
    }

    public String supplierTest(Supplier<String> sp){
        return sp.get();
    }

    // Function<T,R>:

    @Test
    public void test3(){
        String s = functionTest("hello!", (str) -> str.substring(0, 3));
        System.out.println(s);
    }

    public String functionTest(String str, Function<String,String> f){
        return f.apply(str);
    }

    // Predicate
    @Test
    public void test4(){
        List<String> list = Arrays.asList("hello", "ab", "atguigu");
        List<String> list1 = predicateTest(list, (str) -> str.length() > 3);
        for (String s:list1){
            System.out.println(s);
        }
    }

    public List<String> predicateTest(List<String> list, Predicate<String> f){
        ArrayList<String> newList = new ArrayList<>();
        for(String s:list){
            if(f.test(s)){
                newList.add(s);
            }
        }
        return newList;
    }
}
