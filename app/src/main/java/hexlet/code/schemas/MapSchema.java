package hexlet.code.schemas;


import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> map = obj -> obj == null || obj instanceof Map<?, ?>;
        add(map);
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeof = obj -> ((Map<?, ?>) obj).size() == size;
        add(sizeof);
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema> map) {
        Predicate<Object> shape = obj -> obj == null || map.keySet().stream().allMatch(key -> {
            Object value = ((Map<?, ?>) obj).get(key);
            return map.get(key).isValid(value);
        });
        add(shape);
        return this;
    }
}
