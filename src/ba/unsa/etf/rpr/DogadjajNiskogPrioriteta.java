package ba.unsa.etf.rpr;

import java.time.LocalDateTime;

public class DogadjajNiskogPrioriteta extends Dogadjaj {

    public DogadjajNiskogPrioriteta(String naziv, LocalDateTime vrijemePocetka, LocalDateTime vrijemeKraja) throws NeispravanFormatDogadjaja {
        super(naziv, vrijemePocetka, vrijemeKraja);
    }

    public DogadjajNiskogPrioriteta() {
    }

    @Override
    public String toString() {
        return getNaziv()+" (nizak prioritet) - početak: " +
                getPocetak().getDayOfMonth()+"/"+getPocetak().getMonthValue()+"/"+getPocetak().getYear()+
                " ("+ getPocetak().getHour()+ ":"+getPocetak().getMinute() +"), kraj: "+
                getKraj().getDayOfMonth()+"/"+getKraj().getMonthValue()+"/"+getKraj().getYear()+
                " ("+ getKraj().getHour()+ ":"+getKraj().getMinute() +")";
    }
}
