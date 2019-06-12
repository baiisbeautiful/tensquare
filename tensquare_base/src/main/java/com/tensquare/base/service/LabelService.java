package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.common.util.IdWorker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {
    @Resource
    private LabelDao labelDao;
    @Resource
    private IdWorker idWorker;
    public void add(Label label) {
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public List<Label> findAll() {
       return labelDao.findAll();
    }

    public List<Label> findByRecommend() {
       return labelDao.findByrecommend("1");
    }

    public List<Label> findByState() {
        return labelDao.findByState("1");
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void updateById(String id, Label label) {
        Label label1 = labelDao.findById(id).get();
        label1.setLabelname(label.getLabelname());
        label1.setState(label.getState());
        label1.setCount(label.getCount());
        label1.setRecommend(label.getRecommend());
        labelDao.save(label1);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Specification<Label> createSpcification(Map searchMap){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                 List<Predicate> predicateList=new ArrayList<>();
                 if(searchMap.get("labelname")!=null&&!"".equals(searchMap.get("labelname"))){
                     predicateList.add(cb.like(root.get("labelname").as(String.class),
                             "%"+searchMap.get("labelname")+"%") );
                 }
                 if(searchMap.get("state")!=null&&!"".equals(searchMap.get("state"))){
                     predicateList.add(cb.equal(root.get("state").as(String.class),
                             searchMap.get("state")));
                 }
                 if(searchMap.get("recommend")!=null&&!"".equals(searchMap.get("recommend"))){
                     predicateList.add(cb.equal(root.get("recommend").as(String.class),searchMap.get("recommend")));
                 }
                 return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }


    public List<Label> findSearch(Map searchMap) {
        Specification<Label> spcification = createSpcification(searchMap);
        return labelDao.findAll(spcification);
    }

    public Page<Label> findSearch(Map searchMap, int page, int size) {
        Specification<Label> spcification = createSpcification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(spcification,pageRequest);
    }
}
