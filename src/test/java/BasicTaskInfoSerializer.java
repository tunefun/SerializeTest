import ser.annotations.SerializeField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasicTaskInfoSerializer extends TaskSerializer{


    @Override
    public List<String> fieldsExclude(){
        List<String> list = new ArrayList<>();
        list.addAll(super.fieldsExclude());
        list.add("jobs");
        return list;
    }

    @SerializeField(name="jobCount")
    int getJobCount(Task task){
        return task.getJobs().length;
    }
}
