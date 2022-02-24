package com.huadi.itmp.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author meteor
 */
@Configuration
public class SocketIOConfig {

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname("0.0.0.0");
        config.setPort(8002);
        config.setRandomSession(true);
        config.setOrigin(null);
        config.setBossThreads(1);
        config.setAllowCustomRequests(true);
        // Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        // Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        return new SocketIOServer(config);
    }

}
