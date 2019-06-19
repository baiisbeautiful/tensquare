package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2019/6/11 11:45
 **/
@RestController
@RequestMapping("/label")
public class LabelController {
    @Resource
    private LabelService labelService;

    /**
     * 新增一条label
     * @param label
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true, StatusCode.OK,"增加成功");
    }

    /**
     * 查询所有label
     * @return
     */
    @GetMapping
    public List<Label> findAll(){
        return labelService.findAll();
    }

    /**
     * 查询推荐标签列表
     * @return
     */
    @GetMapping("/toplist")
    public Result findByRecommend(){
        List<Label> list=labelService.findByRecommend();
        return new Result(true,StatusCode.OK,"查询列表成功",list);
    }

    /**
     * 查询有效标签列表
     * @return
     */
    @GetMapping("/list")
    public Result findByState(){
        List<Label> list=labelService.findByState();
        return new Result(true,StatusCode.OK,"成功查询到数据",list);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{labelid}")
    public Result findById(@PathVariable("labelid")String id){
        System.out.println("NO.1");
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
       /* Label label=labelService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",label);*/
    }

    /**
     * 根据id修改
     * @param id
     * @param label
     * @return
     */
    @PutMapping("/{labelid}")
    public Result updateById(@PathVariable("labelid")String id,@RequestBody Label label){
        labelService.updateById(id,label);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{labelid}")
    public Result deleteById(@PathVariable("labelid")String id){
        labelService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 多条件复杂查询
     * @param searchMap
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap){
        List<Label> labelList=labelService.findSearch(searchMap);
        return new Result(true,StatusCode.OK,"查询成功",labelList);
    }
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@PathVariable("page")int page,@PathVariable("size")int size,
                             @RequestBody Map searchMap){
        Page pageList = labelService.findSearch(searchMap, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(pageList.getTotalElements(),
                pageList.getContent()));
    }
}
