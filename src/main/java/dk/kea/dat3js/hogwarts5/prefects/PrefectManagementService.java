package dk.kea.dat3js.hogwarts5.prefects;


import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class PrefectManagementService {

    private final StudentRepository studentRepository;

    public PrefectManagementService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean canBePrefect(Student student) {
        // TODO: Check for max two prefects per house, only one prefect per gender, candidates must be in year 5+

        student.setPrefect(true);
        studentRepository.save(student);
        return true;
    }

    public void createPrefect(Student student) {
        if (!canBePrefect(student)) {
            throw new IllegalArgumentException("Student cannot be a prefect");
        } else {
            student.setPrefect(true);
            studentRepository.save(student);
        }

    }

    public void removePrefect(Student student) {
        if (!student.isPrefect()) {
            throw new IllegalArgumentException("Student is not a prefect");
        } else {
            student.setPrefect(false);
            studentRepository.save(student);
        }
    }
}
