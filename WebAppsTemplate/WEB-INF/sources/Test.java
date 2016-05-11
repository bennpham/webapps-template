import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import helperpackage.*;

public class Test extends HttpServlet
{		
	// Gets JDBC Connection for Connection Pooling
	private Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	    return DriverManager.getConnection(MyConstants.SQL_URL, MyConstants.USERNAME, MyConstants.PASSWORD);
	  }
	
	
	// Process Request
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Connection connection = null;
        PrintWriter out = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		
		try {   
			connection = getConnection();
			// JDBC stuff
			ps = connection.prepareStatement("SELECT * FROM test WHERE ? LIKE '%test%'");
			ps.setString(1, "test");
			rs = ps.executeQuery();
			
	    	// setContentType before getWriter is called to set content type for writing
	        response.setContentType("text/html;charset=UTF-8");  
	    	out = response.getWriter();
			
	    	out.println("HELLO WORLD!<br/>This is a servlet<br/><br/>");
	    	
	    	while (rs.next()) {
	    		out.println(rs.getString("description") + "<br/>");
	    	}
			
		} catch (SQLException sqlException) {
//			out.println(sqlException); //UNCOMMENT FOR DEBUGGING (Don't forget to move out=response.getWriter)
			sqlException.printStackTrace();
		} catch (java.lang.Exception javaException) {
//			out.println(javaException); //UNCOMMENT FOR DEBUGGING (Don't forget to move out=response.getWriter)
			javaException.printStackTrace();
		} finally {
			if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			if (ps != null) {try {ps.close();} catch (SQLException e) {}}
			if (connection != null) {try {connection.close();} catch (SQLException e) {}}
			if (out != null) {out.close();}
		}
	}
	
	
	// HTTP GET Method
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
    	processRequest(request, response);
    }

    
    // HTTP POST Method
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	processRequest(request, response);
    }
	
	
	@Override
	public String getServletInfo() {
		return "Test servlet description";
	}
}