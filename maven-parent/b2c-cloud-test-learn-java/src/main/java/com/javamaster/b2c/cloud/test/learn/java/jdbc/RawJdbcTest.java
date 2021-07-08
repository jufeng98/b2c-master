package com.javamaster.b2c.cloud.test.learn.java.jdbc;

import static com.javamaster.b2c.cloud.test.learn.java.utils.PropertiesUtils.getProp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.*;
import java.util.Scanner;

/**
 * @author yudong
 * @date 2020/9/25
 */
@Slf4j
public class RawJdbcTest {


    @SneakyThrows
    public static void main(String[] args) {
        Connection connection = DriverManager.getConnection(getProp("Local.URL_ONLINE_EXAM"),
                getProp("Local.USERNAME"), getProp("Local.PASSWORD"));
        Scanner scanner = new Scanner(System.in);
        Statement statement = connection.createStatement();
        while (true) {
            System.out.print("input sql:");
            String sql = scanner.nextLine();
            if ("exit".equals(sql)) {
                break;
            }
            boolean isResultSet = statement.execute(sql);
            if (isResultSet) {
                ResultSet resultSet = statement.getResultSet();
                printResultSet(resultSet);
            }
        }
        statement.close();
        connection.close();
    }

    @Test
    @SneakyThrows
    public void test() {
        String sql = "select username,password from users limit 0,1";
        Connection connection = DriverManager.getConnection(getProp("Local.URL_ONLINE_EXAM"),
                getProp("Local.USERNAME"), getProp("Local.PASSWORD"));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        printResultSet(resultSet);
        resultSet.close();
        statement.close();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        printResultSet(resultSet);
        resultSet.close();
        preparedStatement.close();

        String sql1 = "select username,password from users where username = ?";
        preparedStatement = connection.prepareStatement(sql1);
        preparedStatement.setString(1, "1050106158");
        resultSet = preparedStatement.executeQuery(sql);
        printResultSet(resultSet);
        resultSet.close();
        preparedStatement.close();

        connection.close();
    }


    @SneakyThrows
    private static void printResultSet(ResultSet resultSet) {
        int length = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= length; i++) {
            String name = resultSet.getMetaData().getColumnName(i);
            System.out.print(name + " ");
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= length; i++) {
                System.out.print(resultSet.getObject(i) + " ");
            }
            System.out.println();
        }
    }
}
