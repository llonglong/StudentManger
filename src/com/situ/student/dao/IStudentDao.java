package com.situ.student.dao;

import java.util.List;

import com.situ.student.pojo.Admin;
import com.situ.student.pojo.Student;
import com.situ.student.vo.SearchCondition;

public interface IStudentDao {
	/**
	 * 添加学生到数据库
	 * @param student
	 * @return
	 */
	public int add(Student student);
	
	/**
	 * 根据id删除指定学生
	 * @param id
	 * @return
	 */
	public boolean deleteById(int id);
	
	/**
	 * 修改更新学生信息
	 * @param student
	 * @return
	 */
	public int updateStudent(Student student);
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	public List<Student> findAll();
	
	/**
	 * 根据学生姓名查询
	 * @return
	 */
	public List<Student> findByName(String name);
	
	/**
	 * 根据学生性别查询
	 * @return
	 */
	public List<Student> findByGender();
    
	
	/**
     * 判断指定的学生在数据库是否存在
     * @param name
     * @return
     */
	public boolean checkName(String name);

	public Student findById(Integer id);

	public List<Student> searchByCondition(SearchCondition searchCondition);

	public int getTotalCount();

	public List<Student> findPageBeanList(int index, int pageSize);

	public int getTotalCount(SearchCondition searchCondition);

	public List<Student> findPageBeanList(SearchCondition searchCondition);

	public boolean delectAll(String[] ids);



	



	

}
