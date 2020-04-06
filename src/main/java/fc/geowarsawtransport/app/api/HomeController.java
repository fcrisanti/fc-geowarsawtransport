package fc.geowarsawtransport.app.api;

import com.google.gson.Gson;
import fc.geowarsawtransport.app.domain.MapObject;
import fc.geowarsawtransport.app.domain.VehicleFacade;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
@ControllerAdvice
@RequiredArgsConstructor

public class HomeController {

    private final VehicleFacade vehicleFacade;


    @GetMapping(path = "/")
    public String showAllBuses(Model model) {
        return "index";
    }

//    @Async
//    public void refresh(Model model) {
//        Timer timer = new Timer();
//        TimerTask myTask = new TimerTask() {
//            @Override
//            public void run() {
//                model.addAttribute("refresh", vehiclePositions());
//            }
//        };
//        timer.schedule(myTask, 7000, 2000);
//    }

    @ModelAttribute("refresh")
    public List<String[]> vehiclePositions() {
        List<VehicleDTO> vehicles = vehicleFacade.getAllVehicleByBTStop("Centrum");
        List<String[]> places = new ArrayList<>();
        for (VehicleDTO vehicle : vehicles) {
            String[] position = new String[4];
            position[0] = vehicle.getLines();
            position[1] = String.valueOf(vehicle.getLat());
            position[2] = String.valueOf(vehicle.getLon());
            position[3] = vehicle.getLines();
            places.add(position);
        }
        return places;
    }

//    public ModelAndView update() {
//        ModelAndView model = new ModelAndView("index.html");
//        model.addAttribute("mapObjects", vehiclePositions());
//        return model;
//    }



    @GetMapping(path = "/lines/{name}")
    public ResponseEntity<List<VehicleDTO>> allLinesByBTStop(@PathVariable String name) {
        return ResponseEntity.ok(vehicleFacade.getAllVehicleByBTStop(name));
    }
}
