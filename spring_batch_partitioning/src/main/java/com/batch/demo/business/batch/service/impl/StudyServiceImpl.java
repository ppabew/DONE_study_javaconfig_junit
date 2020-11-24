package com.batch.demo.business.batch.service.impl;

import com.batch.demo.business.batch.mapper.StudyMapper;
import com.batch.demo.business.batch.service.StudyService;
import com.batch.demo.business.batch.vo.StudyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * date : 2020/11/24
 * file_name : StudyServiceImpl
 * package_name : com.batch.demo.business.batch.service.impl
 * project_name : demo
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@Service
@Slf4j
public class StudyServiceImpl implements StudyService {


    @Autowired
    StudyMapper studyMapper;

    @Override
    public List<StudyVO> selectFilmList() {
        log.info("studyMapper : {}",studyMapper.selectFilmList());
        return studyMapper.selectFilmList();
    }
}
