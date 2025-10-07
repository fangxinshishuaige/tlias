package com.itheima;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){

        Claims claim = JwtUtils.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi6YeR5bq4IiwiaWQiOjEsInVzZXJuYW1lIjoiamlueW9uZyIsImV4cCI6MTc1OTc5NjU2N30.f6JYzndELG_XwQp4f7H_SLTDqDD6lDgCR6KdvyXrk4I");
        System.out.println(claim);
        System.out.println("-------------");
        System.out.println(claim.get("id"));
    }
}
