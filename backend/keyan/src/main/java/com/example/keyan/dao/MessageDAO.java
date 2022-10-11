package com.example.keyan.dao;

import com.example.keyan.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDAO {

    /**
     * 根据消息编号查询消息
     * @param pid
     * @return
     */
    Message getMessageByPid(@Param("pid") long pid);

    /**
     * 根据发布教职工工号查询消息
     * @param tno
     * @return
     */
    List<Message> getMessageByFaculty(@Param("tno") long tno);


    /**
     * 发布消息
     * @param pid
     * @param tno
     * @param context
     * @param annex
     * @return
     */
    int insertMessage(@Param("pid") long pid,@Param("tno") long tno,@Param("context") String context,@Param("annex") String annex);

    /**
     * 根据消息编号删除消息内容
     * @param pid
     * @return
     */
    int deleteMessage(@Param("pid") long pid);

    /**
     * 更新消息内容
     * @param pid
     * @param newContext
     * @param newAnnex
     * @return
     */
     int updateMessage(@Param("pid") long pid,@Param("newContext") String newContext,@Param("newAnnex") String newAnnex);
}
