package com.xicheng.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.xicheng.mq.rabbitmq.common.ConnectionConstant;
import com.xicheng.mq.rabbitmq.common.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * description work模式: 两个消费者同时消费一个topic的内容
 *
 * @author xichengxml
 * @date 2020-08-01 20:41
 */
@Slf4j
public class C02_Producer {

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectionUtil.getChannel();
        if (channel == null) {
            return;
        }
        channel.queueDeclare(ConnectionConstant.WORK_TOPIC, false, false, false, null);

        String message = "hello work ";

        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", ConnectionConstant.WORK_TOPIC, null, (message + i).getBytes());
        }

        channel.close();
    }
}
