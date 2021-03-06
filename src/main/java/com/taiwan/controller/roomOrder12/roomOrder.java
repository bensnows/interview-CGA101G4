package com.taiwan.controller.roomOrder12;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.Roomtype;
import com.taiwan.service.impl.ReservationServiceImpl12;
import com.taiwan.service.impl.RoomOrderServiceImpl;
import com.taiwan.service.roomtype.impl.RoomtypeService12;

/**
 * Servlet implementation class roomOrder
 */
@WebServlet("/roomOrder12/roomOrder")
public class roomOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomOrderServiceImpl roomOrderServiceImpl = new RoomOrderServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getContextPath());
		System.out.println("訪問成功43");
		// 來自addEmp.jsp的請求

		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("訪問成功48");

//			try {
		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		String custIdST = req.getParameter("custId");
		System.out.println("訪問成功53");

		String custIdReg = "^[(0-9)]{5,5}$";
		if (custIdST == null || custIdST.trim().length() == 0) {
			String url = "/front-end/custLogin/CustomerLogin.jsp";
			RequestDispatcher failureView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			failureView.forward(req, res);
			
			return;
			
		} else if (!custIdST.trim().matches(custIdReg)) { // 以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("custId", "會員編號: 只能數字，且長度必需在5到5之間");
		}
		

		String roomIdST = req.getParameter("roomId");
		String roomIdReg = "^[((0-9)]{1,3}$";
		if (roomIdST == null || roomIdST.trim().length() == 0) {
			errorMsgs.put("roomId", "房型: 請勿空白");
		} else if (!roomIdST.trim().matches(roomIdReg)) { // 以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("roomId", "房型: 只能是數字 , 且長度必需在1到3之間");
		}
		Integer roomId = Integer.valueOf(roomIdST.trim());
		System.out.println(roomId);


		java.sql.Timestamp ckin = null;
		System.out.println(req.getParameter("ckin"));
		System.out.println(req.getParameter("ckin").trim());

		try {
			Date date=java.sql.Date.valueOf(req.getParameter("ckin").trim());
			ckin =new Timestamp(date.getTime());
		} catch (IllegalArgumentException e) {
			errorMsgs.put("ckin", "請輸入日期");
		}
		java.sql.Timestamp ckout = null;
		try {
			Date date=java.sql.Date.valueOf(req.getParameter("ckout").trim());
			ckout = new Timestamp(date.getTime());
		} catch (IllegalArgumentException e) {
			errorMsgs.put("ckout", "請輸入日期");
		}
		if (ckout.before(ckin)) {
			errorMsgs.put("ckout", "開始日期要比結束日期早");
			errorMsgs.put("ckin", "開始日期要比結束日期早");

		}
		//存入日期
			
				Map<String, String> map = new HashMap<>();
				map.put("ckin", req.getParameter("ckin").trim());
				map.put("ckout", req.getParameter("ckout").trim());
				req.setAttribute("dateMap", map);
		// Send the use back to the form, if there were errors

//
		String amountST = req.getParameter("amount");
//		String amountReg = "^[((1-9)]{1,3}$";
//		if (amountST == null || amountST.trim().length() == 0) {
//			errorMsgs.put("amount", "房數: 請勿空白");
//		} else if (!amountST.trim().matches(amountReg)) { // 以下練習正則(規)表示式(regular-expression)
//			errorMsgs.put("amount", "房數: 只能是數字 , 且長度必需在1到2之間");
//		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/cmpRoomtype.jsp");
			failureView.forward(req, res);
			return;
		}
		
		ReservationServiceImpl12 RTSvc=new ReservationServiceImpl12();
		RoomtypeService12 roomtypeService12=new RoomtypeService12();
		Roomtype roomtype=roomtypeService12.searchRoomtype(roomId);
		String roomName=roomtype.getRoomtypeName();
		int max= RTSvc.maxBuyAmount(ckin, ckout, roomId);
		Integer amount = Integer.valueOf(amountST.trim());
		if(max > 0 ) {
		if (amount > max) {
			errorMsgs.put("amount", roomName+"房數: 預定區間剩"+max+"間可預訂");
		}
		}else if(max==0){
			errorMsgs.put("amount", roomName+"房數: 預定區間已無空房");
		}
//		
//			
//
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/addOrder/cmpRoomtype.jsp");
			failureView.forward(req, res);
			return;
		}
		Integer custId = Integer.valueOf(custIdST.trim());

		// 抓房價
		RoomtypeService12 roomtypeSVC = new RoomtypeService12();
		Integer price = roomtypeSVC.searchRoomtype(roomId).getRoomtypePrice();
		//抓廠商
		Integer cmpid=roomtypeSVC.searchRoomtype(roomId).getCmpId();
		//抓copId
		Integer custCopId=Integer.valueOf(req.getParameter("custCopId"));
		System.out.println(custCopId);
//
		/*************************** 2.開始新增資料 ***************************************/
		RoomOrderServiceImpl roomOrderSvc = new RoomOrderServiceImpl();
		if (custCopId>0) {
			roomOrderSvc.addRoomOrder(custId, ckin, ckout, roomId, amount, price,cmpid,custCopId);
		}else {
			roomOrderSvc.addRoomOrder(custId, ckin, ckout, roomId, amount, price,cmpid);
			
		}
		RTSvc.updateReserveAmount(ckin, ckout, roomId, amount);
		/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

		String url = "/front-end/addOrder/selectOrder.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
		successView.forward(req, res);
//		
	}
//

}
