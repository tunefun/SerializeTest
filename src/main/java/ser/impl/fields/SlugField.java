package ser.impl.fields;

import ser.Field;
import ser.ReflectUtils;

public class SlugField extends Field {
    private String fieldName;
    public SlugField(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object toRepresentation(Object in) {
        if(in == null){
            return null;
        }else{
            return ReflectUtils.getFieldValue(in, fieldName);
        }
    }
}
