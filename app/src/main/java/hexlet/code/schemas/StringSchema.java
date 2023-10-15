package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class StringSchema {

    public List<Predicate<Object>> list = new ArrayList<>();

    public final boolean isValid(Object data) {
        if (data instanceof String || data == null) {
            return list.stream().allMatch(check -> check.test(data));
        }
        return false;
    }

    public StringSchema required() {
        Predicate<Object> required = input -> Objects.nonNull(input) && !input.toString().isEmpty();
        list.add(required);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<Object> minLength = input -> input == null || input.toString().length() >= length;
        list.add(minLength);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<Object> contains = input -> input.toString().contains(str);
        list.add(contains);
        return this;
    }
}
