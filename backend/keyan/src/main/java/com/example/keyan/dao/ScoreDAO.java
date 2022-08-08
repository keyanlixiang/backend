package com.example.keyan.dao;

import com.example.keyan.model.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScoreDAO {

    Score getScore(@Param("sno") long sno, @Param("cno") long cno);
}
