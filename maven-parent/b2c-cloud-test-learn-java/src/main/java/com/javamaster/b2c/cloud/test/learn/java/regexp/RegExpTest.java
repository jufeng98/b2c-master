package com.javamaster.b2c.cloud.test.learn.java.regexp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yudong
 * @date 2019/7/15
 */
public class RegExpTest {

    @Test
    public void test() {
        String text = "This is the text to be searched for occurrences of the http:// pattern.";
        String regex = ".*http://.*";
        boolean matches = Pattern.matches(regex, text);
        System.out.println("matches = " + matches);
    }

    @Test
    public void test1() {
        String text = "This is the text which is to be searched for occurrences of the word 'is'.";
        String regex = "is";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
        }
    }

    @Test
    public void test2() {
        String text = "Line 1\nLine2\nLine3";
        Pattern pattern = Pattern.compile("^");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Found match at: " + matcher.start() + " to " + matcher.end());
        }
        System.out.println("------");
        pattern = Pattern.compile("^http://");
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Found match at: " + matcher.start() + " to " + matcher.end());
        }
        System.out.println("------");
        text = "http://jenkov.com";
        pattern = Pattern.compile(".com$");
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Found match at: " + matcher.start() + " to " + matcher.end());
        }
        System.out.println("------");
        text = "Mary had a little lamb";
        pattern = Pattern.compile("\\b");
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Found match at: " + matcher.start() + " to " + matcher.end());
        }
    }

    @Test
    public void test3() {
        String text = "John writes about this, and John writes about that, and John writes about everything. ";
        String patternString1 = "(John)";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("found: " + matcher.group(1));
        }
    }

    @Test
    public void test4() {
        String text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        String patternString1 = "(John) (.+?) ";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("found: " + matcher.group(1) +
                    " " + matcher.group(2));
        }
    }

    @Test
    public void test5() {
        String text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        String patternString1 = "((John) (.+?)) ";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("found: <" + matcher.group(1) +
                    "> <" + matcher.group(2) +
                    "> <" + matcher.group(3) + ">");
        }
    }

    @Test
    public void test6() {
        String text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        String patternString1 = "((John) (.+?)) ";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);
        String replaceAll = matcher.replaceAll("Joe Blocks ");
        System.out.println("replaceAll   = " + replaceAll);
        String replaceFirst = matcher.replaceFirst("Joe Blocks ");
        System.out.println("replaceFirst = " + replaceFirst);
    }

    @Test
    public void test7() {
        String text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        String patternString1 = "((John) (.+?)) ";
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, "Joe Blocks ");
            System.out.println(stringBuffer.toString());
        }
        matcher.appendTail(stringBuffer);
        System.out.println(stringBuffer.toString());
    }

    @Test
    public void test8() {
        String text = "This is the text to be searched for occurrences of the http:// pattern.";
        String patternString = "This is the";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        System.out.println("lookingAt = " + matcher.lookingAt());
        System.out.println("matches   = " + matcher.matches());
    }

    @Test
    public void test9() {
        String text = "A sep Text sep With sep Many sep Separators";
        String patternString = "sep";
        Pattern pattern = Pattern.compile(patternString);
        String[] split = pattern.split(text);
        System.out.println("split.length=" + split.length);
        for (String element : split) {
            System.out.println("element=" + element);
        }
    }
}
