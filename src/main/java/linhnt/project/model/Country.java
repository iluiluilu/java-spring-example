package linhnt.project.model;

import lombok.Data;

@Data
public class Country {
	private String name;
	private String shortCode;
	
	public Country(String n, String c){
		this.name=n;
		this.shortCode=c;
	}
}
