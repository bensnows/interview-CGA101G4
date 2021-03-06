package com.taiwan.controller.customerManage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.EmployeeVO;
import com.taiwan.service.custPlatMail.impl.CustPlatMailServiceImpl;

@WebServlet("/custManage/ReplyCustPlatMail")
public class ReplyCustPlatMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReplyCustPlatMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得想回復的會員Id
		Integer custId = Integer.valueOf(request.getParameter("hiddenWho"));
		// 取得想回復的訊息
		String msg = request.getParameter("msg");
		// 取得管理員Id
		HttpSession session = request.getSession();
		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
		Integer empId = employeeVO.getEmpId();
		// 抓取信件的流水號(custPlatId)
		Integer custPlatId = Integer.valueOf(request.getParameter("custPlatId"));
		// 去update是誰回復了信件
		CustPlatMailServiceImpl dao0 = new CustPlatMailServiceImpl();
		dao0.updateEmpId(empId, custPlatId);
		// 把回覆信件的資料送到資料庫
		CustPlatMailServiceImpl dao = new CustPlatMailServiceImpl();
		dao.setCust_Plat_Mail(custId, empId, msg, empId);
		// 轉送前端
		RequestDispatcher successView = request.getRequestDispatcher("/back-end/customer/custPlatMailManage.jsp");
		successView.forward(request, response);
	}

}
