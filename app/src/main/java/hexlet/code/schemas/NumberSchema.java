package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> numb = obj -> obj instanceof Integer || obj == null;
        add(numb);
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positive = x ->  x == null || (int) x > 0;
        add(positive);
        return this;
    }

    public NumberSchema range(int a, int b) {
        Predicate<Object> range = x -> (int) x >= a && (int) x <= b;
        add(range);
        return this;
    }
}
