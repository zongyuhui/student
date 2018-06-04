package com.zyh.student.service;

;
import com.zyh.student.Exception.StudentException;
import com.zyh.student.domain.Student;
import com.zyh.student.enums.ResultEnum;
import com.zyh.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class studentService {
    @Autowired
    StudentRepository studentRepository;



    @Transactional
    public void insertTwo(){
        Student stu1=new Student();
        stu1.setName("张三");
        stu1.setScore(85);
        studentRepository.save(stu1);

        Student stu2=new Student();
        stu2.setName("李四");
        stu2.setScore(88);
        studentRepository.save(stu2);
    }

    public void getScore(Integer id) throws Exception {
        Student stu=studentRepository.findOne(id);
        Integer score=stu.getScore();
        if(score<=30){
            throw new StudentException(ResultEnum.PRIMARY_SCHOOL);
        }else if(score>30){
            throw new StudentException(ResultEnum.MIDDLE_SCHOOL);
        }
    }
    public Student findOne(Integer id){
        return studentRepository.findOne(id);
    }
}
