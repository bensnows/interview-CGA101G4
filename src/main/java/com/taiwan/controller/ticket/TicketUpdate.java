package com.taiwan.controller.ticket;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.taiwan.beans.TicketVO;
import com.taiwan.service.TicketService;
import com.taiwan.service.tktImg.TktImgService;
import com.taiwan.utils.ControllerUtil;
import com.taiwan.utils.UUIDFileName;

@WebServlet("/ticket/tktUpdate")
@MultipartConfig
public class TicketUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			Integer tktId = Integer.valueOf(request.getParameter("tktId"));
			// 收到票券名稱的請求參數
			String tktName = request.getParameter("tktName");
			// 如果是空值 或是 去掉空白等於空字串
			if (tktName == null || tktName.trim().equals("")) {
				errorMsgs.put("tktName", "票券名稱不能為空");
			}
			// 票券數量
			Integer originalAmount = null;
			try {
				// String 轉型轉成 Integer
				originalAmount = Integer.valueOf(request.getParameter("originalAmount"));
				// 如果輸入數量小於等於0
				if (originalAmount <= 0) {
					errorMsgs.put("originalPrice", "票券起始販賣數量請輸入大於0的數字");
				}
			} catch (Exception e) {
				errorMsgs.put("originalAmount", "票券可販賣數量請填數字");
			}
			// 票券價格
			Integer price = null;
			try {
				price = Integer.valueOf(request.getParameter("price"));
				if (price <= 0) {
					errorMsgs.put("price", "票券價格請輸入大於0的數字");
				}

			} catch (Exception e) {
				errorMsgs.put("price", "票券價格請輸入數字");
			}
			// 以字串形式獲取開始日期跟結束日期
			String startString = request.getParameter("startdate");
			String endString = request.getParameter("enddate");
			// 把不要的符號去掉
			startString = startString.replace("T", " ") + ":00";
			endString = endString.replace("T", " ") + ":00";
			// 轉成timestamp類
			Timestamp startdate = Timestamp.valueOf(startString);
			Timestamp enddate = Timestamp.valueOf(endString);
			if (enddate.before(startdate)) {
				errorMsgs.put("timeError", "結束日期比開始日期還早");
			}
			// 獲取票券種類
			String kind = request.getParameter("kind");
//					System.out.println(kind);
			// 取得縣市名稱
			String city = request.getParameter("city");
//					System.out.println(city);
			// 取得鄉鎮市區名
			String town = request.getParameter("town");
//					System.out.println(town);
			String address = request.getParameter("address");
			// 把地址串接起來
			String allAddress = new StringBuffer(city).append(town).append(address).toString();
			if (address == null || address.trim().equals("")) {
				errorMsgs.put("address", "地址欄位不得為空");
			}
			String instruction = request.getParameter("instruction");
			if (instruction == null || instruction.trim().equals("")) {
				errorMsgs.put("instruction", "請輸入票券介紹");
			}
			String notice = request.getParameter("notice");
			if (notice == null || notice.trim().equals("")) {
				errorMsgs.put("notice", "請輸入票券注意事項");
			}
			String howuse = request.getParameter("howuse");
			if (howuse == null || howuse.trim().equals("")) {
				errorMsgs.put("howuse", "請輸入如何使用");
			}
			String canxpolicy = request.getParameter("canxpolicy");
			if (canxpolicy == null || canxpolicy.trim().equals("")) {
				errorMsgs.put("canxpolicy", "請輸入取消政策");
			}
			TicketVO ticketVO = new TicketVO();
			ticketVO.setTktId(tktId);
			ticketVO.setTktName(tktName);
			ticketVO.setOriginalAmount(originalAmount);
			ticketVO.setPrice(price);
			ticketVO.setStartdate(startdate);
			ticketVO.setEnddate(enddate);
			ticketVO.setLocation(city);
			ticketVO.setInstruction(instruction);
			ticketVO.setAddress(allAddress);
			ticketVO.setNotice(notice);
			ticketVO.setHowuse(howuse);
			ticketVO.setCanxpolicy(canxpolicy);
			ticketVO.setKind(kind);

			if (!errorMsgs.isEmpty()) {
				request.setAttribute("ticketVO", ticketVO);
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_create.jsp");
				rd.forward(request, response);
				return;
			}
			// 開始更新資料
			ticketService.update(tktId, tktName, originalAmount, price, startdate, enddate, city, instruction,
					allAddress, notice, howuse, canxpolicy, kind); 
			
			//我票券照片要存在這個檔案目錄之下
			String saveDirectory = "/images/ticket/" + tktId;
			System.out.println(saveDirectory);
			// 找到阿飄路徑
			String realPath = getServletContext().getRealPath(saveDirectory);
			System.out.println(realPath);
			// 再如果阿飄路徑下沒有這個資料夾就創造，有就不用
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists()) {
				fsaveDirectory.mkdirs();
			}
			Collection<Part> parts = request.getParts();
			UUIDFileName uuidFileName = new UUIDFileName();
			System.out.println("check");
			for (Part part : parts) {
				System.out.println("check in");
				String filename = uuidFileName.getFileNameFromPart(part);
				if (filename != null && part.getContentType() != null) {
					part.write(realPath + "/" + filename);
					// 傳入db的路徑前面不能再有斜槓，不然伺服器找的時候會跑一次阿飄路徑
					String dbSaveDirectory = "images/ticket/" + tktId;
					// 要傳回數據庫的路徑
					TktImgService tktImgService = new TktImgService();
					String dbPath = dbSaveDirectory + "/" + filename;
					System.out.println(dbPath);
					tktImgService.addTktImg(dbPath, tktId);

				}
			}
			// 如果錯誤訊息的map不是空值的話，就請求轉發回/ticket/ticket_create.jsp
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_update.jsp");
				rd.forward(request, response);
				return;
			}
			String whichPage=(String)request.getSession().getAttribute("whichPage");
			RequestDispatcher rd = request.getRequestDispatcher("/ticket/findAll?whichPage="+whichPage);
			rd.forward(request, response);
		} catch (Exception e) {
			errorMsgs.put("anotherError", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_update.jsp");
			rd.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
