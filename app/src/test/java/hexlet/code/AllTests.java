package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllTests {
    StringSchema s;
    NumberSchema n;

    @BeforeEach
    public void prepare() {
        Validator v = new Validator();
        s = v.string();
        n = v.number();

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

}
