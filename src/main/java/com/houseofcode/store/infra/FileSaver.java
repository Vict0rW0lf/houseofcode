package com.houseofcode.store.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest request;
	
	public String write(String baseFolder, MultipartFile file) {
		try {
			String location = request.getServletContext().getRealPath("/" + baseFolder);
			String pathname = location + "/" + file.getOriginalFilename();
			file.transferTo(new File(pathname));
			
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
