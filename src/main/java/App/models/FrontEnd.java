package App.models;

import App.Cache.CacheService;
import App.models.stepikService.POJO.Response;
import App.models.stepikService.StepikService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * Класс FrontEnd обрабатывает запрос пользователя, где
 * id - идентификатор урока, полученный в параметре запроса,
 * result - аналог Callable в Spring.
 */

public class FrontEnd implements Runnable {
    private String id;
    private DeferredResult<ResponseEntity> result;

    public FrontEnd(DeferredResult<ResponseEntity> result, String id) {
        this.result = result;
        this.id = id;
    }

    /**
     * В новом потоке проверяем наличие массива в кэше
     * Если его нет, обращаемся к StepikService, получаем массив Stepik шагов и дату их обновления.
     * В кэш добавляются шаги (массивы шагов), обновленные в этом месяце.
     * (предполагается, что чем ближе update_date к текущей дате, тем они востребованнее)
     * Затем массив шагов передается в result и отдается контроллером пользователю.
     */
    @Override
    public void run() {
        try {
            String cached_steps = CacheService.getInstance().get(id);
            if (cached_steps == null) {

                Response json = StepikService.getInstance().getInfo(id);
                if (json == null) {
                    result.setResult(new ResponseEntity<>("[]", HttpStatus.NOT_FOUND));
                    return;
                }

                Date update_date = json.getLessons().get(0).getUpdate_date();
                String steps = (new ObjectMapper()).writeValueAsString(json.getLessons().get(0).getSteps());
                if (checkUpdateDate(update_date))
                    CacheService.getInstance().put(id, steps);

                result.setResult(new ResponseEntity<>(steps, HttpStatus.OK));

            } else result.setResult(new ResponseEntity<>(cached_steps, HttpStatus.OK));
        }
        catch (Exception e) {
            result.setResult(new ResponseEntity<>("[]", HttpStatus.NOT_FOUND));
        }
        }

    /**
     * Соответствие update_date текущей дате
     */
    private boolean checkUpdateDate(Date update_date) {
        LocalDate up_date = update_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDateTime.now().toLocalDate();

        return  (up_date.getMonth() == today.getMonth() &&
                 up_date.getYear() == today.getYear());
    }

}
