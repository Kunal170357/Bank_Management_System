package com.bank.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kunal.dbconnect.DbConnect;

/**
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
		
		int ano=Integer.parseInt(request.getParameter("account"));
		String pass=request.getParameter("password");
		
		try
		{
			Connection con=DbConnect.getConnect();
			PreparedStatement ps1=con.prepareStatement("select * from users where accno=? and apassword=?");
			ps1.setInt(1, ano);
			ps1.setString(2, pass);
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
			{
				UserData.setAcc_num(ano);
				response.sendRedirect("Transfer2.html");
			}
			else
			{
				response.sendRedirect("UserLogin.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
