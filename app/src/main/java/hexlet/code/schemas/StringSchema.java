package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        add("required", value -> value instanceof String && !value.toString().isEmpty());
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
