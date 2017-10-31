package web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class DogController {

    // key는 dog의 이름, value는 Dog객체
    private Map<String, Dog> dogMap = new HashMap<String, Dog>();

    @RequestMapping(value = "dogs/{name}", method = RequestMethod.GET)
    public Dog selectDogByName(@PathVariable String name) {
        log.info("HaHaHa {}", name);
        return dogMap.get(name);
    }

    @RequestMapping(value = "dogs", method = RequestMethod.POST)
    public void createDog(@RequestBody Dog dog) {
        dogMap.put(dog.getName(), dog);
    }

    @RequestMapping(value = "dogs/count", method = RequestMethod.GET)
    public int selectTotalDogCount() {
        return dogMap.size();
    }

    @RequestMapping(value = "dogs/{name}", method = RequestMethod.PUT)
    public Dog updateDogType(@PathVariable String name, @RequestParam String type) {
        Dog dog = dogMap.get(name);
        dog.setType(type);
        return dog;
    }

    @RequestMapping(value = "dogs/{name}", method = RequestMethod.DELETE)
    public Dog deleteDog(@PathVariable String name) {
        if (dogMap.containsKey(name)) {
            return dogMap.remove(name);
        }
        throw new RuntimeException("no dog");
    }
}
