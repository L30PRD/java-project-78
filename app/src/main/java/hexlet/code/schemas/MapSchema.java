package hexlet.code.schemas;


import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        add("required", value -> {
            if (isRequiredFlag()) {
                return value instanceof Map<?, ?>;
            }
            return true;
        });

    }

    public MapSchema required() {
        changeFlag();
        return this;
    }

    public MapSchema sizeof(int size) {
        add("sizeof", value -> ((Map<?, ?>) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> map) {
        add("shape", value -> map.keySet().stream().allMatch(key -> {
            Object obj = ((Map<?, ?>) value).get(key);
            return map.get(key).isValid(obj);
        }));
        return this;
    }
}
