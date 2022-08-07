package com.example.keyan.dao;

import com.example.keyan.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentDAO {
    /**
     * 根据学号查询学生信息
     * @param sno
     * @return
     */
    Student getStudentBySno(@Param("sno") long sno);
}
