package com.example.keyan.service;

import com.example.keyan.model.Faculty;
import com.example.keyan.pojo.Result;

public interface FacultyService {

    /**
     * 教职工登录
     * @param tno
     * @param tpassword
     * @return
     */
    Result<Faculty> login(long tno,String tpassword);

    /**
     * 教职工修改密码
     * @param tno
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Result<Faculty> updatePassword(long tno,String oldPassword,String newPassword);
}
