package fc.geowarsawtransport.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import fc.geowarsawtransport.app.infrastructure.DTO.VehicleDTO;
import lombok.Builder;

@Builder
public class MapObject {

    public String name;
    public Double lon;
    public Double lat;
    public int index;

    public static MapObject generate(VehicleDTO vehicle) {
      return MapObject.builder()
                .name(vehicle.brigade)
                .lon(vehicle.lon)
                .lat(vehicle.lat)
                .index(1)
                .build();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MapObject{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", index=").append(index);
        sb.append('}');
        return sb.toString();
    }
}
