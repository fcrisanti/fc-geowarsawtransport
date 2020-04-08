package fc.geowarsawtransport.app.api;

import fc.geowarsawtransport.app.domain.VehicleFacade;
import fc.geowarsawtransport.app.domain.btstop.Stop;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
@RequiredArgsConstructor
public class HomeController {

    private final VehicleFacade vehicleFacade;

    List<String[]> places = new ArrayList<>();

    @GetMapping(path = "/")
    public String showAllBuses(Model model) {
        Stop stop = new Stop();
        model.addAttribute("objList", places);
        model.addAttribute("stop", stop);
        return "index";
    }

    @PostMapping(path = "/stop")
    public String vehiclePositions(Stop stop, Model model) {
        List<VehicleDTO> vehicles = vehicleFacade.getAllVehicleByBTStop(stop.getName());
        places.clear();
        for (VehicleDTO vehicle : vehicles) {
            String[] position = new String[4];
            position[0] = vehicle.getLines();
            position[1] = String.valueOf(vehicle.getLat());
            position[2] = String.valueOf(vehicle.getLon());
            position[3] = vehicle.getLines();
            places.add(position);
        }
        return "redirect:/";
    }


    @GetMapping(path = "/lines/{name}")
    public ResponseEntity<List<VehicleDTO>> allLinesByBTStop(@PathVariable String name) {
        return ResponseEntity.ok(vehicleFacade.getAllVehicleByBTStop(name));
    }
}
