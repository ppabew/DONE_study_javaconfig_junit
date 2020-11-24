package com.batch.demo.business.batch.mapper;

import com.batch.demo.business.batch.vo.StudyVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyMapper {
    List<StudyVO> selectFilmList();
}
