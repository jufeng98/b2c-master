package org.javamaster.b2c.file;

import lombok.SneakyThrows;
import org.javamaster.b2c.file.controller.FileController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

@AutoConfigureMockMvc
@SpringBootTest(classes = FileController.class)
public class FileControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void uploadFile() {
        File file = ResourceUtils.getFile("classpath:电脑创意Ⅰ-102.jpg");
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                file.getName(),
                MediaType.IMAGE_JPEG_VALUE,
                new FileInputStream(file)
        );
        ResultActions resultActions = mockMvc.perform(multipart("/upload/uploadFile").file(mockMultipartFile).accept("text/plain;charset=utf-8"));
        resultActions.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .setCharacterEncoding(StandardCharsets.UTF_8.name());
        resultActions.andDo(print());
    }

    @Test
    @SneakyThrows
    public void uploadFile2() {
        File file = ResourceUtils.getFile("classpath:电脑创意Ⅰ-102.jpg");
        MockPart mockPart = new MockPart(
                "file",
                file.getName(),
                StreamUtils.copyToByteArray(new FileInputStream(file))
        );
        ResultActions resultActions = mockMvc.perform(multipart("/upload/uploadFile2").part(mockPart).accept("text/plain;charset=utf-8"));
        resultActions.andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .setCharacterEncoding(StandardCharsets.UTF_8.name());
        resultActions.andDo(print());
    }

}
