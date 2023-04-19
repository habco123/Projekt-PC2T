public class AnimatedFilm extends Filmy{
    private int recommended_age;
    private int review;
    private String stringReview;

    public AnimatedFilm(String film_name, String director_name, int release_year, String[] actors, int recommended_age){
        super(film_name, director_name, release_year, actors);
        this.recommended_age = recommended_age;
    }
    
    public int getReccomendedAge(){
        return recommended_age;
    }

    public void setReview(int review){
        this.review = review;
    }
    public void setReview_string(String stringReview){
        this.stringReview = stringReview;
    }

    public int getReview(){
        return review;
    }

    public String getReview_String(){
        return stringReview;
    }
}
//haha hihi