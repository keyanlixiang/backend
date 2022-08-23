package com.example.keyan.service.impl;

import com.example.keyan.dao.StudentDAO;
import com.example.keyan.model.Student;
import com.example.keyan.pojo.Result;
import com.example.keyan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public Result<Student> login(long sno, String spassword) {
        Result<Student> result=new Result<>();
        Student student=studentDAO.getStudentBySno(sno);
        if(student==null){
            result.setSuccess(false);
            result.setCode("204");
            result.setMessage("没找到该学生信息");
            return result;
        }
        if(!spassword.equals(student.getSpassword())){
            result.setSuccess(false);
            result.setCode("203");
            result.setMessage("密码错误");
            return result;
        }
        result.setData(student);
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("登录成功");
        return result;
    }

    @Override
    public Result<Student> updatePassword(long sno, String oldPassword, String newPassword) {
        Result<Student> result=new Result<>();

        if(oldPassword.equals(newPassword)){
            result.setSuccess(false);
            result.setMessage("新密码与旧密码相同");
            result.setCode("202");
            return  result;
        }

        Student student=studentDAO.getStudentBySno(sno);
        if(!oldPassword.equals(student.getSpassword())){
            result.setSuccess(false);
            result.setCode("203");
            result.setMessage("原密码输入错误");
            return result;
        }

        studentDAO.updateSpassword(sno,newPassword);
        student=studentDAO.getStudentBySno(sno);
        if(!newPassword.equals(student.getSpassword())){
            result.setSuccess(false);
            result.setMessage("修改密码过程中出现问题");
            result.setCode("204");
            return  result;
        }
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("密码修改成功");
        result.setData(student);
        return result;
    }
}
