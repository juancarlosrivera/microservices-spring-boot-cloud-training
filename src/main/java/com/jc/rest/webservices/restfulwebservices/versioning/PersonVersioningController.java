package com.jc.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    /**
     * Versioning using URIs. Used by Twitter
     */
    @GetMapping("/v1/person")
    public PersonV1 personV1() {

        return new PersonV1("JC Rivera");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2() {

        return new PersonV2(new Name("JC", "Rivera"));
    }

    /**
     * Versioning using params. Used by Amazon
     */
    @GetMapping(value = "/person/param",
                params = "version=v1")
    public PersonV1 paramV1() {

        return new PersonV1("JC Rivera");
    }

    @GetMapping(value = "/person/param",
                params = "version=v2")
    public PersonV2 paramV2() {

        return new PersonV2(new Name("JC", "Rivera"));
    }

    /**
     * Versioning using http header. Used by Microsoft
     */
    @GetMapping(value = "/person/header",
                headers = "X-API-VERSION=1")
    public PersonV1 headerV1() {

        return new PersonV1("JC Rivera");
    }

    @GetMapping(value = "/person/header",
                headers = "X-API-VERSION=2")
    public PersonV2 headerV2() {

        return new PersonV2(new Name("JC", "Rivera"));
    }

    /**
     * Versioning using mime type (content negotiation or accept header). Used by Github
     */
    @GetMapping(value = "/person/produces",
                produces = "application/jc.app-v1+json")
    public PersonV1 producesV1() {

        return new PersonV1("JC Rivera");
    }

    @GetMapping(value = "/person/produces",
                produces = "application/jc.app-v2+json")
    public PersonV2 producesV2() {

        return new PersonV2(new Name("JC", "Rivera"));
    }
}
