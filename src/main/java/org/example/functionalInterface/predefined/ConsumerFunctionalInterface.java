package org.example.functionalInterface.predefined;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerFunctionalInterface {

    public static void main(String[] args) {

        Consumer<String> consumer = new Consumer<String>() {

            @Override
            public void accept(String string) {

            }

            @Override
            public Consumer<String> andThen(Consumer<? super String> after) {
                return Consumer.super.andThen(after);
            }
        };

    }
}
