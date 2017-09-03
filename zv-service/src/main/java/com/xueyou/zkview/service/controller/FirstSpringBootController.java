package com.xueyou.zkview.service.controller;

import com.xueyou.zkview.service.pojo.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class FirstSpringBootController {

    @RequestMapping(value = "/greeting", method = {RequestMethod.GET})
    public String greeting() {
        return "sagddf";
    }

    @RequestMapping(value = "/greeting2", method = {RequestMethod.POST})
    public Student greeting2(int id,String name) {
        Student student = new Student(id, name);
        return student;
    }

    @RequestMapping(value = "/greeting3", method = {RequestMethod.GET})
    public List<Student> greeting3() {
        Student student = new Student(1, "xiaoming");
        Student student2 = new Student(2, "xiaohong");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);
        return studentList;
    }

    @RequestMapping(value = "/greeting4")
    public List<Map<String,String>> greeting4(){
        Map<String,String> map = new HashMap<>();
        map.put("name","xiaoxiao");
        map.put("id","12");
        Map<String,String> map2 = new HashMap<>();
        map2.put("name","xxxx");
        map2.put("id","16");
        List<Map<String,String>> mapList = new ArrayList<>();
        mapList.add(map);
        mapList.add(map2);

        return mapList;

    }
}

