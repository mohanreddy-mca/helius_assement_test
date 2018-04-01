package com.compare.files;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * 
 * @author Mohan
 * 
 * This is SpringBoot main class. starting point of this microservice.
 *
 */

@SpringBootApplication(scanBasePackages = {"com.compare.files"})
@ComponentScan(basePackages = {"com.compare.files"})
public class CompareFilesApplication {

	/**
	 * 
	 */
	private static final int DECIMAL_POINTS = 4;
	
	/**
	 * @param args
	 * 2 files taking from command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CompareFilesApplication.class, args);
		try {
			CsvFileReaderService csvFileReaderService = new CsvFileReaderService();
			FileResultBean outputList = csvFileReaderService.compareCSVFiles(args[0],args[1], DECIMAL_POINTS);
			
			System.out.println("===========Not found Records START========");
			for (String string : outputList.getNotFoundList()) {
				System.out.println(string);
			}
			System.out.println("===========Not found Records END========");
			
			System.out.println("===========Different Records START========");
			for (String string : outputList.getDifferentList()) {
				System.out.println(string);
			}
			System.out.println("===========Different Records END========");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
