package com.example.keyan.control;

import com.example.keyan.model.Message;
import com.example.keyan.pojo.Result;
import com.example.keyan.service.MessageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MessageControl {


    @Autowired
    private MessageService messageService;


    @RequestMapping("message/getByPid")
    @ResponseBody
    public Result<Message> getMessageByPid(@Param("pid") long pid){
        return messageService.getMessageByPid(pid);
    }

    @RequestMapping("message/getByTno")
    @ResponseBody
    public Result<List<Message>> getMessageByFaculty(@Param("tno") long tno){
        return messageService.getMessageByFaculty(tno);
    }

    @RequestMapping("message/update")
    @ResponseBody
    public Result<Message> updateMessage(@Param("pid") long pid, @Param("newContext") String newContext, @Param("newAnnex") String newAnnex){
        return messageService.updateMessage(pid,newContext,newAnnex);
    }


    @RequestMapping("message/delete")
    @ResponseBody
    public Result<Message> deleteMessage(@Param("pid") long pid){
         return messageService.deleteMessage(pid);
    }


    @RequestMapping("message/insert")
    @ResponseBody
    public Result<Message> insertMessage(@Param("pid") long pid,@Param("tno") long tno,@Param("context") String context,@Param("annex") String annex){
       return messageService.insertMessage(pid, tno, context, annex);
    }
}
