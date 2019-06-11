package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/11 11:41
 **/
public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label>{

    List<Label> findByrecommend(String recommend);

    List<Label> findByState(String s);

}
