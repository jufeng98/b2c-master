package org.javamaster.java.agent.collector;

import org.javamaster.java.agent.advice.Advice;

import java.util.concurrent.ConcurrentHashMap;

public class WatchCollector {

    private static final ConcurrentHashMap<String, WatchListener> listeners = new ConcurrentHashMap<>();

    public static void add(String key, Advice advice) {
        for (WatchListener value : listeners.values()) {
            value.call(key, advice);
        }
    }

    public static void addListener(String key, WatchListener watchListener) {
        listeners.put(key, watchListener);
    }

    public static void removeListener(String key) {
        listeners.remove(key);
    }

    public interface WatchListener {
        void call(String key, Advice advice);
    }

}
