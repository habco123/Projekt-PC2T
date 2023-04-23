import java.sql.*;
import java.util.Arrays;

public class Databaza {
    private String meno;
    private Connection conn;
    public boolean connect(){
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:Filmy.db");
            System.out.println("Uspesne pripojene k databazi");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean disconnect(){
        try{
            conn.close();
            System.out.println("Uspesne odpojene z databazi");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS NormalneFilmy (" + "id integer PRIMARY KEY, " + "Film_name TEXT NOT NULL," + "Director_name TEXT NOT NULL," + "release_year int NOT NULL," + "actors TEXT NOT NULL" + ");";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
    }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void insertRecord(String meno, String director, int year, String actors){
        String sql = "INSERT INTO NormalneFilmy(Film_name,Director_name,release_year,actors) VALUES (?,?,?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, meno);
            pstmt.setString(2, director);
            pstmt.setInt(3, year);
            pstmt.setString(4, actors);
            pstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String getMeno(){
        return meno;
    }
    public Film getRecord(){
        Film film = null;
        String sql = "SELECT id,Film_name,Director_name,release_year,actors FROM NormalneFilmy";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //System.out.println(rs.getInt("id") + "\t" + rs.getString("Film_name") + "\t" + rs.getString("Director_name"));
                String film_name = rs.getString("film_name");
                String director = rs.getString("Director_name");
                int year = rs.getInt("release_year");
                String herci = rs.getString("actors");
                StringBuilder builder = new StringBuilder(herci);
                meno = film_name;
                builder.deleteCharAt(herci.length() - 1);
                builder = new StringBuilder(builder);
                builder.deleteCharAt(0);
                //System.out.println("Meno: " + film_name + " reziser: " + director + " rok vydania: " + year);
                String[] actorsArr = builder.toString().split(",");
                //System.out.println("Herci:" + Arrays.toString(actorsArr));
                film = new Film(film_name, director, year, actorsArr);
                System.out.println("Uspesne nahrane z databaze");
                return film;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return film;
    }
}
