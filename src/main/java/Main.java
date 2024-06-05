import basics.Customer;
import functions.SomeTriesKt;
import functions.StandardLibFunctionsKt;
import oop.SomeClass;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Customer man = new Customer("Man");
        System.err.println(man);
//        SomeClass someClass = new SomeClass("string", 22);
//        System.err.println(someClass);
        SomeClass noArgs = new SomeClass();
        System.err.println(SomeTriesKt.realGen('r')); //todo using jvmName 'realGen'
        try {
            StandardLibFunctionsKt.require_assert_checkTries("a");
        } catch (IOException e) {
            throw new RuntimeException(e); //todo due to annotation on Kotlin fun
        }
    }
}
