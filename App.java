import java.util.*;


public class App{
    
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    Map<String, Film> filmMap = new HashMap<String, Film>();


    while(true){
        System.out.println("1... pridat film");
        System.out.println("2... najst film podla nazvu");
        System.out.println("3... najst film podla nazvu herca");
        System.out.println("4... odstranit film");
        System.out.println("5... skoncenie programu");
        System.out.print("Co chces robit:");
        int vyber  = sc.nextInt();
        sc.nextLine();

        switch(vyber){
            case 1:
                System.out.print("Meno filmu: ");
                String film_name = sc.nextLine();
                System.out.print("Meno rezisera: ");
                String director_name = sc.nextLine();
                System.out.print("Rok vydania: ");
                int release_year = sc.nextInt();
                System.out.println("Review out of / 5 ");
                int review = sc.nextInt();
                sc.nextLine();
                System.out.println("Mena hercov (rozdeleni ciarkou): ");
                String[] actors = sc.nextLine().split(",");

                Film film = new Film(film_name, director_name, release_year, review, actors);
                filmMap.put(film_name, film);
                System.out.println("Film bol uspesne pridany");
                break;
            case 2:
                System.out.print("Meno filmu ktory chces vyhladat: ");
                String hladanyFilm_meno = sc.nextLine();
                if(filmMap.containsKey(hladanyFilm_meno)){
                    Film hladanyFilm = filmMap.get(hladanyFilm_meno);
                    System.out.println("---------------------");
                    System.out.println("Meno filmu: " + hladanyFilm.getFilm_name());
                    System.out.println("Meno rezisera: " + hladanyFilm.getDirector_name());
                    System.out.println("Rok vydanaia: " + hladanyFilm.getRelease_year());
                    System.out.println("Review (out of/5) : " + hladanyFilm.getReview());
                    System.out.println("Herci: " + Arrays.toString(hladanyFilm.getActors()));
                    System.out.println("---------------------");
                }
                else {
                    System.out.println("Hladany film neexistuje (asi zle zadane meno)");
                }
                break;
            case 3:
                boolean najdeny = false;
                System.out.println("Napis meno herca: ");
                String hladanyActor = sc.nextLine();
                for(Film film_databaza : filmMap.values()){
                    String[] actorsArray = film_databaza.getActors();
                    if(Arrays.asList(actorsArray).contains(hladanyActor)){
                        System.out.println("---------------------");
                        System.out.println("Meno filmu: " + film_databaza.getFilm_name());
                        System.out.println("Meno rezisera: " + film_databaza.getDirector_name());
                        System.out.println("Rok vydanaia: " + film_databaza.getRelease_year());
                        System.out.println("Review (out of/5) : " + film_databaza.getReview());
                        System.out.println("Herci: " + Arrays.toString(film_databaza.getActors()));
                        System.out.println("---------------------");
                        najdeny = true;
                    }
                }
                if(najdeny == false){
                    System.out.println("Dany herec neni v databaze");
                }
                break;
            case 5:
                System.out.println("Koniec programu");
                System.exit(0);
                sc.close();
                break;
        }
    }
    
    }
}