package ser.impl;

import ser.Serializer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class FieldIncludeSerializer extends Serializer {

    private Set<String> include = new HashSet<String>();
    public FieldIncludeSerializer(String... tempFields) {
        if (tempFields.length == 0){
            include.addAll(fieldsInclude());
        }else {
            include.addAll(Arrays.asList(tempFields));
        }
    }

    protected boolean toSerialize(String name) {
        return include.contains(name);
    }

    public abstract List<String> fieldsInclude();
}
