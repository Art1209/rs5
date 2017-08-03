package rs5.persistence.serializeTools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import rs5.persistence.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersListSerializeModel extends JsonSerializer {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        List<User> tempUserList;
        List<String> userLoginList = new ArrayList<>();
        try{
            tempUserList = (List<User>) value;
            for (User user:tempUserList){
                userLoginList.add(user.getLogin());
            }

        } catch (ClassCastException castexc){
            castexc.printStackTrace();
            gen.writeString("null");
            tempUserList = null;
        }
        gen.writeString(userLoginList.toString());
    }
}

