package com.example.keyan.tongxun;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SmsDAO {

    int insertSms(Sms sms);

    List<Sms> getAllSms(@Param("toUserId")String toUserId);

}
