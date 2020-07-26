package com.xicheng.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.xicheng.mq.rabbitmq.common.ConnectionConstant;
import com.xicheng.mq.rabbitmq.common.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-07-26 11:16
 */
@Slf4j
public class C01_Consumer {

    public static void main(String[] args) throws Exception {
        Channel channel = ConnectionUtil.getChannel();
        if (null == channel) {
            return;
        }
        channel.queueDeclare(ConnectionConstant.TOPIC, false, false, false, null);
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(ConnectionConstant.TOPIC, true, queueingConsumer);
        QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
        while (null != delivery) {
            log.info("message: {}", new String(delivery.getBody()));
            delivery = queueingConsumer.nextDelivery();
        }
    }
}
