package fc.geowarsawtransport.app.domain;

import fc.geowarsawtransport.app.domain.btstop.BusTramStop;
import fc.geowarsawtransport.app.infrastructure.BusTramStopRepository;
import fc.geowarsawtransport.app.infrastructure.DTO.Result;
import fc.geowarsawtransport.app.infrastructure.DTO.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusTramStopOperator {

    private final BusTramStopRepository busTramStopRepository;
    private final GeoRetrievalClient geoRetrievalClient;

    public List<String> btStopLines(String btStopName) {
        List<BusTramStop> busTramStops = busTramStopRepository.findAllByName(btStopName);
        List<Result> valueList = new ArrayList<>();
        for (BusTramStop busTramStop : busTramStops) {
            valueList.addAll(geoRetrievalClient.getStopLinesByZespolAndSlupek(busTramStop.getZespol(), busTramStop.getSlupek()));
        }
        List<String> busTramLines = valueList.stream()
                .map(Result::getValues)
                .flatMap(Collection::stream)
                .map(Value::getValue)
                .collect(Collectors.toList());
        return busTramLines;
    }


}
