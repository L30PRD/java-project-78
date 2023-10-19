package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private boolean requiredFlag = false;
    private final LinkedHashMap<String, Predicate<Object>> map = new LinkedHashMap<>();;

    public final boolean isValid(Object obj) {
        if (map.get("required").toString().equals("false")) {
            return false;
        }
        return map.values().stream().allMatch(x -> x.test(obj));
    }

    public final void add(String type, Predicate<Object> obj) {
        map.put(type, obj);
    }

    public final void changeFlag() {
        this.requiredFlag = !this.requiredFlag;
    }

    public final boolean isRequiredFlag() {
        return requiredFlag;
    }
}
