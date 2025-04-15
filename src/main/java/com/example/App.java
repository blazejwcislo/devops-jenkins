package com.example;

public final class App {

    private App() {
        throw new UnsupportedOperationException("Utility class.");
    }

    /**
     * Description.
     *
     * @param args desc
     */
    public static void main(final String[] args) {
        String maybeNull = System.getProperty("possibly.null");
        if (maybeNull == null) {
            // SpotBugs widzi, że tu może być null
            System.out.println(maybeNull.length());
        }

        System.out.println("Hello");
    }
}
