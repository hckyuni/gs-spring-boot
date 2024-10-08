package com.example.springboot;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final JdbcTemplate jdbcTemplate;

    public HelloController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/")
    public String index() {
	return "Greetings from Spring Boot!";
    }


    @GetMapping("/user")
    public String getUser(@RequestParam String username) {
        // 취약점이 있는 코드: SQL Injection 공격에 노출됨
        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        return jdbcTemplate.queryForObject(query, String.class);
    }
}