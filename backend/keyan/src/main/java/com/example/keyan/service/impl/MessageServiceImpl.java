package com.example.keyan.service.impl;

import com.example.keyan.dao.MessageDAO;
import com.example.keyan.model.Message;
import com.example.keyan.pojo.Result;
import com.example.keyan.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public Result<Message> getMessageByPid(long pid) {
        Result<Message> result=new Result<>();
        Message message=messageDAO.getMessageByPid(pid);
        if(message==null){
            result.setCode("403");
            result.setMessage("查询失败");
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setData(message);
        result.setCode("200");
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result<List<Message>> getMessageByFaculty(long tno) {
        Result<List<Message>> result=new Result<>();
        List<Message> messages=messageDAO.getMessageByFaculty(tno);
        if(messages==null){
            result.setCode("403");
            result.setMessage("查询失败");
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setData(messages);
        result.setCode("200");
        result.setMessage("查询成功");
        return result;

    }

    @Override
    public Result<Message> insertMessage(long pid, long tno, String context, String annex) {
        Result<Message> result=new Result<>();
        messageDAO.insertMessage(pid, tno, context, annex);
        Message message=messageDAO.getMessageByPid(pid);
        if(message==null){
            result.setCode("403");
            result.setMessage("插入消息失败");
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setData(message);
        result.setCode("200");
        result.setMessage("插入消息成功");
        return result;

    }

    @Override
    public Result<Message> deleteMessage(long pid) {
        Result<Message> result=new Result<>();
        Message message=messageDAO.getMessageByPid(pid);
        messageDAO.deleteMessage(pid);
        Message message1=messageDAO.getMessageByPid(pid);
        if(message1!=null){
            result.setCode("403");
            result.setMessage("删除消息失败");
            result.setSuccess(false);
            return result;
        }
        result.setSuccess(true);
        result.setData(message);
        result.setCode("200");
        result.setMessage("删除消息成功");
        return result;
    }

    @Override
    public Result<Message> updateMessage(long pid, String newContext, String newAnnex) {
        Result<Message> result=new Result<>();
        Message oldMessage=messageDAO.getMessageByPid(pid);
        if(oldMessage.getPcontext().equals(newContext)&&oldMessage.getAnnex().equals(newAnnex)){
            result.setSuccess(false);
            result.setCode("403");
            result.setMessage("更新消息内容未变更");
            return result;
        }
        messageDAO.updateMessage(pid, newContext, newAnnex);
        Message newMessage=messageDAO.getMessageByPid(pid);
        if(newMessage.getPcontext().equals(newContext)&&newMessage.getAnnex().equals(newAnnex)){
            result.setSuccess(true);
            result.setData(newMessage);
            result.setCode("200");
            result.setMessage("更新消息成功");
            return result;
        }
        result.setSuccess(false);
        result.setCode("403");
        result.setMessage("更新消息失败");
        return result;
    }
}
