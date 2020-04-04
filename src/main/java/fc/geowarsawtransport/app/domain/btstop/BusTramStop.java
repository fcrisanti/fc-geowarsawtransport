package fc.geowarsawtransport.app.domain.btstop;

import fc.geowarsawtransport.app.infrastructure.DTO.StopsResultDTO;
import fc.geowarsawtransport.app.infrastructure.DTO.Value;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
@Setter(AccessLevel.PRIVATE)
@Getter
public class BusTramStop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "card_sequence")
    @SequenceGenerator(name = "card_sequence")
    private long id;

    private String name;
    private long zespol;
    private long slupek;
    private Double lat;
    private Double lon;

    public static BusTramStop generate(StopsResultDTO values) {
        List<Value> btProperties = values.getValues();
        BusTramStop busTramStop = new BusTramStop();

        for (Value btProperty : btProperties) {
            switch (btProperty.key) {
                case ("zespol"): {
                    busTramStop.setZespol(Long.parseLong(btProperty.value));
                    break;
                }
                case ("slupek"): {
                    busTramStop.setSlupek(Long.parseLong(btProperty.value));
                    break;
                }
                case ("szer_geo"): {
                    busTramStop.setLat(Double.parseDouble(btProperty.value));
                    break;
                }
                case ("dlug_geo"): {
                    busTramStop.setLon(Double.parseDouble(btProperty.value));
                    break;
                }
                case ("nazwa_zespolu"): {
                    busTramStop.setName(btProperty.value);
                    break;
                }
            }
        }
        return busTramStop;
    }
}
