package com.situ.student.serve.impl;

import java.util.List;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoMysqlImpl;
import com.situ.student.pojo.Admin;
import com.situ.student.pojo.Student;
import com.situ.student.serve.IStudentServe;
import com.situ.student.vo.PageBean;
import com.situ.student.vo.SearchCondition;

public class StudnetSreveImpl implements IStudentServe{
	public IStudentDao studentDao = new StudentDaoMysqlImpl();

	@Override
	public List<Student> findAll() {
		
		return studentDao.findAll();
	}

	@Override
	//判断该学生是否存在
	public boolean add(Student student) {
		if(studentDao.checkName(student.getName())){
			System.out.println("用户名已经存在");
			
		}else{
			int result = studentDao.add(student);
			if (result > 0) {
				return true;
			}else {
				return false;
			}
		}
		
		return false;
		
	}

	@Override
	public  List<Student> findByName(String searchname) {
		// TODO Auto-generated method stub
		return studentDao.findByName(searchname);
	}

	@Override
	public Student findById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.findById(id);
	}

	@Override
	public int updateStudent(Student student) {
		
		// TODO Auto-generated method stub
		return studentDao.updateStudent(student);
	}

	@Override
	public boolean delectById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.deleteById(id);
	}

	@Override
	public PageBean searchByCondition(SearchCondition searchCondition) {
		PageBean pageBean = new PageBean();
		//当前是第几页
		pageBean.setPageIndex(searchCondition.getPageIndex());
		//每一页有几条数据
		pageBean.setPageSize(searchCondition.getPageSize());
		//数据库中有几条数据
		//int totalCount = studentDao.getTotalCount();
		// TODO 具体实现这个方法而不是写死
		// int totalCount = 10;
		int totalCount = studentDao.getTotalCount(searchCondition);
		pageBean.setTotalCount(totalCount);
		//一共多少页
		int totalPage = (int) Math.ceil(1.0 * totalCount / searchCondition.getPageSize()); 
		pageBean.setTotalPage(totalPage);
		//当前页数据
		int index = (searchCondition.getPageIndex() - 1) * searchCondition.getPageSize();
		
		List<Student> list = studentDao.findPageBeanList(searchCondition);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean getPageBean(int pageIndex, int pageSize) {
		PageBean pageBean = new PageBean();
		//当前是第几页
		pageBean.setPageIndex(pageIndex);
		//每一页有几条数据
		pageBean.setPageSize(pageSize);
		//数据库中有几条数据
		//int totalCount = studentDao.getTotalCount();
		// TODO 具体实现这个方法而不是写死
		// int totalCount = 10;
		int totalCount = studentDao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		//一共多少页
		int totalPage = (int) Math.ceil(1.0 * totalCount / pageSize); 
		pageBean.setTotalPage(totalPage);
		//当前页数据
		int index = (pageIndex - 1) * pageSize;
		
		List<Student> list = studentDao.findPageBeanList(index, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public boolean checkName(String name) {
		// TODO Auto-generated method stub
		return studentDao.checkName(name);
	}

	@Override
	public boolean delectAll(String[] ids) {
		// TODO Auto-generated method stub
		return studentDao.delectAll(ids);
	}

	

    
	
	

}
