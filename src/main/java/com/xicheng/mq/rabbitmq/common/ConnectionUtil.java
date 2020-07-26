package com.xicheng.mq.rabbitmq.common;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-07-26 11:18
 */
@Slf4j
public class ConnectionUtil {

    private static Connection getConnetction() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ConnectionConstant.HOST);
        connectionFactory.setPort(ConnectionConstant.PORT);
        connectionFactory.setUsername(ConnectionConstant.USER_NAME);
        connectionFactory.setPassword(ConnectionConstant.PASSWORD);
        connectionFactory.setVirtualHost(ConnectionConstant.VIRTUAL_HOST);
        return connectionFactory.newConnection();
    }

    public static Channel getChannel() {
        try {
            Connection connection = getConnetction();
            return connection.createChannel();
        } catch (Exception e) {
            log.info("getConnection error: ", e);
        }
        return null;
    }
}
