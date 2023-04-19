public class AnimatedFilm extends Filmy{
    private int min;

    public AnimatedFilm(String film_name, String director_name, int release_year, String[] actors, int min){
        super(film_name, director_name, release_year, actors);
        this.min = min;
    }

    public int getMin(){
        return min;
    }
    public String typFilmu(){
        return "Animak";
    }
}
