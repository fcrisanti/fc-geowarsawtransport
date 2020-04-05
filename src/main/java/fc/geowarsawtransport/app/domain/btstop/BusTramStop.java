package fc.geowarsawtransport.app.domain.btstop;

import fc.geowarsawtransport.app.infrastructure.DTO.Result;
import fc.geowarsawtransport.app.infrastructure.DTO.Value;
import fc.geowarsawtransport.app.infrastructure.UrlModel;
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
    private String googleMapsUrl;

    public static BusTramStop generate(Result values) {
        List<Value> btProperties = values.getValues();
        BusTramStop busTramStop = new BusTramStop();

        for (Value btProperty : btProperties) {
            try {
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
            } catch (RuntimeException exception) {
                continue;
            }
            if(!(busTramStop.getLat()==null)&&!(busTramStop.getLon()==null)) {
                busTramStop.googleMapsUrl= UrlModel.makeGoogleMaps(busTramStop.getLon(),busTramStop.getLat());
            }
        }
        return busTramStop;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusTramStop{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", zespol=").append(zespol);
        sb.append(", slupek=").append(slupek);
        sb.append(", lat=").append(lat);
        sb.append(", lon=").append(lon);
        sb.append('}');
        return sb.toString();
    }
}

