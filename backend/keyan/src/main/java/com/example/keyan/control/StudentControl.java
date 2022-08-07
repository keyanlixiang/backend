package com.example.keyan.control;

import com.example.keyan.dao.StudentDAO;
import com.example.keyan.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentControl {

    @Autowired
    private StudentDAO studentDAO;


    @RequestMapping("/getStudent")
    @ResponseBody
    public Student getStudent(@Param("sno") long sno){
        return studentDAO.getStudentBySno(sno);
    }
}
