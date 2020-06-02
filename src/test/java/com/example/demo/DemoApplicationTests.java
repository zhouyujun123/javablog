package com.example.demo;


import com.example.demo.entity.TUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        List<Long> userList = new ArrayList<>();
        userList.add(1L);
        userList.add(2L);
        userList.add(4L);

        List<Long> treeList1 = new LinkedList<>();
        List<Long> treeList2 = new LinkedList<>();
        List<Long> userIdList = new ArrayList<>();
        userIdList.add(1L);
        userIdList.add(2L);
        userIdList.add(3L);
        List<Long> list = new LinkedList<>();

        for (Long user : userList) {
            int mark = 0;
            for (Long userIds : userIdList) {
                if (user.equals(userIds)) {
                    treeList1.add(user);
                    mark++;
                    break;
                }
            }
            if (mark == 0) {
                treeList2.add(user);
            }
        }
        list.addAll(treeList1);
        list.addAll(treeList2);
        System.out.println();
    }


}
