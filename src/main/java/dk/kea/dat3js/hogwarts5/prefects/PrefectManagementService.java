package dk.kea.dat3js.hogwarts5.prefects;


import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrefectManagementService {

    private final StudentRepository studentRepository;

    public PrefectManagementService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean canBePrefect(Student student) {
        // TODO: Check for max two prefects per house, only one prefect per gender, candidates must be in year 5+
        List<Student> currentPrefects = studentRepository.findByHouseNameAndAndPrefectTrue(student.getHouse()
                .getName());
        System.out.println(currentPrefects.toString());

        if (student.isPrefect()) throw new IllegalArgumentException("Student is already a prefect");

        if (student.getSchoolYear() < 5) return false;

        if (currentPrefects.size() >= 2) return false;

        for (Student currentStudent : currentPrefects) {
            if (currentStudent.getGender()
                    .equals(student.getGender())) return false;
        }
        return true;
    }

    public Student createPrefect(Student student) {
        if (!canBePrefect(student)) {
            throw new IllegalArgumentException("Student cannot be a prefect");
        } else {
            student.setPrefect(true);
            return studentRepository.save(student);
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
