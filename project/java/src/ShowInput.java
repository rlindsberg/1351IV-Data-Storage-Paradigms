
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elias
 */
public class ShowInput extends javax.swing.JFrame {

    /**
     * Creates new form ShowInPut
     */
    public ShowInput() {
        initComponents();
        printHelp();
        getConnection();
       Show_Products_In_JTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Welcome");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Social_sec_no", "Name"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("All Guides");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(265, 265, 265))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(383, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowInput.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowInput().setVisible(true);
            }
        });
    }
    
    public ArrayList<Guide> getProductList()
    {
            ArrayList<Guide> guideList  = new ArrayList<Guide>();
            Connection con = getConnection();
            String query = "SELECT * FROM guide";
            
            Statement st;
            ResultSet rs;
            
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Guide guide;
            
            while(rs.next())
            {
                guide = new Guide(rs.getString("social_sec_no"),rs.getString("name"));
                guideList.add(guide);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ShowInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return guideList; 
                
    }
    
    public void Show_Products_In_JTable()
    {
        ArrayList<Guide> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
      
          
            
            model.addRow(row);
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
        public Connection getConnection(){
            Connection con = null;
            try{
                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/museum","root","");  
                 JOptionPane.showMessageDialog(null, "Connected!");
            return con;
            }
            catch(SQLException ex){
                 Logger.getLogger(ShowInput.class.getName()).log(Level.SEVERE, null, ex);
                  JOptionPane.showMessageDialog(null, "Not Connected!");
                 return null;
            }
        }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
