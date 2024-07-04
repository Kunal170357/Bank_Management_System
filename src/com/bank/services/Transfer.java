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
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
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
		
		int saccno=Integer.parseInt(request.getParameter("saccount"));
		int raccno=Integer.parseInt(request.getParameter("raccount"));
		int amount=Integer.parseInt(request.getParameter("amt"));
		
		try
		{
			int sbal=0,rbal=0;
			Connection con=DbConnect.getConnect();
			PreparedStatement ps1=con.prepareStatement("select abalance from users where accno=?");
			ps1.setInt(1, saccno);
			ResultSet rs=ps1.executeQuery();
			while(rs.next())
			{
				sbal=rs.getInt(1);
			}
			if(sbal>=amount)
			{
				PreparedStatement ps2=con.prepareStatement("select abalance from users where accno=?");
				ps2.setInt(1, raccno);
				ResultSet rs1=ps2.executeQuery();
				while(rs1.next())
				{
					rbal=rs1.getInt(1);
				}
				int temp=rbal+amount;
				PreparedStatement ps3=con.prepareStatement("update users set abalance=? where accno=?");
				ps3.setInt(1, temp);
				ps3.setInt(2, raccno);
				int i=ps3.executeUpdate();
				
				int temp2=sbal-amount;
				PreparedStatement ps4=con.prepareStatement("update users set abalance=? where accno=?");
				ps4.setInt(1, temp2);
				ps4.setInt(2, saccno);
				int j=ps4.executeUpdate();
				if(i>0 && j>0)
				{
					response.sendRedirect("success.html");
				}
				else
				{
					response.sendRedirect("failed");
				}
				String sts="Transfered";
				int id;
				int sacc=saccno;
				int racc=raccno;
				PreparedStatement ps5=con.prepareStatement("insert into transact values (?,?,?,?,?)");
				ps5.setInt(1, 0);
				ps5.setInt(2, sacc);
				ps5.setInt(3, racc);
				ps5.setInt(4, amount);
				ps5.setString(5, sts);
				ps5.executeUpdate();
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
