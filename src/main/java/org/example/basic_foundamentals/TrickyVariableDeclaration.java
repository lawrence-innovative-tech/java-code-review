package org.example.basic_foundamentals;

public class TrickyVariableDeclaration {

    int shadowingVariables = 10;


    public static void main(String args[]){
        TrickyVariableDeclaration trickyVariableDeclaration = new TrickyVariableDeclaration();
        trickyVariableDeclaration.variableShadowing(10);

    }

    /* for the instance variable and parameter variable are same name.
    * Accessing the instance variable use "this" keyword.
    * Access to Parameter variable as use reference name alone, best eg(while using constructor
    * using the same name as parameter variables name to assign use this keyword.
    *
    * */
    public void variableShadowing(int shadowingVariables){

        System.out.println("Instance shadowing: " + this.shadowingVariables);
        System.out.println("Parameter shadowing: " + shadowingVariables);
    }

    /* Final keyword nunes
    * 1. Primitive variable - Once assigned or initialed the value into the variables we can't change it,
      eg, final int finalVariables = 10; -> we can't change the value, if tried to change we get compiler error.
      *
    * 2. We can't change the reference's instead we change the reference pointing the variables values.
      eg, final StringBuilder sb = new StringBuilder("Hello");
      sb.append(" World"); // ✅ ALLOWED: Modifying the object's state
      System.out.println(sb); // Output: "Hello World"
      // sb = new StringBuilder("New"); // ❌ COMPILER ERROR: Cannot reassign the reference.
      *
    * 3. Final instance variables must be assigned a value by the time the constructor finishes
        eg, final int x; // Blank final - must be initialized in constructor
        final int y = 10; // Initialized at declaration
        public FinalTest(int value) {
            this.x = value; // ✅ Mandatory. Forgetting this causes a compiler error.
        }
        // public FinalTest() { } // ❌ COMPILER ERROR: Constructor must assign value to x.
        *
      *
    * Effectively final Variables (for Lambdas)
        A variable or parameter that is not declared final but whose value is never changed after
        initialization is "effectively final". This is important for access in lambda expressions and
        anonymous classes.
        int effectiveFinalVar = 50; // Not declared 'final'...
        // effectiveFinalVar = 60; // ❌ If you uncomment this, the line below breaks.

        Runnable r = () -> {
            System.out.println("Value is: " + effectiveFinalVar); // ✅ Allowed
            // effectiveFinalVar = 10; // ❌ Can't modify it inside the lambda either
        };
        new Thread(r).start();

        Key Takeaway: Lambda expressions can only use variables that are final or effectively final.
    *
    * */
    public void finalKeywordNunes(){
        // The examples are in the command block.
    }

    /* Default Values vs. No Default Values
    * This is a crucial distinction between instance variables and local variables.
           public class DefaultValues {
                int instanceInt; // Gets default value: 0
                String instanceString; // Gets default value: null
                void myMethod() {
                    int localInt;
                    String localString;
                    // System.out.println(localInt); // ❌ COMPILER ERROR: variable might not have been initialized
                    // System.out.println(localString); // ❌ COMPILER ERROR
                    localInt = 5; // Must be initialized before use
                    System.out.println(localInt); // ✅ Now it's fine
                }
            }
    * */
}
