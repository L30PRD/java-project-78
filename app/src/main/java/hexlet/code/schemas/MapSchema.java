package hexlet.code.schemas;


import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> map = obj -> obj == null || obj instanceof Map<?,?>;
        add(map);
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeof = obj -> ((Map<?,?>) obj).size() == size;
        add(sizeof);
        return this;
    }
}
