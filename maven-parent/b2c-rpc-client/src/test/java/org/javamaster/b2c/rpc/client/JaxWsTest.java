package org.javamaster.b2c.rpc.client;

import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;

/**
 * Created on 2018/8/26.<br/>
 *
 * @author yudong
 */
public class JaxWsTest {

    @Test
    public void testJaxWs() throws Exception {
        Service service = new Service();
        Call call = service.createCall();
        call.setTargetEndpointAddress("http://localhost:8080/rpc/services/bookService");
        call.setOperationName(new QName("http://webservice.javamaster.org/",
                "getBookWithString"));
        call.addParameter("id", XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnType(XMLType.XSD_STRING);
        Object result = call.invoke(new Object[]{"10001"});
        System.out.println(result);
    }

}
