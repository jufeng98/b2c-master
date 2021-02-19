package org.javamaster.b2c.actuator.repository;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * trace跟踪条目存储在其他地方——既不消耗内存，又能长久保存,只需实现Spring Boot的TraceRepository接口即可
 *
 * @author yudong
 */
@Service
public class MongoTraceRepository implements HttpTraceRepository {

    @Override
    public List<HttpTrace> findAll() {
        // TODO
        return null;
    }

    @Override
    public void add(HttpTrace trace) {
        // TODO
    }

}
