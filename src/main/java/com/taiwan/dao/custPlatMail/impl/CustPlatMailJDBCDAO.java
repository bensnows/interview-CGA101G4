package com.taiwan.dao.custPlatMail.impl;

import static java.sql.Types.INTEGER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.CustPlatMailVO;
import com.taiwan.dao.custPlatMail.CustPlatMailDao_interface;
import com.taiwan.utils.config.DbUtil;

public class CustPlatMailJDBCDAO implements CustPlatMailDao_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = DbUtil.getUrl();
	String userid = "root";
	String passwd = "rootitri";

	private static final String GET_CUST_PLAT_MAIL = "SELECT CUST_PLAT_ID ,CUST_ID ,EMP_ID ,MSG,CUST_PLAT_TIME ,WHO"
			+ " FROM CUST_PLAT_MAIL LIMIT ? OFFSET ? ;";
	private static final String SET_CUST_PLAT_MAIL = "INSERT INTO CUST_PLAT_MAIL(CUST_ID,EMP_ID,MSG,WHO) VALUES(?,?,?,?);";
	private static final String GET_ALL = "SELECT * FROM CUST_PLAT_MAIL;";
	private static final String GET_ALL_BY_CUSTID = "SELECT * FROM CUST_PLAT_MAIL WHERE CUST_ID=? ORDER BY CUST_PLAT_TIME DESC;";
	private static final String UPDATE_EMP_ID = "UPDATE CUST_PLAT_MAIL SET EMP_ID=? WHERE CUST_PLAT_ID=?;";

	@Override
	public List<CustPlatMailVO> getCust_Plat_Mail(Integer rowNum, Integer offset) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CustPlatMailVO> list = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_CUST_PLAT_MAIL);
			ps.setInt(1, rowNum);
			ps.setInt(2, offset);
			rs = ps.executeQuery();
			list = new ArrayList<CustPlatMailVO>();
			while (rs.next()) {
				CustPlatMailVO custPlatMailVO = new CustPlatMailVO();
				custPlatMailVO.setCustPlatId(rs.getInt("CUST_PLAT_ID"));
				custPlatMailVO.setCustId(rs.getInt("CUST_ID"));
				custPlatMailVO.setEmpId(rs.getInt("EMP_ID"));
				custPlatMailVO.setMsg(rs.getString("MSG"));
				custPlatMailVO.setCustPlatTime(rs.getTimestamp("CUST_PLAT_TIME"));
				custPlatMailVO.setWho(rs.getInt("WHO"));
				list.add(custPlatMailVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public void setCust_Plat_Mail(CustPlatMailVO mail) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(SET_CUST_PLAT_MAIL);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
	}

	@Override
	public List<CustPlatMailVO> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CustPlatMailVO> list = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			list = new ArrayList<CustPlatMailVO>();
			while (rs.next()) {
				CustPlatMailVO custPlatMailVO = new CustPlatMailVO();
				custPlatMailVO.setCustPlatId(rs.getInt("CUST_PLAT_ID"));
				custPlatMailVO.setCustId(rs.getInt("CUST_ID"));
				custPlatMailVO.setEmpId(rs.getInt("EMP_ID"));
				custPlatMailVO.setMsg(rs.getString("MSG"));
				custPlatMailVO.setCustPlatTime(rs.getTimestamp("CUST_PLAT_TIME"));
				custPlatMailVO.setWho(rs.getInt("WHO"));
				list.add(custPlatMailVO);

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<CustPlatMailVO> getAllByCustId(Integer custId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CustPlatMailVO> list = new ArrayList<CustPlatMailVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL_BY_CUSTID);
			ps.setInt(1, custId);
			rs = ps.executeQuery();

			while (rs.next()) {
				CustPlatMailVO custPlatMailVO = new CustPlatMailVO();
				custPlatMailVO.setCustPlatId(rs.getInt("CUST_PLAT_ID"));
				custPlatMailVO.setCustId(rs.getInt("CUST_ID"));
				custPlatMailVO.setEmpId(rs.getInt("EMP_ID"));
				custPlatMailVO.setMsg(rs.getString("MSG"));
				custPlatMailVO.setCustPlatTime(rs.getTimestamp("CUST_PLAT_TIME"));
				custPlatMailVO.setWho(rs.getInt("WHO"));
				list.add(custPlatMailVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void updateEmpId(Integer empId, Integer custPlatId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE_EMP_ID);
			ps.setInt(1, empId);
			ps.setInt(2, custPlatId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}

	}
}
