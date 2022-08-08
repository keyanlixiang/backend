package com.example.keyan.dao;

import com.example.keyan.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseDAO {
    /**
     * 根据课程号查找课程信息
     * @param cno
     * @return
     */
    Course getCourseByTno(@Param("cno") long cno);
}
