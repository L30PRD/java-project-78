package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllTests {
    StringSchema s;
    NumberSchema n;
    MapSchema m;

    @BeforeEach
    public void prepare() {
        Validator v = new Validator();
        s = v.string();
        n = v.number();
        m = v.map();

    }
    @Test
    public void stringSchemeTest() {

        assertTrue(s.isValid(""));
        assertTrue(s.isValid(null));

        s.required();

        assertFalse(s.isValid(null));
        assertFalse(s.isValid(""));
        assertFalse(s.isValid(5));
        assertTrue(s.isValid("what does the fox say"));
        assertTrue(s.minLength(3).isValid("hexlet"));
        assertTrue(s.contains("wh").isValid("what does the fox say"));
        assertFalse(s.contains("whatthe").isValid("what does the fox say"));
    }

    @Test
    public void numberSchemeTest() {

        assertTrue(n.isValid(null));
        assertTrue(n.positive().isValid(null));

        n.required();

        assertFalse(n.isValid(null));
        assertFalse(n.isValid("5"));
        assertTrue(n.isValid(10));


        assertFalse(n.isValid(-10));
        assertFalse(n.isValid(0));

        n.range(5, 10);

        assertTrue(n.isValid(5));
        assertTrue(n.isValid(10));
        assertFalse(n.isValid(4));
        assertFalse(n.isValid(11));
    }

    @Test
    public void mapSchemeTest() {

        assertTrue(m.isValid(null));

        m.required();

        assertFalse(m.isValid(null));
        assertTrue(m.isValid(new HashMap()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(m.isValid(data));

        m.sizeof(2);

        assertFalse(m.isValid(data));
        data.put("key2", "value2");
        assertTrue(m.isValid(data));
    }
}
