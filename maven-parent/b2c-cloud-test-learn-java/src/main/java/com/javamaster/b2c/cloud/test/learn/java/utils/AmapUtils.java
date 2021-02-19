package com.javamaster.b2c.cloud.test.learn.java.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.learn.java.model.Coordinates;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.javamaster.b2c.config.B2cMasterConsts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2020/2/26
 */
@Slf4j
public class AmapUtils {
    /**
     * 计算高德两个经纬度之间的距离
     *
     * @param start
     * @param end
     * @return
     */
    public static float calculateLineDistance(Coordinates start, Coordinates end) {
        double var2 = start.getLng();
        double var4 = start.getLat();
        double var6 = end.getLng();
        double var8 = end.getLat();
        var2 *= 0.01745329251994329D;
        var4 *= 0.01745329251994329D;
        var6 *= 0.01745329251994329D;
        var8 *= 0.01745329251994329D;
        double var10 = Math.sin(var2);
        double var12 = Math.sin(var4);
        double var14 = Math.cos(var2);
        double var16 = Math.cos(var4);
        double var18 = Math.sin(var6);
        double var20 = Math.sin(var8);
        double var22 = Math.cos(var6);
        double var24 = Math.cos(var8);
        double[] var26 = new double[3];
        double[] var27 = new double[3];
        var26[0] = var16 * var14;
        var26[1] = var16 * var10;
        var26[2] = var12;
        var27[0] = var24 * var22;
        var27[1] = var24 * var18;
        var27[2] = var20;
        double var28 = Math.sqrt((var26[0] - var27[0]) * (var26[0] - var27[0]) + (var26[1] - var27[1]) * (var26[1] - var27[1]) + (var26[2] - var27[2]) * (var26[2] - var27[2]));
        return (float) (Math.asin(var28 / 2.0D) * 1.27420015798544E7D);
    }

    public static List<String> getLngLat(List<String> addresses) throws Exception {
        String url = "https://restapi.amap.com/v3/geocode/geo";
        String key = B2cMasterConsts.Map.GAODE_KEY_2;
        boolean batch = true;
        String params = "key=" + key + "&output=JSON&batch=true" + "&address=" + StringUtils.join(addresses, "|");
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + params;
            log.info("req:{}", urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("res:{}", result.toString());
            JSONObject jsonObject = JSONObject.parseObject(result.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
            List<String> locations = jsonArray.stream().map(obj -> {
                JSONObject jsonObject1 = (JSONObject) obj;
                return jsonObject1.getString("location");
            }).collect(Collectors.toList());
            return locations;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ignored) {
            }
        }
    }
}
