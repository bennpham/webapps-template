<%@page import="java.io.*,
java.sql.*,
javax.naming.Context,
javax.naming.InitialContext,
javax.naming.NamingException,
javax.servlet.*,
javax.servlet.http.*,
javax.sql.DataSource"
%>

HELLO WORLD!<br/>
This is JSP<br/><br/>

<%
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
            Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource datasource = (DataSource) envCtx.lookup("jdbc/TestDB");
            connection = datasource.getConnection();
            // JDBC stuff
            ps = connection.prepareStatement("SELECT * FROM test WHERE ? LIKE '%test%'");
            ps.setString(1, "test");
            rs = ps.executeQuery();		
		
            while (rs.next()) {
                out.println(rs.getString("description") + "<br/>");
            }
        } catch (SQLException sqlException) {
//          out.println(sqlException); //UNCOMMENT FOR DEBUGGING
            sqlException.printStackTrace();
	} catch (java.lang.Exception javaException) {
//          out.println(javaException); //UNCOMMENT FOR DEBUGGING
            javaException.printStackTrace();
	} finally {
            if (rs != null) {try {rs.close();} catch (SQLException e) {}}
            if (ps != null) {try {ps.close();} catch (SQLException e) {}}
            if (connection != null) {try {connection.close();} catch (SQLException e) {}}
            if (out != null) {out.close();}
	}	
%>