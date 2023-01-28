package com.example.keyan.tongxun;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Component
@ServerEndpoint(value = "/webSocket/{userId}")
public class WebSocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);



    private static OffSmsDAO offSmsDAO;

    private Session session;
    private String userId;

    @Autowired
    public void setOffSmsDAO(OffSmsDAO offSmsDAO){
        WebSocketService.offSmsDAO=offSmsDAO;
    }

    /*
     *
     * 有连接时回调
     * */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        WebSocketMapUtil.put(userId, session);
        logger.info(userId+"上线");

        //处理离线时期收到的信息
        List<Sms> offSms=offSmsDAO.getAllOffSms(userId);
        if(offSms!=null) {
            if (offSms.size() > 0) {
                for (Sms offsms : offSms) {
                    sendMessage(offsms, session);
                    offSmsDAO.deleteOffSms(offsms);
                }
            }
        }
    }

    /*
     *
     * 断开连接时回调
     * */
    @OnClose
    public void onClose(@PathParam("userId") String userId) {

        /*
         *
         * 移除关闭的连接
         * */
        WebSocketMapUtil.remove(userId);
        logger.info(userId+"下线");
    }

    /*
     *
     * 发生错误时回调
     * */
    @OnError
    public void onError(@PathParam("userId") String userId, Throwable error) {
        logger.info(userId + "发生连接错误" + error.getMessage());
        error.printStackTrace();
    }

    /*
     *
     * 收到消息时回调
     * */
    @OnMessage
    public void onMessage(String message, @PathParam("userId") String userId) throws Exception {

        logger.info("收到来自" + userId + "的消息：" + message);

        // 将传送过来的JSON格式数据转换成Object
        ObjectMapper objectMapper = new ObjectMapper();
        Sms messageData = objectMapper.readValue(message, Sms.class);

        // 私聊
        if (messageData.getMsgType() == 1) {

            // 发送者的Session
            Session fromSession = WebSocketMapUtil.get(userId);
            // 接收者的Session
            Session toSession = WebSocketMapUtil.get(messageData.getToUserId());
            // 判断接收者是否在线
            if (toSession != null) {
                // 在线，传达消息
                sendMessage(messageData, toSession);
                sendMessage(messageData, fromSession);
            } else {
                //离线，保存到数据库中
                offSmsDAO.insertOffSms(messageData);
                sendMessage(messageData, fromSession);
            }
        }

        // 发送所有人
        if (messageData.getMsgType() == 0) {
            // 遍历当前所有在线人
            sendMessageAll(messageData);
            logger.info("这是一条群发消息：" + JSON.toJSONString(messageData));
        }
    }

    /*
     *
     * 发送消息
     * */
    public void sendMessage(Sms messageData, Session session) {
        session.getAsyncRemote().sendText(JSON.toJSONString(messageData));
    }

    /*
     *
     * 群发消息
     * */
    public void sendMessageAll(Sms messageData) {
        for (String key : WebSocketMapUtil.getAllKey()) {
            Session session_map = WebSocketMapUtil.get(key);
            messageData.setToUserId(key);
            if(session_map==null){
                //保存离线信息
                offSmsDAO.insertOffSms(messageData);
            }else {
                //在线发送信息
                session_map.getAsyncRemote().sendText(JSON.toJSONString(messageData));
            }
        }
    }



}
