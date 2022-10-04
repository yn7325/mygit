package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Remote;
import utils.GetConnection;

public class RemoteBasic {
	public String GetRole(String user){
		String role = "";
		Connection conn = GetConnection.getmysqlConnection();
		String sql="SELECT role FROM users where userName='"+user+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			rs.next();
			role = rs.getString("role");
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			GetConnection.closeAll(stmt, rs, conn);
		}
		return role;
	};
	public List<Remote> GetAllData(String user){
		List<Remote> datalist = new ArrayList<>();
		Connection conn = GetConnection.getmysqlConnection();
		String role = GetRole(user);
		String sql = "";String sql1 = "";
		if(role.equals("0")) {
			sql="select * from terminals";
		}else {
			sql="select * from terminals where role ='"+role+"' or role = '0'";
		}
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Remote remote=new Remote();
				remote.setRid(rs.getString("RID"));
				remote.setIp(rs.getString("IP"));
				remote.setPort(rs.getString("Port"));
				remote.setTerminalNum(rs.getString("terminalNum"));
				remote.setIsOnline(rs.getString("isOnline"));
				remote.setTns(rs.getString("TNS"));
				remote.setDescription(rs.getString("description"));
				datalist.add(remote);
				//System.out.print(rs.getString("terminalNum"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {

			GetConnection.closeAll(stmt, rs, conn);
		}
		//System.out.print(datalist.get(1).getterminalNum());
		return datalist;
	};
	public List<Remote> GetNodeData(String terminalNum){
		List<Remote> datalist = new ArrayList<>();
		Connection conn = GetConnection.getmysqlConnection();
		String sql="select * from term_cr120_para_basic_config where terminalNum=\""+terminalNum+"\"";
		String searchBattery = "select";
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			Remote remote=new Remote();
			remote.setTerminalNum(rs.getString("terminalNum"));
			remote.setCst(rs.getString("CST"));
			remote.setDate(rs.getString("Date"));
			remote.setTime(rs.getString("Time"));
			remote.setWms(rs.getString("WMS"));
			remote.setDms(rs.getString("DMS"));
			remote.setSvr(rs.getString("SVR"));
			stmt.close();
			rs.close();
			sql="select * from terminals where terminalNum=\""+terminalNum+"\"";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			remote.setIp(rs.getString("IP"));
			remote.setIp(rs.getString("RID"));
			remote.setIp(rs.getString("isOnline"));
			remote.setIp(rs.getString("TNS"));
			remote.setDescription(rs.getString("description"));
			datalist.add(remote);
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {

			GetConnection.closeAll(stmt, rs, conn);
		}
		return datalist;
	}
		

}
