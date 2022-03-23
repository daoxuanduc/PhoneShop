package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import context.DBContext;
import model.Account;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8"); //Vietnamese
		try {
			//request.getSession(true).invalidate();
			//make sure that email is valid 
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@#$%^&*]+"; 
			
			//collect data from a login form
			String user = request.getParameter("usr");
			String password = request.getParameter("pwd");
			request.setAttribute("usr", user);
			
			//create Cookies
			Cookie cookie_user = new Cookie("user", user);
			Cookie cookie_password = new Cookie("password", password);
			cookie_user.setMaxAge(2592000);
			cookie_password.setMaxAge(2592000);
			response.addCookie(cookie_user);
			response.addCookie(cookie_password);
			
			Account acc = new Account();
			acc.setUsr(user);
			acc.setPwd(password);
			HttpSession session = request.getSession(true);
			if (!password.matches(regex) || !user.matches(regexMail)) {
				request.setAttribute("error", "Invalid syntax");
				//response.sendRedirect("login.jsp");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			
			//read information of account in database
			String sql="select count(*) as count from account where user_mail=? and password=?";
			DBContext d = new DBContext();
			Connection conn = d.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			int count = 0;	
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			//check account 
			if (count!=0) {
				//set session
				session.setAttribute("email", user);
				session.setAttribute("user", user.split("@")[0]);
				session.setAttribute("hidden", "hidden");
				session.setAttribute("nohidden", "");
				//login is valid, redirect to index.jsp
				request.getRequestDispatcher("ListAllProduct").forward(request, response);
			}
			else {
				request.setAttribute("error", "Wrong username or password");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			response.getWriter().println(ex);
		}
	}

}
