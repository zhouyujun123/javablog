package com.example.demo;


import com.example.demo.entity.TUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        String[] a = new String[5];
        Map<String, String[]> map = new HashMap<>();
        map.put("b",null);
        map.put("c",null);
        map.put("a",null);
        Map<String, String[]> treeMap = new TreeMap<>(map);
        System.out.println();
    }



}
