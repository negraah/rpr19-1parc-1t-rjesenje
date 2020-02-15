package ba.unsa.etf.rpr;

import java.time.LocalDateTime;
import java.util.Objects;

public class Dogadjaj implements Comparable {
    private String naziv;
    //private LocalDateTime datum;
    private LocalDateTime vrijemePocetka;
    private LocalDateTime vrijemeKraja;

    public Dogadjaj(String naziv, LocalDateTime vrijemePocetka, LocalDateTime vrijemeKraja) throws NeispravanFormatDogadjaja {
         if(vrijemePocetka.isAfter(vrijemeKraja)){
             throw new NeispravanFormatDogadjaja("Neispravan format početka i kraja dogadjaja");
         }
        this.naziv = naziv;
     //   this.datum = datum;
        this.vrijemePocetka = vrijemePocetka;
        this.vrijemeKraja = vrijemeKraja;
    }

    public Dogadjaj() {
    }


    public void setPocetak(LocalDateTime vrijeme) throws NeispravanFormatDogadjaja {
        if(vrijeme.isAfter(vrijemeKraja)){
            throw new NeispravanFormatDogadjaja("Neispravan format početka i kraja događaja");
        }
         vrijemePocetka=vrijeme;

    }

    public void setKraj(LocalDateTime vrijeme) throws NeispravanFormatDogadjaja {
        if(vrijeme.isBefore(vrijemePocetka)){
            throw new NeispravanFormatDogadjaja("Neispravan format početka i kraja događaja");
        }
        vrijemeKraja=vrijeme;
    }

    public LocalDateTime getPocetak() {
      return vrijemePocetka;

    }

    public LocalDateTime getKraj() {

    return vrijemeKraja;

    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dogadjaj dogadjaj = (Dogadjaj) o;
        return Objects.equals(naziv, dogadjaj.naziv) &&
                Objects.equals(vrijemePocetka, dogadjaj.vrijemePocetka) &&
                Objects.equals(vrijemeKraja, dogadjaj.vrijemeKraja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, vrijemePocetka, vrijemeKraja);
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof Dogadjaj){
            if((this instanceof DogadjajVisokogPrioriteta && !(o instanceof DogadjajVisokogPrioriteta)) ||
                    (this instanceof DogadjajSrednjegPrioriteta && o instanceof DogadjajNiskogPrioriteta)){
                return 1;
            }else if((o instanceof DogadjajVisokogPrioriteta && !(this instanceof DogadjajVisokogPrioriteta)) ||
                    (o instanceof DogadjajSrednjegPrioriteta && this instanceof DogadjajNiskogPrioriteta)){
                return -1;
            }else {
                return this.getNaziv().compareTo(((Dogadjaj) o).getNaziv());
            }
        } else {
            return 0;
        }
    }
}
