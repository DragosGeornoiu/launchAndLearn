package ro.dragos.geornoiu.jep.cafe.refactoring.text.blocks.haiku;

import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.tuple.primitive.CharIntPair;
import org.eclipse.collections.impl.factory.Strings;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
//@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class Haiku {
    private final String haiku = """
            Breaking Through                  Pavement                  Wakin' with Bacon        Homeward Found
            ----------------                  --------                  -----------------        --------------
            The wall disappears               Beautiful pavement!       Wakin' with Bacon        House is where I am
            As soon as you break through the  Imperfect path before me  On a Saturday morning    Home is where I want to be
            Intimidation                      Thank you for the ride    Life’s little pleasures  Both may be the same
                        
            Winter Slip and Slide              Simple Nothings                With Deepest Regrets
            ---------------------              ---------------                --------------------
            Run up the ladder                  A simple flower                With deepest regrets
            Swoosh down the slide in the snow  Petals shine vibrant and pure  That which you have yet to write
            Winter slip and slide              Stares into the void           At death, won't be wrote
                        
            Caffeinated Coding Rituals  Finding Solace               Curious Cat                Eleven
            --------------------------  --------------               -----------                ------
            I arrange my desk,          Floating marshmallows        I see something move       This is how many
            refactor some ugly code,    Cocoa brewed hot underneath  What it is, I am not sure  Haiku I write before I
            and drink my coffee.        Comfort in a cup             Should I pounce or not?    Write a new tech blog.
            """;


//    @Benchmark //uncomment this if you want to be added in comparison
    public Object distinct_letters_EC() {
        String distinctLetters = Strings.asChars(this.haiku)
                .select(Character::isAlphabetic)
                .collectChar(Character::toLowerCase)
                .distinct()
                .toString();

        return distinctLetters;
    }

    //    @Benchmark //uncomment this if you want to be added in comparison
    public Object distinct_letters_Java17() {
        String distinctLetters = this.haiku.chars()
                .filter(Character::isAlphabetic)
                .map(Character::toLowerCase)
                .distinct()
                .mapToObj(Character::toString)
                .collect(Collectors.joining());

        return distinctLetters;
    }

    @Benchmark
    public Object top_letters_EC() {
        CharBag chars = Strings.asChars(this.haiku)
                .select(Character::isAlphabetic)
                .collectChar(Character::toLowerCase)
                .toBag();

        ListIterable<CharIntPair> top3 = chars.topOccurrences(3);
        return top3;
    }

    @Benchmark
    public Object top_letters_Java17_V1() {
        Map<String, Long> chars = this.haiku.chars()
                .filter(Character::isAlphabetic)
                .map(Character::toLowerCase)
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //this is how we could get the highest appearing
//        Map.Entry<String, Long> mostFrequentLetter = chars.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();

        //this is how we get a list of most frequent. It is a bug if we have 2 values which are the same
//        List<Map.Entry<String, Long>> mostFrequentLetters = chars.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(3).toList();

        //solving the previous issue also by inverting the map, regrouping the key-value pairs by value and putting the keys which have the same value in a list
        Map<Long, List<String>> map = chars.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(
                                Map.Entry::getKey,
                                Collectors.toList()
                        )
                ));

        Map.Entry<Long, List<String>> mostSeenLetters = map.entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .orElseThrow();

        return mostSeenLetters;
    }

    @Benchmark
    public Object top_letters_Java17_V2() {
        Map<String, Long> chars = this.haiku.chars()
                .filter(Character::isAlphabetic)
                .map(Character::toLowerCase)
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<Long, List<String>> map = chars.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(
                                Map.Entry::getKey,
                                Collectors.toList()
                        )
                ));

        List<Map.Entry<Long, List<String>>> mostSeenLetters = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(
                        Comparator.reverseOrder()
                )).toList();



        return mostSeenLetters;
    }

    @Benchmark
    public Object top_letters_Java17_V3_record() {
        record Letter (int codepoint) {
            Letter(int codepoint) {
                this.codepoint = Character.toLowerCase(codepoint);
            }
        }

        record LetterCount(long count) implements Comparable<LetterCount> {
            @Override
            public int compareTo(LetterCount o) {
                return Long.compare(this.count, o.count);
            }
        }

        record LetterByCount(Letter letter, LetterCount count) {
            LetterByCount(Map.Entry<Letter, LetterCount> entry) {
                this(entry.getKey(), entry.getValue());
            }
        }

        record LettersByCount(LetterCount count, List<Letter> letters) {
            LettersByCount(Map.Entry<LetterCount, List<Letter>> entry) {
                this(entry.getKey(), entry.getValue());
            }

            public static Comparator<? super LettersByCount> comparingByCount() {
                return Comparator.comparing(LettersByCount::count, Comparator.naturalOrder());
            }
        }

        Map<Letter, LetterCount> chars = this.haiku.chars()
                .filter(Character::isAlphabetic)
                .mapToObj(Letter::new)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.collectingAndThen(
                       Collectors.counting(), LetterCount::new
                )));

        Map<LetterCount, List<Letter>> map = chars.entrySet().stream()
                .map(LetterByCount::new)
                .collect(Collectors.groupingBy(
                        LetterByCount::count,
                        Collectors.mapping(
                                LetterByCount::letter,
                                Collectors.toList()
                        )
                ));

        var mostSeenLetters = map.entrySet().stream()
                .map(LettersByCount::new)
                .sorted(LettersByCount.comparingByCount().reversed()
                ).toList();



        return mostSeenLetters;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}