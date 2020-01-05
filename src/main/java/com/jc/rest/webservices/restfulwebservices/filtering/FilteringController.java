package com.jc.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    /**
     * It will filter values set by @JsonIgnore property declared in ExampleFilteringBeanStatically
     *
     * @return Bean with the fields filtered
     */
    @GetMapping("/filtering-statically")
    public ExampleFilteringBeanStatically returnExampleFilteringBeanStatically() {

        return new ExampleFilteringBeanStatically("value1", "value2", "value3");
    }

    /**
     * It will filter list values set by @JsonIgnore property declared in ExampleFilteringBeanStatically
     *
     * @return list of beans with the fields filtered
     */
    @GetMapping("/filtering-list-statically")
    public List<ExampleFilteringBeanStatically> returnExampleFilteringListBeanStatically() {

        return new ArrayList(Arrays.asList(new ExampleFilteringBeanStatically("value1", "value2", "value3"),
                                           new ExampleFilteringBeanStatically("value11", "value22", "value33")));
    }

    /**
     * It will filter dynamically field 3
     *
     * @return mapping with the bean filtered
     */
    @GetMapping("/filtering-dynamically")
    public MappingJacksonValue returnExampleFilteringBeanDynamically() {

        ExampleFilteringBeanDynamically exampleFilteringBeanDynamically = new ExampleFilteringBeanDynamically("value1", "value2", "value3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("FilterMyBean", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(exampleFilteringBeanDynamically);
        mapping.setFilters(filters);
        return mapping;
    }

    /**
     * It will filter the list dynamically, excluding field 2
     *
     * @return mapping with the bean filtered
     */
    @GetMapping("/filtering-list-dynamically")
    public MappingJacksonValue returnExampleFilteringListBeanDynamically() {

        List<ExampleFilteringBeanDynamically> exampleFilteringBeanDynamicallyList =
                Arrays.asList(new ExampleFilteringBeanDynamically("value1", "value2", "value3"),
                              new ExampleFilteringBeanDynamically("value11", "value22", "value33"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("FilterMyBean", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(exampleFilteringBeanDynamicallyList);
        mapping.setFilters(filters);
        return mapping;
    }
}
