import ser.annotations.SerializeField;
import ser.impl.FieldExcludeSerializer;
import ser.impl.fields.SlugField;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskSerializer extends FieldExcludeSerializer {
    @SerializeField(name="createUserName")
    public SlugField createUser = new SlugField("name");

    @Override
    public List<String> fieldsExclude(){
        return Collections.singletonList("innerToken");
    }
}
