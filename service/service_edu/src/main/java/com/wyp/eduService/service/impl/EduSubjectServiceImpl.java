package com.wyp.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyp.eduService.entity.EduSubject;
import com.wyp.eduService.entity.excel.SubjectData;
import com.wyp.eduService.entity.subject.OneSubject;
import com.wyp.eduService.entity.subject.TwoSubject;
import com.wyp.eduService.listener.SubjectExcelListener;
import com.wyp.eduService.mapper.EduSubjectMapper;
import com.wyp.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-14
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> subjectList() {

        //查询出所有一级节点
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        List<EduSubject> eduSubjects = baseMapper.selectList(queryWrapper);

        //查询出所有二级节点
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.ne("parent_id",0);
        List<EduSubject> eduSubjects2 = baseMapper.selectList(queryWrapper2);
        System.out.println(eduSubjects2.size());
        //封装一级节点
        List<OneSubject> oneSubjects =  new ArrayList<>();
        for (int i = 0; i < eduSubjects.size(); i++) {
            OneSubject oneSubject = new OneSubject();
            oneSubject.setId(eduSubjects.get(i).getId());
            oneSubject.setLabel(eduSubjects.get(i).getTitle());
            for (int j = 0; j < eduSubjects2.size(); j++) {
                if(oneSubject.getId().equals(eduSubjects2.get(j).getParentId())){
                    TwoSubject twoSubject = new TwoSubject();
                    twoSubject.setId(eduSubjects2.get(j).getId());
                    twoSubject.setLabel(eduSubjects2.get(j).getTitle());
                    oneSubject.getTwoSubjects().add(twoSubject);
                }
            }
            oneSubjects.add(oneSubject);
        }
        return oneSubjects;
    }


}
