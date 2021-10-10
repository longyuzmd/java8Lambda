package com.atguigu.stream;


import com.atguigu.entity.Employee;
import com.atguigu.entity.Status;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI2 {
    Employee e = new Employee("田七",31,9999.99);
    // 创建集合
    List<Employee> employees = Arrays.asList(
            new Employee("张三",21,6666.66, Status.FREE),
            new Employee("李四",31,8888.88, Status.BUSY),
            new Employee("王五",27,12000.00,Status.FREE),
            new Employee("赵六",31,6000.44, Status.FREE),
            new Employee("田七",31,9999.99, Status.BUSY),
            new Employee("田七",31,9999.99, Status.VOCATION)
            );

    @Test
    // 中间操作
    public void test1(){
        Stream<Employee> stream = employees.stream().filter((e) -> {
            System.out.println("中间操作 判断Stream API");
            return e.getSalary() > 7000;
        });

        stream.forEach(System.out::println);
    }

    @Test
    public void test2(){
        employees.stream().filter((e) -> e.getSalary() > 7000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("==========================");

        employees.stream().filter((e) -> e.getSalary() > 7000)
                .skip(2)
                .forEach(System.out::println);

        System.out.println("========================");

        employees.stream().filter((e) -> e.getSalary() > 7000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map
     */
    @Test
    public void test3(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        employees.stream()
                .map((e) -> e.getName())
                .forEach(System.out::println);


        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map(TestStreamAPI2::filterCharacter)
                .forEach((sm) -> sm.forEach(System.out::println));

        System.out.println("============");
        list.stream()
                .flatMap(TestStreamAPI2::filterCharacter)
                .forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for(Character ch : str.toCharArray()){
            list.add(ch);
        }

        return list.stream();
    }

    /**
     * sorted() 自然排序 Comparable
     * sorted(Comparator com) 定制排序 Comparator
     */
    @Test
    public void test5(){
        List<String> list = Arrays.asList("bbb", "aaa", "ddd", "eee", "ccc");

        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("==================");

        employees.stream()
                .sorted((e1,e2) -> {
                    if(e1.getAge().equals(e2.getAge())){
                        return e1.getName().compareTo(e2.getName());
                    }else{
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }
}
