package src.student.model;

import java.util.List;

public class ReadFilesForm {
	String fileName;
	int index;

	public ReadFilesForm(String fileName, int index) {
		this.fileName = fileName;
		this.index = index;
	}

	public String getFileName() {
		return fileName;
	}

	public int getIndex() {
		return index;
	}
}
