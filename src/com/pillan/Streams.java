package com.pillan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N36",
                "B12", "B6",
                "G54", "G49", "G60", "G50", "g64",
                "I26", "I17", "I29",
                "O71");


        // No streams solution
        List<String> gNumbers = new ArrayList<>();

        someBingoNumbers.forEach(number ->{
            if (number.toUpperCase().startsWith("G")) {
                gNumbers.add(number);
                //System.out.println(number);
            }
        });
        gNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
        gNumbers.forEach((String s) -> System.out.println(s));


        System.out.println("**********************");


        // :: = Method reference
        // used when calling an existing method


        // Stream solution
        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        // Distinct removes dupes
        // concatinating two streams with .concat
        // count = count the objects

        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71");
        Stream<String> inNumberStream = Stream.of("N40", "N46", "I26", "I17", "I29", "O71");
        Stream<String> concatStream = Stream.concat(ioNumberStream,inNumberStream);
        System.out.println(concatStream.distinct().count());

    }

}
