package com.atguigu.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 创建Stream
 *
 * 中间操作
 *
 * 终止操作
 */
public class TestStreamAPI {

    // 创建Stream
    @Test
    public void test1(){
        // 1、可以通过Collection 系列集合提供的stream() 或 parallelStream()
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();


        // 2、通过Arrays中的静态方法获取数据流
        String[] arr = {"nihao"};
        Stream<String> stream1 = Arrays.stream(arr);

        // 3、通过Stream类中的静态方法of()
        Stream<String> stream2 = Stream.of("aa", "bb");

        // 4、创建无限流
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random())
                .forEach(System.out::println);
    }
}
