package com.taiwan.dao.roomtype;

import com.taiwan.beans.Roomtype;

public interface Roomtype_interface {
	public Roomtype searchRoomtype(Integer roomid);
	public void addEvaluate(Integer roomId,Integer score);
}