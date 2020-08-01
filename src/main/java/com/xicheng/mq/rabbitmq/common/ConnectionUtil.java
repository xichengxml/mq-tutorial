package com.xicheng.mq.rabbitmq.common;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * description
 *
 * @author xichengxml
 * @date 2020-07-26 11:18
 */
@Slf4j
public class ConnectionUtil {

    private static Connection connection;

    private static Connection getConnection() throws Exception {
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
            connection = getConnection();
            return connection.createChannel();
        } catch (Exception e) {
            log.info("getConnection error: ", e);
        }
        return null;
    }

    private static void closeConnection() {
        if (null != connection) {
            try {
                connection.close();
            } catch (IOException e) {
                log.error("close connection error: ", e);
            }
        }
    }
}
