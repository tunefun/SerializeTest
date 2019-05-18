package ser.impl;

import ser.Serializer;

public class FullSerializer extends Serializer {

    protected boolean toSerialize(String name) {
        return true;
    }
}
