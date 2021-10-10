package com.atguigu.stream;

import com.atguigu.entity.Employee;
import com.atguigu.entity.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamAPI3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三",21,6666.66, Status.FREE),
            new Employee("李四",31,8888.88, Status.BUSY),
            new Employee("王五",27,12000.00,Status.FREE),
            new Employee("赵六",31,6000.44, Status.FREE),
            new Employee("田七",31,9999.99, Status.BUSY),
            new Employee("田七",31,9999.99, Status.VOCATION)
    );

    /**
     * 终止操作
     * allMatch 检查是否匹配所有元素 return Boolean
     * anyMatch 至少匹配一个元素
     * noneMatch 检查是否没有匹配元素
     * findFirst 返回第一个元素
     * findAny 返回当前流任意一个元素
     * count 返回流中元素总个数
     * max 返回流中最大的元素
     * min 返回流中最小的元素
     *
     * 归约：reduce()
     *
     * 收集：collect()
     */
    // 组函数
    @Test
    public void test9(){
        DoubleSummaryStatistics sum = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(sum.getCount());
        System.out.println(sum.getAverage());
        System.out.println(sum.getMax());
        System.out.println(sum.getMin());
        System.out.println(sum.getSum());

        System.out.println("======================");

        String str = employees.stream()
                .map(Employee::getName)
                .distinct()
                .collect(Collectors.joining(",","====","===="));
        System.out.println(str);
    }

    // 分片
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 10000));
        System.out.println(collect);
    }

    // 多级分组
    @Test
    public void test7(){
        Map<Status, Map<String, List<Employee>>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 30) {
                        return "青年";
                    } else {
                        return "中年";
                    }
                })));
        System.out.println(map);
    }

    // 分组
    @Test
    public void test6(){
        Map<Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    // collect() 相关函数
    @Test
    public void test5(){
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        Double avg = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        Optional<Employee> max = employees.stream().max((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()));
        System.out.println(max.get());

        Optional<Employee> min = employees.stream().min(Comparator.comparing(Employee::getSalary));
        System.out.println(min.get());
    }

    @Test
    public void test4(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,8,7,9,10);

        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        System.out.println("======================");

        Optional<Double> reduce1 = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);

        System.out.println(reduce1.get());

        System.out.println("========================");

        // 收集
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);


        System.out.println("======================");

        HashSet<String> collect1 = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));

        collect1.forEach(System.out::println);
    }

    @Test
    public void test(){
        boolean b = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Status.FREE));
        System.out.println(b);

        System.out.println("===================================");

        boolean b1 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Status.FREE));
        System.out.println(b1);

        System.out.println("=========================");

        boolean b2 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Status.FREE));
        System.out.println(b2);

        System.out.println("===================");

        Optional<Employee> first = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .findFirst();
        System.out.println(first.get());

        System.out.println("=========================");

        Optional<Employee> any = employees.stream().findAny();
        System.out.println(any);

        Optional<Employee> any1 = employees.parallelStream().findAny();
        System.out.println("any1====="+any1);

        System.out.println("=========================");
        long count = employees.stream().count();
        System.out.println(count);

        Optional<Employee> max = employees.stream().max(Comparator.comparing(Employee::getSalary));
        System.out.println(max);

        System.out.println("=============================");

        Optional<Employee> min = employees.stream().min(Comparator.comparing(Employee::getSalary));
        System.out.println(min);
    }
}
