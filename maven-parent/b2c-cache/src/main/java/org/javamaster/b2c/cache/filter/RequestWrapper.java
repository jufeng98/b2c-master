package org.javamaster.b2c.cache.filter;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * <p>确保HttpServletRequest的body可被重复读取</p>
 * Created on 2019/3/13.<br/>
 *
 * @author yudong
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private byte[] body;

    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
    }

    @Override
    public ServletInputStream getInputStream() {
        return new ServletByteArrayInputStream(body);
    }
}

class ServletByteArrayInputStream extends ServletInputStream {

    private ByteArrayInputStream byteArrayInputStream;

    ServletByteArrayInputStream(byte[] body) {
        this.byteArrayInputStream = new ByteArrayInputStream(body);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }

    @Override
    public int read() {
        return byteArrayInputStream.read();
    }
}