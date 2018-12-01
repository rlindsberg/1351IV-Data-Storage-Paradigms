import java.sql.*; 
import java.util.*; 

// mysql-connector-java-8.0.13.jar has to be in the same folder in order for the following command to work:
// javac -cp .:mysql-connector-java-8.0.13.jar Main.java && java -cp .:mysql-connector-java-8.0.13.jar Main

public class Main {
	public static void main(String[] args) {
		Connection con = null;
		try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/museum","root","hejhejhej");  
				if (args.length < 1) {
					printHelp();
					return;
				}				
				final String cmd = args[0];
				switch (cmd) {
					case "guides":
						displayAllGuides(con);
						break;
					case "language":
						if (args.length < 2) {
							System.out.println("Usage: language <guide_social_sec_no>");
						} else {
							displayGuideLanguages(con, args[1]);
						}
						break;
					case "addlang":
						if (args.length < 3) {
							System.out.println("Usage: addlang <guide_social_sec_no> <language>");
						} else {					
							addLanguageToGuide(con, args[1], args[2]);
						}
						break;
					case "help":
					default:
						printHelp();
						return;
				}
				//con.close();  						
			} catch (Exception e) {
				System.out.println(e);
			}     
	}

	private static void displayAllGuides(Connection con) throws Exception {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM guide"); 				  
		while(rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2));  				
		}
		stmt.close();
	}

	private static void displayGuideLanguages(Connection con, String persnr) throws Exception {
		String query = "SELECT language FROM guide_language WHERE social_sec_no = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, persnr);
		ResultSet rs = stmt.executeQuery();			  
		while(rs.next()) {
			System.out.println(rs.getString(1));  				
		} 
		stmt.close();
	}

	private static void addLanguageToGuide(Connection con, String persnr, String language) throws Exception {
		//Validate language
		if (language == null || language.isEmpty()) {
			System.out.println("You need to provide a valid language.");	
			return;	
		}
		//Check if a guide with pnr actually exists.
		String query = "SELECT * FROM guide WHERE social_sec_no = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, persnr);
		ResultSet rs = stmt.executeQuery();
		if (!rs.next()) {
			System.out.println(String.format("No guide exists with persnr: %s", persnr));
			stmt.close();
			return;
		}
		//Check if language exists, if not, create it(?)
		query = "SELECT * FROM language WHERE name = ?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, language);
		rs = stmt.executeQuery();
		if (!rs.next()) {
			query = "INSERT INTO language (name) VALUES (?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, language);
			stmt.executeUpdate();
		}		  		
		//Do the actual insert in guide_language
		query = "INSERT INTO guide_language (social_sec_no, language) VALUES (?,?)";
		stmt = con.prepareStatement(query);
		stmt.setString(1, persnr);
		stmt.setString(2, language);
		stmt.executeUpdate();			  
		query = "SELECT * FROM guide_language WHERE social_sec_no = ?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, persnr);
		rs = stmt.executeQuery();			  
		while(rs.next()) {
			System.out.println(rs.getString(2));  				
		}
		stmt.close(); 		
	}

	private static void printHelp() {
		System.out.println("Usage: <command> <args>");
		System.out.println("v-- Examples --v");
		System.out.println("To see all guides: \t\t\t\"guides\"");
		System.out.println("To see all languages a guide speaks: \t\"language\" \"123456-7890\"");
		System.out.println("To add a language to a guide: \t\t\"addlang\" \"123456-7890\" \"Swedish\"");		
	}
 }