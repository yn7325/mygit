package entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Users {

	private String stationNum;
	private String role;
	private String userName;
	private String password;
	private int id;
	private String province;
	private String city;
	private String country;
	private String location;
	private String longitude;
	private String latitude;
	private String description;
	private String tree_text;
	@JSONField(name = "tree_text")
	public String gettree_text() {
		return tree_text;
	}

	public void settree_text(String tree_text1) {
		this.tree_text = tree_text1;
	}
	@JSONField(name = "province")
	public String getprovince() {
		return province;
	}

	public void setprovince(String province1) {
		this.province = province1;
	}
	@JSONField(name = "city")
	public String getcity() {
		return city;
	}

	public void setcity(String city1) {
		this.city = city1;
	}
	@JSONField(name = "country")
	public String getcountry() {
		return country;
	}

	public void setcountry(String country1) {
		this.country = country1;
	}
	@JSONField(name = "location")
	public String getlocation() {
		return location;
	}

	public void setlocation(String location1) {
		this.location = location1;
	}
	@JSONField(name = "longitude")
	public String getlongitude() {
		return longitude;
	}

	public void setlongitude(String longitude1) {
		this.longitude = longitude1;
	}
	@JSONField(name = "latitude")
	public String getlatitude() {
		return latitude;
	}

	public void setlatitude(String latitude1) {
		this.latitude = latitude1;
	}
	@JSONField(name = "description")
	public String getdescription() {
		return description;
	}

	public void setdescription(String description1) {
		this.description = description1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStationNum() {
		return stationNum;
	}

	public void setStationNum(String stationNum) {
		this.stationNum = stationNum;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
