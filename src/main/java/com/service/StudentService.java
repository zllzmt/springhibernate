package com.service;


import com.dao.StudentMapper;
import com.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
*@ClassName:StudentService
 @Description:TODO
 @Author:
 @Date:2018/9/6 15:26 
 @Version:v1.0
*/
//Propagation.MANDATORY 无事务，并不新建事务
//Propagation.Support 无事务，并不新建事务
//Propagation.REQUIRED 无事务，并不新建事务
//Propagation.PROPAGATION_REQUIRES_NEW  无事务，并不新建事务,有事务 同一事务
//Propagation.NOT_SUPPORTED   无事务，并不新建事务,有事务 同一事务





@Service
public class StudentService {
    @Autowired
    StudentMapper studentDao;
    public Student findById(int id){
        Student student = studentDao.selectByPrimaryKey(id);
        return student;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertTx(){
        Student s1 = new Student();
        s1.setAge(1200);
        s1.setName("qq");
        Student s2 = new Student();
        s2.setAge(1200);
        s2.setName("qqq");
        studentDao.insert(s1);
        //System.out.println(1/0);
        studentDao.insert(s2);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertp1(){
        Student s1 = new Student();
        s1.setAge(1200);
        s1.setName("qq");
        Student s2 = new Student();
        s2.setAge(1200);
        s2.setName("qqq");
        studentDao.insert(s1);
        //
        insertTx();
        System.out.println(1/0);
    }
}
