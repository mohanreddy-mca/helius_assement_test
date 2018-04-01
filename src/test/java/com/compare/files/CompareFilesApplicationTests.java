package com.compare.files;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author Mohan
 * 
 * This class is using to test file compare service related methods.
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompareFilesApplicationTests {
	
	@Autowired
	private CsvFileReaderService csvFileReaderService;
	
	private String file1, file2;
	private int DECIMAL_POINTS;

	@Before
	public void fileLoads() {
		file1 = "File_1.csv";
		file2 = "File_2.csv";
	}

	@Test
	public void compareFiles() {
		try {
			FileResultBean outputList = csvFileReaderService.compareCSVFiles(file1, file2);
			assertEquals(2, outputList.getNotFoundList().size());
			assertEquals(1, outputList.getDifferentList().size());
		}catch(Exception e) {

		}
	}
	
	@Test
	public void compareFilesWith2Decimals() {
		try {
			DECIMAL_POINTS = 2;
			FileResultBean outputList = csvFileReaderService.compareCSVFiles(file1, file2, DECIMAL_POINTS);
			assertEquals(3, outputList.getNotFoundList().size());
			assertEquals(0, outputList.getDifferentList().size());
		}catch(Exception e) {

		}
	}
	
	@Test
	public void compareNullFiles() {
		try {
			String file1 = null, file2 = null;
			FileResultBean outputList = csvFileReaderService.compareCSVFiles(file1, file2);
			assertEquals(null, outputList);
			assertEquals(null, outputList);
			
		}catch(Exception e) {

		}
	}
}
