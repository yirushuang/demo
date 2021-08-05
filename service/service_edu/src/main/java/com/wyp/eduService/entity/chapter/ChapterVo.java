package com.wyp.eduService.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节vo类
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    List<VideoVo> videos = new ArrayList<>();
}
