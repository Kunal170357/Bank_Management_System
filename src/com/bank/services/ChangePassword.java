package com.bank.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kunal.dbconnect.DbConnect;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String opass=request.getParameter("opassword");
		String npass=request.getParameter("npassword");
		int ano=UserData.getAcc_num();
		try
		{
			Connection con=DbConnect.getConnect();
			PreparedStatement ps2=con.prepareStatement("update users set apassword=? where accno=?");
			ps2.setString(1, npass);
			ps2.setInt(2, ano);
			int i=ps2.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("success2.html");
			}
			else
			{
				response.sendRedirect("failed2.html");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
