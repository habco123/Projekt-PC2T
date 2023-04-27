public class Film extends Filmy{

    public Film(String film_name, String director_name, int release_year, String[] actors){
        super(film_name, director_name, release_year, actors);
    }
    public String typFilmu(){
        return "Normalny film";
    }
}