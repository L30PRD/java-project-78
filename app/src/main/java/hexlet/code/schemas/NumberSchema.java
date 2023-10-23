package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        add("required", value ->  value instanceof Integer);
    }

    public NumberSchema required() {
        changeFlag();
        return this;
    }

    public NumberSchema positive() {
        add("positive", value -> (int) value > 0);
        return this;
    }

    public NumberSchema range(int a, int b) {
        add("range", value -> (int) value >= a && (int) value <= b);
        return this;
    }
}
