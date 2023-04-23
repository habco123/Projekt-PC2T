import java.sql.*;

public class Databaza {
    private String meno_normal;
    private String meno_animated;
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
    public boolean createTableNormal(){
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
    public boolean createTableAnimated(){
        String sql = "CREATE TABLE IF NOT EXISTS AnimovaneFilmy (" + "id integer PRIMARY KEY, " + "Film_name TEXT NOT NULL," + "Director_name TEXT NOT NULL," + "release_year int NOT NULL," + "actors TEXT NOT NULL," + "min_age int " +");";
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void insertRecordAnimated(String meno, String director, int year, String actors, int min_age){
        String sql = "INSERT INTO AnimovaneFilmy(Film_name,Director_name,release_year,actors,min_age) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, meno);
            pstmt.setString(2, director);
            pstmt.setInt(3, year);
            pstmt.setString(4, actors);
            pstmt.setInt(5, min_age);
            pstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void insertRecordNormal(String meno, String director, int year, String actors){
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
    public String getMenoNormal(){
        return meno_normal;
    }
    public String getMenoAnimated(){
        return meno_animated;
    }
    public AnimatedFilm getRecordAnimated(){
        AnimatedFilm film = null;
        String sql = "SELECT id,Film_name,Director_name,release_year,actors,min_age FROM AnimovaneFilmy";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String film_name = rs.getString("film_name");
                String herci = rs.getString("actors");
                this.meno_animated = film_name;
                StringBuilder builder = new StringBuilder(herci);
                builder.deleteCharAt(herci.length() - 1);
                builder = new StringBuilder(builder);
                builder.deleteCharAt(0);
                String[] actors = builder.toString().split(",");
                film = new AnimatedFilm(film_name, rs.getString("Director_name"), rs.getInt("release_year"), actors, rs.getInt("min_age"));
                return film;
            }
            System.out.println("Uspesne nahrane z databazy");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return film;
    }
    public Film getRecordNormal(){
        Film film = null;
        String sql = "SELECT id,Film_name,Director_name,release_year,actors FROM NormalneFilmy";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String film_name = rs.getString("film_name");
                String herci = rs.getString("actors");
                StringBuilder builder = new StringBuilder(herci);
                this.meno_normal = film_name;
                builder.deleteCharAt(herci.length() - 1);
                builder = new StringBuilder(builder);
                builder.deleteCharAt(0);
                String[] actorsArr = builder.toString().split(",");
                film = new Film(film_name, rs.getString("Director_name"), rs.getInt("release_year"), actorsArr);
                return film;
            }
            System.out.println("Uspesne nahrane z databaze");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return film;
    }
}
