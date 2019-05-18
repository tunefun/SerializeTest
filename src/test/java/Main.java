import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ser.Field;
import ser.Serializer;
import ser.impl.FullSerializer;
import ser.impl.fields.ListField;


public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        User user1 = new User(3, "bbbb");
        Task task1 = new Task(1, "task1", user1, new String[]{"job1", "job2"});
        User user2 = new User(5, "cccc");
        Task task2 = new Task(1, "task2", user2, new String[]{"job5", "job6", "job7"});

        Serializer ser1 = new TaskSerializer();
        Object serialized = ser1.toRepresentation(task1);
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(serialized));

        Field ser2 = new ListField(new BasicTaskInfoSerializer());
        Object serData2 = ser2.toRepresentation(new Task[]{task1, task2});
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(serData2));
    }
}

