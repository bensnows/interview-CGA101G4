package com.taiwan.controller.faq;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.service.faq.FaqService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/faq/faqDelete")
public class FaqDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FaqService faqService=ControllerUtil.getBean(FaqService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String,String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//接收要刪除的newsId
			Integer faqId=Integer.valueOf(request.getParameter("faqId"));
			//開始刪除資料
			faqService.delete(faqId);
			//刪除成功，準備請求轉向
			RequestDispatcher rd=request.getRequestDispatcher("/faq/findAll");
			rd.forward(request, response);
			
			
		}catch (Exception e) {
			errorMsgs.put("delete error",e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/faq/findAll");
			rd.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
