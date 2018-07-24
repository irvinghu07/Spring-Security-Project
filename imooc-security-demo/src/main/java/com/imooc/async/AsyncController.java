package com.imooc.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;
    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @RequestMapping("/order")
    public String order() throws InterruptedException {
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程结束");
        return "success";
    }

    @RequestMapping("/async")
    public Callable<String> async() throws InterruptedException {
        logger.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程结束");
                return "success";
            }
        };
        logger.info("主线程结束");
        return result;
    }

    @RequestMapping("/defer")
    public DeferredResult<String> defer(){
        logger.info("主线程开始");
//        随机生成订单号并放入消息队列
        String orderNumStr = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceHolder(orderNumStr);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumStr, result);
        logger.info("主线程结束");
        return result;
    }
}
