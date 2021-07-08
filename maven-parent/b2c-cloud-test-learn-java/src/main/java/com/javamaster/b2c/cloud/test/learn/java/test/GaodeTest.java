package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.model.Coordinates;
import com.javamaster.b2c.cloud.test.learn.java.utils.AmapUtils;
import com.javamaster.b2c.cloud.test.learn.java.utils.PositionUtils;
import static com.javamaster.b2c.cloud.test.learn.java.utils.PropertiesUtils.getProp;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author yudong
 * @date 2020/2/19
 */
public class GaodeTest {

    @Test
    public void test() throws Exception {
        double longitude = 113.350255;
        double latitude = 23.147079;
        String url = "https://restapi.amap.com/v3/geocode/regeo";
        String params = "key=" + getProp("Map.GAODE_KEY") + "&output=JSON&location=" + longitude + "," + latitude;
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + params;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ignored) {
            }
        }
        System.out.println(result);
    }

    @Test
    public void test1() {
        Coordinates start = new Coordinates();
        start.setLng(110.196150);
        start.setLat(21.678881);
        Coordinates end = new Coordinates();
        end.setLng(110.195455);
        end.setLat(21.674458);
        System.out.println(AmapUtils.calculateLineDistance(start, end));
    }

    @Test
    public void test2() {
        // 113.852047057707,22.511171597629
        double lng = 113.858529;
        double lat = 22.517206;
        System.out.println(PositionUtils.bd09_To_Gcj02(lat, lng));
        System.out.println(PositionUtils.bd092GCJ(lat, lng));
    }

    @Test
    public void test3() {
        try {
            List<String> addresses = Collections.singletonList(
                    "广东省广州市天河区五山路31号东莞xx信息科技有限公司"
            );
            System.out.println(AmapUtils.getLngLat(addresses));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
