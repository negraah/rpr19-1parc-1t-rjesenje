package ba.unsa.etf.rpr;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Pretrazivanje {
    public  List<Dogadjaj>filtrirajPoKriteriju(Predicate<Dogadjaj> f);
    public  List<Dogadjaj>dajDogadjajeZaDan(LocalDateTime datum);
    public List<Dogadjaj>dajSortiraneDogadjaje();
    public List<Dogadjaj>dajSortiraneDogadjaje(BiFunction<Dogadjaj,Dogadjaj,Integer> f);
    public Set<Dogadjaj> dajSortiranePoPrioritetu();
    public boolean daLiSamSlobodan(LocalDateTime datum);
    public boolean daLiSamSlobodan(LocalDateTime datum1, LocalDateTime datum2);


}
