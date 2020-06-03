package fc.geowarsawtransport.app.domain;

import fc.geowarsawtransport.app.domain.objects.BusTramStop;
import fc.geowarsawtransport.app.infrastructure.BusTramStopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
@RequiredArgsConstructor
class GPSOperator {

    private final BusTramStopRepository busTramStopRepository;

    public BusTramStop findClosestBusStop(double x1, double y1) {
        double distance = 0.0;
        Map<Double,BusTramStop> stopDistance = new TreeMap<>();
        List<BusTramStop> busTramStopList = busTramStopRepository.findAll();
        for (BusTramStop busTramStop : busTramStopList) {
            try {
                distance = Point2D.distance(x1, y1, busTramStop.getLon(), busTramStop.getLat());
                stopDistance.put(distance, busTramStop);
            } catch (RuntimeException exception) {
                continue;
            }
        }
        return stopDistance.entrySet().iterator().next().getValue();
    }
}
