package com.taiwan.controller.authority;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.EmployeeVO;
import com.taiwan.service.employee.EmployeeService;

@WebServlet("/Authority/AuthorityStatusChange")
public class AuthorityStatusChange extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(req.getContextPath());
		System.out.println("訪問成功");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("empId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("empId", "請輸入員工編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer empId = null;
			try {
				empId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("empId", "員工編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/authority/authorityIndex.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO employeeVO = empSvc.getOneEmp(empId);
			if (employeeVO == null) {
				errorMsgs.put("empId", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/authority/authorityIndex.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("employeeVO", employeeVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception","無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 ****************************************/
			Integer empId = Integer.valueOf(req.getParameter("empId"));

			/*************************** 2.開始查詢資料 ****************************************/
			EmployeeService empSvc = new EmployeeService();
			EmployeeVO employeeVO = empSvc.getOneEmp(empId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?empId=" + employeeVO.getEmpId() + "&empName=" + employeeVO.getEmpName() + "&empPassword="
					+ employeeVO.getEmpPassword() + "&empStatus=" + employeeVO.getEmpStatus() + "&hiredate="
					+ employeeVO.getHiredate();
			String url = "/back-end/emp/update_emp_input.jsp" + param;

			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
			System.out.println(param);

//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception","無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer empId = Integer.valueOf(req.getParameter("empId").trim());

			String empName = req.getParameter("empName");
			String enpNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,12}$";
			if (empName == null || empName.trim().length() == 0) {
				errorMsgs.put("empName", "員工姓名: 請勿空白");
			} else if (!empName.trim().matches(enpNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("empName", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到12之間");
			}

			String empPassword = req.getParameter("empPassword");
			String empPasswordReg = "^[((a-zA-Z0-9)]{6,12}$";
			if (empPassword == null || empPassword.trim().length() == 0) {
				errorMsgs.put("empPassword", "員工密碼: 請勿空白");
			} else if (!empPassword.trim().matches(empPasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("empPassword", "員工密碼: 只能是英文字母和數字 , 且長度必需在6到12之間");
			}

			String empStatus = req.getParameter("empStatus");
//			
			if ("啟用".equals(empStatus) != true && "未啟用".equals(empStatus) != true && "離職".equals(empStatus) != true) {
				errorMsgs.put("empStatus", "員工狀態: 請填寫啟用、未啟用或離職");
			}

			java.sql.Date hiredate = null;
			try {
				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("hiredate", "請輸入日期");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			EmployeeService empSvc = new EmployeeService();
			System.out.println(empId + "," + empName + "," + empPassword + "," + empStatus + "," + hiredate);

			EmployeeVO employeeVO = empSvc.updateEmp(empId, empName, empPassword, 1, empStatus, hiredate);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

////				/***************************其他可能的錯誤處理*************************************/
//		} catch (Exception e) {
//			errorMsgs.put("修改資料失敗",e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/emp/update_emp_input.jsp");
//			failureView.forward(req, res);
//			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String empName = req.getParameter("empName");
			String enpNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,12}$";
			if (empName == null || empName.trim().length() == 0) {
				errorMsgs.put("empName", "員工姓名: 請勿空白");
			} else if (!empName.trim().matches(enpNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("empName", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到12之間");
			}

			String empPassword = req.getParameter("empPassword");
			String empPasswordReg = "^[((a-zA-Z0-9)]{6,12}$";
			if (empPassword == null || empPassword.trim().length() == 0) {
				errorMsgs.put("empPassword", "員工密碼: 請勿空白");
			} else if (!empPassword.trim().matches(empPasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("empPassword", "員工密碼: 只能是英文字母和數字 , 且長度必需在6到12之間");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			EmployeeService empSvc = new EmployeeService();
			empSvc.addEmp(empName, empPassword,1);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			String url = "/back-end/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

////			/***************************其他可能的錯誤處理**********************************/
//		} catch (Exception e) {
//			errorMsgs.put("Exception",e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/emp/addEmp.jsp");
//			failureView.forward(req, res);
//		}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 ***************************************/

			Integer empId = Integer.valueOf(req.getParameter("empId"));

			/*************************** 2.開始刪除資料 ***************************************/

			EmployeeService empSvc = new EmployeeService();
			empSvc.deleteEmp(empId);

			/*************************** 2.開始刪除資料 ***************************************/

			String url = "/back-end/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);

////				/***************************其他可能的錯誤處理**********************************/
//		} catch (Exception e) {
//			errorMsgs.add("刪除資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/emp/listAllEmp.jsp");
//			failureView.forward(req, res);
//		}
		}
	}
}