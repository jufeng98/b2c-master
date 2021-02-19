package org.summerframework.summer.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.summerframework.summer.web.anno.SummerRequestBody;
import org.summerframework.summer.web.consts.StrConsts;
import org.summerframework.summer.web.mvc.HandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author yudong
 * @date 2019/5/13
 */
public class SummerDispatchServlet extends HttpServlet {

    public static final String HANDLER_MAPPING_MAP_KEY = "HANDLER_MAPPING_MAP_KEY";
    Map<String, HandlerMapping> handlerMappingMap;

    public SummerDispatchServlet(Map<String, HandlerMapping> handlerMappingMap) {
        this.handlerMappingMap = handlerMappingMap;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqPath = req.getRequestURI().substring(req.getContextPath().length() + req.getServletPath().length());
        HandlerMapping handlerMapping = handlerMappingMap.get(reqPath);
        if (handlerMapping == null) {

        }
        if (req.getContentType() != null) {
            if (req.getContentType().contains(StrConsts.APPLICATION_FORM_URLENCODED_VALUE)) {


            } else if (req.getContentType().contains(StrConsts.APPLICATION_JSON_VALUE)) {

            }
        }
        Object reqObj = null;
        for (Parameter parameter : handlerMapping.getParameters()) {
            if (parameter.getAnnotation(SummerRequestBody.class) == null) {
                continue;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            reqObj = objectMapper.readValue("{}", parameter.getType());

        }
        Object resObj = handlerMapping.invoke(new Object[]{reqObj});
        String resStr = "";
        if (resObj.getClass() == String.class) {
            resStr = (String) resObj;
        }

        resp.setContentType("application/json");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(String.format("{%s}", resStr));
        printWriter.close();
    }
}
