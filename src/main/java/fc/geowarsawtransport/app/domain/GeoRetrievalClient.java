package fc.geowarsawtransport.app.domain;

import fc.geowarsawtransport.app.infrastructure.DTO.Result;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;

import java.util.List;

public interface GeoRetrievalClient {

    List<VehicleDTO> getAllBus();

    List<VehicleDTO> getAllTram();

    List<VehicleDTO> getSingleBus(long lineNumber);

    List<VehicleDTO> getSingleTram(long lineNumber);

    List<VehicleDTO> getSingleVehicle(String lineNumber);

    List<Result> getAllBTStops();

    List<Result> getStopLinesByZespolAndSlupek(long zespol, long slupek);
}
