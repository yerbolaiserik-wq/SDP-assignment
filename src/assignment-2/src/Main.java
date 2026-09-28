import abstractfactory.AbstractFactory;
import factorymethod.FactoryMethod;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Factory Method ===");
        FactoryMethod.run();

        System.out.println();
        System.out.println("=== Abstract Factory ===");
        AbstractFactory.run();
    }
}
