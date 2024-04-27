package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrefectService {

    private final StudentRepository studentRepository;
    private final PrefectManagementService prefectManagementService;

    public PrefectService(StudentRepository studentRepository, PrefectManagementService prefectManagementService) {
        this.studentRepository = studentRepository;
        this.prefectManagementService = prefectManagementService;
    }

    public List<Student> getAllPrefects() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .filter(Student::isPrefect)
                .toList();
    }


    public Student getPrefectById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No prefect with id " + id));

        if (!student.isPrefect()) {
            throw new IllegalArgumentException("Student with id " + id + " is not a prefect");
        } else {
            return student;
        }
    }

    public Student createPrefect(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No student with id " + id));

        if (student.isPrefect()) {
            throw new IllegalArgumentException("Student with id " + id + " is already a prefect");
        } else {
            // TODO: Check for max two prefects per house, only one prefect per gender, candidates must be in year 5+
            student.setPrefect(true);
            studentRepository.save(student);
            return student;
        }
    }

    public List<Student> getPrefectsByHouse(String house) {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .filter(student -> student.getHouse()
                        .getName()
                        .equalsIgnoreCase(house))
                .filter(Student::isPrefect)
                .toList();
    }

    public void deletePrefect(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No student with id " + id));

        prefectManagementService.removePrefect(student);
    }
}
