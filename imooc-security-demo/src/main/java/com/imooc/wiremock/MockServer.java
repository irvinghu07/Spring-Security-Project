package com.imooc.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;


public class MockServer {
    public static void main(String[] args) {
        WireMock.configureFor(8062);
        WireMock.removeAllMappings();
        try {
            mockOperation("/order/1", "01.txt");
            mockOperation("/order/2", "02.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mockOperation(String url, String fileName) throws IOException {
        String content = FileUtils
                .readFileToString(new ClassPathResource("wiremock/response/" + fileName)
                        .getFile(), "UTF-8");
        WireMock.stubFor(WireMock
                .get(WireMock.urlEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content)
                        .withStatus(200)
                ));
    }
}
