package com.xueyou.zkview.service.dao;

import com.xueyou.zkview.service.pojo.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wuxueyou on 2017/6/15.
 */
public interface StudentDao {
    List<Student> getAll();

    Student getById(int id);

    List<Student> getByNameLike(String name);

    Student getByIdAndName(Student student);

    Student getByIdAndNameWithParamMap(HashMap<String, Object> params);

    List<Student> getByCreateTime(Date date);

    int insertStudent(Student student);

    int deleteById(Student student);
}
