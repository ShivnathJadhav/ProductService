package com.SpringBootProject.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sample")
public class SampleCotroller {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    //endpoint name: /api/v1/sample/hello/{name}
    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable(value = "name") String n){
        return "Hello " + n;
    }

    @GetMapping("/hello/name/{name}/age/{age}")
    public String bmsExample(@PathVariable(value = "name") String name, @PathVariable(value = "age") int age) {
        return "Hello " + name + ", you are " + age + " years old.";
    }

}
