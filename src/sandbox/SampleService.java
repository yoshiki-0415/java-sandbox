package sandbox;

public interface SampleService {
    // Java 9:privateインタフェース・メソッド
    private void defaultPrivateMethod() {
        System.out.println("private method of interface");
    }

    // Java 8:デフォルトメソッド
    default void defaultMethod() {
        defaultPrivateMethod();
    }
}
