// JACKSON 2 (reference only - not compiled)
package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Set;

public class GenericsExample {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> list = mapper.readValue("[{\"id\":1,\"name\":\"Pen\"}]",
                new TypeReference<List<Product>>() { });
        JavaType setType = mapper.getTypeFactory().constructCollectionType(Set.class, String.class);
        Set<String> set = mapper.readValue("[\"x\"]", setType);
        JavaType resp = mapper.getTypeFactory().constructParametricType(ApiResponse.class, Product.class);
    }
}
