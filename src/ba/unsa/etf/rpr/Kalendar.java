package ba.unsa.etf.rpr;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Kalendar implements Pretrazivanje {
    private ArrayList<Dogadjaj> dogadjaji=new ArrayList<>();

    public Kalendar() {
    }

    public Kalendar(ArrayList<Dogadjaj> dogadjaji) {
        this.dogadjaji = dogadjaji;

    }

    public ArrayList<Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }

    public void setDogadjaji(ArrayList<Dogadjaj> dogadjaji) {
        this.dogadjaji = dogadjaji;
    }

    public void zakaziDogadjaj(Dogadjaj dogadjaj) {
        dogadjaji.add(dogadjaj);
    }

    public List<Dogadjaj> dajKalendar() {
        return dogadjaji;
    }

    public void zakaziDogadjaje(List<Dogadjaj> dogadjaji1) {
        for (Dogadjaj dogadjaj : dogadjaji1) {
            dogadjaji.add(dogadjaj);
        }

    }

    public void otkaziDogadjaj(Dogadjaj dogadjaj) {
        dogadjaji.remove(dogadjaj);
    }

    public void otkaziDogadjaje(List<Dogadjaj> dogadjaji1) {
        for (Dogadjaj dogadjaj : dogadjaji1) {
            dogadjaji.remove(dogadjaj);
        }
    }

    public void otkaziDogadjaje(Function<Dogadjaj, Boolean> f) {
        for (Dogadjaj dogadjaj : dogadjaji) {
            if(f.apply(dogadjaj)){
                dogadjaji.remove(dogadjaj);
            }
        }

    }

    public Map<LocalDate, List<Dogadjaj>> dajKalendarPoDanima() {

        Map<LocalDate, List<Dogadjaj>> mapa = new HashMap<>();

        for (Dogadjaj dogadjaj : dogadjaji) {
            if(mapa.containsKey(dogadjaj.getPocetak().toLocalDate())){
                mapa.get(dogadjaj.getPocetak().toLocalDate()).add(dogadjaj);
            }else{
                mapa.put(dogadjaj.getPocetak().toLocalDate(),new ArrayList<>());
                mapa.get(dogadjaj.getPocetak().toLocalDate()).add(dogadjaj);
            }
        }
        return mapa;
    }

    public Dogadjaj dajSljedeciDogadjaj(LocalDateTime datum) {
        if(dogadjaji.size()==0){
            throw new IllegalArgumentException("Nemate događaja nakon navedenog datuma");
        }
        Dogadjaj dogadjaj1 = null;

        for (Dogadjaj dogadjaj : dogadjaji) {

            if(dogadjaj.getPocetak().isAfter(datum)){
                if(dogadjaj1==null) {
                    dogadjaj1 = dogadjaj;
                }else{
                    if(dogadjaj.getPocetak().isBefore(dogadjaj1.getPocetak())){
                        dogadjaj1=dogadjaj;
                    }
                }
            }
        }

        if(dogadjaj1==null){
            throw new IllegalArgumentException("Nemate događaja nakon navedenog datuma");

        }
    return dogadjaj1;

    }

    @Override
    public String toString() {
        String s = "";
        for (Dogadjaj dogadjaj : dogadjaji) {
            if (s.equals("") == false) {
                s+="\n";
            }
            s+=dogadjaj;
        }


        return s;
    }


    @Override
    public List<Dogadjaj> filtrirajPoKriteriju(Predicate<Dogadjaj> f) {
        return dogadjaji.stream().filter(f).collect(Collectors.toList());
    }

    @Override
    public List<Dogadjaj> dajDogadjajeZaDan(LocalDateTime datum) {
        List<Dogadjaj> lista = new ArrayList<Dogadjaj>();
        for (Dogadjaj dogadjaj : dogadjaji) {
            if(dogadjaj.getPocetak().toLocalDate().equals(datum.toLocalDate())){
                lista.add(dogadjaj);
            }
        }
        return lista;
    }


    @Override
    public List<Dogadjaj> dajSortiraneDogadjaje() {
        List<Dogadjaj> lista = new ArrayList<>();
        dogadjaji.sort(new Comparator<Dogadjaj>() {
                           @Override
                           public int compare(Dogadjaj o1, Dogadjaj o2) {
                               LocalDateTime a = o1.getPocetak();
                               LocalDateTime b = o2.getPocetak();
                               return a.compareTo(b);
                           }
                       });
        return dogadjaji;
    }

    @Override
    public List<Dogadjaj> dajSortiraneDogadjaje(BiFunction<Dogadjaj, Dogadjaj, Integer> f) {
        List<Dogadjaj> lista = new ArrayList<>();
        dogadjaji.sort(new Comparator<Dogadjaj>() {
            @Override
            public int compare(Dogadjaj o1, Dogadjaj o2) {
                return f.apply(o1,o2);
            }
        });
        return dogadjaji;
    }

    @Override
    public Set<Dogadjaj> dajSortiranePoPrioritetu() {
        Set<Dogadjaj> set = new TreeSet<>();
        for (Dogadjaj dogadjaj : dogadjaji) {
            set.add(dogadjaj);
        }

        return set;
    }

    @Override
    public boolean daLiSamSlobodan(LocalDateTime datum) {
        for (Dogadjaj dogadjaj : dogadjaji) {
            if(dogadjaj.getPocetak().toLocalDate().isBefore(datum.toLocalDate()) &&
                    dogadjaj.getKraj().toLocalDate().isAfter(datum.toLocalDate())){
                return false;
            }else if(dogadjaj.getPocetak().toLocalDate().equals(datum.toLocalDate())
            || dogadjaj.getKraj().toLocalDate().equals(datum.toLocalDate())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean daLiSamSlobodan(LocalDateTime datum1, LocalDateTime datum2) {
        if(datum1.toLocalDate().isAfter(datum2.toLocalDate())){
            throw new  IllegalArgumentException("Neispravni podaci o početku i kraju");
        }

        for (Dogadjaj dogadjaj : dogadjaji) {

            if(!(dogadjaj.getKraj().toLocalDate().isBefore(datum1.toLocalDate()) ||
                    dogadjaj.getPocetak().toLocalDate().isAfter(datum2.toLocalDate()))) {
                return false;
            }
       /*     if(datum1.toLocalDate().isAfter(dogadjaj.getPocetak().toLocalDate())
                    && datum2.toLocalDate().isBefore(dogadjaj.getPocetak().toLocalDate())){
                return false;
            }else if(datum1.toLocalDate().isBefore(dogadjaj.getPocetak().toLocalDate())
                    && datum2.toLocalDate().isAfter(dogadjaj.getPocetak().toLocalDate())){
                return false;
            }else if(datum1.toLocalDate().isEqual(dogadjaj.getPocetak().toLocalDate()) ||
                    datum2.toLocalDate().isEqual(dogadjaj.getPocetak().toLocalDate())){

            }*/
        }
        return true;
    }
}
