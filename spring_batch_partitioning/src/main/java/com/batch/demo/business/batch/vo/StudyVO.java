package com.batch.demo.business.batch.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * date : 2020/11/24
 * file_name : StudyVO
 * package_name : com.batch.demo.business.batch.vo
 * project_name : demo
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@Getter
@Setter
@Alias("studyVO")
public class StudyVO {
    private int filmId;
}
