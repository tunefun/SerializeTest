package ser.impl;

import ser.Serializer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class FieldExcludeSerializer extends Serializer {

    private Set<String> exclude = new HashSet<String>();
    public FieldExcludeSerializer(String... tempFields) {
        if (tempFields.length > 0){
            exclude.addAll(Arrays.asList(tempFields));
        }else {
            exclude.addAll(fieldsExclude());
        }
    }

    protected boolean toSerialize(String name) {
        return !exclude.contains(name);
    }
    public abstract List<String> fieldsExclude();
}
