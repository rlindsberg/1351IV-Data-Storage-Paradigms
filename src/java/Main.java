import java.sql.*;  

public class Main {
	public static void main(String[] args) { 
		Connection con = null;
		try {   
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/museum","root","hejhejhej"
			);  
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from artist");
			System.out.println("id\ttitle\tdesc");  				  
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));  				
			} 
			con.close();  		
		} catch(Exception e) { 
			// 
		}
	}
}