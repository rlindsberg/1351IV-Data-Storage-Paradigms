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
			ResultSet rs = stmt.executeQuery("select * from guide");
			System.out.println("social_sec_no\tname");  				  
			while(rs.next()) {
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));  				
			} 
			con.close();  		
		} catch(Exception e) { 
			// 
		}
	}
}