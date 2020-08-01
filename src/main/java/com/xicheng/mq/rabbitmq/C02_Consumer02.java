package com.xicheng.mq.rabbitmq;

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
 * @date 2020-08-01 21:14
 */
@Slf4j
public class C02_Consumer02 {

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectionUtil.getChannel();
        if (null == channel) {
            return;
        }
        channel.queueDeclare(ConnectionConstant.WORK_TOPIC, false, false, false, null);
        channel.basicQos(1);
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(ConnectionConstant.WORK_TOPIC, false, queueingConsumer);

        QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();

        while (delivery != null) {
            log.info("C02_Consumer02 main message: {}", new String(delivery.getBody()));
            TimeUnit.MILLISECONDS.sleep(100);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            delivery = queueingConsumer.nextDelivery();
        }
    }
}
