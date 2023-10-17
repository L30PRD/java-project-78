package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        Predicate<Object> str = obj -> obj instanceof String || obj == null;
        add(str);
    }

    @Override
    public BaseSchema required() {
        Predicate<Object> required = obj -> Objects.nonNull(obj) && !obj.toString().isEmpty();
        add(required);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<Object> minLength = obj -> obj == null || obj.toString().length() >= length;
        add(minLength);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<Object> contains = obj -> obj.toString().contains(str);
        add(contains);
        return this;
    }
}
