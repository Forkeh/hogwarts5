package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.students.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prefects")
public class PrefectController {

    private final PrefectService prefectService;

    public PrefectController(PrefectService prefectService) {
        this.prefectService = prefectService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllPrefects() {
        return ResponseEntity.ok(prefectService.getAllPrefects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getPrefectById(@PathVariable Integer id) {
        return ResponseEntity.ok(prefectService.getPrefectById(id));
    }

    @GetMapping("/house/{house}")
    public ResponseEntity<List<Student>> getPrefectsByHouse(@PathVariable String house) {
        return ResponseEntity.ok(prefectService.getPrefectsByHouse(house));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Student> createPrefect(@PathVariable Integer id) {
        return ResponseEntity.ok(prefectService.createPrefect(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deletePrefect(@PathVariable Integer id) {
        prefectService.deletePrefect(id);
        return ResponseEntity.noContent()
                .build();
    }
}
