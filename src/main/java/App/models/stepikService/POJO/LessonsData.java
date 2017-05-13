package App.models.stepikService.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;



@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonsData {

    private List<Integer> steps;

    private Date update_date;

    public List<Integer> getSteps() {
        return steps;
    }

    public Date getUpdate_date() {
        return update_date;
    }
}