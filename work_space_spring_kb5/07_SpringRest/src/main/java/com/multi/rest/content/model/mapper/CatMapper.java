package com.multi.rest.content.model.mapper;

import com.multi.rest.content.model.vo.Cat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CatMapper {
    List<Cat> selectAll();
    List<Cat> selectByName(String name);
    Cat selectById(int id);
    Cat selectMostRecent();
    int insertCat(Cat cat);
    int updateCat(Cat cat);
    int deleteCat(int id);
}
