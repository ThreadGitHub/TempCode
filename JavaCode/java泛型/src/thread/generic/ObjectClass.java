package thread.generic;

public class ObjectClass {
    private String argName = "字符串";

    public <T> T getClass(Class<T> arg)  {
        return (T)argName;
    }
}
