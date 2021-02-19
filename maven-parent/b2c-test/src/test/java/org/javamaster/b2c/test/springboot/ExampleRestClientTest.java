//package org.javamaster.b2c.test;
//
//import org.javamaster.b2c.test.service.RemoteService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.client.MockRestServiceServer;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
//
//@RunWith(SpringRunner.class)
//@RestClientTest(RemoteService.class)
//public class ExampleRestClientTest {
//
//    @Autowired
//    private RemoteService service;
//
//    @Autowired
//    private MockRestServiceServer server;
//
//    @Test
//    public void getVehicleDetailsWhenResultIsSuccessShouldReturnDetails()
//            throws Exception {
//        server.expect(requestTo("/greet/details"))
//                .andRespond(withSuccess("hello", MediaType.TEXT_PLAIN));
//        String greeting = this.service.callRestService();
//        assertThat(greeting).isEqualTo("hello");
//    }
//
//}
