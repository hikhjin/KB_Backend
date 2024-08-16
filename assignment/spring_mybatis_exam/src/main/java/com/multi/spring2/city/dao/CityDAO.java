package com.multi.spring2.city.dao;

import com.multi.spring2.city.domain.CityVO;
import com.multi.spring2.city.mapper.CityMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CityDAO {
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public CityDAO(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
    public List<CityVO> selectAllCity() {
        return sqlSessionTemplate.getMapper(CityMapper.class).all();
    }

    public int insertCity(CityVO cityVO) {
        return sqlSessionTemplate.getMapper(CityMapper.class).insert(cityVO);
    }
}

