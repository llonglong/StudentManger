package com.situ.student.serve;

import com.situ.student.pojo.Admin;

public interface IAdminServe {

	Admin findByNameAndPassword(String name, String password);

}
