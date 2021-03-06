package com.xicheng.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.xicheng.mq.rabbitmq.common.ConnectionConstant;
import com.xicheng.mq.rabbitmq.common.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * description simple模式
 *
 * @author xichengxml
 * @date 2020-07-26 11:09
 */
@Slf4j
public class C01_Producer {

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectionUtil.getChannel();
        if (channel == null) {
            return;
        }
        channel.queueDeclare(ConnectionConstant.SIMPLE_TOPIC, false, false, false, null);

        String message = "hello simple ";

        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", ConnectionConstant.SIMPLE_TOPIC, null, (message + i).getBytes());
        }

        channel.close();
    }
}
