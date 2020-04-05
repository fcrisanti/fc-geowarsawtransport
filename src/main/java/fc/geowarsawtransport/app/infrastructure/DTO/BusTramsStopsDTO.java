package fc.geowarsawtransport.app.infrastructure.DTO;

        import java.util.List;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.fasterxml.jackson.annotation.JsonPropertyOrder;
        import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "result"
})
@Getter
public class BusTramsStopsDTO {

    @JsonProperty("result")
    public List<Result> result;

}
