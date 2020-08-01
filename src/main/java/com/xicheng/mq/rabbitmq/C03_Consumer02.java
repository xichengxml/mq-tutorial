package com.xicheng.mq.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.xicheng.mq.rabbitmq.common.ConnectionConstant;
import com.xicheng.mq.rabbitmq.common.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-08-02 01:02
 */
@Slf4j
public class C03_Consumer02 {

    private static final String EXCHANGE_NAME = "exchange";

    private static final String ROUTING_KEY = "routingKey";

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectionUtil.getChannel();
        if (null == channel) {
            return;
        }
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(ConnectionConstant.PUBLISH_TOPIC, false, false, false, null);

        channel.queueBind(ConnectionConstant.PUBLISH_TOPIC, EXCHANGE_NAME, ROUTING_KEY);
        channel.basicQos(1);

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(ConnectionConstant.PUBLISH_TOPIC, false, queueingConsumer);

        QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
        while (delivery != null) {
            String message = new String(delivery.getBody());
            log.info("C03_Consumer02 main message: {}", message);
            TimeUnit.MILLISECONDS.sleep(100);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), true);
            delivery = queueingConsumer.nextDelivery();
        }
    }
}
