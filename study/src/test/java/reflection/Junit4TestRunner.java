package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class Junit4TestRunner {

    @Test
    void run() throws Exception {
        Class<Junit4Test> clazz = Junit4Test.class;

        Method[] methods = clazz.getMethods();

        Arrays.stream(methods)
                .filter(method -> method.getAnnotation(MyTest.class) != null)
                .forEach(method -> {
                    try {
                        method.invoke(clazz.getDeclaredConstructor().newInstance());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                });
        // TODO Junit4Test에서 @MyTest 애노테이션이 있는 메소드 실행
    }
}
