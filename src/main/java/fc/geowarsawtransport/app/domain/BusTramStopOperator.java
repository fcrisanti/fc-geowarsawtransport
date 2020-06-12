package fc.geowarsawtransport.app.domain;

import fc.geowarsawtransport.app.domain.objects.BusTramStop;
import fc.geowarsawtransport.app.domain.objects.Timetable;
import fc.geowarsawtransport.app.infrastructure.BusTramStopRepository;
import fc.geowarsawtransport.app.infrastructure.DTO.Result;
import fc.geowarsawtransport.app.infrastructure.DTO.Value;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
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
                .distinct()
                .collect(Collectors.toList());
        return busTramLines;
    }

    public List<Timetable> departureTime(long slupek, String btStopName) throws Exception {
        List<String> lines = btStopLines(btStopName);
        List<Timetable> btStopTimetables = new ArrayList<>();
        long zespol = busTramStopRepository.findAllByName(btStopName).stream().findFirst().orElseThrow(Exception::new).getZespol();

        for (String line : lines) {
            Timetable timetable = new Timetable();
            List<Result> timetableData = geoRetrievalClient.getTimetableByLineByZespolAndSlupek(zespol, slupek, line);
            if (timetableData.isEmpty()) {
                continue;
            }
            timetable.setLine(line);
            timetable.setDirection(
                    timetableData.stream()
                            .map(Result::getValues)
                            .flatMap(Collection::stream)
                            .filter(t -> t.getKey().equals("kierunek"))
                            .map(Value::getValue)
                            .findAny()
                            .orElseGet(String::new)
            );
            timetable.setDepartureTimes(
                    timetableData.stream()
                            .map(Result::getValues)
                            .flatMap(Collection::stream)
                            .filter(t -> t.getKey().equals("czas"))
                            .map(Value::getValue)
                            .distinct()
                            .collect(Collectors.toList()));
            btStopTimetables.add(timetable);
        }
        return btStopTimetables;
    }

    public List<Long> btStopSlupek(String btStopName) {
        List<BusTramStop> busTramStops = busTramStopRepository.findAllByName(btStopName);
        return busTramStops.stream()
                .map(BusTramStop::getSlupek)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<BusTramStop> btStopSlupekObjectList(String btStopName) {
        return busTramStopRepository.findAllByName(btStopName);
    }

    public List<VehicleDTO> btStopVehicles(String btStopName) {
        List<String> lines = btStopLines(btStopName);
        List<VehicleDTO> btLinesVehicles = new ArrayList<>();
        for (String line : lines) {
            btLinesVehicles.addAll(geoRetrievalClient.getSingleVehicle(line));
        }
        return btLinesVehicles;
    }

    public List<String> btStopNames() {
        return busTramStopRepository.findAll().stream()
                .map(BusTramStop::getName)
                .distinct()
                .collect(Collectors.toList());
    }
}
