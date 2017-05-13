package App.controllers;

import App.models.FrontEnd;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import java.util.concurrent.ExecutionException;


@RestController
public class LessonController {

    @Autowired
    private TaskExecutor taskExecutor;
    
    @RequestMapping(value = "/api")
    public DeferredResult<ResponseEntity> router(@RequestParam(value="lesson") String id)
            throws JsonProcessingException, ExecutionException {
        DeferredResult<ResponseEntity> result = new DeferredResult<>();
        taskExecutor.execute(new FrontEnd(result, id));
        return result;
    }
}


