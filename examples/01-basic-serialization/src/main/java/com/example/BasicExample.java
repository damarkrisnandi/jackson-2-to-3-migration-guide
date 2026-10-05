package com.example;

import java.time.LocalDate;

import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

public class BasicExample {
    public static void main(String[] args) {
        // ObjectMapper is immutable in Jackson 3: configure through the builder.
        // java.time (JSR-310), Jdk8 and parameter-names support are built in: no module registration.
        // WRITE_DATES_AS_TIMESTAMPS is already disabled by default.
        ObjectMapper mapper = JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();

        Person p = new Person("Ada", 36, LocalDate.of(1815, 12, 10));

        // No try/catch or "throws": JacksonException is unchecked.
        String json = mapper.writeValueAsString(p);
        Person back = mapper.readValue(json, Person.class);

        System.out.println(json);
        System.out.println(back);
    }
}
