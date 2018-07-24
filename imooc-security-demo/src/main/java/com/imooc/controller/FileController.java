package com.imooc.controller;

import com.imooc.domain.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private static final String PATH = "/Users/irving/Documents/IDEA/imoocsecurity/imooc-security-demo/src/main/resources/upload/";

    @PostMapping
    public FileInfo upload(MultipartFile hello) throws IOException {
        logger.info(String.format("uploadFileName:%s\noriginalFileName:%s\nfileZise:%s\n"
                , hello.getName(), hello.getOriginalFilename(), hello.getSize()));
        File localFile = new File(PATH, new Date().getTime() + ".txt");
        hello.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer buffer = new StringBuffer(PATH).append(id).append(".txt");
        logger.info("buffer:{}", buffer);
        try (
                InputStream inputStream = new FileInputStream(new File(buffer.toString()));
                OutputStream outputStream = response.getOutputStream();
        ) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=hello.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
