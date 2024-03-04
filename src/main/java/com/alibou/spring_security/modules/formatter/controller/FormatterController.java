package com.alibou.spring_security.modules.formatter.controller;

import com.alibou.spring_security.formatters.DateFormatter;
import com.alibou.spring_security.formatters.IntegerFormatter;
import com.alibou.spring_security.modules.user.entities.User;
import com.alibou.spring_security.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class FormatterController {

    @Autowired
    private final DateFormatter dateFormatter;

    @Autowired
    private final IntegerFormatter integerFormatter;

    @Autowired
    private final UserRepository userRepository;


    @GetMapping("/example")
    public ResponseEntity<Object> formatterDate(@RequestParam(value = "date", required = false) Date date, // dựa vào kiểu du lieu ở day de chay vao ham formatter
                                @RequestParam(value = "number", required = false) Integer number) {
        System.out.println("example");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        Map<String, Object> result = new HashMap<>();
        result.put("formattedDate", dateFormatter.print(date, Locale.getDefault()));
        result.put("formattedNumber", integerFormatter.print(number, Locale.getDefault()));
        result.put("receivedDate", date);
        result.put("receivedNumber", number);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }


    @GetMapping("/list-user-page")
    @Transactional
    public String getAllUsers(Pageable pageable) {
        System.out.println("page: " + pageable.getPageNumber());
        System.out.println("size: " + pageable.getPageSize());
        return "list-user-page";
    }


    @GetMapping("/example-dependencies")
    public String customDependencies() {
        return "example-dependencies";
    }
}
