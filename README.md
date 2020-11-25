# DONE-study-javaconfig-junit
## STEP 1 : 프로젝트 방향

해당 프로젝트의 경우 초기셋팅 프로젝트로 기존 xml 방식에서 Javaconfig 방식으로 프로젝트 진행



## STEP 2 : JavaConfig 셋팅

##### 다음과 같이 JavaConfig로 작성 기존의 경우 xml 파일로 작성했으나 Java로 작성시 추적 및 수정이 용이 앞으로 모든 프로젝트는 JavaConfig로 진행

```
package com.batch.demo.framework.context.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@MapperScan(basePackages = "com.batch.demo.**.**.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfiguration {

    /**
     * dataSource
     *
     * @return
     */
    @Primary
    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * sqlSessionFactory
     * @param dataSource
     * @param applicationContext
     *
     * @return
     * @throws Exception
     */
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext) throws java.lang.Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml")); //mybatis-config 위치
        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml")); //mapper.xml 파일들 위치
        return sessionFactoryBean.getObject();
    }

    /**
     * transactionManager
     * @param dataSource
     *
     * @return
     * @throws Exception
     */
    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) throws java.lang.Exception {
       return new DataSourceTransactionManager(dataSource);
    }

}

```



## STEP2 WARN1 : JavaConfig 셋팅시 주의 사항

```
    /**
     * sqlSessionFactory
     * @param dataSource
     * @param applicationContext
     *
     * @return
     * @throws Exception
     */
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource, ApplicationContext applicationContext) throws java.lang.Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml")); //mybatis-config 위치
        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/TT.xml")); //다음 구간에서 잘못되었을 경우 오류 발생
        return sessionFactoryBean.getObject();
    }
```

아래 경로가 잘못되었을 경우 BeanCreationException: Error creating bean with name 'sqlSessionFactory' defined in class path resource 다음과 같은 오류가 발생할 수 있다.

그러므로 다음과 같이 모든 xml파일을 허용하면 해당 오류를 방지할 수 있다.

```
sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
```

<img src='./img/스크린샷 2020-11-25 오후 5.09.19.png'/>



## STEP3 : Junit 셋팅 및 테스트

#### 기본적인 설정 프로젝트이므로 간단한 셋팅만하고 테스트 후 결과를 확인해보도록하자

```
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
```

테스트가 정상적으로 완료되면 다음과 같은 화면을 확인할 수 있다.(IDEA : IntelliJ)

<img src='./img/스크린샷 2020-11-25 오후 5.17.37.png'>



## STEP4 : 정리

해당 프로젝트의 경우 기본적인 Javaconfig 설정에 의의를 두므로 간단한 설정 셋팅 후 Junit 테스트까지 진행해보았다.