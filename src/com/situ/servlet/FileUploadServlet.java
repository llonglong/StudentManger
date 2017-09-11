package com.situ.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import sun.nio.ch.IOUtil;

public class FileUploadServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024);
		String tempPath = getServletContext().getRealPath("temp");
		factory.setRepository(new File(tempPath));
		
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		servletFileUpload.setHeaderEncoding("utf-8");
		
		if (servletFileUpload.isMultipartContent(req)) {
			List<FileItem> list = null;
			try {
				list = servletFileUpload.parseRequest(req);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (list != null) {
				for (FileItem fileItem : list) {
					if (fileItem.isFormField()) {
						String fieldName = fileItem.getFieldName();
						String fieldValue = fileItem.getString("utf-8");
						System.out.println("fieldName: " + fieldName + ", fieldValue: " + fieldValue);
					}else {
						String fileName = fileItem.getName();
						String uploadPath = getServletContext().getRealPath("upload");
						InputStream inputStream = fileItem.getInputStream();
						OutputStream outputStream = new FileOutputStream(uploadPath + "/" + fileName);
						IOUtils.copy(inputStream,outputStream);
						outputStream.close();
						inputStream.close();
						fileItem.delete();
					}
				}
			}
		}
		
	}

}
