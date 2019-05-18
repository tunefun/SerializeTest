package ser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectUtils {
    public static Object getValue(Object object, Field field){
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }
    public static Object getFieldValue(Object object, String name) {
        Field field = getField(object.getClass(), name);
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }
    public static Field getField(Class clazz, String name)  {
        if (clazz.equals(Object.class)){
            throw new NoSuchFieldError();
        }
        else{
            try {
                return clazz.getDeclaredField(name);
            }catch (java.lang.NoSuchFieldException e) {
                return getField(clazz.getSuperclass(), name);
            }
        }
    }
    public static Set<String> listFieldNames(Class clazz){
        Set<String> fields = new HashSet<String>();
        _listFieldNames(fields, clazz);
        return fields;
    }
    private static void _listFieldNames(Set<String> fields, Class clazz){
        if (!clazz.equals(Object.class)){
            for (Field field: clazz.getDeclaredFields()) {
                fields.add(field.getName());
            }
            _listFieldNames(fields, clazz.getSuperclass());
        }
    }
    public static Collection<Field> listFieldByType(Class clazz, Class type){
        Map<String, Field> fields = new HashMap<>();
        _listFieldByType(fields, clazz, type);
        return fields.values();
    }
    private static void _listFieldByType(Map<String, Field> fields, Class clazz, Class type){
        if (!clazz.equals(Object.class)){
            for (Field field: clazz.getDeclaredFields()) {
                if(type.isAssignableFrom(field.getType())){
                    fields.putIfAbsent(field.getName(), field);
                }
            }
            _listFieldByType(fields, clazz.getSuperclass(), type);
        }
    }
    public static Collection<Method> listMethodByAnnotation(Class clazz, Class anno){
        Map<String, Method> map = new HashMap<>();
        _listMethodByAnnotation(map, clazz, anno);
        return map.values();
    }
    private static void _listMethodByAnnotation(Map<String, Method> map, Class clazz, Class anno){
        if (!clazz.equals(Object.class)){
            for (Method method: clazz.getDeclaredMethods()){
                String parametsId = "";
                Class[] parameters = method.getParameterTypes();
                for(int i =0; i < parameters.length; i ++){
                    parametsId += parameters[i].toString();
                    if(i != parameters.length - 1){
                        parametsId += ",";
                    }
                }
                String methodId = method.getName() + " " + parametsId;
                if(!map.containsKey(methodId)){
                    if(method.getAnnotation(anno) != null){
                        map.put(methodId, method);
                    }
                }
            }
            _listMethodByAnnotation(map, clazz.getSuperclass(), anno);
        }

    }
}
