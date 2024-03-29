package com.example.keyan.control;

import com.example.keyan.dao.CourseDAO;
import com.example.keyan.dao.FacultyDAO;
import com.example.keyan.dao.ScoreDAO;
import com.example.keyan.dao.StudentDAO;
import com.example.keyan.model.Course;
import com.example.keyan.model.Faculty;
import com.example.keyan.model.Score;
import com.example.keyan.model.Student;
import com.example.keyan.pojo.Result;
import com.example.keyan.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class StudentControl {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private FacultyDAO facultyDAO;

    @Autowired
    private ScoreDAO scoreDAO;

    @Autowired
    private StudentService studentService;



    @RequestMapping("/gets")
    @ResponseBody
    public Student getStudent(@Param("sno") long sno){
        return studentDAO.getStudentBySno(sno);
    }

    @RequestMapping("/getc")
    @ResponseBody
    public Course getC(@Param("cno") long cno){
        return courseDAO.getCourseByTno(cno);
    }

    @RequestMapping("/gett")
    @ResponseBody
    public Faculty gett(@Param("tno") long tno){
        return facultyDAO.getFacultyByTno(tno);
    }


    @RequestMapping("/getScore")
    @ResponseBody
    public Score getScore(@Param("sno")long sno,@Param("cno") long cno){
        return scoreDAO.getScore(sno,cno);
    }



    @RequestMapping("student/login")
    @ResponseBody
    public Result<Student> login(@Param("sno") long sno,@Param("spassword") String spassword){
        return studentService.login(sno,spassword);
    }
    @RequestMapping("student/updatePassword")
    @ResponseBody
    public Result<Student> updatePassword(@Param("sno") long sno,@Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword){
        return studentService.updatePassword(sno,oldPassword,newPassword);
    }


}
