package com.example.keyan.service.impl;

import com.example.keyan.dao.FacultyDAO;
import com.example.keyan.model.Faculty;
import com.example.keyan.pojo.Result;
import com.example.keyan.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
        private FacultyDAO facultyDAO;

    @Override
    public Result<Faculty> login(long tno, String tpassword) {
        Result<Faculty> result=new Result<>();
        Faculty faculty=facultyDAO.getFacultyByTno(tno);
        if(faculty==null){
            result.setSuccess(false);
            result.setCode("204");
            result.setMessage("没找到该教职工信息");
            return result;
        }
        if(!tpassword.equals(faculty.getTpassword())){
            result.setSuccess(false);
            result.setCode("203");
            result.setMessage("密码错误");
            return result;
        }
        result.setSuccess(true);
        result.setData(faculty);
        result.setCode("200");
        result.setMessage("登录成功");
        return result;
    }

    @Override
    public Result<Faculty> updatePassword(long tno, String oldPassword, String newPassword) {
        Result<Faculty> result=new Result<>();

        if(oldPassword.equals(newPassword)){
            result.setSuccess(false);
            result.setMessage("新密码与旧密码相同");
            result.setCode("202");
            return  result;
        }

        Faculty faculty=facultyDAO.getFacultyByTno(tno);
        if(!oldPassword.equals(faculty.getTpassword())){
            result.setSuccess(false);
            result.setMessage("原密码输入错误");
            result.setCode("203");
            return  result;
        }

        facultyDAO.updateTpassword(tno,newPassword);
        faculty=facultyDAO.getFacultyByTno(tno);
        if(!newPassword.equals(faculty.getTpassword())){
            result.setSuccess(false);
            result.setMessage("修改密码过程中出现问题");
            result.setCode("204");
            return  result;
        }
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("密码修改成功");
        result.setData(faculty);
        return result;
    }
}
