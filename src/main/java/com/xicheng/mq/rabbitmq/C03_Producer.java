package com.xicheng.mq.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.xicheng.mq.rabbitmq.common.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * description publish模式
 *
 * @author xichengxml
 * @date 2020-08-02 00:45
 */
@Slf4j
public class C03_Producer {

    private static final String EXCHANGE_NAME = "exchange";

    private static final String ROUTING_KEY = "routingKey";

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectionUtil.getChannel();
        if (null == channel) {
            return;
        }
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        for (int i = 0; i < 100; i++) {
            String message = "hello publish" + i;
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());
        }

        channel.close();
    }
}
