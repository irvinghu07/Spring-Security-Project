package com.imooc.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {
    private static final Logger logger = LoggerFactory.getLogger(MockQueue.class);

    private String placeHolder;

    private String completeHolder;

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder){
        new Thread(() -> {
            logger.info("接到下单请求: " + placeHolder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeHolder = placeHolder;
            logger.info("下单请求处理完毕: " + placeHolder);
        }).start();
    }

    public String getCompleteHolder() {
        return completeHolder;
    }

    public void setCompleteHolder(String completeHolder) {
        this.completeHolder = completeHolder;
    }
}
