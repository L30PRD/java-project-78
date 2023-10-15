package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hexlet.code.schemas.StringSchema;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class stringTest {
    StringSchema s;

    @BeforeEach
    public void prepare() {
        Validator v = new Validator();
        s = v.string();
    }
    @Test
    public void allTest() {
        assertTrue(s.isValid(""));
        assertTrue(s.isValid(null));
        s.required();
        assertFalse(s.isValid(null));
        assertFalse(s.isValid(""));
        assertFalse(s.isValid(5));
        assertTrue(s.isValid("what does the fox say"));
        assertTrue(s.isValid("hexlet"));
        assertTrue(s.contains("wh").isValid("what does the fox say"));
        assertFalse(s.contains("whatthe").isValid("what does the fox say"));
    }



}
