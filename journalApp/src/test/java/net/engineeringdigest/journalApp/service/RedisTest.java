package net.engineeringdigest.journalApp.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
@Test
    public void testRedis(){
//        redisTemplate.opsForValue().set("email","sfiuwefb@gmail.com");
        Object email = redisTemplate.opsForValue().get("salary");
        int a =1;
    }
}
