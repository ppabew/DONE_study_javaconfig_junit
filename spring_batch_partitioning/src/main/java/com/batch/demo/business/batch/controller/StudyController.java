package com.batch.demo.business.batch.controller;

import com.batch.demo.business.batch.service.StudyService;
import com.batch.demo.business.batch.vo.StudyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * date : 2020/11/24
 * file_name : StudyController
 * package_name : com.batch.demo.business.batch.controller
 * project_name : demo
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@RestController
@RequestMapping(value = "/study")
@Slf4j
public class StudyController {

    @Autowired
    StudyService studyService;


    @PostMapping(value = "/getStudy")
    public List<StudyVO> getStudyList() {
        List<StudyVO> studyList = new ArrayList<>();
        studyList = studyService.selectFilmList();
        return studyList;
    }


}
