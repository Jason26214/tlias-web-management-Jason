package com.jason;

import com.jason.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","Tom");
        claims.put("username","tom123");

        JwtUtils.generateJwt(claims);
    }

    @Test
    public void parseJwt() {
        String jwt = "eyJKV1QiOiJKU3BXZGh1UEdibE5aQXBWY2xtWCIsImFsZyI6IkhTMjU2In0.eyJqdGkiOiJyUVJrJHlOOjclKkJ3fUFfQS1dTX40Izt5R2E6YV9GeyIsImlzcyI6Ikphc29uIiwibmFtZSI6IlRvbSIsImlkIjoxLCJ1c2VybmFtZSI6InRvbTEyMyIsInN1YiI6InRvbTEyMyIsImlhdCI6MTcyNDIyOTgzMywiZXhwIjoxNzI0MjczMDMzfQ.KVGJeNPFaZfs2ghyd2oEBFCo3ynXPikWqQRXp6WCTrQ";
        Claims claims = JwtUtils.parseJWT(jwt);


        System.out.println(claims);
    }

}
