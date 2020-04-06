package fc.geowarsawtransport.app.domain;

import fc.geowarsawtransport.app.domain.btstop.BusTramStop;
import fc.geowarsawtransport.app.domain.btstop.BusTramStopOperator;
import fc.geowarsawtransport.app.infrastructure.BusTramStopRepository;
import fc.geowarsawtransport.app.infrastructure.DTO.Result;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleFacade {

    private final BusTramStopRepository busTramStopRepository;
    private final GeoRetrievalClient geoRetrievalClient;
    private final GPSOperator gpsOperator;
    private final BusTramStopOperator busTramStopOperator;

    public List<VehicleDTO> getAllBuses() {
        return geoRetrievalClient.getAllBus();
    }

    public List<VehicleDTO> getAllTram() {
        return geoRetrievalClient.getAllTram();
    }

    public List<VehicleDTO> getSingleTram(long line) {
        return geoRetrievalClient.getSingleTram(line);
    }

    public List<VehicleDTO> getSingleBus(long line) {
        return geoRetrievalClient.getSingleBus(line);
    }

    public List<VehicleDTO> getSingleVehicle(String line) {
        return geoRetrievalClient.getSingleVehicle(line);
    }

    public List<VehicleDTO> getAllVehicleByBTStop(String btStopName) {
        return busTramStopOperator.btStopVehicles(btStopName);
    }

    public List<Result> getAndSaveAllBTStops() {
        busTramStopRepository.deleteAll();
        List<Result> busTramStops = geoRetrievalClient.getAllBTStops();
        for (Result busTramStop : busTramStops) {
             busTramStopRepository.save(BusTramStop.generate(busTramStop));
        }
        return busTramStops;
    }

    public BusTramStop closestBTStop(double x, double y) {
        return gpsOperator.findClosestBusStop(x,y);
    }

    public List<String> getBTStopLines(String name) {
        return busTramStopOperator.btStopLines(name);
    }





}
