import basics.Customer;
import oop.SomeClass;

public class Main {
    public static void main(String[] args) {
        Customer man = new Customer("Man");
        System.err.println(man);
//        SomeClass someClass = new SomeClass("string", 22);
//        System.err.println(someClass);
        SomeClass noArgs = new SomeClass();
    }
}
