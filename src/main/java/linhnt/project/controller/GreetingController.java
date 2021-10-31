package linhnt.project.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import linhnt.project.model.Country;

@RestController
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting() {
		return "Hello";
	}
	
	@GetMapping("download")
	public void download(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=test.xlsx";
        response.setHeader(headerKey, headerValue);
        
        ServletOutputStream outputStream = response.getOutputStream();
        Workbook workbook = writeCountryListToFile();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
	}
	
	public Workbook writeCountryListToFile() throws Exception{
		List<Country> countryList = new ArrayList<>();
		Country c = new Country("test", "test");
		countryList.add(c);
		Workbook workbook = null;
		
		workbook = new XSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("Countries");
		
		Iterator<Country> iterator = countryList.iterator();
		
		int rowIndex = 0;
		while(iterator.hasNext()){
			Country country = iterator.next();
			Row row = sheet.createRow(rowIndex++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(country.getName());
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(country.getShortCode());
		}
		
		
		return workbook;
	}
}