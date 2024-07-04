package com.bank.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kunal.dbconnect.DbConnect;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
		String name=request.getParameter("aname");
		String mob=request.getParameter("acontact");
		int balance=Integer.parseInt(request.getParameter("abalance"));
		String mail=request.getParameter("aemail");
		String addrs=request.getParameter("addr");
		String pass=request.getParameter("password");
		try
		{
			Connection con=DbConnect.getConnect();
			PreparedStatement ps1=con.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
			ps1.setInt(1, ano);
			ps1.setString(2, name);
			ps1.setString(3, mob);
			ps1.setInt(4, balance);
			ps1.setString(5, mail);
			ps1.setString(6, addrs);
			ps1.setString(7, pass);
			int i=ps1.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("success.html");
			}
			else
			{
				response.sendRedirect("failed.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
