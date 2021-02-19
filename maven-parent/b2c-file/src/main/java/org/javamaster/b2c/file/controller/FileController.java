package org.javamaster.b2c.file.controller;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.*;
import org.springframework.util.DigestUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yudong
 * @date 2020/11/2
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class FileController {

    @SneakyThrows
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        log.info("{},{},{},{}", multipartFile.getName(), multipartFile.getContentType(),
                multipartFile.getSize(), multipartFile.getResource().getFilename());
        byte[] bytes = multipartFile.getBytes();
        File file = new File("target", Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        StreamUtils.copy(bytes, fileOutputStream);
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    @SneakyThrows
    @PostMapping("/uploadFile1")
    public String uploadFile1(@RequestPart("file") MultipartFile multipartFile) {
        return uploadFile(multipartFile);
    }

    @SneakyThrows
    @PostMapping("/uploadFile2")
    public String uploadFile2(@RequestPart("file") Part part) {
        log.info("{},{},{},{},{}", part.getSubmittedFileName(), part.getContentType(),
                part.getName(), part.getSize(), part.getHeaderNames());
        byte[] bytes = StreamUtils.copyToByteArray(part.getInputStream());
        File file = new File("target", part.getSubmittedFileName());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        StreamUtils.copy(bytes, fileOutputStream);
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    @SneakyThrows
    @PostMapping("/checkBigFile")
    public Map<String, Object> checkBigFile(String fileMd5, Integer fileSize, Integer chunkSize, String fileName) {
        Map<String, Object> map = new HashMap<>();

        File filePath = new File("b2c-file/target", fileMd5);
        if (!filePath.exists()) {
            Files.createDirectories(filePath.toPath());
        }

        File currentFile = new File(filePath, fileName);
        if (currentFile.exists()) {
            // 文件已经被成功上传过,直接返回url
            map.put("code", 0);
            map.put("url", currentFile.getAbsolutePath());
            return map;
        }

        // 文件总片数
        int chunkNum = (int) Math.ceil(1.0 * fileSize / chunkSize);
        List<Integer> chunks = IntStream.range(0, chunkNum).boxed().collect(Collectors.toList());

        // 已上传的文件片
        List<Integer> chunkNames = Files.walk(filePath.toPath())
                .filter(Files::isRegularFile)
                .map(path -> Integer.parseInt(path.getFileName().toString()))
                .collect(Collectors.toList());

        // 得到缺失的文件片号
        chunks.removeAll(chunkNames);

        map.put("code", 1);
        map.put("missingChunks", chunks);
        return map;
    }

    @SneakyThrows
    @PostMapping("/uploadBigFile")
    public Map<String, Object> uploadBigFile(Integer chunk, String fileMd5, String chunkMd5, MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        byte[] bytes = file.getBytes();
        String md5 = DigestUtils.md5DigestAsHex(bytes);
        if (!chunkMd5.equals(md5)) {
            map.put("code", 1);
            map.put("msg", "分片已损坏,请重新上传");
            return map;
        }
        File filePath = new File("b2c-file/target/" + fileMd5, String.valueOf(chunk));
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            StreamUtils.copy(bytes, fileOutputStream);
            map.put("code", 0);
            map.put("msg", "分片上传成功");
        } catch (Exception e) {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            Files.delete(filePath.toPath());
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
        return map;
    }

    @SneakyThrows
    @PostMapping("/mergeBigFile")
    public Map<String, Object> mergeBigFile(String fileMd5, String fileName) {
        Map<String, Object> map = new HashMap<>();
        File filePath = new File("b2c-file/target", fileMd5);
        File currentFile = new File(filePath, fileName);
        if (currentFile.exists()) {
            // 文件已经被成功合并过,直接返回url
            map.put("code", 0);
            map.put("url", currentFile.getAbsolutePath());
            return map;
        }
        List<Path> chunkFiles = Files.walk(filePath.toPath())
                .filter(Files::isRegularFile)
                .sorted()
                .collect(Collectors.toList());

        @Cleanup
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (Path chunkFile : chunkFiles) {
            byte[] chunkFileBytes = Files.readAllBytes(chunkFile);
            byteArrayOutputStream.write(chunkFileBytes);
            Files.delete(chunkFile);
        }
        byte[] fileBytes = byteArrayOutputStream.toByteArray();

        String currentFileMd5 = DigestUtils.md5DigestAsHex(fileBytes);
        log.info("file md5:{}", currentFileMd5);
        // if (!currentFileMd5.equals(fileMd5)) {
        //     map.put("code", 1);
        //     map.put("msg", "文件已损坏,请重新上传");
        //     return map;
        // }

        @Cleanup
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(currentFile));
        StreamUtils.copy(fileBytes, bufferedOutputStream);

        map.put("code", 0);
        map.put("url", currentFile.getAbsolutePath());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/exportGet", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportGet(Integer[] list) throws Exception {
        log.info("exportGet:{}", Arrays.toString(list));
        return exportPost(Arrays.asList(list));
    }

    @ResponseBody
    @RequestMapping(value = "/exportGet1", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportGet(String ids) throws Exception {
        List<Integer> list = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        log.info("exportGet1:{}", list);
        return exportPost(list);
    }

    @ResponseBody
    @RequestMapping(value = "/exportPost", method = RequestMethod.POST)
    public ResponseEntity<byte[]> exportPost(@RequestBody List<Integer> list) throws Exception {
        log.info("exportPost:{}", list);
        byte[] bytes = generateBytes();
        log.info("generate finish");
        HttpHeaders headers = new HttpHeaders();
        String fileName = RandomStringUtils.randomAlphabetic(5) + ".zip";
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
        headers.setContentType(new MediaType("application", "octet-stream"));
        headers.add("Pragma", "no-cache");
        headers.add("Cache-Control", "no-cache");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    private byte[] generateBytes() {
        int size = 300 * 1024 * 1024;
        return RandomUtils.nextBytes(size);
    }
}
