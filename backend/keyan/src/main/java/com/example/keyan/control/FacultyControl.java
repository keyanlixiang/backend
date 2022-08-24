package com.example.keyan.control;


import com.example.keyan.model.Faculty;
import com.example.keyan.pojo.Result;
import com.example.keyan.service.FacultyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FacultyControl {

    @Autowired
    private FacultyService facultyService;

    @RequestMapping("faculty/login")
    @ResponseBody
    public Result<Faculty> login(@Param("tno") long tno, @Param("tpassword") String tpassword){
        return facultyService.login(tno, tpassword);
    }


    @RequestMapping("faculty/updatePassword")
    @ResponseBody
    public Result<Faculty> updatePassword(@Param("tno") long tno, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword){
        return facultyService.updatePassword(tno,oldPassword,newPassword);
    }

}
