package App.models.stepikService.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    private List<LessonsData> lessons;

    public List<LessonsData> getLessons() {
        return lessons;
    }

}

