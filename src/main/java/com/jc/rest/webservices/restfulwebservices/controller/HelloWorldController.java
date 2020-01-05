package com.jc.rest.webservices.restfulwebservices.controller;

import com.jc.rest.webservices.restfulwebservices.data.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {

        return "Hello world";
    }

    //hello world bean
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {

        return new HelloWorldBean("Hello World Bean");
    }

    //hello world bean internationalized
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {

        return messageSource.getMessage("good-morning.message", null, LocaleContextHolder.getLocale());
    }

    @GetMapping(path = "/hello-world-path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {

        return new HelloWorldBean(String.format("Hello world bean, %s", name));
    }
}
