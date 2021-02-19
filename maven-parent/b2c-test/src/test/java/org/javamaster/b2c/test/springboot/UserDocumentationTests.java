//package org.javamaster.b2c.test;
//
//import org.javamaster.b2c.test.controller.UserController;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
//@AutoConfigureRestDocs
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@AutoConfigureRestDocs
//public class UserDocumentationTests {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @LocalServerPort
//	  private int port;
//
//    @Test
//    public void listUsers() throws Exception {
//        this.mvc.perform(get("/users").accept(MediaType.TEXT_PLAIN))
//                .andExpect(status().isOk())
//                .andDo(document("list-users"));
//    }
//
//}