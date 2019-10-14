package pl.secrets.secrets.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "secret")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@Getter
@Setter
@ToString
public class Secret {
    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String title;

    private Date createdAt = new Date();

    public Secret() {
        super();
    }

    public Secret(String title) {
        this.title = title;
    }
}
