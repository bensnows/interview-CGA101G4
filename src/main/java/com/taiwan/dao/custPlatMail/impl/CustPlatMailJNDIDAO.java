package com.taiwan.dao.custPlatMail.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taiwan.beans.CustPlatMailVO;
import com.taiwan.dao.custPlatMail.CustPlatMailDao_interface;
import com.taiwan.utils.JndiUtil;

import static java.sql.Types.*;

public class CustPlatMailJNDIDAO implements CustPlatMailDao_interface {

	private static final String GET_CUST_PLAT_MAIL = "SELECT CUST_PLAT_ID ,CUST_ID ,EMP_ID ,MSG,CUST_PLAT_TIME ,WHO"
			+ " FROM CUST_PLAT_MAIL LIMIT ? OFFSET ? ;";
	private static final String SET_CUST_PLAT_MAIL = "INSERT INTO CUST_PLAT_MAIL(CUST_ID,EMP_ID,MSG,WHO) VALUES(?,?,?,?);";
	private static final String GET_ALL = "SELECT * FROM CUST_PLAT_MAIL ORDER BY CUST_PLAT_TIME DESC;";
	private static final String GET_ALL_BY_CUSTID = "SELECT * FROM CUST_PLAT_MAIL WHERE CUST_ID=? ORDER BY CUST_PLAT_TIME DESC;";
	private static final String UPDATE_EMP_ID = "UPDATE CUST_PLAT_MAIL SET EMP_ID=? WHERE CUST_PLAT_ID=?;";

	@Override
	public List<CustPlatMailVO> getCust_Plat_Mail(Integer rowNum, Integer offset) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustPlatMailVO> list = new ArrayList<CustPlatMailVO>();
		try {
			conn = JndiUtil.getConnection();
			ps = conn.prepareStatement(GET_CUST_PLAT_MAIL);
			ps.setInt(1, rowNum);
			ps.setInt(2, offset);
			rs = ps.executeQuery();
			while (rs.next()) {
				CustPlatMailVO custPlatMailVO = new CustPlatMailVO();
				custPlatMailVO.setCustPlatId(rs.getInt(1));
				custPlatMailVO.setCustId(rs.getInt(2));
				custPlatMailVO.setEmpId(rs.getInt(3));
				custPlatMailVO.setMsg(rs.getString(4));
				custPlatMailVO.setCustPlatTime(rs.getTimestamp(5));
				custPlatMailVO.setWho(rs.getInt(6));
				list.add(custPlatMailVO);
			}
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public void setCust_Plat_Mail(CustPlatMailVO mail) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = JndiUtil.getConnection();
			ps = conn.prepareStatement(SET_CUST_PLAT_MAIL);
			ps.setInt(1, mail.getCustId());
			if (mail.getEmpId() == null) {
				ps.setNull(2, INTEGER);
			} else {
				ps.setInt(2, mail.getEmpId());
			}
			ps.setString(3, mail.getMsg());
			ps.setInt(4, mail.getWho());
			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<CustPlatMailVO> getAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustPlatMailVO> list = new ArrayList<CustPlatMailVO>();
		try {
			conn = JndiUtil.getConnection();
			ps = conn.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				CustPlatMailVO custPlatMailVO = new CustPlatMailVO();
				custPlatMailVO.setCustPlatId(rs.getInt(1));
				custPlatMailVO.setCustId(rs.getInt(2));
				custPlatMailVO.setEmpId(rs.getInt(3));
				custPlatMailVO.setMsg(rs.getString(4));
				custPlatMailVO.setCustPlatTime(rs.getTimestamp(5));
				custPlatMailVO.setWho(rs.getInt(6));
				list.add(custPlatMailVO);
			}
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public List<CustPlatMailVO> getAllByCustId(Integer custId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustPlatMailVO> list = new ArrayList<CustPlatMailVO>();
		try {
			conn = JndiUtil.getConnection();
			ps = conn.prepareStatement(GET_ALL_BY_CUSTID);
			ps.setInt(1, custId);
			rs = ps.executeQuery();
			while (rs.next()) {
				CustPlatMailVO custPlatMailVO = new CustPlatMailVO();
				custPlatMailVO.setCustPlatId(rs.getInt(1));
				custPlatMailVO.setCustId(rs.getInt(2));
				custPlatMailVO.setEmpId(rs.getInt(3));
				custPlatMailVO.setMsg(rs.getString(4));
				custPlatMailVO.setCustPlatTime(rs.getTimestamp(5));
				custPlatMailVO.setWho(rs.getInt(6));
				list.add(custPlatMailVO);
			}
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public void updateEmpId(Integer empId, Integer custPlatId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JndiUtil.getConnection();
			ps = conn.prepareStatement(UPDATE_EMP_ID);
			ps.setInt(1, empId);
			ps.setInt(2, custPlatId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
}
