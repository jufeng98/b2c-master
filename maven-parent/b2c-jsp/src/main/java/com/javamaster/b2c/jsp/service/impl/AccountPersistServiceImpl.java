package com.javamaster.b2c.jsp.service.impl;

import com.javamaster.b2c.jsp.exception.BusinessException;
import com.javamaster.b2c.jsp.model.Account;
import com.javamaster.b2c.jsp.service.AccountPersistService;
import com.javamaster.b2c.jsp.utils.JaxbUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.dom4j.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

/**
 * Created on 2018/9/15.<br/>
 *
 * @author yudong
 */
@Service
public class AccountPersistServiceImpl implements AccountPersistService {
    Logger logger = LoggerFactory.getLogger(getClass());

    private static final File FILE;

    static {
        FILE = new File("G:\\account.xml");
    }

    @Override
    public void createAcount(Account account) {
        try {
            Document doc;
            if (!FILE.exists()) {
                doc = DocumentHelper.createDocument(DocumentHelper.createElement("accounts"));
            } else {
                doc = readDoc();
                if (getUserEle(account.getId(), doc.getRootElement()) != null) {
                    throw new BusinessException("用户id已存在!");
                }

            }
            Element root = doc.getRootElement();
            Element ele = DocumentHelper.createElement("account");
            Field[] fields = account.getClass().getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                Element element = DocumentHelper.createElement(field.getName());
                element.setText(String.valueOf(field.get(account)));
                ele.add(element);
            }
            root.add(ele);

            writeDoc(doc);

        } catch (Exception e) {
            throw new RuntimeException(account.toString(), e);
        }
    }

    @Override
    public Account readAcount(String id) {
        try {
            Document doc = readDoc();
            Element root = doc.getRootElement();
            Element userEle = getUserEle(id, root);
            if (userEle == null) {
                return null;
            }
            return JaxbUtils.xml2Bean(userEle.asXML(), Account.class);
        } catch (Exception e) {
            throw new RuntimeException(id, e);
        }
    }

    @Override
    public void deleteAcount(String id) {
        try {
            Document doc = readDoc();
            Element root = doc.getRootElement();
            Element userEle = getUserEle(id, root);
            if (userEle == null) {
                return;
            }
            root.remove(userEle);
        } catch (Exception e) {
            throw new RuntimeException(id, e);
        }
    }

    @Override
    public void updateAcount(Account account) {
        try {
            Document doc = readDoc();
            Element root = doc.getRootElement();
            Element userEle = getUserEle(account.getId(), root);
            if (userEle == null) {
                return;
            }
            Field[] fields = account.getClass().getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                Object object = field.get(account);
                if (object == null) {
                    continue;
                }
                String value = String.valueOf(object);
                logger.debug("updateAcount name:{},value:{}", field.getName(), value);
                if (StringUtils.isNotBlank(value)) {
                    userEle.element(field.getName()).setText(value);
                }
            }
            writeDoc(doc);
        } catch (Exception e) {
            throw new RuntimeException(account.toString(), e);
        }

    }

    private Document readDoc() throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(FILE);
        return doc;
    }

    private void writeDoc(Document doc) throws Exception {
        Writer out = new OutputStreamWriter(new FileOutputStream(FILE), StandardCharsets.UTF_8);
        XMLWriter writer = new XMLWriter(out, OutputFormat.createPrettyPrint());
        writer.write(doc);
        out.close();
        writer.close();
    }

    private Element getUserEle(String id, Element root) {
        String s = String.format("/accounts/account[id='%s']", id);
        logger.debug("getUserEle s:{}", s);
        return ((Element) root.selectSingleNode(s));
    }
}
