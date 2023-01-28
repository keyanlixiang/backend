package com.example.keyan.tongxun;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OffSmsDAO {

    int insertOffSms(Sms sms);

    int deleteOffSms(Sms sms);

    List<Sms> getAllOffSms(@Param("toUserId")String toUserId);

}
