import java.util.*;
//suckujes kokot ty slabko vyjebany :))))))))
public class App{
    
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    Map<String, Filmy> filmMap = new HashMap<String, Filmy>();


    while(true){
        System.out.println("1... pridat film");
        System.out.println("2... najst film podla nazvu");
        System.out.println("3... najst vsetky filmy v ktorych sa vyskytoval hladany herec: ");
        System.out.println("4... odstranit film/animak");
        System.out.println("5... Vypis celej databazi");
        System.out.println("6... upravit existujuci film/animak");
        System.out.println("7... pridat review");
        System.out.println("8... skoncenie programu");
        System.out.print("Co chces robit: ");
        int vyber  = sc.nextInt();
        sc.nextLine();

        switch(vyber){
            case 1:
                System.out.println("1... Animak\n2... Normalny film");
                System.out.print("Aky film chces pridat: ");
                int film_type = sc.nextInt();
                sc.nextLine();
                if(film_type == 2){
                    System.out.print("Meno: ");
                    String film_name = sc.nextLine();
                    System.out.print("Meno rezisera: ");
                    String director_name = sc.nextLine();
                    System.out.print("Rok vydania: ");
                    int release_year = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Mena hercov (rozdeleni ciarkou): ");
                    String[] actors = sc.nextLine().split(",");

                    Film film = new Film(film_name, director_name, release_year, actors);
                    filmMap.put(film_name, film);
                    System.out.println("Film bol uspesne pridany");
                }else{
                    System.out.print("Meno: ");
                    String film_name = sc.nextLine();
                    System.out.print("Meno rezisera: ");
                    String director_name = sc.nextLine();
                    System.out.print("Rok vydania: ");
                    int release_year = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Mena animatorov (rozdeleni ciarkou): ");
                    String[] actors = sc.nextLine().split(",");
                    System.out.print("Vekove obmedzenie: ");
                    int min_age = sc.nextInt();
                    
                    AnimatedFilm film = new AnimatedFilm(film_name, director_name, release_year, actors, min_age);
                    filmMap.put(film_name, film);
                    System.out.println("Animak bol uspesne pridany");
                }
                break;
            case 2:
                System.out.print("Meno film/animaku ktory chces vyhladat: ");
                String hladanyFilm_meno = sc.nextLine();
                if(filmMap.containsKey(hladanyFilm_meno)){
                    Filmy hladanyFilm = filmMap.get(hladanyFilm_meno);
                    System.out.println("---------------------");
                    
                    System.out.println("Meno: " + hladanyFilm.getFilm_name());
                    System.out.println("Meno rezisera: " + hladanyFilm.getDirector_name());
                    System.out.println("Rok vydanaia: " + hladanyFilm.getRelease_year());
                    System.out.println("Herci: " + Arrays.toString(hladanyFilm.getActors()));
                    hladanyFilm.getReviews();
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
                for(Filmy film_databaza : filmMap.values()){
                    String[] actorsArray = film_databaza.getActors();
                    if(Arrays.asList(actorsArray).contains(hladanyActor)){
                        System.out.println("---------------------");
                        System.out.println("Meno: " + film_databaza.getFilm_name());
                        System.out.println("Meno rezisera: " + film_databaza.getDirector_name());
                        System.out.println("Rok vydanaia: " + film_databaza.getRelease_year());
                        System.out.println("Herci: " + Arrays.toString(film_databaza.getActors()));
                        System.out.println("---------------------");
                        najdeny = true;
                    }
                }
                if(najdeny == false){
                    System.out.println("Dany herec neni v databaze");
                }
                break;
            case 4:
                System.out.print("Meno filmu/animaku ktory chces vymazat: ");
                String film_name = sc.nextLine();
                if(filmMap.containsKey(film_name)){
                    filmMap.remove(film_name);
                    System.out.println("Uspesne vymazany!");
                }
                else
                    System.out.println("Film nebol najdeny (asi zle zadane meno)");
                break;
            case 5:
                for(Filmy film : filmMap.values()){
                    System.out.println("---------------------");
                    System.out.println("Meno: " + film.getFilm_name());
                    System.out.println("Meno rezisera: " + film.getDirector_name());
                    System.out.println("Rok vydania: " + film.getRelease_year());
                    if(film instanceof AnimatedFilm){
                        System.out.println("Animatori: " + Arrays.toString(film.getActors()));
                        //jak dostat min vek? 
                    }else{
                        System.out.println("Herci: " + Arrays.toString(film.getActors()));
                    }
                    System.out.println("---------------------");
                    
                }
                break;
            case 6:
                System.out.print("Meno filmu/animaku ktory chces upravit: ");
                String changeFilm_name = sc.nextLine();
                if(filmMap.containsKey(changeFilm_name)){
                    Filmy film = filmMap.get(changeFilm_name);
                    System.out.println("---------------------");
                    System.out.println("Momentalne informacie o filme: " + film.getFilm_name());
                    System.out.println("Meno rezisera: " + film.getDirector_name());
                    System.out.println("Rok vydania: " + film.getRelease_year());
                    System.out.println("Actors: " + Arrays.toString(film.getActors()));
                    System.out.println("---------------------");
                    System.out.print("Napis nove meno filmu (stlac enter ked ho chces nechat): ");
                    String newFilm_name = sc.nextLine();
                    System.out.print("Napis reziser filmu (stlac enter ked ho chces nechat): ");
                    String newDirector_name = sc.nextLine();
                    System.out.print("Novy rok vydania (stlac enter ked ho chces nechat): ");
                    int newRelease_year = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novy herci (oddeleny ciarkou)(stlac eneter pokial chces nechat): ");
                    String newActors = sc.nextLine();
                    if(!newFilm_name.isEmpty()){
                        film.setFilm_name(newFilm_name);
                    }
                    if(!newDirector_name.isEmpty()){
                        film.setDirector_name(newDirector_name);
                    }
                    if(newRelease_year != 0){
                        film.setRelease_year(newRelease_year);
                    }
                    if(!newActors.isEmpty()){
                         String[] actors = newActors.split(",");
                         film.setActors(actors);
                    }
                }else{
                    System.out.println("Hladany film neexituje!");
                }
                break;
            case 7:
                System.out.print("Meno filmu/animaku: ");
                String hladanyFilm = sc.nextLine();
                if(filmMap.containsKey(hladanyFilm)){
                    Filmy film = filmMap.get(hladanyFilm);
                    System.out.print("Ak sa jedna o animak ohodnot x/10 pri normalnom filme x/5: ");
                    int review_int = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Napis nejake slovne hodnotenie (ak nechces stal enter): ");
                    String review_string = sc.nextLine();
                    film.setReviews(review_int, review_string);
                }
                else{
                    System.out.println("Hladany film neexistuje (asi zle zadane meno)");
                }
                break;

            case 10:
                Map<String, List<String>> actorFilmsMap = new HashMap<>();
                for(Filmy film : filmMap.values()){
                    for(String actor : film.getActors()){
                        List<String> films = actorFilmsMap.getOrDefault(actor, new ArrayList<>());
                        films.add(film.getFilm_name());
                        actorFilmsMap.put(actor, films);
                    }
                }

                boolean herecNajdeny = false;
                for(Map.Entry<String, List<String>> entry : actorFilmsMap.entrySet()){
                    if(entry.getValue().size() > 1){
                        System.out.println(entry.getKey() + "bol v: " + entry.getValue().size() + "filmoch");
                        for(String film : entry.getValue()){
                            System.out.println("- " + film);
                        }
                        herecNajdeny = true;
                    }
                }
                if(!herecNajdeny){
                    System.out.println("ziadny herec ktory bol vo viac ako 1 filme nene");
                }
                break;
                
            case 8:
                System.out.println("Koniec programu");
                System.exit(0);
                sc.close();
                break;
        }
    }
    
    }
}