package test;

import java.util.Arrays;

public class User {

	private String name;
	private String phone;
	private String[] address;

	@Override
	public String toString() {
		// 这里就是User实体转为json字符串
		return "user [" + name + ", " + phone + ", " + Arrays.toString(address) + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String[] getAddress() {
		return address;
	}

	public void setAddress(String[] address) {
		this.address = address;
	}

}

