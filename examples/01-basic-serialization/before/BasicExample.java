// JACKSON 2 (reference only - not compiled). Needs com.fasterxml.jackson.core:jackson-databind 2.x
package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;           // checked exception
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;         // separate artifact
import java.time.LocalDate;

public class BasicExample {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Person p = new Person("Ada", 36, LocalDate.of(1815, 12, 10));
        String json = mapper.writeValueAsString(p);
        Person back = mapper.readValue(json, Person.class);
        System.out.println(json);
        System.out.println(back);
    }
}
