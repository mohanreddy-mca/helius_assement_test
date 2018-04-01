package com.compare.files;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Mohan
 * 
 * This Service is using to compare 2 files and finding Not found and Different records.
 * this service is calling from main class of microservice (CompareFilesApplication.java).
 *
 */

@Service
public class CsvFileReaderService {
	
	public FileResultBean compareCSVFiles(String file1, String file2) throws IOException{
		int DECIMAL_POINTS = 0;
		return compareCSVFiles(file1, file2, DECIMAL_POINTS);
	}

	FileResultBean outputList = null;
	
	public FileResultBean compareCSVFiles(String file1, String file2, int DECIMAL_POINTS)throws IOException {
		
		if(file1.isEmpty()|| file2.isEmpty()) {
			throw new IOException("File are empty"); 
		}
		outputList = new FileResultBean();
		
		// Instead of loading all records into memoty using scanner to read rows one by one from file1.
		try(FileInputStream file1Stream = new FileInputStream(file1);
				Scanner sc1 = new Scanner(file1Stream, "UTF-8");) {

			String file1headers = sc1.nextLine();

			while(sc1.hasNextLine()) {
				String file1_row = sc1.nextLine();
				file1_row = FileReaderUtil.updateDecimalPlaces(file1_row, DECIMAL_POINTS);

				//Loading file2 into memory, to compare file1 each record to all records in file2.
				List<List<String>> file2_list = FileReaderUtil.loadCsvFile(file2);
				//removing headers from list.
				file2_list.remove(0);
				boolean recordFound = false, recordNotFound = false;

				Iterator<List<String>> it = file2_list.iterator();
				while(it.hasNext()) {
					List<String> list = it.next();
					String file2_row = list.toString().replace("[","").replace("]","").replace(" ", "");
					//Rounding All decimal values to 2 points.
					file2_row = FileReaderUtil.updateDecimalPlaces(file2_row, DECIMAL_POINTS);
					if(FileReaderUtil.isRecordFound(file1_row, file2_row)) {
						recordFound = true;
						//NOTE: To Increase performance we can remove matching records.
						//it.remove();
						break;
					}
				}

				if(!recordFound) {
					int totalRecords = file2_list.size();
					int notFoundRecords = 0;
					for(List<String> list: file2_list) {
						String file2_row = list.toString().replace("[","").replace("]","").replace(" ", "");
						if(FileReaderUtil.isRecordNotFound(file1_row, file2_row)) {
							notFoundRecords ++;
						}
					}
					if(totalRecords == notFoundRecords) {
						outputList.getNotFoundList().add("Not found"+ file1_row);
						recordNotFound = true;
					}
				}

				if(!recordFound && !recordNotFound) {
					outputList.getDifferentList().add("Differnt "+file1_row);
				}
			}
			//Scanner suppresses Exceptions.
			if(sc1.ioException() != null) {
				throw sc1.ioException();
			}
			
			return outputList;
		}
	}
}
