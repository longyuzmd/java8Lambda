package com.atguigu.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class TestParStreamAPI4 {

    @Test
    public void test(){
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).toMillis()); // 11257
    }
}
