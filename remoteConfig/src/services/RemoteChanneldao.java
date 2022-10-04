package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.GetConnection;
import entity.RemoteChannel;

public class RemoteChanneldao {
	public List<RemoteChannel> GetChannelData(String terminalNum){
		List<RemoteChannel> datalist = new ArrayList<>();
		Connection conn = GetConnection.getmysqlConnection();
		String sql="SELECT * FROM term_cr120_para_csp_config JOIN term_cr120_para_csm_config ON term_cr120_para_csp_config.terminalNum = term_cr120_para_csm_config.terminalNum\r\n"
				+ "WHERE term_cr120_para_csp_config.terminalNum=\""+terminalNum+"\"";
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RemoteChannel remote=new RemoteChannel();
				remote.terminalNum = rs.getString("terminalNum");
				remote.csp1s = rs.getString("CSP_01_S");
				remote.csp1a = rs.getString("CSP_01_A");
				remote.csp1p = rs.getString("CSP_01_P");
				remote.csp1k = rs.getString("CSP_01_K");
				remote.csp1b = rs.getString("CSP_01_B");
				
				remote.csp2s = rs.getString("CSP_02_S");
				remote.csp2a = rs.getString("CSP_02_A");
				remote.csp2p = rs.getString("CSP_02_P");
				remote.csp2k = rs.getString("CSP_02_K");
				remote.csp2b = rs.getString("CSP_02_B");
				
				remote.csp3s = rs.getString("CSP_03_S");
				remote.csp3a = rs.getString("CSP_03_A");
				remote.csp3p = rs.getString("CSP_03_P");
				remote.csp3k = rs.getString("CSP_03_K");
				remote.csp3b = rs.getString("CSP_03_B");
				
				remote.csp4s = rs.getString("CSP_04_S");
				remote.csp4a = rs.getString("CSP_04_A");
				remote.csp4p = rs.getString("CSP_04_P");
				remote.csp4k = rs.getString("CSP_04_K");
				remote.csp4b = rs.getString("CSP_04_B");
				
				remote.csp5s = rs.getString("CSP_05_S");
				remote.csp5a = rs.getString("CSP_05_A");
				remote.csp5p = rs.getString("CSP_05_P");
				remote.csp5k = rs.getString("CSP_05_K");
				remote.csp5b = rs.getString("CSP_05_B");
				
				remote.csp6s = rs.getString("CSP_06_S");
				remote.csp6a = rs.getString("CSP_06_A");
				remote.csp6p = rs.getString("CSP_06_P");
				remote.csp6k = rs.getString("CSP_06_K");
				remote.csp6b = rs.getString("CSP_06_B");
				
				remote.csm1s = rs.getString("CSM_01_S");
				remote.csm1a = rs.getString("CSM_01_A");
				remote.csm1p0 = rs.getString("CSM_01_P_0");
				remote.csm1p1 = rs.getString("CSM_01_P_1");
				remote.csm1p2 = rs.getString("CSM_01_P_2");
				remote.csm1p3 = rs.getString("CSM_01_P_3");
				remote.csm1p4 = rs.getString("CSM_01_P_4");
				remote.csm1b = rs.getString("CSM_01_B");
				remote.csm1m = rs.getString("CSM_01_M");
				remote.csm1r = rs.getString("CSM_01_R");
				remote.csm1n = rs.getString("CSM_01_N");
				
				remote.csm2s = rs.getString("CSM_02_S");
				remote.csm2a = rs.getString("CSM_02_A");
				remote.csm2p0 = rs.getString("CSM_02_P_0");
				remote.csm2p1 = rs.getString("CSM_02_P_1");
				remote.csm2p2 = rs.getString("CSM_02_P_2");
				remote.csm2p3 = rs.getString("CSM_02_P_3");
				remote.csm2p4 = rs.getString("CSM_02_P_4");
				remote.csm2b = rs.getString("CSM_02_B");
				remote.csm2m = rs.getString("CSM_02_M");
				remote.csm2r = rs.getString("CSM_02_R");
				remote.csm2n = rs.getString("CSM_02_N");
				
				remote.csm3s = rs.getString("CSM_03_S");
				remote.csm3a = rs.getString("CSM_03_A");
				remote.csm3p0 = rs.getString("CSM_03_P_0");
				remote.csm3p1 = rs.getString("CSM_03_P_1");
				remote.csm3p2 = rs.getString("CSM_03_P_2");
				remote.csm3p3 = rs.getString("CSM_03_P_3");
				remote.csm3p4 = rs.getString("CSM_03_P_4");
				remote.csm3b = rs.getString("CSM_03_B");
				remote.csm3m = rs.getString("CSM_03_M");
				remote.csm3r = rs.getString("CSM_03_R");
				remote.csm3n = rs.getString("CSM_03_N");
				
				remote.csm4s = rs.getString("CSM_04_S");
				remote.csm4a = rs.getString("CSM_04_A");
				remote.csm4p0 = rs.getString("CSM_04_P_0");
				remote.csm4p1 = rs.getString("CSM_04_P_1");
				remote.csm4p2 = rs.getString("CSM_04_P_2");
				remote.csm4p3 = rs.getString("CSM_04_P_3");
				remote.csm4p4 = rs.getString("CSM_04_P_4");
				remote.csm4b = rs.getString("CSM_04_B");
				remote.csm4m = rs.getString("CSM_04_M");
				remote.csm4r = rs.getString("CSM_04_R");
				remote.csm4n = rs.getString("CSM_04_N");
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
