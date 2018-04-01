package com.compare.files;

import java.util.ArrayList;
import java.util.List;

public class FileResultBean {

	List<String> notFoundList;
	
	List<String> differentList;
	
	FileResultBean(){
		notFoundList = new ArrayList<>();
		differentList = new ArrayList<>();
	}

	public List<String> getNotFoundList() {
		return notFoundList;
	}

	public void setNotFoundList(List<String> notFoundList) {
		this.notFoundList = notFoundList;
	}

	public List<String> getDifferentList() {
		return differentList;
	}

	public void setDifferentList(List<String> differentList) {
		this.differentList = differentList;
	}
	
}
