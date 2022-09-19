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
		String sql = "";
		if(role.equals("0")) {
			sql="select * from term_para_basic_config";
		}else {
			sql="select * from term_para_basic_config where role ='"+role+"' or role = '0'";
		}
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Remote remote=new Remote();
				remote.setRid(rs.getString("RID"));
				remote.setTns(rs.getString("T_id"));
				remote.setIp(rs.getString("ip"));
				remote.setPort(rs.getString("port"));
				remote.setIsonline(rs.getString("isonline"));
				remote.setCst(rs.getString("CST"));
				remote.setDate(rs.getString("Date"));
				remote.setTime(rs.getString("Time"));
				remote.setWms(rs.getString("WMS"));
				remote.setDms(rs.getString("DMS"));
				remote.setSvr(rs.getString("SVR"));
				remote.setTitle(rs.getString("T_id"));
				remote.setClre(rs.getString("CLRE"));
				datalist.add(remote);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {

			GetConnection.closeAll(stmt, rs, conn);
		}

		return datalist;
	};
	public List<Remote> GetNodeData(String rid){
		List<Remote> datalist = new ArrayList<>();
		Connection conn = GetConnection.getmysqlConnection();
		String sql="select * from term_para_basic_config where rid=\""+rid+"\"";
		String searchBattery = "select";
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Remote remote=new Remote();
				remote.setRid(rs.getString("RID"));
				remote.setTns(rs.getString("T_id"));
				remote.setIp(rs.getString("ip"));
				remote.setPort(rs.getString("port"));
				remote.setIsonline(rs.getString("isonline"));
				remote.setCst(rs.getString("CST"));
				remote.setDate(rs.getString("Date"));
				remote.setTime(rs.getString("Time"));
				remote.setWms(rs.getString("WMS"));
				remote.setDms(rs.getString("DMS"));
				remote.setSvr(rs.getString("SVR"));
				remote.setTitle(rs.getString("T_id"));
				remote.setClre(rs.getString("CLRE"));
				datalist.add(remote);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {

			GetConnection.closeAll(stmt, rs, conn);
		}

		return datalist;
	}
		

}
