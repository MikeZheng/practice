import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.zrich.bean.Animal;
import com.zrich.bean.Size;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class JacksonTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Animal.class);
        InputStream file = JacksonTest.class.getResourceAsStream("animal.json");
        List<Animal> animals = objectMapper.readValue(file, collectionType);
        for (Animal animal : animals) {
            System.out.println(animal.getClass());
            System.out.println(animal);
        }
        System.out.println(objectMapper.writeValueAsString(Size.BIG));
    }

}

