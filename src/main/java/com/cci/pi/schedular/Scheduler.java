package com.cci.pi.schedular;
import com.cci.pi.model.Task;
import com.cci.pi.repository.TaskRepository;
import com.cci.pi.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private final TaskRepository taskRepository;

    @Autowired
    public Scheduler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //This is scheduled to run every 10 min
    @Scheduled(fixedRate = 600000)
    public void updateTask() {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            if (task.getStatus().equals("pending")) {
                logger.info("pending task:" + task.getName());
                task.setStatus("Done");
                taskRepository.save(task);
            }
        }
    }
}
