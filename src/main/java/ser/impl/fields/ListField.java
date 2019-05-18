package ser.impl.fields;

import ser.Field;
import ser.Serializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListField extends Field {

    private Field serializer;
    public ListField(Field serializer) {
        this.serializer = serializer;
    }

    public Object toRepresentation(Object in) {
        List ret = new ArrayList();
        if (in.getClass().isArray()){
            in = Arrays.asList((Object[])in);
        }
        if(in instanceof Iterable){
            for (Object each:(Iterable) in) {
                ret.add(serializer.toRepresentation(each));
            }
            return ret;
        }
        else {
            throw new RuntimeException("ListField can't serialize data isn't iterable");
        }
    }
}
