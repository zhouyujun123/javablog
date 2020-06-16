package com.example.demo;


import com.example.demo.dto.FindDTO;
import com.example.demo.service.TSubscriptionService;
import com.example.demo.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;




    @Test
    void contextLoads() {
        redisTemplate.opsForList().leftPush("happyjava:list", "new happyjava1");
        redisTemplate.opsForList().leftPush("happyjava:list", "new happyjava2");
        redisTemplate.opsForList().leftPush("happyjava:list", "new happyjava3");
        Long size = redisTemplate.opsForList().size("happyjava:list");
        redisTemplate.opsForList().range("happyjava:list", 0L, size-1L);
    }

    @Test
    void testAddSetRedis(){
        redisTemplate.opsForSet().add("setValue-name-type", "new happyjava1");
        redisTemplate.opsForSet().add("setValue-name-type", "new happyjava2");
        redisTemplate.opsForSet().add("setValue-name-type", "new happyjava3");
        redisTemplate.opsForSet().add("setValue-name-type", "new happyjava4");
        redisTemplate.opsForSet().add("setValue-name-type", "new happyjava1");


    }

    @Test
    void testRedis(){
        FindDTO findDTO = new FindDTO();
        FindDTO findDTO2 = new FindDTO();
        findDTO.setText("fdsdfwef");
        findDTO.setType(33);
        BeanUtils.copyProperties(findDTO,findDTO2);
        System.out.println("改变前======>"+findDTO2);
        findDTO.setText("新属性");
        System.out.println("改变后======>"+findDTO2);
    }


}
