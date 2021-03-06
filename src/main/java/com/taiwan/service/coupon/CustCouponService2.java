package com.taiwan.service.coupon;

import java.sql.Timestamp;
import java.util.Set;

import com.taiwan.beans.CustCoupon;

public interface  CustCouponService2 {
	 //查詢某票券擁有的顧客(一對多)(回傳 Set)
		public Set<CustCoupon> getCustByCopId(Integer copId);

		
		
	//查詢某一個客人所擁有的會員優惠券編號
	public CustCoupon queryCustCouponByCustId(Integer custId, Integer cop_id);
		
	//更改已使用優惠券的狀態值(對票券)
	public void updateCustCouponStatusByTkt(Integer custId,Integer tktOrderId,String status, Timestamp usedate);
}
