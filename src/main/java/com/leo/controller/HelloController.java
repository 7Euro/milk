package com.leo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author leo
 * @date 2019/11/18 22:56
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String test () {
        return "login";
    }
}
