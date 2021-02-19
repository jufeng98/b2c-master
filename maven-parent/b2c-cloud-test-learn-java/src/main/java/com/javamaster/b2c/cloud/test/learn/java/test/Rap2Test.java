package com.javamaster.b2c.cloud.test.learn.java.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.learn.java.utils.HeaderUtils;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @date 2019/5/9
 */
public class Rap2Test {
    @Test
    public void testCopyInterfaces() throws Exception {
        String cookieValue = "Hm_lvt_ed752fa41bdfde359bfde28ccd27b888=1560580664; koa.sid=phMsLHn1y_kpiZ83W5CpTXP7lSCmU7d1; koa.sid.sig=v0oRNdG8cBxaZNj3Hn2Gx6Xhn40";

        String originId = "65";
        String originTabName = "至尊洗衣二期";
        String originInterfaceUrl = "/washingService-controller/wash/queryCornerNum";

        String targetId = "62";
        String targetTabName = "天使代下单";

        int creatorId = 100002344;


        JSONObject originResJsonObject = executeGet("http://rap2api.bluemoon.com.cn/repository/get?id=" + originId, HeaderUtils.headers1(cookieValue)).getJSONObject("data");
        JSONObject targetResJsonObject = executeGet("http://rap2api.bluemoon.com.cn/repository/get?id=" + targetId, HeaderUtils.headers1(cookieValue)).getJSONObject("data");
        JSONArray originModules = originResJsonObject.getJSONArray("modules");
        JSONArray targetModules = targetResJsonObject.getJSONArray("modules");
        JSONObject originModule = null;
        JSONObject targetModule = null;
        for (Object sourceModule : originModules) {
            JSONObject jsonObject = (JSONObject) sourceModule;
            if (jsonObject.getString("name").equals(originTabName)) {
                originModule = jsonObject;
                break;
            }
        }
        for (Object module : targetModules) {
            JSONObject jsonObject = (JSONObject) module;
            if (jsonObject.getString("name").equals(targetTabName)) {
                targetModule = jsonObject;
                break;
            }
        }
        JSONArray interfaces = originModule.getJSONArray("interfaces");
        JSONObject originInterface = null;
        for (Object anInterface : interfaces) {
            JSONObject jsonObject = (JSONObject) anInterface;
            if (jsonObject.getString("url").equals(originInterfaceUrl)) {
                originInterface = jsonObject;
                break;
            }
        }
        JSONObject newCreateJsonObj = new JSONObject();
        newCreateJsonObj.put("name", originInterface.getString("name"));
        newCreateJsonObj.put("url", originInterface.getString("url"));
        newCreateJsonObj.put("method", originInterface.getString("method"));
        newCreateJsonObj.put("description", originInterface.getString("description"));
        newCreateJsonObj.put("repositoryId", targetResJsonObject.getIntValue("id"));
        newCreateJsonObj.put("moduleId", targetModule.getIntValue("id"));
        newCreateJsonObj.put("creatorId", originInterface.getIntValue("creatorId"));
        newCreateJsonObj.put("lockerId", originInterface.getInteger("lockerId"));
        System.out.println(JSONObject.toJSONString(newCreateJsonObj, true));

        int newInterfaceId = create(newCreateJsonObj, cookieValue);
        System.out.println(newInterfaceId);

        int lockId = lock(newInterfaceId, cookieValue);
        System.out.println(lockId);

        JSONArray jsonArray = originInterface.getJSONArray("properties");
        Map<Integer, String> map = new HashMap<>(60);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            int id = object.getIntValue("id");
            String memoryId = "memory-" + (i + 1);
            map.put(id, memoryId);
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            int parentId = object.getIntValue("parentId");
            if (parentId != -1) {
                String parentMemoryId = map.get(parentId);
                object.put("parentId", parentMemoryId);
            }
            String memoryId = "memory-" + (i + 1);
            object.put("id", memoryId);
            object.put("memory", true);
            object.put("interfaceId", newInterfaceId);
            object.put("creatorId", creatorId);
            object.put("moduleId", targetModule.getIntValue("id"));
            object.put("repositoryId", targetId);

        }
        JSONObject newInterfaceReqJsonObj = new JSONObject();
        newInterfaceReqJsonObj.put("properties", jsonArray);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bodyOption", "FORM_DATA");
        jsonObject.put("requestParamsType", "BODY_PARAMS");
        newInterfaceReqJsonObj.put("summary", jsonObject);

        System.out.println(JSONObject.toJSONString(newInterfaceReqJsonObj, true));
        JSONObject newInterfaceResJsonObj = update(newInterfaceReqJsonObj, newInterfaceId, cookieValue);
        System.out.println(newInterfaceResJsonObj);

        boolean isOk = unlock(newInterfaceId, cookieValue);
        System.out.println(isOk);
    }

    private int create(JSONObject newCreateJsonObj, String cookieValue) throws Exception {
        JSONObject resJsonObject = executePost(newCreateJsonObj, "http://rap2api.bluemoon.com.cn/interface/create", HeaderUtils.headers2(cookieValue)).getJSONObject("data");
        return resJsonObject.getJSONObject("itf").getIntValue("id");
    }

    private int lock(int id, String cookieValue) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        JSONObject resJsonObject = executePost(jsonObject, "http://rap2api.bluemoon.com.cn/interface/lock", HeaderUtils.headers3(cookieValue)).getJSONObject("data");
        return resJsonObject.getIntValue("id");
    }

    private JSONObject update(JSONObject updateCreateJsonObj, int newInterfaceId, String cookieValue) throws Exception {
        JSONObject resJsonObject = executePost(updateCreateJsonObj, "http://rap2api.bluemoon.com.cn/properties/update?itf=" + newInterfaceId, HeaderUtils.headers4(cookieValue)).getJSONObject("data");
        return resJsonObject;
    }

    private boolean unlock(int id, String cookieValue) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        JSONObject resJsonObject = executePost(jsonObject, "http://rap2api.bluemoon.com.cn/interface/unlock", HeaderUtils.headers3(cookieValue)).getJSONObject("data");
        return resJsonObject.getBoolean("isOk");
    }

    private JSONObject executeGet(String url, Headers headers) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String resJsonStr = new String(response.body().bytes(), StandardCharsets.UTF_8);
        JSONObject resJsonObject = JSONObject.parseObject(resJsonStr);
        return resJsonObject;
    }

    private JSONObject executePost(JSONObject reqJsonObj, String url, Headers headers) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), reqJsonObj.toJSONString());
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String resJsonStr = new String(response.body().bytes(), StandardCharsets.UTF_8);
        JSONObject resJsonObject = JSONObject.parseObject(resJsonStr);
        return resJsonObject;
    }
}
