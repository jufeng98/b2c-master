package com.javamaster.b2c.cloud.test.learn.java.java8;

import com.javamaster.b2c.cloud.test.learn.java.java8.model.Card;
import static java.util.stream.Collectors.groupingBy;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created on 2018/11/22.<br/>
 *
 * @author yudong
 */
public class FunctionInterfaceTest {

    @Test
    public void test() {
        Predicate<Card> predicate = Card::isExpensive;
        Predicate<Card> nPredicate = predicate.negate();
        Predicate<Card> andPredicate = predicate.and(card -> card.getColor().equals(Color.WHITE));
        Predicate<Card> orPredicate = predicate.or(card -> card.getColor().equals(Color.WHITE));
        // and和or方法是按照在表达式链中的位置，从左向右确定优先级的。因此，a.or(b).and(c)可以看作(a || b) && c
        Predicate<Card> andOrPredicate = nPredicate.and(andPredicate.or(orPredicate));
        System.out.println(andOrPredicate);
        System.out.println("---");

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        // 数学上会写作g(f(x))
        int result = h.apply(1);
        System.out.println(result);
        System.out.println("---");

        Function<Integer, Integer> k = x -> x + 1;
        Function<Integer, Integer> q = x -> x * 2;
        Function<Integer, Integer> r = k.compose(q);
        // 数学上会写作k(q(x))
        int result1 = r.apply(1);
        System.out.println(result1);
        System.out.println("---");

        System.out.println(integrate((double x) -> x + 10.0, 3, 7));
        System.out.println("---");

    }

    @Test
    public void test1() {
        File[] hiddenFiles = new File("F:\\").listFiles(File::isHidden);
        System.out.println(hiddenFiles[0]);
        System.out.println("---");

        List<Card> cards = new ArrayList<>();
        Supplier<Card> supplier = Card::new;
        System.out.println(supplier.get());
        System.out.println("---");

        Function<Integer, Card> function = Card::new;
        System.out.println(function.apply(6));
        System.out.println("---");

        for (int i = 0; i < 5; i++) {
            cards.add(getCard());
        }
        cards.forEach((card -> System.out.println(card)));
        System.out.println("---");

        System.out.println(filterCars(cards, card -> card.getColor().equals(Color.WHITE)));
        System.out.println("---");

        System.out.println(filterCars(cards, Card::isExpensive));

        Map<Integer, List<Card>> cardByAmt = cards.stream().filter(Card::isExpensive).collect(groupingBy(Card::getAmt));
        System.out.println(cardByAmt);
        System.out.println("---");

        cards.sort(Card::compareTo);
        cards.sort(Comparator.comparing(card -> card.getAmt()));
        cards.sort(Comparator.comparing(Card::getAmt).reversed());
        cards.sort(Comparator.comparing(Card::getAmt).reversed().thenComparing(Card::getCardvalue));
        List<String> list = cards.stream().filter(Card::isExpensive).sorted(Card::compareTo).map(Card::getCardvalue).collect(Collectors.toList());
        System.out.println(list);
        System.out.println("---");
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    public static List<Card> filterCars(List<Card> inventory,
                                        Predicate<Card> p) {
        return inventory.stream().filter(p).collect(Collectors.toList());
    }

    private static Card getCard() {
        Card card = new Card();
        card.setColor(RandomUtils.nextInt(0, 10) % 2 == 0 ? Color.BLACK : Color.WHITE);
        card.setOrderNo(RandomStringUtils.randomAlphabetic(6));
        card.setCardvalue(RandomStringUtils.randomAlphabetic(5));
        card.setCardcount("");
        card.setAmt(RandomUtils.nextInt(1, 7));
        card.setDetailedtype("");

        return card;
    }
}

