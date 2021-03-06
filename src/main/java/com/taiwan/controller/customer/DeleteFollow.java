package com.taiwan.controller.customer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.Company;
import com.taiwan.beans.FollowVO;
import com.taiwan.dao.impl.CompanyDaoJNDI14;
import com.taiwan.service.impl.FollowServiceImpl;

@WebServlet("/cust/DeleteFollow")
public class DeleteFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteFollow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 取前端送來要刪掉的會員編號、廠商編號
		Integer custId = Integer.parseInt(request.getParameter("custId"));
		Integer cmpId = Integer.parseInt(request.getParameter("cmpId"));
		System.out.println("delete=" + custId + "," + cmpId);
		// 到資料庫刪掉
		FollowServiceImpl followServiceImpl = new FollowServiceImpl();
		followServiceImpl.deletFollow(custId, cmpId);
		// 再取一次關注廠商的資料，並轉送到關注廠商的網頁
		List<FollowVO> list = followServiceImpl.searchAllFollow(custId);
		// 去資料庫抓所有廠商的資料，轉送到前端
		CompanyDaoJNDI14 companyDaoJNDI14 = new CompanyDaoJNDI14();
		List<Company> companyList = companyDaoJNDI14.queryCompanyAll();
		request.setAttribute("companyList", companyList);
		// 把所有廠商的資料中會員已關注的廠商濾掉，轉送到前端
		List<Company> selectCompanyList = new LinkedList<Company>();

		for (Company company : companyList) {
			boolean add = true;
			for (FollowVO followVO : list) {
				if (company.getCmpId().equals(followVO.getCmpId())) {
					System.out.println("false");
					add = false;
					break;
				}
			}
			if (add) {
				selectCompanyList.add(company);
			}
		}
		request.setAttribute("selectCompanyList", selectCompanyList);
		request.setAttribute("list", list);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/showFollow.jsp");
		successView.forward(request, response);

	}

}
