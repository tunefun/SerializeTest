package hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ser.Field;
import ser.Serializer;
import ser.impl.fields.ListField;

@RestController
@RequestMapping("/task")
public class TaskController {
    private User user1 = new User(3, "bbbb");
    private Task task1 = new Task(1, "task1", user1, new String[]{"job1", "job2"});
    private User user2 = new User(5, "cccc");
    private Task task2 = new Task(1, "task2", user2, new String[]{"job5", "job6", "job7"});

    private Serializer detailSerializer = new TaskSerializer();
    private Field listSerializer = new ListField(new BasicTaskInfoSerializer());

    @RequestMapping(path = {"", "/"})
    public Field.SerializedData list(){
        return listSerializer.serialize(new Task[]{task1, task2});
    }


    @RequestMapping(path = {"/{id}", "/{id}/"})
    public Field.SerializedData getOne(@PathVariable int id){
        return detailSerializer.serialize(task1);
    }


}
