package ser;

import ser.annotations.SerializeField;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Serializer extends Field {
    Map<String, Field> convertMap;
    Map<String, Computer> computerMap;
    private boolean isInit = false;


    void init(){
        convertMap = collectFieldField();
        computerMap = generateComputeField();
        isInit = true;
    }
    private Map<String, Field> collectFieldField(){
        Map<String, Field> map = new HashMap<String, Field>();
        for (java.lang.reflect.Field field: ReflectUtils.listFieldByType(this.getClass(), Field.class)) {
            String name = field.getName();
            SerializeField anno = field.getAnnotation(SerializeField.class);
            Field f = (Field) ReflectUtils.getValue(this, field);
            if(anno != null){
                f.setName(anno.name());
            }
            else {
                f.setName(name);
            }
            map.put(name, f);
        }
        return map;
    }
    private Map<String, Computer> generateComputeField(){
        Map<String, Computer> map = new HashMap<String, Computer>();
        for(Method method: ReflectUtils.listMethodByAnnotation(this.getClass(), SerializeField.class)){
            String name = method.getAnnotation(SerializeField.class).name();
            map.put(name, new Computer(){

                @Override
                public Object compute(Object in) {
                    try {
                        method.setAccessible(true);
                        return method.invoke(Serializer.this, in);
                    } catch (IllegalAccessException e) {
                        throw new Error(e);
                    } catch (InvocationTargetException e) {
                        throw new Error(e);
                    }
                }
            });
        }
        return map;
    }

    @Override
    public Object toRepresentation(Object in) {
        if (!isInit){
            init();
        }
        if(in == null){
            return null;
        }
        Map<String, Object> result = new HashMap<String, Object>();
        for (String name: ReflectUtils.listFieldNames(in.getClass())) {
            if(toSerialize(name)){
                Object value = ReflectUtils.getFieldValue(in, name);
                if(convertMap.containsKey(name)){
                    Field field = convertMap.get(name);
                    value = field.toRepresentation(value);
                    name = field.getName();
                }
                result.put(name, value);
            }
        }
        for (String name: computerMap.keySet()) {
            if(toSerialize(name)){
                result.put(name, computerMap.get(name).compute(in));
            }
        }
        return result;
    }

    protected abstract boolean toSerialize(String name);

}
