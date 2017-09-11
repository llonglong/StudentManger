package com.situ.student.serve;

import java.util.List;

import com.situ.student.pojo.Admin;
import com.situ.student.pojo.Student;
import com.situ.student.vo.PageBean;
import com.situ.student.vo.SearchCondition;

public interface IStudentServe {

	/**
	 * 返回所有数据
	 * @return
	 */
	List<Student> findAll();
    
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	boolean add(Student student);
	
	
	

	List<Student> findByName(String name);

	

	Student findById(Integer id);

	

	int updateStudent(Student student);

	boolean delectById(Integer id);

	PageBean searchByCondition(SearchCondition searchCondition);

	PageBean getPageBean(int pageIndex, int pageSize);

	boolean checkName(String name);

	boolean delectAll(String[] ids);

	

	
	

}
