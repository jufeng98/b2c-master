package com.javamaster.b2c.cloud.test.user;

import com.alibaba.fastjson.JSONObject;
import com.javamaster.b2c.cloud.test.common.model.Page;
import com.javamaster.b2c.cloud.test.common.model.Users;
import com.javamaster.b2c.cloud.test.user.vo.UpdatePasswordReqVo;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created on 2018/12/10.<br/>
 *
 * @author yudong
 */

public class UserControllerTest extends CommonTestCode {

    @Test
    @WithMockUser(username = "admin",
            password = "admin",
            authorities = "ROLE_ADMIN")
    public void testCreateUsers() throws Exception {
        Users users = Users.getInstance().username("80546269").password("123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/createUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(users)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin",
            password = "admin",
            authorities = "ROLE_ADMIN")
    public void testCloseUsers() throws Exception {
        Users users = Users.getInstance().username("80546269");

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/disabledUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(users)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "80546269",
            password = "654321")
    public void testUpdatePassword() throws Exception {
        UpdatePasswordReqVo reqVo = new UpdatePasswordReqVo();
        reqVo.setNewPassword("123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/updatePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(reqVo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @WithMockUser(username = "admin",
            password = "admin",
            authorities = "ROLE_ADMIN")
    public void testFindUsers() throws Exception {
        Users users = Users.getInstance();
        users.setPage(Page.getInstance(1, 3));

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/findUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(users)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
