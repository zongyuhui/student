package com.zyh.student.controller;

import com.zyh.student.domain.Result;
import com.zyh.student.domain.Student;
import com.zyh.student.repository.StudentRepository;
import com.zyh.student.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private studentService stuService;

    /**
     * 查询全部
     * @return
     */
    @GetMapping(value="/student")
    public List<Student> StudentList() {
        return studentRepository.findAll();
    }

//    @PostMapping(value="/student")
//    public Student add(@PathParam("name") String name,
//                       @PathParam("score") Integer score) {
//        Student stu=new Student();
//        stu.setName(name);
//        stu.setScore(score);
//        return studentRepository.save(stu);
//    }

    /**
     * 添加
     * @param stu
     * @param bindingResult
     * @return
     */
    @PostMapping(value="/student")
    public Result<Student> Add(@Valid Student stu, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Result result=new Result();
            result.setCode(1);
            result.setMsg(bindingResult.getFieldError().getDefaultMessage());

//            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        stu.setName(stu.getName());
        stu.setScore((int) stu.getScore());

    Result result=new Result();
    result.setCode(0);
    result.setMsg("成功");
    result.setData(studentRepository.save(stu));
        return result;
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping(value="/student/{id}")
    public void delet(@PathVariable("id") Integer id){
        studentRepository.delete(id);
    }

    /**
     * 更新
     * @param id
     * @param name
     * @param score
     * @return
     */
    @PutMapping(value="/student/{id}")
    public Student update(@PathVariable("id") Integer id,
                          @RequestParam ("name") String name,
                          @RequestParam("score") Integer score){
        Student stu= studentRepository.findOne(id);

//        Student stu=new Student();
        stu.setName(name);
        stu.setScore(score);
        return studentRepository.save(stu);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping(value="/student/{id}")
    public Student find(@PathVariable("id") Integer id){
        return studentRepository.findOne(id);
    }

    /**
     * 根据name查询
     * @param name
     * @return
     */
    @RequestMapping("/findByName/{name}")
    public Student findByName(@PathVariable("name") String name){
        return  studentRepository.findByName(name);
    }

    @RequestMapping("/findByScore")
    public Student findByScore(Integer score) {
        return studentRepository.findByScore(score);
    }

    @PostMapping(value="/student/getScore/{id}")
    public void getScore(@PathVariable("id") Integer id) throws Exception {
        stuService.getScore(id);
    }

    @RequestMapping("/queryForPage")
    public void queryForPage() {
        Pageable pageable = new PageRequest(0, 20, new Sort(new Sort.Order(Sort.Direction.DESC, "id")));
        Page<Student> result = studentRepository.findByName("杨幂", pageable);
//        System.out.println(result.getContent());
    }

    @PostMapping(value="/student/insertTwo")
    public void insertTwo(){
        stuService.insertTwo();
    }


}
