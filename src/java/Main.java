import java.sql.*; 
import java.util.*; 

public class Main {
	public static void main(String[] args) {
		Connection con = null;
		try {
				Class.forName("com.mysql.jdbc.Driver");  
				con = DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/museum","root","hejhejhej"
				);  
				Statement stmt = con.createStatement();
				String cmd = args[0];
				if (args.length < 1) {
					System.out.println("Usage: <command> <arguments...>");
					return;
				}
				switch (cmd) {
					case "guides":
						displayAllGuides(stmt);
						break;
					case "languages":
						displayLanguagesForGuide(stmt, args[1]);
						break;
					case "addlang":
						addLanguageToGuide(stmt, args[1]);
						break;
					default:
						System.out.println("Usage: <command> <arguments...>");
						return;
				}
				stmt.close();
				con.close();  						
			} catch (Exception e) {
				System.out.println(e);
			}     
	}

	public static void displayAllGuides(Statement stmt) throws Exception {
		ResultSet rs = stmt.executeQuery("select * from guide");
		System.out.println("Personnummer\nNamn");  				  
		while(rs.next()) {
			System.out.println(rs.getString(1)+"\t"+rs.getString(2));  				
		}
	}

	public static void displayLanguagesForGuide(Statement stmt, String pnr) throws Exception {
		System.out.println("\nLanguage(s)");  	
		ResultSet rs = stmt.executeQuery("SELECT language FROM guide_language WHERE social_sec_no = \"" + pnr +"\"");			  
		while(rs.next()) {
			System.out.println(rs.getString(1));  				
		} 
	}

	public static void addLanguageToGuide(Statement stmt, String pnr) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter language:");
		String input = scanner.nextLine();
		String sql = String.format("INSERT INTO guide_language (social_sec_no, language) VALUES (\"%s\",\"%s\")", pnr, input);
		stmt.executeUpdate(sql);			  
		scanner.close();
	}
 }