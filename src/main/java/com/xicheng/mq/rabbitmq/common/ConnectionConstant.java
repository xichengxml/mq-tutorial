package com.xicheng.mq.rabbitmq.common;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-07-26 12:13
 */
public interface ConnectionConstant {

    String HOST = "192.168.133.129";

    Integer PORT = 5672;

    String USER_NAME = "xichengxml";

    String PASSWORD = "123456";

    String VIRTUAL_HOST = "/xichengxml";

    String SIMPLE_TOPIC = "simple";

    String WORK_TOPIC = "work";

    String PUBLISH_TOPIC = "publish";
}
