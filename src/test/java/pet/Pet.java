package pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private int id;
    private Categoria category;
    private String name;
    @Builder.Default
    private List<String> photoUrls = Arrays.asList();
    private String status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
 public static class Categoria{
     private int id;
     private String name;
 }
}
