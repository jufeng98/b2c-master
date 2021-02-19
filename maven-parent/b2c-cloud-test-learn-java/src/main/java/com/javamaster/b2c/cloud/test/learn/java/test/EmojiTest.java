package com.javamaster.b2c.cloud.test.learn.java.test;

import com.vdurmont.emoji.EmojiParser;
import org.junit.Test;

/**
 * @author yudong
 * @date 2019/5/9
 */
public class EmojiTest {
    @Test
    public void test(){
        String str = "An 😀awesome 😃string with a few 😉emojis!";
        String result = EmojiParser.parseToAliases(str);
        System.out.println(result);

        System.out.println(EmojiParser.parseToHtmlHexadecimal(str));

        System.out.println(EmojiParser.removeAllEmojis(str));
    }
}
