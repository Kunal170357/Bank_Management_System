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
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
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
		
doGet(request, response);
		
		int ano=Integer.parseInt(request.getParameter("account"));
		try
		{
			Connection con=DbConnect.getConnect();
			PreparedStatement ps1=con.prepareStatement("delete from users where accno=?");
			ps1.setInt(1, ano);
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
