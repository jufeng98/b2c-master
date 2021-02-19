package org.javamaster.b2c.spring.aop.service.impl;

import lombok.SneakyThrows;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.javamaster.b2c.spring.aop.SpringAopApplication;
import org.javamaster.b2c.spring.aop.annos.AopLog;
import org.javamaster.b2c.spring.aop.model.Demo;
import org.javamaster.b2c.spring.aop.model.Inventor;
import org.javamaster.b2c.spring.aop.model.Simple;
import org.javamaster.b2c.spring.aop.service.SpringService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yudong
 * @date 2020/6/4
 */
@Service
public class SpringServiceImpl implements SpringService {

    @Value("#{systemProperties['file.encoding']}")
    private String encoding;
    @Value("#{systemEnvironment['JAVA_HOME']}")
    private String home;

    @Override
    public String test() {
        System.out.println(encoding);
        System.out.println(home);
        return "test";
    }

    @Override
    @AopLog
    public String test1(String param) {
        return param;
    }

    @Test
    public void junitTest() {
        ExpressionParser parser = SpringAopApplication.parser;

        Expression exp = parser.parseExpression("'Hello World'");
        System.out.println((String) exp.getValue());

        System.out.println(parser.parseExpression("6.0221415E+23").getValue());

        System.out.println(parser.parseExpression("true").getValue());

        System.out.println(parser.parseExpression("null").getValue());

        Expression exp1 = parser.parseExpression("'Hello World'.concat('!')");
        System.out.println((String) exp1.getValue());

        // invokes 'getBytes()'
        Expression exp2 = parser.parseExpression("'Hello World'.bytes");
        System.out.println((byte[]) exp2.getValue());

        Expression exp3 = parser.parseExpression("'Hello World'.bytes.length");
        System.out.println((Integer) exp3.getValue());

        Expression exp4 = parser.parseExpression("new String('hello world').toUpperCase()");
        System.out.println(exp4.getValue(String.class));

        System.out.println(parser.parseExpression("'abc'.substring(2, 3)").getValue(String.class));
    }

    @Test
    public void junitTest1() {
        ExpressionParser parser = SpringAopApplication.parser;

        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, Calendar.AUGUST, 9);
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        Expression exp = parser.parseExpression("name");
        System.out.println((String) exp.getValue(tesla));

        Inventor einstein = parser.parseExpression("new org.javamaster.b2c.spring.aop.model.Inventor('Albert Einstein', 'German')").getValue(Inventor.class);
        System.out.println(einstein);

        EvaluationContext context = new StandardEvaluationContext(tesla);
        System.out.println((String) exp.getValue(context));

        System.out.println(parser.parseExpression("#{name}", ParserContext.TEMPLATE_EXPRESSION).getValue(tesla));

        System.out.println(parser.parseExpression("name == 'Nikola Tesla'").getValue(context, Boolean.class));
    }

    @Test
    public void junitTest2() {
        ExpressionParser parser = SpringAopApplication.parser;

        Simple simple = new Simple();
        simple.booleanList.add(true);
        StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);
        parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");
        System.out.println(simple.booleanList.get(0));

        SpelParserConfiguration config = new SpelParserConfiguration(true, true);
        ExpressionParser parser1 = new SpelExpressionParser(config);
        Expression expression1 = parser1.parseExpression("list[3]");
        Demo demo = new Demo();
        System.out.println(expression1.getValue(demo));
    }

    @Test
    public void junitTest3() {
        ExpressionParser parser = SpringAopApplication.parser;

        System.out.println(parser.parseExpression("#{T(java.lang.Math).random() * 100.0}", ParserContext.TEMPLATE_EXPRESSION).getValue(Double.class));

        System.out.println(parser.parseExpression("T(java.util.Date)").getValue(Class.class));

        // java.lang包内的不需要使用全限定名
        System.out.println(parser.parseExpression("T(String)").getValue(Class.class));

        System.out.println(parser.parseExpression("2==2").getValue(Boolean.class));

        System.out.println(parser.parseExpression("2<-5.0").getValue(Boolean.class));

        System.out.println(parser.parseExpression("'black'<'block'").getValue(Boolean.class));

        System.out.println(parser.parseExpression("'xyz' instanceof T(Integer)").getValue(Boolean.class));

        System.out.println(parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class));

        System.out.println(parser.parseExpression("'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class));

        System.out.println(parser.parseExpression("true and false").getValue(Boolean.class));

        System.out.println(parser.parseExpression("true or false").getValue(Boolean.class));

        System.out.println(parser.parseExpression("!true").getValue(Boolean.class));

        System.out.println(parser.parseExpression("1 + 1").getValue(Integer.class));

        System.out.println(parser.parseExpression("1 - -3").getValue(Integer.class));

        System.out.println(parser.parseExpression("6 / -3").getValue(Integer.class));

        System.out.println(parser.parseExpression("'test' + ' ' + 'string'").getValue(String.class));
    }

    @SneakyThrows
    @Test
    public void junitTest4() {
        ExpressionParser parser = SpringAopApplication.parser;

        List<Integer> primes = new ArrayList<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("primes", primes);

        List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(context);
        System.out.println(primesGreaterThanTen);

        context.registerFunction("reverseString", SpringServiceImpl.class.getDeclaredMethod("reverseString", String.class));
        System.out.println(parser.parseExpression("#reverseString('hello')").getValue(context, String.class));
    }


    public static String reverseString(String input) {
        StringBuilder backwards = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            backwards.append(input.charAt(input.length() - 1 - i));
        }
        return backwards.toString();
    }

    @Test
    public void junitTest5() {
        ExpressionParser parser = SpringAopApplication.parser;

        System.out.println(parser.parseExpression("false ? 'trueExp' : 'falseExp'").getValue(String.class));

        Inventor tesla = new Inventor("Nikola Tesla", new Date(), "Serbian");
        EvaluationContext context = new StandardEvaluationContext(tesla);

        System.out.println(parser.parseExpression("name?:'Unknown'").getValue(context, String.class));
        System.out.println(parser.parseExpression("name?.length()").getValue(context, Integer.class));

        tesla.setName(null);
        System.out.println(parser.parseExpression("name?:'Unknown'").getValue(context, String.class));
        System.out.println(parser.parseExpression("name?.length()").getValue(context, Integer.class));

    }

    @Test
    @SuppressWarnings("ALL")
    public void junitTest6() {
        ExpressionParser parser = SpringAopApplication.parser;
        Inventor tesla = new Inventor("Nikola Tesla", new Date(), "Bei Jin");
        Inventor tesla1 = new Inventor("Nikola Tesla", new Date(), "Shang Hai");
        Inventor tesla2 = new Inventor("Nikola Tesla", new Date(), "New York");
        Inventor tesla3 = new Inventor("Nikola Tesla", new Date(), "Serbian");
        Inventor tesla4 = new Inventor("Nikola Tesla", new Date(), "Serbian");
        List<Inventor> inventors = Arrays.asList(tesla, tesla1, tesla2, tesla3, tesla4);
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("inventors", inventors);

        List<Inventor> list = (List<Inventor>) parser.parseExpression("#inventors.?[serbian=='Serbian']").getValue(context);
        System.out.println(list);

        List<Inventor> list1 = (List<Inventor>) parser.parseExpression("#inventors.![serbian]").getValue(context);
        System.out.println(list instanceof Iterable);
        System.out.println(list1);

        List<Inventor> list2 = (List<Inventor>) parser.parseExpression("#inventors.![serbian=='Serbian']").getValue(context);
        System.out.println(list2);

        Map<String, Object> params = new HashMap<>(1, 1);
        params.put("backOrderCode", "H12345764564");
        context.setVariable("params", params);
        Object object = parser.parseExpression("#params[backOrderCode]").getValue(context);
        System.out.println("1." + object);

        StandardEvaluationContext context1 = new StandardEvaluationContext(params);
        Object object1 = parser.parseExpression("#params[backOrderCode]").getValue(context);
        System.out.println("2." + object1);

    }

    @Test
    @SuppressWarnings("ALL")
    public void junitTest7() {
        ExpressionParser parser = SpringAopApplication.parser;

        JSONArray backOrderInfos = new JSONArray();
        JSONObject backOrderInfo = new JSONObject();
        backOrderInfo.put("backOrderCode", "H200611SPVPI6");
        backOrderInfo.put("sourceName", "liangyudong");
        backOrderInfos.add(backOrderInfo);
        backOrderInfo.put("backOrderCode", "H200615I3R16P");
        backOrderInfo.put("sourceName", "jufeng");
        backOrderInfos.add(backOrderInfo);
        Object[] objects = new Object[]{"box", backOrderInfos};
        System.out.println(parser.parseExpression("#{[1].![#this.opt('backOrderCode')]}",
                ParserContext.TEMPLATE_EXPRESSION).getValue(objects));


        StandardEvaluationContext context1 = new StandardEvaluationContext(objects);
        Object object1 = parser.parseExpression("[1].![#this.opt('backOrderCode')]").getValue(context1);
        System.out.println(object1);

    }
}
