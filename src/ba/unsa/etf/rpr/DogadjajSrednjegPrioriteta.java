package ba.unsa.etf.rpr;

import java.time.LocalDateTime;

public class DogadjajSrednjegPrioriteta extends Dogadjaj {
    public DogadjajSrednjegPrioriteta(String naziv, LocalDateTime vrijemePocetka, LocalDateTime vrijemeKraja) throws NeispravanFormatDogadjaja {
        super(naziv, vrijemePocetka, vrijemeKraja);
    }

    public DogadjajSrednjegPrioriteta() {
    }
    @Override
    public String toString() {
        return getNaziv()+" (srednji prioritet) - početak: " +
                getPocetak().getDayOfMonth()+"/"+getPocetak().getMonthValue()+"/"+getPocetak().getYear()+
                " ("+ getPocetak().getHour()+ ":"+getPocetak().getMinute() +"), kraj: "+
                getKraj().getDayOfMonth()+"/"+getKraj().getMonthValue()+"/"+getKraj().getYear()+
                " ("+ getKraj().getHour()+ ":"+getKraj().getMinute() +")";
    }
}
