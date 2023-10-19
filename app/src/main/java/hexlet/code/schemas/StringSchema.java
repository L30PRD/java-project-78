package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        add("required", value -> {
            if (isRequiredFlag()) {
                return value instanceof String && !value.toString().isEmpty();
            }
            return true;
        });
    }

    public StringSchema required() {
        changeFlag();
        return this;
    }

    public StringSchema minLength(int length) {
        add("minLength", value -> (value.toString().length() >= length));
        return this;
    }

    public StringSchema contains(String str) {
        add("contains", value -> (value.toString().contains(str)));
        return this;
    }
}
