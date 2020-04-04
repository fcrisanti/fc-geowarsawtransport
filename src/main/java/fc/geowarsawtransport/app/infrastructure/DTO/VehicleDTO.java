package fc.geowarsawtransport.app.infrastructure.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Lines",
        "Lon",
        "VehicleNumber",
        "Time",
        "Lat",
        "Brigade"
})
@Getter
public class VehicleDTO {

        @JsonProperty("Lines")
        public String lines;
        @JsonProperty("Lon")
        public Double lon;
        @JsonProperty("VehicleNumber")
        public String vehicleNumber;
        @JsonProperty("Time")
        public String time;
        @JsonProperty("Lat")
        public Double lat;
        @JsonProperty("Brigade")
        public String brigade;

    }
