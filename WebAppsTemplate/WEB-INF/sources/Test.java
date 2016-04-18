import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class Test extends HttpServlet
{
	private DataSource datasource;
	
	
	// Look up JNDI datasource only once at init
	public void init(ServletConfig config) throws ServletException {
		try {
			Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
			datasource = (DataSource) envCtx.lookup("jdbc/TestDB");		
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	// Gets JDBC Connection for Connection Pooling
	private Connection getConnection() throws SQLException {
	    return datasource.getConnection();
	  }
	
	
	// HTTP GET Method
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
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

    
    // HTTP POST Method
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	doGet(request, response);
    }
}