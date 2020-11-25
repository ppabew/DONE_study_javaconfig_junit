package com.batch.demo.business.batch.mapper;

import com.batch.demo.business.batch.vo.StudyVO;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class StudyMapperTest extends TestCase {

    @Autowired
    StudyMapper studyMapper;

    @Test
    public void selectFilmList() {
        List<StudyVO> studyList = new ArrayList<>();
        studyList = studyMapper.selectFilmList();
        for (StudyVO study:studyList) {
            log.info("data : {}",study.getFilmId());
        }
    }

}