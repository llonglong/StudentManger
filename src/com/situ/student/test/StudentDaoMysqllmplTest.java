package com.situ.student.test;

import java.util.List;

import org.junit.Test;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoMysqlImpl;
import com.situ.student.pojo.Student;

public class StudentDaoMysqllmplTest {
	
	@Test
	public void testAdd(){
		Student student = new Student("张三22",20,"男","北京");
		IStudentDao studentDao = new StudentDaoMysqlImpl();
		int result = studentDao.add(student);
		if (result>0) {
			System.out.println("插入成功");
		}
		else {
			System.out.println("插入失败");
		}
	}
	
	@Test
	public void testFindAll() {
		IStudentDao studentDao = new StudentDaoMysqlImpl();
		List<Student>list = studentDao.findAll();
		for (Student student : list) {
			System.out.println(student);
		}
	}

}
