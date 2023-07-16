package thread.generic;

public class GenericClass<T> {
    private T value;

    public GenericClass(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public T getClassValue(Class<T> arg) {
        return (T)value;
    }

    @Override
    public String toString() {
        return "thread.generic.GenericClass{" +
                "value=" + value +
                '}';
    }
}
