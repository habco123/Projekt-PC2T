import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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
        System.out.println("9... zapisat film do suboru");
        System.out.println("10... hercovia ktory su viac ako 1 krat");
        System.out.println("11... Citat informacie o filme zo suboru");
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
                    System.out.println("-----------");
                    System.out.println(actors[1]);
                    System.out.println("-----------");

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
                    System.out.println("typ (animak je x/10 normalny je x/5): " + hladanyFilm.typFilmu());
                    System.out.println("Meno: " + hladanyFilm.getFilm_name());
                    System.out.println("Meno rezisera: " + hladanyFilm.getDirector_name());
                    System.out.println("Rok vydanaia: " + hladanyFilm.getRelease_year());
                    if(hladanyFilm instanceof AnimatedFilm){
                        System.out.println("Animatori: " + Arrays.toString(hladanyFilm.getActors()));
                    }else{
                        System.out.println("Herci: " + Arrays.toString(hladanyFilm.getActors()));
                    }
                    int num;
                    if(hladanyFilm instanceof AnimatedFilm){
                        num = 1;
                    }else{
                        num = 0;
                    }
                    hladanyFilm.getReviews(num);
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
                    //System.out.println("typ: " + film.typFilmu());
                    System.out.println("Meno: " + film.getFilm_name());
                    System.out.println("Meno rezisera: " + film.getDirector_name());
                    System.out.println("Rok vydania: " + film.getRelease_year());
                    if(film instanceof AnimatedFilm){
                        System.out.println("Animatori: " + Arrays.toString(film.getActors()));
                        AnimatedFilm animak = (AnimatedFilm) film;
                        System.out.println("Min vek divaka: " + animak.getMin());
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
            case 9:
                System.out.print("meno filmu ktory chces zapisat: ");
                String ulozit_film_name = sc.nextLine();
                System.out.print("Meno suboru da jakeho chcers zappisat: ");
                String file_name = sc.nextLine();
                Filmy ulozit_film = filmMap.get(ulozit_film_name);
                if(filmMap.containsKey(ulozit_film_name)){
                    try{
                        FileWriter myWriter = new FileWriter(file_name);
                        myWriter.write("Meno filmu: " + ulozit_film.getFilm_name() + "\n");
                        myWriter.write("Meno rezisera: " + ulozit_film.getDirector_name() + "\n");
                        myWriter.write("Rok vydania: " + ulozit_film.getRelease_year() + "\n");
                        if(ulozit_film instanceof AnimatedFilm){
                            myWriter.write("Animatori: " + Arrays.toString(ulozit_film.getActors()) + "\n");
                            AnimatedFilm animak = (AnimatedFilm) ulozit_film;
                            myWriter.write("Min vek divaka: " + animak.getMin() + "\n");
                        }else{
                            myWriter.write("Herci: " + Arrays.toString(ulozit_film.getActors()) + "\n");    
                        }
                        myWriter.close();
                        ulozit_film.zapisatReview(file_name);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                else 
                    System.out.println("hladany film neexistuje ");
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
                        System.out.println(entry.getKey() + " bol/a v: " + entry.getValue().size() + " filmoch");
                        for(String film : entry.getValue()){
                            System.out.println("- " + film);
                        }
                        herecNajdeny = true;
                    }
                }
                if(!herecNajdeny){
                    System.out.println("ziadny herec ktory bol vo viac ako 1 filme neni");
                }
                break;
            case 11:
                System.out.println("Zapis informacie do suboru vo formate:\nmeno:\nmeno rezisera:\nrok vydania:\nmena hercov/animatorov oddelene ciarkou\nmin vek divaka (ak ide o animak)");
                System.out.print("typ filmu:\n1... animak\n2... normalny film ");
                int film_type_in = sc.nextInt();
                sc.nextLine();
                System.out.print("Meno suboru: ");
                String file_name_in = sc.nextLine();
                try{
                    if(film_type_in == 1){
                        BufferedReader reader = new BufferedReader(new FileReader(file_name_in));
                        String name = reader.readLine();
                        String director_name = reader.readLine();
                        int release_year = Integer.parseInt(reader.readLine());
                        String[] actors = reader.readLine().split(",");
                        int min = Integer.parseInt(reader.readLine());
                        AnimatedFilm animak = new AnimatedFilm(name, director_name, release_year, actors, min);
                        filmMap.put(name, animak);
                        reader.close();
                        System.out.println("Animak bol uspesne zapisany zo suboru");
                    }else{
                        BufferedReader reader = new BufferedReader(new FileReader(file_name_in));
                        String name = reader.readLine();
                        String director_name = reader.readLine();
                        int release_year = Integer.parseInt(reader.readLine());
                        String[] actors = reader.readLine().split(",");
                        Film film = new Film(name, director_name, release_year, actors);
                        filmMap.put(name, film);
                        reader.close();
                        System.out.println("Film bol uspesne zapisany zo suboru");
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
                break;
            case 12:
                Databaza databaza = new Databaza();
                databaza.connect();
                databaza.createTableAnimated();
                databaza.createTableNormal();
                for(Filmy film : filmMap.values()){
                    if(film instanceof AnimatedFilm){
                        AnimatedFilm animak = (AnimatedFilm) film;
                        databaza.insertRecordAnimated(film.getFilm_name(), film.getDirector_name(), film.getRelease_year(), Arrays.toString(film.getActors()), animak.getMin());
                    }else{
                        databaza.insertRecordNormal(film.getFilm_name(), film.getDirector_name(), film.getRelease_year(), Arrays.toString(film.getActors()) );
                    }
                }
                databaza.disconnect();
                break;
            case 13:
                Databaza databaza2 = new Databaza();
                databaza2.connect();
                for(int i = 1; i <= databaza2.getVelkostNormal(); i++){
                    Film film = databaza2.getRecordNormal(i);
                    String nameNormal = databaza2.getMenoNormal();
                    filmMap.put(nameNormal, film);
                }
                for(int i = 0; i <= databaza2.getVelkostAnimated(); i++){
                    AnimatedFilm animak = databaza2.getRecordAnimated(i);
                    String nameAnimated = databaza2.getMenoAnimated();
                    filmMap.put(nameAnimated, animak);
                }
                databaza2.disconnect();
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
