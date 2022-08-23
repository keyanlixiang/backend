package com.example.keyan.service;

import com.example.keyan.model.Student;
import com.example.keyan.pojo.Result;

public interface StudentService {

    /**
     * 学生登录
     * @param sno
     * @param spassword
     * @return
     */
    Result<Student> login(long sno,String spassword);


    /**
     * 学生修改密码
     * @param sno
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Result<Student> updatePassword(long sno,String oldPassword,String newPassword);
}
