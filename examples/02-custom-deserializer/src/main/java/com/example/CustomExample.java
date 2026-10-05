package com.example;

import java.math.BigDecimal;

import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

public class CustomExample {
    public static void main(String[] args) {
        // Annotation-based registration (see Money) works with a plain mapper:
        JsonMapper mapper = JsonMapper.builder().build();
        String json = mapper.writeValueAsString(new Money(new BigDecimal("12.50"), "EUR"));
        System.out.println(json);
        System.out.println(mapper.readValue(json, Money.class));
        System.out.println(mapper.readValue("{\"amount\":3,\"currency\":\"USD\"}", Money.class));

        // Programmatic registration: modules are added through the builder.
        SimpleModule module = new SimpleModule("money")
                .addSerializer(Money.class, new MoneySerializer())
                .addDeserializer(Money.class, new MoneyDeserializer());
        JsonMapper viaModule = JsonMapper.builder().addModule(module).build();
        System.out.println(viaModule.writeValueAsString(new Money(BigDecimal.ONE, "GBP")));
    }
}
