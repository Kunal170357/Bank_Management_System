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
 * Servlet implementation class Withdraw
 */
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
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
		int account=Integer.parseInt(request.getParameter("account"));
		int amount=Integer.parseInt(request.getParameter("amt"));
		if(amount>0)
		{
			try
			{
				int balance=0;
				Connection con=DbConnect.getConnect();
				PreparedStatement ps1=con.prepareStatement("select abalance from users where accno=?");
				ps1.setInt(1, account);
				ResultSet rs=ps1.executeQuery();
				while(rs.next())
				{
					balance=rs.getInt(1);
				}
				if(amount>0 && balance>=amount && balance>=4999)
				{
					int temp=balance-amount;
					PreparedStatement ps2=con.prepareStatement("update users set abalance=? where accno=?");
					ps2.setInt(1, temp);
					ps2.setInt(2, account);
					int i=ps2.executeUpdate();
					if(i>0)
					{
						response.sendRedirect("success.html");
					}
					else
					{
						response.sendRedirect("failed.html");
					}
					String sts="debited";
					int id;
					int sacc=account;
					String racc="-------";
					PreparedStatement ps3=con.prepareStatement("insert into transact values (?,?,?,?,?)");
					ps3.setInt(1, 0);
					ps3.setInt(2, sacc);
					ps3.setString(3, racc);
					ps3.setInt(4, amount);
					ps3.setString(5, sts);
					ps3.executeUpdate();
				}else
				{
					response.sendRedirect("failed.html");
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			response.sendRedirect("failed.html");
		}
	}

}
