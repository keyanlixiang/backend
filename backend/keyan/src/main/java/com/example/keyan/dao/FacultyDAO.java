package com.example.keyan.dao;

import com.example.keyan.model.Faculty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FacultyDAO {
    /**
     * 根据工号查询教职工信息
     * @param tno
     * @return
     */
    Faculty getFacultyByTno(@Param("tno") long tno);
}
