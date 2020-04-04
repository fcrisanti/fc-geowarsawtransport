package fc.geowarsawtransport.app.infrastructure.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "value",
        "key"
})
@Getter
public class Value {

    @JsonProperty("value")
    public String value;
    @JsonProperty("key")
    public String key;
}
