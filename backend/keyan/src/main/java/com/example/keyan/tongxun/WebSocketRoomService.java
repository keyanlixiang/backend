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
import java.io.IOException;

@Component
@ServerEndpoint(value = "/webSocket/{roomId}/{userId}")
public class WebSocketRoomService {


    private static OffSmsDAO offSmsDAO;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    private Session session;
    private String roomId;
    private String userId;
    private String id;

    @Autowired
    public void setOffSmsDAO(OffSmsDAO offSmsDAO){
        WebSocketRoomService.offSmsDAO=offSmsDAO;
    }

    @OnOpen
    public void open(@PathParam("roomId") String roomId, Session session, @PathParam("userId") String userId ) throws IOException {
        this.session=session;
        this.userId=userId;
        this.roomId=roomId;
        this.id=roomId+"-"+userId;
        WebSocketRoomMapUtil.put(id,session);
        logger.info(userId+"上线");


        if(offSmsDAO.getAllOffSms(id).size()>0) {
            for (Sms messageData : offSmsDAO.getAllOffSms(id)) {
                session.getAsyncRemote().sendText(JSON.toJSONString(messageData));
                offSmsDAO.deleteOffSms(messageData);
            }
        }
    }

    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        //移除关闭的连接
        WebSocketRoomMapUtil.remove(id);
        logger.info(userId+"下线");
    }

    @OnError
    public void onError(@PathParam("userId") String userId, Throwable error) {
        logger.info(userId + "发生连接错误" + error.getMessage());
        error.printStackTrace();
    }


    @OnMessage
    public void onMessage(String message, @PathParam("roomId") String roomId, @PathParam("userId") String userId) throws Exception {

        logger.info("收到来自" + userId + "的消息：" + message);
        // 将传送过来的JSON格式数据转换成Object
        ObjectMapper objectMapper = new ObjectMapper();
        Sms messageData = objectMapper.readValue(message, Sms.class);

        for(String key:WebSocketRoomMapUtil.getAllKey()){
            Session session=WebSocketRoomMapUtil.get(key);
            Sms newmessage = new Sms();
            newmessage = messageData;
            newmessage.setToUserId(key);
            if(session==null){
                //保存离线消息
                offSmsDAO.insertOffSms(newmessage);
            }else {
                session.getAsyncRemote().sendText(JSON.toJSONString(newmessage));
            }

        }

    }

}
