import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class io{
    private String film_name;
    private String director_name;
    private int release_year;
    private String[] actors;
    private int min_age;
    private Map<Integer, String> reviews;

    io(String film_name, String director_name, int release_year, String[] actors){
        this.film_name = film_name;
        this.director_name = director_name;
        this.release_year = release_year;
        this.actors = actors;
    }
    public void getReview(int review, String reviw_string){
        reviews.put(review, reviw_string);
    }
    public void getActors(int min_age){
        this.min_age = min_age;
    }
    public void fileIN(){
        File myFile = new File("Films.txt");
        try{
            FileWriter myWriter = new FileWriter("Films.txt");
            myWriter.write("Meno filmu: " + film_name + "\n");
            myWriter.write("Meno rezisera: " + director_name + "\n");
            myWriter.write("Rok vydania: " + release_year + "\n");
            myWriter.write("Herci " + Arrays.toString(actors) + "\n");
            myWriter.write("-------------------");
            System.out.println("Uspesne ulozene");
            myWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}