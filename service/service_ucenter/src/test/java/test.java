import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class test {

    @Test
    public void test1(){

        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        String rides =  redisTemplate.opsForValue().get("15565663061");

        System.out.println(rides);

    }
}
