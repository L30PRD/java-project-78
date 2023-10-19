package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

public class BaseSchema {
    boolean requiredFlag = false;
    LinkedHashMap<String, Predicate<Object>> map = new LinkedHashMap<>();;

    public final boolean isValid(Object obj) {
        if (map.get("required").toString().equals("false")) {
            return false;
        }
        return map.values().stream().allMatch(x -> x.test(obj));
    }

    public void add(String type, Predicate<Object> obj) {
        map.put(type, obj);
    }

    public void changeFlag() {
        this.requiredFlag = !this.requiredFlag;
    }

    public boolean isRequiredFlag() {
        return requiredFlag;
    }
}
