package com.example;

import java.util.List;
import java.util.Map;
import java.util.Set;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.json.JsonMapper;

public class GenericsExample {
    public static void main(String[] args) {
        JsonMapper mapper = JsonMapper.builder().build();

        // TypeReference moved to tools.jackson.core.type
        List<Product> list = mapper.readValue(
                "[{\"id\":1,\"name\":\"Pen\"},{\"id\":2,\"name\":\"Ink\"}]",
                new TypeReference<List<Product>>() { });
        System.out.println(list);

        Map<String, List<Integer>> map = mapper.readValue(
                "{\"a\":[1,2],\"b\":[3]}", new TypeReference<Map<String, List<Integer>>>() { });
        System.out.println(map);

        // JavaType via the mapper's TypeFactory (obtained with getTypeFactory())
        JavaType setType = mapper.getTypeFactory().constructCollectionType(Set.class, String.class);
        Set<String> set = mapper.readValue("[\"x\",\"y\",\"x\"]", setType);
        System.out.println(set);

        // Generic wrapper types
        JavaType respType = mapper.getTypeFactory().constructParametricType(ApiResponse.class, Product.class);
        ApiResponse<Product> resp = mapper.readValue("{\"ok\":true,\"data\":{\"id\":9,\"name\":\"Cap\"}}", respType);
        System.out.println(resp);

        ApiResponse<List<Product>> resp2 = mapper.readValue(
                "{\"ok\":true,\"data\":[{\"id\":1,\"name\":\"Pen\"}]}",
                new TypeReference<ApiResponse<List<Product>>>() { });
        System.out.println(resp2.data().get(0).name());

        // Writing: a typed writer keeps generic info
        System.out.println(mapper.writerFor(new TypeReference<List<Product>>() { }).writeValueAsString(list));

        // Invalid input -> unchecked exception
        try {
            mapper.readValue("[{\"id\":\"oops\"}]", new TypeReference<List<Product>>() { });
        } catch (tools.jackson.core.JacksonException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }
    }
}
