package com.jc.rest.webservices.restfulwebservices.controller;

import com.jc.rest.webservices.restfulwebservices.data.ExampleFilteringBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public ExampleFilteringBean returnExampleFilteringBean() {

        return new ExampleFilteringBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<ExampleFilteringBean> returnExampleFilteringListBean() {

        return new ArrayList(Arrays.asList(new ExampleFilteringBean("value1", "value2", "value3"), new ExampleFilteringBean("value11", "value22", "value33")));
    }
}
