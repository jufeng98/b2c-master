package org.javamaster.b2c.test.learn.java;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yudong
 * @date 2019/10/28
 */
public class Java9NewFeatureTest {

    @Test
    public void test() {
        Set<String> set = Set.of("A", "B", "C");
        System.out.println(set);
        List<String> list = List.of("A", "B", "C");
        System.out.println(list);
        Map<String, String> map = Map.of("A", "Apple", "B", "Boy", "C", "Cat");
        System.out.println(map);

        Map<String, String> map1 = Map.ofEntries(
                new AbstractMap.SimpleEntry<>("A", "Apple"),
                new AbstractMap.SimpleEntry<>("B", "Boy"),
                new AbstractMap.SimpleEntry<>("C", "Cat"));
        System.out.println(map1);
    }

    @Test
    public void test1() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");
        String np = "Not Present";
        Process p = pb.start();
        ProcessHandle.Info info = p.info();
        System.out.printf("Process ID : %s%n", p.pid());
        System.out.printf("Command name : %s%n", info.command().orElse(np));
        System.out.printf("Command line : %s%n", info.commandLine().orElse(np));

        System.out.printf("Start time: %s%n",
                info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
                        .toLocalDateTime().toString()).orElse(np));

        System.out.printf("Arguments : %s%n",
                info.arguments().map(a -> String.join(" ", a)).orElse(np));

        System.out.printf("User : %s%n", info.user().orElse(np));
    }

    @Test
    public void test2() {
        Stream.of("ab", "bb", "cddd", "", "e", "f").takeWhile(s -> s.length() == 2)
                .forEach(System.out::print);
        System.out.println("---");
        Stream.of("ab", "bb", "cddd", "", "e", "f").dropWhile(s -> s.length() == 2)
                .forEach(System.out::print);
    }

    @Test
    public void test3() throws IOException {
        Reader inputString = new StringReader("hello world");
        BufferedReader br = new BufferedReader(inputString);
        try (br) {
            System.out.println(br.readLine());
        }
    }

    @Test
    public void test4() {
        Card card = new CardImpl();
        card.displayCardDetails();
    }
}

