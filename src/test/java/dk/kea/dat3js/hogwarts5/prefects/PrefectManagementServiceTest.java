package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.common.Gender;
import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrefectManagementServiceTest {

    @BeforeEach
    void setUp() {
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        House slytherin = new House("Slytherin", "Salazar Slytherin", new String[]{"green", "silver"});
        House hufflepuff = new House("Hufflepuff", "Helga Hufflepuff", new String[]{"yellow", "black"});
        House ravenclaw = new House("Ravenclaw", "Rowena Ravenclaw", new String[]{"blue", "bronze"});

        Student harry = new Student("Harry", "James", "Potter", gryffindor, 5, true, Gender.MALE);
        Student hermione = new Student("Hermione", "Jean", "Granger", gryffindor, 5, false, Gender.FEMALE);
        Student ron = new Student("Ron", "Bilius", "Weasley", gryffindor, 5, false, Gender.MALE);
        Student neville = new Student("Neville", "Frank", "Longbottom", gryffindor, 6, true, Gender.MALE);
        Student ginny = new Student("Ginny", "Molly", "Weasley", gryffindor, 4, false, Gender.FEMALE);
        Student fred = new Student("Fred", "Gideon", "Weasley", gryffindor, 2, false, Gender.MALE);
        Student george = new Student("George", "Fabian", "Weasley", gryffindor, 6, true, Gender.MALE);
        Student percy = new Student("Percy", "Ignatius", "Weasley", gryffindor, 5, false, Gender.MALE);

        Student draco = new Student("Draco", "Malfoy", slytherin, 5, false, Gender.MALE);
        Student cedric = new Student("Cedric", "Diggory", hufflepuff, 6, false, Gender.MALE);
        Student luna = new Student("Luna", "Lovegood", ravenclaw, 4, false, Gender.FEMALE);
        Student cho = new Student("Cho", "Chang", ravenclaw, 5, true, Gender.FEMALE);
        Student padma = new Student("Padma", "Patil", ravenclaw, 5, true, Gender.FEMALE);

        List<Student> students = new ArrayList<>(List.of(harry, hermione, ron, neville, ginny, fred, george, percy, draco, cedric, luna, cho, padma));
    }

    @Test
    void shouldNotBePrefectSchoolYearUnder5() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student fred = new Student("Fred", "Gideon", "Weasley", gryffindor, 2, false, Gender.MALE);

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

        // Act
        PrefectManagementService prefectManagementService = new PrefectManagementService(studentRepository);
        boolean result = prefectManagementService.canBePrefect(fred);


        // Assert
        assertFalse(result);
    }

    @Test
    void shouldBePrefectSchoolYearOver5() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student fred = new Student("Fred", "Gideon", "Weasley", gryffindor, 5, false, Gender.MALE);

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

        // Act
        PrefectManagementService prefectManagementService = new PrefectManagementService(studentRepository);
        boolean result = prefectManagementService.canBePrefect(fred);


        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotBePrefectAlreadyTwoPrefects() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student harry = new Student("Harry", "James", "Potter", gryffindor, 5, true, Gender.MALE);
        Student hermione = new Student("Hermione", "Jean", "Granger", gryffindor, 5, true, Gender.FEMALE);
        Student ron = new Student("Ron", "Bilius", "Weasley", gryffindor, 5, false, Gender.MALE);

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        PrefectManagementService prefectManagementService = new PrefectManagementService(studentRepository);
        when(studentRepository.findByHouseNameAndAndPrefectTrue(gryffindor.getName())).thenReturn(List.of(harry, hermione));

        // Act
        boolean result = prefectManagementService.canBePrefect(ron);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldNotBePrefectAlreadyOnePrefectPerGender() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student harry = new Student("Harry", "James", "Potter", gryffindor, 5, true, Gender.MALE);
        Student ron = new Student("Ron", "Bilius", "Weasley", gryffindor, 5, false, Gender.MALE);

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        PrefectManagementService prefectManagementService = new PrefectManagementService(studentRepository);
        when(studentRepository.findByHouseNameAndAndPrefectTrue(gryffindor.getName())).thenReturn(List.of(harry));

        // Act
        boolean result = prefectManagementService.canBePrefect(ron);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldBePrefectNoPrefectOfGender() {
        // Arrange
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"});
        Student hermione = new Student("Hermione", "Jean", "Granger", gryffindor, 5, true, Gender.FEMALE);
        Student ron = new Student("Ron", "Bilius", "Weasley", gryffindor, 5, false, Gender.MALE);

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

        // Act
        PrefectManagementService prefectManagementService = new PrefectManagementService(studentRepository);
        boolean result = prefectManagementService.canBePrefect(ron);

        // Assert
        assertTrue(result);
    }
}