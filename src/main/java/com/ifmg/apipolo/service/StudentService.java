package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Modality;
import com.ifmg.apipolo.entity.Student;
import com.ifmg.apipolo.repository.StudentRepository;
import com.ifmg.apipolo.vo.ModalityVO;
import com.ifmg.apipolo.vo.StudentVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public void createStudent(StudentVO studentVO) {

        Student student = new Student();

        student.setAge(studentVO.getAge());
        student.setRegister(studentVO.getRegister());
        student.setCampus(studentVO.getCampus());
        student.setFirstName(studentVO.getFirstName());
        student.setLastName(studentVO.getLastName());
        student.setSex(student.getSex());

        studentRepository.save(student);
    }

    public List<StudentVO> list(){

        List<StudentVO> listVO = new ArrayList<>();
        List<Student> list = studentRepository.findAll();

        for(Student student : list)
            listVO.add(new StudentVO(student));

        return listVO;
    }

    public void updateStudent(StudentVO studentVO) {
        Student student = studentRepository.getReferenceById(studentVO.getId());

        if(studentVO.getAge() != null)
            student.setAge(studentVO.getAge());

        if(studentVO.getRegister() != null)
            student.setRegister(studentVO.getRegister());

        if(studentVO.getCampus() != null)
            student.setCampus(studentVO.getCampus());

        if(studentVO.getFirstName() != null && studentVO.getLastName() != null){
            student.setFirstName(studentVO.getFirstName());
            student.setLastName(studentVO.getLastName());
        }

        if(student.getSex() != null)
            student.setSex(student.getSex());

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
