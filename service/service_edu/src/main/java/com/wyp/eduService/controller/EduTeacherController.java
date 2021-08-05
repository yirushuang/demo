package com.wyp.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyp.commonutils.R;
import com.wyp.commonutils.ResultCode;
import com.wyp.eduService.entity.EduTeacher;
import com.wyp.eduService.entity.vo.TeacherQuery;
import com.wyp.eduService.service.EduTeacherService;
import com.wyp.servicebase.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-08
 */
@Api(value = "讲师管理")
@RestController
@RequestMapping("/eduService/teacher")
@Slf4j
@CrossOrigin
public class EduTeacherController {

    @Resource
    private EduTeacherService teacherService;

    @ApiOperation(value = "讲师列表")
    @GetMapping("teacherList")
    public R teacherList(){
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.le("is_deleted","1");
        List<EduTeacher> teacherList = teacherService.list(wrapper);
        return R.ok().data("teacherList",teacherList);
    }

    @ApiOperation(value = "删除讲师")
    @DeleteMapping("{id}")
    public R deleteTeacher(@ApiParam(value = "讲师id",name = "id") @PathVariable String id){
        boolean b = teacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页查询讲师")
    @GetMapping("teacherPageList/{current}/{limit}")
    public R teacherPageList(@PathVariable long current,@PathVariable long limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
        teacherService.page(eduTeacherPage,null);
        return R.ok().code(ResultCode.SUCCESS).data("eduTeacherPage",eduTeacherPage);
    }


    @PostMapping("conditionPage/{current}/{limit}")
    public R conditionPage(@PathVariable Long current, @PathVariable long limit , @RequestBody TeacherQuery teacherQuery){

        Integer level =teacherQuery.getLevel();

        System.out.println(level);
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(teacherQuery.getName())){
            wrapper.like("name",teacherQuery.getName());
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(teacherQuery.getBegin())){
            wrapper.ge("gmt_create",teacherQuery.getBegin());
        }
        if(!StringUtils.isEmpty(teacherQuery.getEnd())){
            wrapper.le("gmt_modified",teacherQuery.getEnd());
        }

        teacherService.page(teacherPage,wrapper);

        return R.ok().data("list",teacherPage.getRecords()).data("total",teacherPage.getTotal());
    }

    @PostMapping("/insertTeacher")
    public R insertTeacher(@RequestBody EduTeacher eduTeacher){
        eduTeacher.setIsDeleted(false);
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else{
            return R.error();
        }
    }


    @PostMapping("/update")
    public R updateTeacher(@RequestBody EduTeacher teacher){
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("id",teacher.getId());
        boolean update = teacherService.update(teacher, wrapper);
        if(update){
            return  R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("{id}")
    public R selectById(@PathVariable String id){
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        EduTeacher teacher = teacherService.getOne(wrapper);
        return  R.ok().data("teacher",teacher);
    }

    @PostMapping("/error")
    public R error(){
        try{
            int i= 1/0;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new MyException(ResultCode.ERROR,"不能除零");
        }
        return R.ok();
    }



}

