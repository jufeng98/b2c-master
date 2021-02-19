package org.javamaster.b2c.cache.filter;

import lombok.Cleanup;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author yudong
 * @date 2021/1/18
 */
@WebFilter(urlPatterns = "/*")
public class CompressFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (!isGipSupported(req)) {
            filterChain.doFilter(req, res);
        } else {
            res.setHeader("Content-Encoding", "gzip");
            ResponseWrapper responseWrapper = new ResponseWrapper(res);
            filterChain.doFilter(req, responseWrapper);

            @Cleanup
            ServletByteArrayOutputStream originalOutputStream = (ServletByteArrayOutputStream) responseWrapper.getOutputStream();

            @Cleanup
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            @Cleanup
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(originalOutputStream.toByteArray());
            gzipOutputStream.finish();

            ServletOutputStream realOutPutStream = res.getOutputStream();
            byteArrayOutputStream.writeTo(realOutPutStream);
            realOutPutStream.flush();
        }
    }


    private boolean isGipSupported(HttpServletRequest req) {
        String encoding = req.getHeader("Accept-Encoding");
        return encoding != null && encoding.contains("gzip");
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
