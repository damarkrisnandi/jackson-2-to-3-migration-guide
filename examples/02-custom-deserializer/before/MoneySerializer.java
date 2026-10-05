// JACKSON 2 (reference only - not compiled)
package com.example;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class MoneySerializer extends StdSerializer<Money> {
    public MoneySerializer() { super(Money.class); }

    @Override
    public void serialize(Money value, JsonGenerator g, SerializerProvider provider) throws IOException {
        g.writeString(value.amount().toPlainString() + " " + value.currency());
    }
}
