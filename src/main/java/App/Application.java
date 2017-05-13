package App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Точка входа в приложение
 * И boot конфигурация
 */

@ComponentScan
@EnableAutoConfiguration
public class Application {

    private static final int CORE_POOL_SIZE = 150;
    private static final int MAX_POOL_SIZE = 150;
    private static final int QUEUE_CAPACITY = 300;

    @Bean(name = "taskExecutor")
    public TaskExecutor getExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        return taskExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
