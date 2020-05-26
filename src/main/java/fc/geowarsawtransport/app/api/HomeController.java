package fc.geowarsawtransport.app.api;

import fc.geowarsawtransport.app.domain.VehicleFacade;
import fc.geowarsawtransport.app.domain.btstop.BusTramStop;
import fc.geowarsawtransport.app.domain.btstop.BusTramStopOperator;
import fc.geowarsawtransport.app.domain.btstop.Stop;
import fc.geowarsawtransport.app.infrastructure.BusTramStopRepository;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
public class HomeController {

    private final VehicleFacade vehicleFacade;
    private final BusTramStopOperator busTramStopOperator;

    List<String[]> places = new ArrayList<>();

    @GetMapping(path = "/")
    public String vehiclePositions(@PathParam(value = "stop") String stop, Model model) {
        model.addAttribute("objList", places);
        List<String> btStopNamesList = busTramStopOperator.btStopNames();
        model.addAttribute("btStopNamesList",btStopNamesList);

        List<VehicleDTO> vehicles = vehicleFacade.getAllVehicleByBTStop(stop);
        places.clear();
        for (VehicleDTO vehicle : vehicles) {
            String[] position = new String[4];
            position[0] = vehicle.getLines();
            position[1] = String.valueOf(vehicle.getLat());
            position[2] = String.valueOf(vehicle.getLon());
            position[3] = vehicle.getLines();
            places.add(position);
        }
        return "index";
    }


    @GetMapping(path = "/lines/{name}")
    public ResponseEntity<List<VehicleDTO>> allLinesByBTStop(@PathVariable String name) {
        return ResponseEntity.ok(vehicleFacade.getAllVehicleByBTStop(name));
    }
}
