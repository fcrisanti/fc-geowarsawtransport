package fc.geowarsawtransport.app.domain.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Timetable {
    String line;
    String direction;
    List<String> departureTimes;
}
