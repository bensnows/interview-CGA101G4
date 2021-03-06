package com.taiwan.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taiwan.utils.JndiUtil;

public class CustPlatMailVO {
	private Integer custPlatId;
	private Integer custId;
	private Integer empId;
	private String msg;
	private Timestamp custPlatTime;
	private Integer who;

	public Integer getCustPlatId() {
		return custPlatId;
	}

	public void setCustPlatId(Integer custPlatId) {
		this.custPlatId = custPlatId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Timestamp getCustPlatTime() {
		return custPlatTime;
	}

	public void setCustPlatTime(Timestamp custPlatTime) {
		this.custPlatTime = custPlatTime;
	}

	public Integer getWho() {
		return who;
	}

	public void setWho(Integer who) {
		this.who = who;
	}

	public CustPlatMailVO() {
		super();
	}

	public CustPlatMailVO(Integer custPlatId, Integer custId, Integer empId, String msg, Timestamp custPlatTime,
			Integer who) {
		super();
		this.custPlatId = custPlatId;
		this.custId = custId;
		this.empId = empId;
		this.msg = msg;
		this.custPlatTime = custPlatTime;
		this.who = who;
	}

	@Override
	public String toString() {
		return "Cust_Plat_Mail [custPlatId=" + custPlatId + ", custId=" + custId + ", empId=" + empId + ", msg=" + msg
				+ ", custPlatTime=" + custPlatTime + ", who=" + who + "]";
	}

	public String getCustAccount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name= null;
		try {
			conn = JndiUtil.getConnection();
			ps = conn.prepareStatement("SELECT ACCOUNT FROM CUSTOMER WHERE CUST_ID = ?;");
			ps.setInt(1, custId);
			System.out.println("?????????JNDI???!???????????????if??????");
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("ACCOUNT");
				System.out.println("??????JNDI???if???:" + name);
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
		return name;
	}

}
