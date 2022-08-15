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
}
