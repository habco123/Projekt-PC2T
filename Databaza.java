import java.sql.*;


public class Databaza {
    private Connection conn;
    public boolean connect(){
        conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:myDB.db");
            System.out.println("Uspesne vytvorene pripojenie");
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void disconnect(){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean createTable(){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS zamestnanci (" + "id integer PRIMARY KEY," + "jmeno varchar(255) NOT NULL,"+ "rodneCislo bigint, " + "popis varchar(50), " + "plat real" + ");";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Nova tabulka uspesne vytvorena");
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void insertRecord(String meno, long RC, String popis, float plat){
        String sql = "INSERT INTO zamestnanci(jmeno,rodneCislo,popis,plat) VALUES(?,?,?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, meno);
            pstmt.setLong(2, RC);
            pstmt.setString(3, popis);
            pstmt.setFloat(4, plat);
            pstmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void selectAll(){
        String sql = "SELECT id, jmeno,rodneCislo, popis, plat FROM zamestnanci";
        try {
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
             while (rs.next()) {
                	System.out.println(rs.getInt("id") +  "\t" +  
rs.getString("jmeno") + "\t" + 
rs.getLong("rodneCislo") + "\t" + 
rs.getString("popis") + "\t" + 
rs.getLong("plat"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }   
    public void getRecord(String jmeno){
        String sql = "SELECT id, popis, plat FROM zamestnanci where jmeno=?";
        try {
              PreparedStatement pstmt  = conn.prepareStatement(sql);
              pstmt.setString(1,jmeno);
              ResultSet rs  = pstmt.executeQuery();
   
             while (rs.next()) {
                  System.out.println(rs.getInt("id") +  "\t" + 
                               rs.getString("popis") + "\t" +
                               rs.getDouble("plat"));
             }
        } 
        catch (SQLException e) {
              System.out.println(e.getMessage());
        }
   }
   public void update(int id, String popis, double plat) {
    String sql = "UPDATE zamestnanci SET plat = ? , "
            + "popis = ? "
            + "WHERE id = ?";

    try {
            PreparedStatement pstmt = conn.prepareStatement(sql);             
            pstmt.setDouble(1, plat);
            pstmt.setString(2, popis);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
    } 
    catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
public void delete(int id) {
    String sql = "DELETE FROM zamestnanci WHERE id = ?";

    try {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        // execute the delete statement
        pstmt.executeUpdate();

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
}
