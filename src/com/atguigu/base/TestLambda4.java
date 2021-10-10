package com.atguigu.base;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用：lambda体中的内容有方法已经实现了，我们可以使用方法引用
 *
 * 三种语法格式：
 *  对象::实例方法名
 *
 *  类::静态方法
 *
 *  类:: 实例方法名
 *
 *  构造器引用
 *  className::new
 *
 *  数组引用：
 *  Type::new
 */
public class TestLambda4 {

    @Test
    public void test6(){
        Function<Integer,String[]> f = String[]::new;
        String[] apply = f.apply(10);
        System.out.println(apply);
    }

    // 对象::实例方法名
    // 实例方法的参数列表和返回值类型要和需要实现的接口方法一致
    @Test
    public void test1(){
        Consumer<String> con = (x) -> System.out.println(x);

        Consumer<String> c = System.out::println;
        c.accept("你好！");
    }

    @Test
    public void test2(){
        String str = new String("你好！");
        Supplier<String> s = str::toString;
        System.out.println(s.get());
    }

    @Test
    public void test3(){
        Comparator<Integer> co = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> com = Integer::compare;
        System.out.println(com.compare(1,2));
    }

    @Test
    public void test4(){
        // 第一参数是方法的调用者，第二参数是方法的参数，可以写成类::实例方法
        BiPredicate<String,String> bp = (x,y) -> x.equals(y);

        BiPredicate<String,String> bp1 = String::equals;

        System.out.println(bp1.test("你好！", new String("你好！")));
    }

    @Test
    public void test5(){
        // 构造方法的参数列表要和函数式方法的参数列表一致
        Supplier<String> sup = () -> new String("你好！");

        Supplier<String> su = String::new;
        System.out.println(su.get());

        Function<String,String> f = String::new;
        System.out.println(f.apply("你好"));
    }
}
