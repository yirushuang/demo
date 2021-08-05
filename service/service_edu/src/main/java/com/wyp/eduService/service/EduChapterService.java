package com.wyp.eduService.service;

import com.wyp.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyp.eduService.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-15
 */
public interface EduChapterService extends IService<EduChapter> {

    //查询出所有章节和小节的信息并封装
    List<ChapterVo> getChapterVideo(String id);

    //删除一个章节，如果该章节下有小节，则无法删除
    boolean deleteChapter(String id);
}
