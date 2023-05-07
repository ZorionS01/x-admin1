package com.szw;

import com.szw.common.utils.JwtUtil;
import com.szw.sys.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Szw 2001
 * @Date 2023/5/3 17:16
 * @Slogn 致未来的你！
 */
@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void createJwt(){
        User user = new User();
        user.setUsername("xiaoni");
        user.setEmail("123@qq.com");
        String token = jwtUtil.createToken(user);
        System.out.println(token);

    }

    @Test
    public void parseJwt(){
        Claims claims = jwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0NGJlOTZiNC0wNDE5LTQ3MDYtYmJkZi0wMDBjZTk4ZjNlZjMiLCJzdWIiOiJ7XCJlbWFpbFwiOlwiMTIzQHFxLmNvbVwiLFwidXNlcm5hbWVcIjpcInhpYW9uaVwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4MzEwNTU0MiwiZXhwIjoxNjgzMTA3MzQyfQ.4jlkjGhpENK4zvd-zoRrYSUEdIObJfjZOMeqYj36PHk");
        System.out.println(claims);

    }

    @Test
    public void parseJwt2(){
        User user = jwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0NGJlOTZiNC0wNDE5LTQ3MDYtYmJkZi0wMDBjZTk4ZjNlZjMiLCJzdWIiOiJ7XCJlbWFpbFwiOlwiMTIzQHFxLmNvbVwiLFwidXNlcm5hbWVcIjpcInhpYW9uaVwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4MzEwNTU0MiwiZXhwIjoxNjgzMTA3MzQyfQ.4jlkjGhpENK4zvd-zoRrYSUEdIObJfjZOMeqYj36PHk", User.class);
        System.out.println(user);
        System.out.println("1233456");
    }
}
