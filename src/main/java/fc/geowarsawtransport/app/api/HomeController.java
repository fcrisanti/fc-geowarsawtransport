package fc.geowarsawtransport.app.api;

import fc.geowarsawtransport.app.domain.VehicleFacade;
import fc.geowarsawtransport.app.domain.objects.BusTramStop;
import fc.geowarsawtransport.app.domain.BusTramStopOperator;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
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
    public String vehiclePositions(@PathParam(value = "stop") String stop, @PathParam(value = "slupek") String slupek, HttpSession session, Model model) {
        model.addAttribute("objList", places);
        List<String> btStopNamesList = busTramStopOperator.btStopNames();
        model.addAttribute("btStopNamesList", btStopNamesList);
        List<Long> btSlupek = busTramStopOperator.btStopSlupek(stop);
        model.addAttribute("btSlupek", btSlupek);
        double viewLat = 52.230147;
        double viewLon = 21.010665;
        if (slupek != null) {
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
        } else if (stop != null) {
            List<BusTramStop> btStopSlupek = busTramStopOperator.btStopSlupekObjectList(stop);
            places.clear();
            for (BusTramStop busTramStop : btStopSlupek) {
                String[] position = new String[4];
                position[0] = String.valueOf(busTramStop.getSlupek());
                position[1] = String.valueOf(busTramStop.getLat());
                position[2] = String.valueOf(busTramStop.getLon());
                position[3] = String.valueOf(busTramStop.getSlupek());
                places.add(position);
            }
            viewLat = btStopSlupek.get(0).getLat();
            viewLon = btStopSlupek.get(0).getLon();
            session.setAttribute("viewLat", viewLat);
            session.setAttribute("viewLon", viewLon);
        } else {
            session.setAttribute("viewLat", viewLat);
            session.setAttribute("viewLon", viewLon);
        }
        return "index";
    }

    @GetMapping(path = "/lines/{name}")
    public ResponseEntity<List<VehicleDTO>> allLinesByBTStop(@PathVariable String name) {
        return ResponseEntity.ok(vehicleFacade.getAllVehicleByBTStop(name));
    }
}
