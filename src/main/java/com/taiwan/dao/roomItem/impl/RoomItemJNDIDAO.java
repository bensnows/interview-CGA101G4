package com.taiwan.dao.roomItem.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import com.taiwan.beans.RoomItemVO;
import com.taiwan.dao.roomItem.RoomItemDAO_interface;
import com.taiwan.utils.JndiUtil;

public class RoomItemJNDIDAO implements RoomItemDAO_interface {
	//未修改版

	private static final String insert = "INSERT INTO Taiwan.ROOM_ITEM ( room_id, room_order_id, room_amount, room_price) VALUES ( ?, ?, ?, ?)";
	private static final String update = "update Taiwan.ROOM_ITEM set evaluate_score = ? , evaluate_msg=? where room_item_id = ? ";
	private static final String query = "select * from Taiwan.ROOM_ITEM where room_order_id = ?";

	@Override
	public RoomItemVO queryRoomItemByRoomItemId(Integer roomItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomItemVO> queryRoomItemByRoomOrderId(Integer roomOrderId) {
		List<RoomItemVO> list = new ArrayList<RoomItemVO>();
		RoomItemVO roomItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = JndiUtil.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, roomOrderId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				roomItemVO = new RoomItemVO();
				roomItemVO.setRoomItemId(rs.getInt("room_item_id"));
				roomItemVO.setRoomId(rs.getInt("room_id"));
				roomItemVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomItemVO.setRoomItemAmount(rs.getInt("room_amount"));
				roomItemVO.setRoomItemEvaluateScore(rs.getInt("evaluate_score"));
				roomItemVO.setRoomItemEvaluateMsg(rs.getString("evaluate_msg"));
				roomItemVO.setRoomItemPrice(rs.getInt("room_price"));
				list.add(roomItemVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<RoomItemVO> queryRoomItemByRoomItemEvaluateScore(Integer room_item_evaluate_score) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertRoomItem(RoomItemVO roomItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = JndiUtil.getConnection();
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, roomItemVO.getRoomId());
			pstmt.setInt(2, roomItemVO.getRoomOrderId());
			pstmt.setInt(3, roomItemVO.getRoomItemAmount());
			pstmt.setInt(4, roomItemVO.getRoomItemPrice());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateRoomItem(RoomItemVO roomItemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con =JndiUtil.getConnection();
			pstmt = con.prepareStatement(update);

			pstmt.setInt(1, roomItemVO.getRoomItemEvaluateScore());
			pstmt.setString(2, roomItemVO.getRoomItemEvaluateMsg());
			pstmt.setInt(3, roomItemVO.getRoomItemId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public RoomItemVO queryByRoomOrderId(Integer roomOrderId) {
		RoomItemVO roomItemVO =new RoomItemVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JndiUtil.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, roomOrderId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				roomItemVO.setRoomItemId(rs.getInt("room_item_id"));
				roomItemVO.setRoomId(rs.getInt("room_id"));
				roomItemVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomItemVO.setRoomItemAmount(rs.getInt("room_amount"));
				roomItemVO.setRoomItemEvaluateScore(rs.getInt("evaluate_score"));
				roomItemVO.setRoomItemEvaluateMsg(rs.getString("evaluate_msg"));
				roomItemVO.setRoomItemPrice(rs.getInt("room_price"));
			}
			// Handle any driver errors
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return roomItemVO;
	}
	@Override
	public void insertRoomItem(RoomItemVO roomItemVO,Connection con) {
		 
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, roomItemVO.getRoomId());
			pstmt.setInt(2, roomItemVO.getRoomOrderId());
			pstmt.setInt(3, roomItemVO.getRoomItemAmount());
			pstmt.setInt(4, roomItemVO.getRoomItemPrice());

			pstmt.executeUpdate();

			// Handle any driver errors
		
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-roomItem");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		
		}
	}

	@Override
	public List<RoomItemVO> queryRoomItemByRoomId(Integer roomId) {
		// TODO Auto-generated method stub
		return null;
	}
}
