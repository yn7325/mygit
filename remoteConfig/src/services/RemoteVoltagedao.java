package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GetConnection;
import entity.RemoteVoltage;

public class RemoteVoltagedao {
	public List<RemoteVoltage> GetVoltageData(String terminalNum){
		List<RemoteVoltage> datalist = new ArrayList<>();
		Connection conn = GetConnection.getmysqlConnection();
		String sql="SELECT * from termianls WHERE terminalNum LIKE '%"+terminalNum+"%' ORDER BY terminalNum DESC";
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RemoteVoltage remote=new RemoteVoltage();
				remote.setVoltage(rs.getString("battery"));
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
