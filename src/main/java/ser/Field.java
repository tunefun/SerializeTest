package ser;

public abstract class Field{
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Object toRepresentation(Object in);

    public SerializedData serialize(Object in){
        return new SerializedData(toRepresentation(in));
    }
    public static class SerializedData{
        private Object data;

        public SerializedData(Object data) {
            this.data = data;
        }

        public Object toMapOrList() {
            return data;
        }


        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
