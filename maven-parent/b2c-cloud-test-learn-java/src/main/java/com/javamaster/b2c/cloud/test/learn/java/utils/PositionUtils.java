package com.javamaster.b2c.cloud.test.learn.java.utils;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author yudong
 * @date 2020/3/17
 */
public class PositionUtils {
    public static double pi = 3.1415926535897932384626;

    private static double PI = Math.PI;
    private static double X_PI = PI * 3000.0 / 180.0;

    public static Pair<Double, Double> bd09_To_Gcj02(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return Pair.of(gg_lon, gg_lat);
    }

    public static Pair<Double, Double> bd092GCJ(double glat, double glon) {
        double x = glon - 0.0065;
        double y = glat - 0.006;
        double[] latlon = new double[2];
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);
        latlon[0] = z * Math.sin(theta);
        latlon[1] = z * Math.cos(theta);
        return Pair.of(latlon[1], latlon[0]);
    }
}