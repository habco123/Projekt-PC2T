
abstract class Filmy {
    private String film_name;
    private String director_name;
    private int release_year;
    
    private String[] actors;

    public Filmy(String film_name, String director_name, int release_year,  String[] actors){
        this.film_name = film_name;
        this.director_name = director_name;
        this.release_year = release_year;
        this.actors = actors;
        
    }
    public String getFilm_name(){
        return film_name;
    }

    public String getDirector_name(){
        return director_name;
    }
    public int getRelease_year(){
        return release_year;
    }
    
    
    public String[] getActors(){
        return actors;
    }
    public void setFilm_name(String name){
        this.film_name = name;
    }
    public void setDirector_name(String name){
        this.director_name = name;
    }
    public void setRelease_year(int year){
        this.release_year = year;
    }
    
    public void setActors(String[] actors){
        this.actors = actors;
    }

}
