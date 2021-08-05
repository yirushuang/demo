package com.wyp.eduService.controller;


import com.wyp.commonutils.R;
import com.wyp.eduService.entity.subject.OneSubject;
import com.wyp.eduService.service.EduSubjectService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-14
 */
@RestController
@CrossOrigin
@RequestMapping("/eduService/subject")
public class EduSubjectController {

    @Resource
    private EduSubjectService eduSubjectService;

    @GetMapping("/list")
    public R subjectList(){
        List<OneSubject> oneSubjects = eduSubjectService.subjectList();
        return  R.ok().data("subjectList",oneSubjects);
    }

}

