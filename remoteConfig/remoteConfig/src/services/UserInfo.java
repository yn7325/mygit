package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Users;
import utils.GetConnection;

public class UserInfo {
	public List<Users> loadList(String page, String rows) {
		int start = (Integer.parseInt(rows)) * (Integer.parseInt(page) - 1);
		String sql = "select userId,userName,userPass,stationNum,role from users limit " + start + ',' + rows;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Users> datalist = new ArrayList<>();
		try {
			conn = GetConnection.getmysqlConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			String sql1 = null;
			String num = null;
			while (rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setStationNum(rs.getString(4));
				user.setRole(rs.getString(5));
				num=rs.getString(4);
				if(num.equals("JD10_1")) num="0878-GX-5A";
				if(num.equals("JD10_2")) num="0576-TZ-1A";
				sql1 = "select province,city,country,location,longitude,latitude,description from stations where stationNum like '"+num+"%'";
				if(!num.equals("0")&&!num.equals("0028-GX")) {
					//System.out.println(num);
					Connection connn = GetConnection.getmysqlConnection();
					Statement stmt1 = connn.prepareStatement(sql1);
					ResultSet rs1 = stmt1.executeQuery(sql1);
					while(rs1.next()) {								
						user.setprovince(rs1.getString(1));
						user.setcity(rs1.getString(2));
						user.setcountry(rs1.getString(3));
						user.setlocation(rs1.getString(4));
						user.setlongitude(rs1.getString(5));
						user.setlatitude(rs1.getString(6));
						user.setdescription(rs1.getString(7));						
					}
					stmt1.close();
					rs1.close();
					sql1 = "select text from tree where num = '"+num+"' and pid = '-1'";
					//System.out.println(sql1);
					stmt1 = connn.prepareStatement(sql1);
					rs1 = stmt1.executeQuery(sql1);
					while(rs1.next()) { 
						//System.out.println(rs1.getString(1));
						user.settree_text(rs1.getString(1));
					}
					GetConnection.closeAll(stmt1, rs1, connn);
				}
				datalist.add(user);				
			}	

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			GetConnection.closeAll(stmt, rs, conn);
		}

		return datalist;
	}

	public int getTotal() {
		Connection conn = null;
		String sql = "select count(*) from users";
		Statement stmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			conn = GetConnection.getmysqlConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			GetConnection.closeAll(stmt, rs, conn);
		}
		return total;
	}

	
}
