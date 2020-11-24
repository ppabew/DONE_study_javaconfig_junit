package com.batch.demo.business.batch.service;

import com.batch.demo.business.batch.vo.StudyVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudyService {
    List<StudyVO> selectFilmList();
}
