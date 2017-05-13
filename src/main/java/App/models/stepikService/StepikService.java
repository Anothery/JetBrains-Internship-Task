package App.models.stepikService;

import App.models.stepikService.POJO.Response;


import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * StepikService используется для работы со Stepik api
 */

public class StepikService {
    private static StepikService instance = new StepikService();
    public static StepikService getInstance(){
        return instance;
    }

    private final String api_url;
    private RestTemplate restTemplate = new RestTemplate();

    private StepikService(){
        api_url = "https://stepik.org/api";
    }

    /**
     * getInfo метод посылает GET запрос к Stepik API и возвращает десериализованный результат
     */
    public Response getInfo(String id){
        try {
            String request_url = api_url + "/lessons/" + id;
            return restTemplate.getForObject(request_url, Response.class);
        }catch (HttpClientErrorException e) {
            return null;
        }

    }
}
