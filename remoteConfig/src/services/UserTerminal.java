package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.math.BigInteger;

import entity.Users;
import entity.Userterminal;

import java.sql.ResultSet;
import utils.GetConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

import utils.GetConnection;
import entity.Userterminal;

public class UserTerminal {
	public List<Userterminal> getUserTerminal(String num,String page,String rows){
		List<Userterminal> datalist = new ArrayList<>();
		int start = (Integer.parseInt(rows)) * (Integer.parseInt(page) - 1);
		Connection conn = GetConnection.getmysqlConnection();
		Statement stmt = null;
		ResultSet rs = null;	
		String sql;int i = 1;	
		if(num.equals("admin")) 
			{
			sql = "select terminalNum,RID,TNS,User,TerminalType,description,SCSW_Num from terminals " + " limit " + start + ',' + rows;
			}
		else
		{
			sql ="select terminalNum,RID,TNS,User,TerminalType,description,SCSW_Num from terminals where User like '" + num + "%'" + " limit " + start + ',' + rows;
		}
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Userterminal sensor = new Userterminal();				
				sensor.setterminalNum(rs.getString(1));
				sensor.setRID(rs.getString(2));
				sensor.setTNS(rs.getString(3));					
				sensor.setUser(rs.getString(4));
				sensor.setTerminalType(rs.getString(5));
				sensor.setdescription(rs.getString(6));
				sensor.setSCSW_Num(rs.getString(7));
				sensor.setRowNumber(i);				
				datalist.add(sensor);
				i++;
			}
			stmt.close();
			rs.close();
			GetConnection.closeAll(stmt, rs, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			GetConnection.closeAll(stmt, rs, conn);
		}	
		return datalist;	
		
	}
//			*/
//		int i = 1;	
//		if(num.equals("JD10_1")) num = "0878-GX-5A";
//		if(num.equals("JD10_2")) num = "0576-TZ-1A";
//		int start = (Integer.parseInt(rows)) * (Integer.parseInt(page) - 1);
//		Connection conn = GetConnection.getmysqlConnection();
//		Statement stmt = null;
//		ResultSet rs = null;			
//		String sql = "select terminalNum,RID,TNS,User,TerminalType,description from terminals where TNS like '" + num + "%'" + " limit " + start + ',' + rows;								
//		if(num.equals("0"))
//			sql = "select terminalNum,RID,TNS,User,TerminalType,description from terminals where TNS like '" + num + "%'" + " limit " + start + ',' + rows;								
//		try {
//			stmt = conn.prepareStatement(sql);
//			rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				Userterminal sensor = new Userterminal();				
//				sensor.setterminalNum(rs.getString(1));
//				sensor.setRID(rs.getString(2));
//				sensor.setTNS(rs.getString(3));					
//				sensor.setUser(rs.getString(4));
//				sensor.setTerminalType(rs.getString(5));
//				sensor.setdescription(rs.getString(6));
//				sensor.setRowNumber(i);				
//				datalist.add(sensor);
//				i++;
//			}
//			stmt.close();
//			rs.close();
//			GetConnection.closeAll(stmt, rs, conn);
//			//System.out.println(datalist);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//
//			GetConnection.closeAll(stmt, rs, conn);
//		}						
						

	
	
	public int totalGrid(String num) {
		int totalnum=0;
		if(num.equals("admin"))
		{
			Connection conn = GetConnection.getmysqlConnection();
			String sql="select count(*) from terminals ";
			Statement stmt = null;
			ResultSet rs = null;
			try{
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					totalnum=Integer.parseInt(rs.getString(1));
				}
				GetConnection.closeAll(stmt, rs, conn);
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				GetConnection.closeAll(stmt, rs, conn);
			}
		}
		else
		{
			Connection conn = GetConnection.getmysqlConnection();
			String sql="select count(*) from terminals where User like '"+num+"%'";
			Statement stmt = null;
			ResultSet rs = null;
			try{
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					totalnum=Integer.parseInt(rs.getString(1));
				}
				GetConnection.closeAll(stmt, rs, conn);
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				GetConnection.closeAll(stmt, rs, conn);
			}
		}
		return totalnum;
	
		
	}
	
	public String[] getUserList(String num) {
//		if(num.equals("0")) {
			String sql = "select count(*) from users";

			Connection con = GetConnection.getmysqlConnection();
			Statement stmt = null;
			ResultSet rs = null;
			int n = 0;
			try {
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery(sql);				
				while(rs.next()) {
					n = rs.getInt(1);
				}
				rs.close();
				stmt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			sql = "select userName from users";
			String[] adminlist = new String[n];
			try {
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				int i = 0;
				while(rs.next()) {
					adminlist[i] = rs.getString(1);
					i++;
					
				}
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				GetConnection.closeAll(stmt, rs, con);
			}
			return adminlist;
	}
	
	public boolean userAddTerminal(String terminalNum,String RID,String TNS,String User,String Longitude,String Latitude,String description,String SCSW_Num,String TerminalType) {	
		String sql = "insert into terminals(terminalNum,RID,TNS,User,description,Longitude,Latitude,SCSW_Num,TerminalType)"
				+ "values('"+terminalNum+"','"+RID+"','"+TNS+"','"+User+"','"+description+"','"
				+Longitude+"','"+Latitude+"','"+SCSW_Num+"','"+TerminalType+"')";
		//System.out.println(sql);		
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = GetConnection.getmysqlConnection();
			stmt = conn.prepareStatement(sql);
			int i1 = stmt.executeUpdate(sql);	
			boolean flag1 = false;
			if(i1>0){
				flag1 = true;
			}
			//System.out.println("flag1="+flag1);
			stmt.close();		
			sql="insert into term_cr120_para_avs_config(terminalNum)"+"values('"+terminalNum+"')";
			stmt = conn.prepareStatement(sql);
			int i = stmt.executeUpdate(sql);
			boolean flag2 = false;
			if(flag1&&i>0) {
			flag2 = true;
			}
			stmt.close();
			sql="insert into term_cr120_para_basic_config(terminalNum)"+"values('"+terminalNum+"')";
			stmt = conn.prepareStatement(sql);
			i = stmt.executeUpdate(sql);
			boolean flag3 = false;
			if(flag2&&i>0) {
				flag3 = true;
			}
			stmt.close();
			sql="insert into term_cr120_para_csm_config(terminalNum)"+"values('"+terminalNum+"')";
			boolean flag4 = false;
			stmt = conn.prepareStatement(sql);
			i = stmt.executeUpdate(sql);
			if(flag3&&i>0) {
				flag4 = true;
			}
			stmt.close();
			sql="insert into term_cr120_para_csp_config(terminalNum)"+"values('"+terminalNum+"')";
			boolean flag5 = false;
			stmt = conn.prepareStatement(sql);
			i = stmt.executeUpdate(sql);
			if(flag4&&i>0) {
				flag5 = true;
			}
			stmt.close();
			sql="insert into term_cr120_para_dss_config(terminalNum)"+"values('"+terminalNum+"')";
			stmt = conn.prepareStatement(sql);
			i = stmt.executeUpdate(sql);
			if(flag5&&i>0) {
				flag = true;
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			GetConnection.closeAll(stmt, rs, conn);
		}
		return flag;
	}
	public boolean DelTerminal(String num) {
		String sql = "delete from terminals where terminalNum ='"+num+"'";
		//System.out.println(sql);
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = GetConnection.getmysqlConnection();
			stmt = conn.prepareStatement(sql);			
			int i1 = stmt.executeUpdate(sql);
			stmt.close();
			sql = "delete from term_cr120_para_avs_config where terminalNum ='"+num+"'";
			stmt = conn.prepareStatement(sql);
			int i2 = stmt.executeUpdate(sql);
			stmt.close();
			sql = "delete from term_cr120_para_basic_config where terminalNum ='"+num+"'";
			stmt = conn.prepareStatement(sql);
			int i3 = stmt.executeUpdate(sql);
			stmt.close();
			sql = "delete from term_cr120_para_csm_config where terminalNum ='"+num+"'";
			stmt = conn.prepareStatement(sql);
			int i4 = stmt.executeUpdate(sql);
			stmt.close();
			sql = "delete from term_cr120_para_csp_config where terminalNum ='"+num+"'";
			stmt = conn.prepareStatement(sql);
			int i5 = stmt.executeUpdate(sql);
			stmt.close();
			sql = "delete from term_cr120_para_dss_config where terminalNum ='"+num+"'";
			stmt = conn.prepareStatement(sql);
			int i6 = stmt.executeUpdate(sql);
			stmt.close();
			if(i1>0 && i2>0 && i3>0 && i4>0&&i5>0&&i6>0){
				flag = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			GetConnection.closeAll(stmt, null, conn);
		}
		return flag;
	
	}
	
	public String[] getUserTerminalChange(String num) {
	
		String[] number = new String[10];
		String sql = "select terminalNum,user,RID,TNS,TerminalType,SCSW_Num,description,Longitude,Latitude from terminals where terminalNum ='" + num + "'";
		Connection conn = GetConnection.getmysqlConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				number[0]=rs.getString(1);
				number[1]=rs.getString(1);
				number[2]=rs.getString(2);
				number[3]=rs.getString(3);
				number[4]=rs.getString(4);
				number[5]=rs.getString(5);
				number[6]=rs.getString(6);
				number[7]=rs.getString(7);
				number[8]=rs.getString(8);
				number[9]=rs.getString(9);
			}
			rs.close();
			stmt.close();
			GetConnection.closeAll(stmt, rs, conn);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
	
			GetConnection.closeAll(stmt, rs, conn);
		}
		return number;
	
	}
	
	public boolean userTerminalEdit(String terminalNum0,String terminalNum,String user,String RID,String TNS,String TerminalType,String SCSW_Num,String description,String Longitude,String Latitude) {
		boolean success = false;
		//if(terminalNum0.substring(0, 10).equals(stationNum)) {
			String sql = "update terminals set terminalNum = '"+terminalNum+"',RID = '"+RID+"',TNS = '"+TNS+"',User = '"+user
					+"',TerminalType = '"+TerminalType+"', description = '"+description
					+ "',SCSW_Num = '"+SCSW_Num+"', Longitude = '"+Longitude
					+ "',Latitude = '"+Latitude+"' where terminalNum ='"+terminalNum0+"'";		
			Connection con = GetConnection.getmysqlConnection();
			Statement stmt = null;
			ResultSet rs = null;
			try {
				stmt = con.createStatement();
				int i = stmt.executeUpdate(sql);
				stmt.close();
				sql = "update term_cr120_para_avs_config set terminalNum = '"+terminalNum+"' where terminalNum  = '"+terminalNum0+"'";
				stmt = con.createStatement();
				int i1 = stmt.executeUpdate(sql);
				stmt.close();
				sql = "update term_cr120_para_basic_config set terminalNum = '"+terminalNum+"' where terminalNum  = '"+terminalNum0+"'";
				stmt = con.createStatement();
				int i2 = stmt.executeUpdate(sql);
				stmt.close();
				sql = "update term_cr120_para_csm_config set terminalNum = '"+terminalNum+"' where terminalNum  = '"+terminalNum0+"'";
				stmt = con.createStatement();
				int i3 = stmt.executeUpdate(sql);
				stmt.close();
				sql = "update term_cr120_para_csp_config set terminalNum = '"+terminalNum+"' where terminalNum  = '"+terminalNum0+"'";
				stmt = con.createStatement();
				int i4 = stmt.executeUpdate(sql);
				stmt.close();
				sql = "update term_cr120_para_dss_config set terminalNum = '"+terminalNum+"' where terminalNum  = '"+terminalNum0+"'";
				stmt = con.createStatement();
				int i5 = stmt.executeUpdate(sql);
				stmt.close();
				if(i>0 && i1>0 &&i2>0&& i3>0&&i4>0&&i5>0)
					success=true;
				GetConnection.closeAll(stmt, rs, con);
			}catch (Exception e) {
				e.printStackTrace();
			} finally {

				GetConnection.closeAll(stmt, rs, con);
			}
		
		
		return success;
	
	}
	
}

