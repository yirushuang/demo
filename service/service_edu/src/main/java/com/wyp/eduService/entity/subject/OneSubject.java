package com.wyp.eduService.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {
    private String id;
    private String label;
    List<TwoSubject> twoSubjects = new ArrayList<>();
}
