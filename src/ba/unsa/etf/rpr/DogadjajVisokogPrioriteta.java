package ba.unsa.etf.rpr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DogadjajVisokogPrioriteta extends Dogadjaj {
    public DogadjajVisokogPrioriteta(String naziv, LocalDateTime vrijemePocetka, LocalDateTime vrijemeKraja) throws NeispravanFormatDogadjaja {
        super(naziv, vrijemePocetka, vrijemeKraja);
    }

    public DogadjajVisokogPrioriteta() {
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy (HH:mm)");
        return getNaziv()+" (visok prioritet) - početak: " + getPocetak().format(formatter)+
                ", kraj: "+getKraj().format(formatter);
    }
}
