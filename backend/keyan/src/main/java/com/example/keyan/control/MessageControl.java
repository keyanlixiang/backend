package com.example.keyan.control;

import com.example.keyan.dao.MessageDAO;
import com.example.keyan.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageControl {

    @Autowired
    private MessageDAO messageDAO;

    @RequestMapping("message/getByPid")
    @ResponseBody
    public Message getMessageByPid(@Param("pid") long pid){
        return messageDAO.getMessageByPid(pid);
    }

    @RequestMapping("message/getByTno")
    @ResponseBody
    public List<Message> getMessageByFaculty(@Param("tno") long tno){
        return messageDAO.getMessageByFaculty(tno);
    }

    @RequestMapping("message/update")
    @ResponseBody
    public void updateMessage(@Param("pid") long pid,@Param("newContext") String newContext,@Param("newAnnex") String newAnnex){
        messageDAO.updateMessage(pid,newContext,newAnnex);
    }


    @RequestMapping("message/delete")
    @ResponseBody
    public void deleteMessage(@Param("pid") long pid){
        messageDAO.deleteMessage(pid);
    }


    @RequestMapping("message/insert")
    @ResponseBody
    public void insertMessage(@Param("pid") long pid,@Param("tno") long tno,@Param("context") String context,@Param("annex") String annex){
        messageDAO.insertMessage(pid, tno, context, annex);
    }
}
