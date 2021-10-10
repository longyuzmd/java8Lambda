package com.atguigu.base;

import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * lambda 书写格式
 * （） -> lambda体
 * 1、无参数无返回值
 * 2、有一个参数，无返回值
 * 3、有一个参数，无返回值的简写方式
 * 4、有一个参数，有返回值
 */
public class TestLambda {

    @Test
    public void testNoParamNoReturn(){
        Runnable r = () -> System.out.println("你好！");
        r.run();
    }

    @Test
    public void testOneParamNoReturn(){
        Consumer con = (x) -> System.out.println(x);
        con.accept("我很牛逼！");

        // 简写
        Consumer sim = x -> System.out.println(x);
        sim.accept("简写！");
    }

    @Test
    public void testOneParamReturn(){
        Comparator<Integer> com = (x,y) -> {
            System.out.println("lambda!");
            return Integer.compare(x,y);
        };
        int compare = com.compare(1, 2);
        System.out.println(compare);
    }

    @Test
    public void testFunctionInterface(){
        Integer value = getValue(10, (x) -> x * 2);
        System.out.println(value);
    }

    public Integer getValue(Integer x,MyFunction my){
        return my.getValue(x);
    }

}
