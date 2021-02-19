package org.javamaster.b2c.rpc.server.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javamaster.b2c.rpc.server.api.BookService;
import org.javamaster.b2c.rpc.server.api.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.*;
import java.util.List;

/**
 * jdk自带的命令解析wsdl文件并生成相应的类结构,根据JDK1.6.0_21及以上的生成本地代码的，
 * 它只能解析服务器端的SOAP协议为1.1，不能解析SOAP1.2的协议
 * wsimport -s . -p com.javamaster.b2c.cloud.test.sys.picc http://partnertest.mypicc.com.cn/ecooperation/webservice/insure?wsdl
 * <p>
 * CXF也提供了根据WSDL生成客户端代码的命令wsdl2java.exe 。它是根据jdk1.7生成的本地代码，
 * 它可以支持SOAP1.1 和SOAP1.2的协议,下载Apache CXF
 * wsdl2java -p com.javamaster.b2c.cloud.test.sys.picc -d C:\\Users\\yu\\axis http://10.79.0.101:8180/CBDCCSWebService/services/NEWCBDCCSWebService?wsdl
 * <p>
 * SOAP1.1：text/xml;charset=utf-8
 * SOAP1.2：application/soap+xml;charset=utf-8
 * <p>
 * http://localhost:8080/rpc/services/bookService?wsdl
 * <p>
 * Created on 2018/8/26.<br/>
 *
 * @author yudong
 */
@Component
@WebService(serviceName = "bookService", targetNamespace = "http://webservice.javamaster.org/")
public class BookServiceEndpoint {

    @Autowired
    private BookService bookService;

    @WebMethod
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * SOAP1.1
     * 请求地址:http://localhost:8080/rpc/services/bookService
     * 请求方法:POST
     * 请求格式:text/xml
     * 请求内容:
     * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://webservice.rpc.test.cloud.b2c.javamaster.com/">
     *   <soapenv:Header/>
     *   <soapenv:Body>
     *     <web:getBook>
     *       <id>23</id>
     *     </web:getBook>
     *   </soapenv:Body>
     * </soapenv:Envelope>
     * 返回内容:
     * <?xml version='1.0' encoding='UTF-8' ?>
     * <S:Envelopexmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
     *   <S:Body>
     *     <ns2:getBookResponsexmlns:ns2="http://webservice.rpc.test.cloud.b2c.javamaster.com/">
     *       <return>
     *         <author>yudong</author>
     *         <id>23</id>
     *       </return>
     *     </ns2:getBookResponse>
     *   </S:Body>
     * </S:Envelope>
     * <p>
     * SOAP1.2
     * 请求地址:http://10.79.0.101:8180/CBDCCSWebService/services/NEWCBDCCSWebService
     * 请求方法:POST
     * 请求格式:application/soap+xml;charset=utf-8
     * 请求内容:
     * <?xml version="1.0" encoding="UTF-8"?>
     * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
     *   <soapenv:Body>
     *     <hasEnabledPassword xmlns="http://web.service.newccs.cbd.javamaster.com">
     *       <in0>180007020302</in0>
     *     </hasEnabledPassword>
     *   </soapenv:Body>
     * </soapenv:Envelope>
     * 返回内容:
     * <soap:Envelopexmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema"xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
     *   <soap:Body>
     *     <ns1:hasEnabledPasswordResponsexmlns:ns1="http://web.service.newccs.cbd.javamaster.com">
     *       <ns1:out>Y</ns1:out>
     *     </ns1:hasEnabledPasswordResponse>
     *   </soap:Body>
     * </soap:Envelope>
     */
    @WebMethod
    public BookDto getBook(@WebParam(name = "id") Long id) {
        return bookService.getBook(id);
    }

    @WebMethod
    public String getBookWithString(@WebParam(name = "id") String id) throws JsonProcessingException {
        id = id != null ? id : "10000";
        long i = Long.parseLong(id);
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bookService.getBook(i));
    }
}
