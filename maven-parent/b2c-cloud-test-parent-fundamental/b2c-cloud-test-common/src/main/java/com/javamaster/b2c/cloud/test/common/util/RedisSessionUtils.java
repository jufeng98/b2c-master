package com.javamaster.b2c.cloud.test.common.util;

import javax.servlet.http.HttpSession;

import static com.javamaster.b2c.cloud.test.common.constant.SessionConst.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;

public class RedisSessionUtils {
    public static void saveUserData(JsonNode userData, HttpSession session) {
        session.setAttribute(TYPE_NAME, userData.get(TYPE_NAME).asText());
        session.setAttribute(TOKEN, userData.get(TOKEN).asText());
        session.setAttribute(USER_ID, userData.get(USER_ID).asText());
        session.setAttribute(USER_ENNAME, userData.get(USER_ENNAME).asText());
        session.setAttribute(USER_NAME, userData.get(USER_NAME).asText());
        session.setAttribute(USER_TYPE, userData.get(USER_TYPE).asText());
        session.setAttribute(IP, userData.get(IP).asText());
        session.setAttribute(LOGIN_TIME, userData.get(LOGIN_TIME).asLong());
        session.setAttribute(LOGIN_CHANNEL, userData.get(LOGIN_CHANNEL).asText());
        session.setAttribute(LOGIN_TYPE, userData.get(LOGIN_TYPE).asText());
        JsonNode email = userData.get(EMAIL);
        session.setAttribute(EMAIL, email != null ? email.asText() : "");
        session.setAttribute(CELLPHONE, userData.get(CELLPHONE).asText());
        JsonNode membersLevel = userData.get(MEMBERS_LEVEL);
        session.setAttribute(MEMBERS_LEVEL, membersLevel != null ? membersLevel.asText() : "");
        session.setAttribute(POINTS, userData.get(POINTS).asDouble());

        JsonNode associateAccount = userData.get("associatedAccount");
        if (associateAccount != null) {
            Iterator<JsonNode> iterator = associateAccount.iterator();
            while ((iterator.hasNext())) {
                JsonNode jsonNode = iterator.next();
                String bind = jsonNode.get("id").get("contactType").asText();
                if (bind.contains("QQ")) {
                    session.setAttribute(BIND_QQ, true);
                    session.setAttribute(BIND_QQ_NO, jsonNode.get("id").get("contactNo").asText());
                } else if (bind.contains("WX")) {
                    session.setAttribute(BIND_WEIXIN, true);
                    session.setAttribute(BIND_WEIXIN_NO, jsonNode.get("id").get("contactNo").asText());
                }
            }
        }

        session.setAttribute(IS_WEB_EXCHANGE, userData.get(IS_WEB_EXCHANGE).asText());
        JsonNode niid = userData.get(NIID);
        session.setAttribute(NIID, niid != null ? niid.asText() : "");
        JsonNode uid = userData.get(UID);
        session.setAttribute(UID, uid != null ? uid.asText() : "");
        JsonNode aid = userData.get(AID);
        session.setAttribute(AID, aid != null ? aid.asText() : "");
        JsonNode iden = userData.get(INENTIFY_STATUS);
        session.setAttribute(INENTIFY_STATUS, iden != null ? iden.asText() : "");
        session.setAttribute(NEW_MEMBER, userData.get(NEW_MEMBER).asBoolean());
        session.setAttribute(CHECK_PASSWORD, userData.get(CHECK_PASSWORD).asBoolean());
        session.setAttribute(AUTHORITY, "ROLE_MEMBER");
    }

}
