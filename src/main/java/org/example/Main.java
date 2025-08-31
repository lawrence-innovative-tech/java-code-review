package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

String[] test = {"sfsf"};
//TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome! - "+ Future[].class.getClassLoader());
        System.out.println("Hello and welcome! 1 - "+ Future.class.getClassLoader());
        ;

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);

        }
        List<String> res = Main.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        res.forEach(System.out::println);
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Set<String> compare = new HashSet<>();
        for(int right = 0; right <= s.length() - 10; right++){
            if (!compare.add(s.substring(right, right + 10)) && !result.contains(s.substring(right, right + 10))){
                result.add(s.substring(right, right + 10));
            }
        }
        return result;
    }
}