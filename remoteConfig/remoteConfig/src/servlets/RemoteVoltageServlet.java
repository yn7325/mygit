package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import entity.RemoteVoltage;
import services.RemoteVoltagedao;
import utils.JsonUtil;

@WebServlet("/RemoteVoltage")
public class RemoteVoltageServlet extends HttpServlet {

	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 public RemoteVoltageServlet () {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Auto-generated method stub
		RemoteVoltagedao remoteVoltagedao=new RemoteVoltagedao();
		String tns = req.getParameter("tns");
		List<RemoteVoltage> list=remoteVoltagedao.GetVoltageData(tns);
		int total  = list.size();

		JSONObject object = JsonUtil.toJsonString(total, list);
		String str = object.toString();
		list = null;
		object = null;
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(str);
		resp.getWriter().flush();
		resp.getWriter().close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	

}
