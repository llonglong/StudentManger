package com.situ.student.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.situ.student.dao.IStudentDao;
import com.situ.student.pojo.Admin;
import com.situ.student.pojo.Student;
import com.situ.student.util.JdbcUtil;
import com.situ.student.vo.SearchCondition;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class StudentDaoMysqlImpl implements IStudentDao{

	@Override
	public int add(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try{
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO student(NAME,age,gender,adderss) VALUES (?,?,?,?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2,student.getAge());
			preparedStatement.setString(3,student.getGender());
			preparedStatement.setString(4, student.getAddress());
			result = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement);
		}
		
		return result;
	}

	@Override
	public boolean deleteById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isSuccess = false;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "delete from student where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int result = preparedStatement.executeUpdate();
			if(result>0){
				isSuccess = true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement);
		}
		return isSuccess;
	}

	@Override
	public List<Student> findAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student>list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT  id,NAME,age,gender,adderss FROM student;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("adderss");
				Student student = new Student(id, name, age, gender, address);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Student> findByName(String searchname) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student>list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT  id,NAME,age,gender,adderss FROM student WHERE NAME=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, searchname);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("adderss");
				Student student = new Student(id, name, age, gender, address);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Student> findByGender() {
		return null;
	}
	
	@Override
	public boolean checkName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExit = false;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id FROM student WHERE NAME=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				isExit = true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return isExit;
		
	}

	@Override
	public Student findById(Integer idSearch) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT  id,NAME,age,gender,adderss FROM student WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idSearch);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("adderss");
				Student student = new Student(id, name, age, gender, address);
				return student;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return null;
	}

	@Override
	public int updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		try{
			connection = JdbcUtil.getConnection();
			String sql = "UPDATE student SET NAME=?,age=?,gender=?,adderss=? where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2,student.getAge());
			preparedStatement.setString(3,student.getGender());
			preparedStatement.setString(4, student.getAddress());
			preparedStatement.setInt(5, student.getId());
			result = preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement);
		}
		
		return result;
	}

	@Override
	public List<Student> searchByCondition(SearchCondition searchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student>list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select id,NAME,age,gender,adderss from student where 1=1";
			String nameSearch = searchCondition.getName();
			List<String> listCondition = new ArrayList<String>();
			if (nameSearch != null && !nameSearch.equals("")) {
				sql += " and name like ?";
				listCondition.add("%" + nameSearch + "%");
			}
			String ageSearch = searchCondition.getAge();
			if (ageSearch != null && !ageSearch.equals("")) {
				sql += " and age = ? ";
				listCondition.add(ageSearch);
			}
			String genderSearch = searchCondition.getGender();
			if (genderSearch != null && !genderSearch.equals("")) {
				sql += " and gender = ? ";
				listCondition.add(genderSearch);
			}
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < listCondition.size(); i++) {
				preparedStatement.setString(i+1, listCondition.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age  = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("adderss");
				Student student = new Student(id, name, age, gender, address);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int getTotalCount() {
		 Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    int count = 0;
		    try {
		       connection = JdbcUtil.getConnection();
		       String sql = "SELECT COUNT(*) FROM student;";
		       preparedStatement = connection.prepareStatement(sql);
		       resultSet = preparedStatement.executeQuery();
		       if (resultSet.next()) {
		           count = resultSet.getInt(1);
		       }
		    } catch (SQLException e) {
		       e.printStackTrace();
		    }
		    
		    return count;

	}

	@Override
	public List<Student> findPageBeanList(int index, int pageSize) {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Student> list = new ArrayList<Student>();
	    try {
	       connection = JdbcUtil.getConnection();
	       String sql = "select * from student limit ?,?;";
	       preparedStatement = connection.prepareStatement(sql);
	       preparedStatement.setObject(1, index);
	       preparedStatement.setObject(2, pageSize);
	       resultSet = preparedStatement.executeQuery();
	       while (resultSet.next()) {
	    	   int id = resultSet.getInt("id");
	           String name = resultSet.getString("name");
	           int age = resultSet.getInt("age");
	           String gender = resultSet.getString("gender");
	           String address = resultSet.getString("adderss");
	           Student student = new Student(id,name, age, gender, address);
	           list.add(student);
	       }
	    } catch (SQLException e) {
	       e.printStackTrace();
	    }
	    return list;

	}

	@Override
	public int getTotalCount(SearchCondition searchCondition) {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    int count = 0;
	    try {
	       connection = JdbcUtil.getConnection();
	       String sql = "SELECT COUNT(*) FROM student WHERE 1=1 ";
	       String nameSearch = searchCondition.getName();
			List<String> listCondition = new ArrayList<String>();
			if (nameSearch != null && !nameSearch.equals("")) {
				sql += " and name like ?";
				listCondition.add("%" + nameSearch + "%");
			}
			String ageSearch = searchCondition.getAge();
			if (ageSearch != null && !ageSearch.equals("")) {
				sql += " and age = ? ";
				listCondition.add(ageSearch);
			}
			String genderSearch = searchCondition.getGender();
			if (genderSearch != null && !genderSearch.equals("")) {
				sql += " and gender = ? ";
				listCondition.add(genderSearch);
			}
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < listCondition.size(); i++) {
				preparedStatement.setString(i+1, listCondition.get(i));
			}
			resultSet = preparedStatement.executeQuery();
	       if (resultSet.next()) {
	           count = resultSet.getInt(1);
	       }
	    } catch (SQLException e) {
	       e.printStackTrace();
	    }
	    
	    return count;
	}

	@Override
	public List<Student> findPageBeanList(SearchCondition searchCondition) {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<Student> list = new ArrayList<Student>();
	    try {
	        connection = JdbcUtil.getConnection();
	        String sql = "select * from student where 1=1 ";
	        String nameSearch = searchCondition.getName();
			List<String> listCondition = new ArrayList<String>();
			if (nameSearch != null && !nameSearch.equals("")) {
				sql += " and name like ?";
				listCondition.add("%" + nameSearch + "%");
			}
			String ageSearch = searchCondition.getAge();
			if (ageSearch != null && !ageSearch.equals("")) {
				sql += " and age = ? ";
				listCondition.add(ageSearch);
			}
			String genderSearch = searchCondition.getGender();
			if (genderSearch != null && !genderSearch.equals("")) {
				sql += " and gender = ? ";
				listCondition.add(genderSearch);
			}
			sql +=  " limit ?,?";
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < listCondition.size(); i++) {
				preparedStatement.setString(i+1, listCondition.get(i));
			}
		   
		   int pageIndex = searchCondition.getPageIndex() - 1;
		   int index = pageIndex*searchCondition.getPageSize();
		   preparedStatement.setInt(listCondition.size() + 1, index);
		   preparedStatement.setInt(listCondition.size() + 2, searchCondition.getPageSize());
	       resultSet = preparedStatement.executeQuery();
	       while (resultSet.next()) {
	    	   int id = resultSet.getInt("id");
	           String name = resultSet.getString("name");
	           int age = resultSet.getInt("age");
	           String gender = resultSet.getString("gender");
	           String address = resultSet.getString("adderss");
	           Student student = new Student(id,name, age, gender, address);
	           list.add(student);
	       }
	    } catch (SQLException e) {
	       e.printStackTrace();
	    }
	    return list;

	}

	@Override
	public boolean delectAll(String[] ids) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = JdbcUtil.getConnection();
			String sql = "delete from student where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			for (String id : ids) {
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.addBatch();
			}
			int[] result = preparedStatement.executeBatch();
			if(result.length == ids.length){
				return  true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JdbcUtil.close(connection, preparedStatement);
		}
		return false;
	}



	
	}

	

	
	

