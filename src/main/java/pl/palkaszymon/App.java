package pl.palkaszymon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello Szymon");

        List<String> names = Arrays.asList("Jakub", "Michał", "Agnieszka", "Kasia");
        Greeter greeter = new Greeter();
        String[] namesAsArray = {"Jakub"};

        ArrayList<String> ladies = new ArrayList<>();

        for (String name : names){
            if(name.endsWith("a")){
                ladies.add(name);
            }
        }

        for (String ladyName : ladies){
            greeter.greet(ladyName);
        }
        names.stream()
                .filter(name -> name.endsWith("a"))
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .forEach(greeter::greet);
    }
}
