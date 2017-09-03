package com.xueyou.zkview.service.controller;

import com.xueyou.zkview.service.dao.StudentDao;
import com.xueyou.zkview.service.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wuxueyou on 2017/6/15.
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisTestController {
    @Autowired
    public StudentDao studentDao;

    @RequestMapping("/getAll")
    public List<Student> getAll() {
        List<Student> studentList = studentDao.getAll();
        return studentList;
    }

    @RequestMapping("/getById")
    public Student getById(int id) {
        return studentDao.getById(id);
    }

    @RequestMapping("/getByNameLike")
    public List<Student> getByNameLike(String name) {
        return studentDao.getByNameLike(name);
    }

    @RequestMapping("/getByIdAndName")
    public Student getByIdAndName(int id, String name) {
        return studentDao.getByIdAndName(new Student(id, name));
    }

    @RequestMapping("/getByIdAndNameWithParamMap")
    public Student getByIdAndNameWithParamMap(int id, String name) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("name", name);
        return studentDao.getByIdAndNameWithParamMap(param);
    }

    @RequestMapping("/getByCreatetime")
    public List<Student> getByCreatetime(String dateStr) throws Exception {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        return studentDao.getByCreateTime(date);
    }

    @RequestMapping("/insertStudent")
    public int insertStudent(int id, String name, int age, String memo) {
        return studentDao.insertStudent(new Student(id, name, age, memo));
    }

    @RequestMapping("/deleteById")
    public int deleteById(int id) {
        return studentDao.deleteById(new Student(id));
    }
}
