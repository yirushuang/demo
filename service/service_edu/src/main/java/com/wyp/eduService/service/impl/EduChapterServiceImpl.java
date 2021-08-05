package com.wyp.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyp.eduService.entity.EduChapter;
import com.wyp.eduService.entity.EduVideo;
import com.wyp.eduService.entity.chapter.ChapterVo;
import com.wyp.eduService.entity.chapter.VideoVo;
import com.wyp.eduService.mapper.EduChapterMapper;
import com.wyp.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyp.eduService.service.EduVideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-15
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Resource
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {

        //根据课程ID查询出所有章节信息
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByAsc("sort");
        List<EduChapter> eduChapters  = baseMapper.selectList(wrapper);
        //根据课程ID查询所有小节信息
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.orderByAsc("sort");
        List<EduVideo> eduVideos = eduVideoService.list(videoQueryWrapper);

        //最终返回的chapterVo集合
        List<ChapterVo> chapterVoList = new ArrayList<>();
        for(int i =0;i<eduChapters.size();i++){
            ChapterVo chapterVo = new ChapterVo();
            chapterVo.setId(eduChapters.get(i).getId());
            chapterVo.setTitle(eduChapters.get(i).getTitle());
            List<VideoVo> videoVos = new ArrayList<>();
            for(int j = 0;j<eduVideos.size();j++){
                if(eduChapters.get(i).getId().equals(eduVideos.get(j).getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    videoVo.setId(eduVideos.get(j).getId());
                    videoVo.setTitle(eduVideos.get(j).getTitle());
                    videoVos.add(videoVo);
                }
                chapterVo.setVideos(videoVos);
            }
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }

    //删除一个章节，如果该章节下有小节，则无法删除
    @Override
    public boolean deleteChapter(String id) {

        List<EduVideo> videos = new ArrayList<>();
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        videos = eduVideoService.list(wrapper);
        if(videos.size()>0){
            return false;
        }else{
            baseMapper.deleteById(id);
            return true;
        }
    }
}
