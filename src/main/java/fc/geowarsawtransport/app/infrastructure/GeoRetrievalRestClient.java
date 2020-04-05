package fc.geowarsawtransport.app.infrastructure;

import fc.geowarsawtransport.app.domain.GeoRetrievalClient;
import fc.geowarsawtransport.app.infrastructure.DTO.BusTramsStopsDTO;
import fc.geowarsawtransport.app.infrastructure.DTO.LinesDTO;
import fc.geowarsawtransport.app.infrastructure.DTO.Result;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleResultDTO;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
class GeoRetrievalRestClient implements GeoRetrievalClient {

    private final RestTemplate restTemplate;
    private final UrlModel urlModel;

    @Override
    public List<VehicleDTO> getAllBus() {
        VehicleResultDTO result = restTemplate.getForObject(
                urlModel.generateAllBusUrl(), VehicleResultDTO.class);
        return result.getResult();
    }

    @Override
    public List<VehicleDTO> getAllTram() {
        VehicleResultDTO result = restTemplate.getForObject(
                urlModel.generateAllTramUrl(), VehicleResultDTO.class);
        return result.getResult();
    }

    @Override
    public List<VehicleDTO> getSingleBus(long lineNumber) {
        return restTemplate.getForObject(
                urlModel.generateSingleBusUrl(lineNumber), VehicleResultDTO.class).getResult();
    }

    @Override
    public List<VehicleDTO> getSingleTram(long lineNumber) {
        return restTemplate.getForObject(
                urlModel.generateSingleTramUrl(lineNumber), VehicleResultDTO.class).getResult();
    }

    @Override
    public List<Result> getAllBTStops() {
        return restTemplate.getForObject(
                urlModel.getBusTramStops(), BusTramsStopsDTO.class).getResult();
    }

    @Override
    public List<Result> getStopLinesByZespolAndSlupek(long zespol, long slupek) {
        return restTemplate.getForObject(
                urlModel.btStopLinesByZespolAndSlupek(zespol, slupek), LinesDTO.class).getResult();
    }
}


