package ser.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ser.Field;

import java.io.IOException;

public class JsonSerializer {
    private ObjectMapper mapper;

    public JsonSerializer(){
        mapper = new ObjectMapper();
    }

//    public JsonSerializer(ObjectMapper mapper) {
//        this.mapper = mapper;
//    }

    public String serializeToString(Field.SerializedData serializedData) throws JsonProcessingException {
        return mapper.writeValueAsString(serializedData.toMapOrList());
    }
//
//    public void wirteValue(JsonGenerator generator, Field.SerializedData serializedData) throws IOException {
//        this.mapper.writer().writeValue(generator, serializedData.toMapOrList());
//    }
//
//    public void writeValue(ObjectWriter writer, JsonGenerator generator, Field.SerializedData serializedData) throws IOException {
//        writer.writeValue(generator, serializedData.toMapOrList());
//    }
}
