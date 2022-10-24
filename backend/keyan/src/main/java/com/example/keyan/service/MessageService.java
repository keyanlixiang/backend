package com.example.keyan.service;

import com.example.keyan.model.Message;
import com.example.keyan.pojo.Result;

import java.util.List;

public interface MessageService {

    /**
     * 根据消息编号查询消息
     * @param pid
     * @return
     */
    Result<Message> getMessageByPid(long pid);


    /**
     * 根据发布教职工工号查询消息
     * @param tno
     * @return
     */
    Result<List<Message>> getMessageByFaculty(long tno);

    /**
     * 发布消息
     * @param pid
     * @param tno
     * @param context
     * @param annex
     * @return
     */
    Result<Message> insertMessage(long pid,long tno,String context,String annex);

    /**
     * 根据消息编号删除消息内容
     * @param pid
     * @return
     */
    Result<Message> deleteMessage(long pid);


    /**
     * 更新消息内容
     * @param pid
     * @param newContext
     * @param newAnnex
     * @return
     */
    Result<Message> updateMessage(long pid, String newContext,String newAnnex);
}
