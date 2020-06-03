package fc.geowarsawtransport.app.api;

import fc.geowarsawtransport.app.domain.VehicleFacade;
import fc.geowarsawtransport.app.domain.objects.BusTramStop;
import fc.geowarsawtransport.app.domain.objects.Timetable;
import fc.geowarsawtransport.app.infrastructure.DTO.Result;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/commands")
class VehicleController {

    private final VehicleFacade vehicleFacade;

    @GetMapping(path = "/bus")
    public ResponseEntity<List<VehicleDTO>> showAllBuses() {
        return ResponseEntity.ok(vehicleFacade.getAllBuses());
    }

    @GetMapping(path = "/tram")
    public ResponseEntity<List<VehicleDTO>> showAllTram() {
        return ResponseEntity.ok(vehicleFacade.getAllTram());
    }

    @GetMapping(path = "/tram/{line}")
    public ResponseEntity<List<VehicleDTO>> showSingleTram(@PathVariable long line) {
        return ResponseEntity.ok(vehicleFacade.getSingleTram(line));
    }

    @GetMapping(path = "/bus/{line}")
    public ResponseEntity<List<VehicleDTO>> showSingleBus(@PathVariable long line) {
        return ResponseEntity.ok(vehicleFacade.getSingleBus(line));
    }

    @GetMapping(path = "/stops")
    public ResponseEntity<List<Result>> updateBusTramStops() {
        return ResponseEntity.ok(vehicleFacade.getAndSaveAllBTStops());
    }

    @GetMapping(path = "/stops/{name}/{slupek}")
    public ResponseEntity<List<Timetable>> getDepartureTimes(@PathVariable long slupek, @PathVariable String name) throws Exception {
        return ResponseEntity.ok(vehicleFacade.getTimetable(slupek,name));
    }

    @GetMapping(path = "/closeststop")
    public ResponseEntity<BusTramStop> closestBTStop(double x, double y) {
        return ResponseEntity.ok(vehicleFacade.closestBTStop(x, y));
    }

    @GetMapping(path = "/lines")
    public ResponseEntity<List<String>> btStopLines(String btStopName) {
        return ResponseEntity.ok(vehicleFacade.getBTStopLines(btStopName));
    }

    @GetMapping(path = "/lines/{name}")
    public ResponseEntity<List<VehicleDTO>> allLinesByBTStop(@PathVariable String name) {
        return ResponseEntity.ok(vehicleFacade.getAllVehicleByBTStop(name));
    }
}
