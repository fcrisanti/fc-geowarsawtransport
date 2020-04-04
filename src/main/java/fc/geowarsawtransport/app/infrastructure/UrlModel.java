package fc.geowarsawtransport.app.infrastructure;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UrlModel {
//    @Value("${um.warszawa.pl.api.url}")
    private String baseUrl = "https://api.um.warszawa.pl/api/action/";
    private String busesAndTramSearch = "/busestrams_get";
    private String resourceIdName = "?resource_id=";
//    @Value("${bus.tram.live.resource.id}")
    private String resourceIdValue = "f2e5503e-927d-4ad3-9500-4ab9e55deb59";
    private String apiKeyName = "&apikey=";
//    @Value("${api.key}")
    private String apiKeyValue = "46657b26-69bc-489e-8985-e7e2c0422f72";
    private String typeName="&type=";
    private String typeBusValue="1";
    private String typeTramValue="2";
    private String lineParamName="&line=";
    private String busTramStops="https://api.um.warszawa.pl/api/action/dbstore_get/?id=ab75c33d-3a26-4342-b36a-6e5fef0a3ac3&sortBy=id&apikey=46657b26-69bc-489e-8985-e7e2c0422f72";

    public String getBusTramStops() {
        return busTramStops;
    }

    StringBuilder builder = new StringBuilder();

    public String generateAllBusUrl() {
        return builder
                .append(baseUrl)
                .append(busesAndTramSearch)
                .append(resourceIdName)
                .append(resourceIdValue)
                .append(apiKeyName)
                .append(apiKeyValue)
                .append(typeName)
                .append(typeBusValue)
                .toString();

    }

    public String generateAllTramUrl() {
        return builder
                .append(baseUrl)
                .append(busesAndTramSearch)
                .append(resourceIdName)
                .append(resourceIdValue)
                .append(apiKeyName)
                .append(apiKeyValue)
                .append(typeName)
                .append(typeTramValue)
                .toString();
    }

    public String generateSingleTramUrl(long lineNumber) {
        return builder
                .append(baseUrl)
                .append(busesAndTramSearch)
                .append(resourceIdName)
                .append(resourceIdValue)
                .append(apiKeyName)
                .append(apiKeyValue)
                .append(typeName)
                .append(typeTramValue)
                .append(lineParamName)
                .append(lineNumber)
                .toString();
    }

    public String generateSingleBusUrl(long lineNumber) {
        return builder
                .append(baseUrl)
                .append(busesAndTramSearch)
                .append(resourceIdName)
                .append(resourceIdValue)
                .append(apiKeyName)
                .append(apiKeyValue)
                .append(typeName)
                .append(typeBusValue)
                .append(lineParamName)
                .append(lineNumber)
                .toString();
    }


}
