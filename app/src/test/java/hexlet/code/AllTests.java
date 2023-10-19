package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class AllTests {
    private StringSchema s;
    private NumberSchema n;
    private MapSchema m;

    private Validator v;

    @BeforeEach
    public void prepare() {
        v = new Validator();
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

    @Test
    public void mapShapeTest() {

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        m.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(m.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(m.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(m.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(m.isValid(human4));
    }
}
