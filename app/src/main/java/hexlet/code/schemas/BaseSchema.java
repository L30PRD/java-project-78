package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class BaseSchema {

    List<Predicate<Object>> list = new ArrayList<>();

    public BaseSchema required() {
        Predicate<Object> required = Objects::nonNull;
        list.add(required);
        return this;
    }

    public final boolean isValid(Object obj) {
        return list.stream().allMatch(x -> x.test(obj));
    }

    public void add(Predicate<Object> obj) {
        list.add(obj);
    }
}
