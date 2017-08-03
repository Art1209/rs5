package rs5.persistence.serializeTools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import rs5.persistence.entity.Role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RolesListSerializeModel extends JsonSerializer {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        List<Role> tempRoleList;
        List<String> roleNameList = new ArrayList<>();
        try{
            tempRoleList = (List<Role>) value;
            for (Role role:tempRoleList){
                roleNameList.add(role.getRole());
            }

        } catch (ClassCastException castexc){
            castexc.printStackTrace();
            gen.writeString("null");
            tempRoleList = null;
        }
        gen.writeString(roleNameList.toString());
    }
}