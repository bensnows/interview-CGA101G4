package com.taiwan.dao.roomOrder;

import java.sql.Timestamp;
import java.util.List;

import com.taiwan.beans.RoomItemVO;
import com.taiwan.beans.RoomOrderVO;

public interface RoomOrderDAO_interface {
		public RoomOrderVO queryRoomOrderByRoomOrderId(Integer roomOrderId);

		public List<RoomOrderVO> queryRoomOrderByRoomOrderCheckinDate(Timestamp roomOrderCheckinDate);

		public List<RoomOrderVO> queryRoomOrderByRoomOrderCheckoutDate(Timestamp roomOrderCheckoutDate);
		
		public List<RoomOrderVO> queryRoomOrderByRoomOrderDate(Timestamp startDate,Timestamp endDate);


		public List<RoomOrderVO> queryRoomOrderByRoomOrderStatus(String roomOrderStatus);

		public List<RoomOrderVO> queryRoomOrderByCustId(Integer custId);

		public Integer insert(RoomOrderVO roomOrderVO);
		
		public void delete(Integer roomOrderId);
		
		public void cancel(RoomOrderVO roomOrderVO);

		public Integer insert(RoomOrderVO roomOrderVO, RoomItemVO roomItemVO);

		public Integer insert2(RoomOrderVO roomOrderVO, RoomItemVO roomItemVO);

}
