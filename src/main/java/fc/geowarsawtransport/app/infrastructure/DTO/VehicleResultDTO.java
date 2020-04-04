package fc.geowarsawtransport.app.infrastructure.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "result"
})
@Getter
public class VehicleResultDTO {

    @JsonProperty("result")
    public List<VehicleDTO> result;

}

