package kafkaTest.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class testDto {
	private int id = 0;
	private String str1 = "";
	private String str2 = "";
	
	
	@Override
	public String toString() {
		return "testDto [id=" + id + ", str1=" + str1 + ", str2=" + str2 + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	
	
}
