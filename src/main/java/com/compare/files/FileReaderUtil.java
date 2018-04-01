package com.compare.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Mohan
 * 
 * This class contain util methods for files comparing.
 *
 */

@Service
public class FileReaderUtil {

	public static String updateDecimalPlaces(String row, int decimalPlaces) {
		String str[] = row.split(",");
		for(int i=0; i<str.length; i++) {
			try {
				if(str[i].contains(".")) {
					double d = Double.parseDouble(str[i]);
					DecimalFormat df = new DecimalFormat("0."+generateNumberSigns(decimalPlaces));
					df.setRoundingMode(RoundingMode.DOWN); 
					str[i] = df.format(d);
				}
			}catch(NumberFormatException nfe) {
				str[i] = str[i];
			}
		}
		return Arrays.toString(str).replace("[","").replace("]","").replace(" ", "");

	}

	public static boolean isRecordFound(String row1, String row2) {

		String row1_cols[] = row1.split(",");
		String row2_cols[] = row2.split(",");
		int length = row1_cols.length;
		int machedRecords = 0;

		for (int i =0; i< row1_cols.length; i++) {
			if(row1_cols[i].equals(row2_cols[i])){
				machedRecords++;
			}
		}
		// All records matching then only returning true
		if(length == machedRecords) {
			return true;
		}
		return false;
	}

	public static boolean isRecordNotFound(String row1, String row2) {

		String row1_cols[] = row1.split(",");
		String row2_cols[] = row2.split(",");
		int length = row1_cols.length;
		int machedRecords = 0;

		for (int i =0; i< row1_cols.length; i++) {
			if(!row1_cols[i].equals(row2_cols[i])){
				machedRecords++;
			}
		}
		// All records matching then only returning true
		if(length == machedRecords) {
			return true;
		}
		return false;
	}

	// Requirement is not clear to implement this method.
	public static boolean isRecordDifferent(String row1, String row2) {

		String row1_cols[] = row1.split(",");
		String row2_cols[] = row2.split(",");

		for (int i =0; i< row1_cols.length; i++) {
			if(row1_cols[i].equals(row2_cols[i])){
				return true;
			}
		}
		return false;
	}

	public static List<List<String>> loadCsvFile(String csvFileName)throws IOException{
		String line = null;
		List<List<String>> csvData = new ArrayList<List<String>>();

		try(BufferedReader stream = new BufferedReader(new FileReader(csvFileName));) {
			
			while((line = stream.readLine()) != null) {
				String splitted[] = line.split(",");
				List<String> dataLine = new ArrayList<String>(splitted.length);
				for(String data: splitted) {
					dataLine.add(data);
				}
				csvData.add(dataLine);
			}
		}
		return csvData;
	}

	public static String generateNumberSigns(int n) {

		String s = "";
		for (int i = 0; i < n; i++) {
			s += "#";
		}
		return s;
	}
}
