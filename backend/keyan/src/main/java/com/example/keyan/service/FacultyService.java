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
}
