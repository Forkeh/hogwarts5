package dk.kea.dat3js.hogwarts5.teachers;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeacherTest {

    @Test
    void getFullNameWithMiddleName() {
        // Arrange
        Teacher teacher = new Teacher("Harry", "James","Potter", null, null, LocalDate.now());

        // Act
        String fullName = teacher.getFullName();

        // Assert
        assertEquals("Harry James Potter", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // Arrange
        Teacher teacher = new Teacher("Cho", null, "Chang", null, null, LocalDate.now());

        // Act
        String fullName = teacher.getFullName();

        // Assert
        assertEquals("Cho Chang", fullName);
    }

    @Test
    void setFullNameWithMiddleName() {
        // Arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, LocalDate.now());

        // Act
        teacher.setFullName("Ron Bilius Weasley");

        // Assert
        assertEquals("Ron", teacher.getFirstName());
        assertEquals("Bilius", teacher.getMiddleName());
        assertEquals("Weasley", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // Arrange
        Teacher teacher = new Teacher("first", null, "last", null, null, LocalDate.now());

        // Act
        teacher.setFullName("Hermione Granger");

        // Assert
        assertEquals("Hermione", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertEquals("Granger", teacher.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // Arrange
        Teacher teacher = new Teacher("first", "middle","last", null, null, LocalDate.now());

        // Act
        teacher.setFullName("Albus Percival Wulfric Brian Dumbledore");

        // Assert
        assertEquals("Albus", teacher.getFirstName());
        assertEquals("Percival Wulfric Brian", teacher.getMiddleName());
        assertEquals("Dumbledore", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutLastName() {
        // Arrange
        Teacher teacher = new Teacher("first", null, null, null, null, LocalDate.now());

        // Act
        teacher.setFullName("Severus");

        // Assert
        assertEquals("Severus", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void setFullNameWithNull() {
        // Arrange
        Teacher teacher = new Teacher("first", "middle","last",  null, null, LocalDate.now());

        // Act
        teacher.setFullName(null);

        // Assert
        assertEquals("first", teacher.getFirstName());
        assertEquals("middle", teacher.getMiddleName());
        assertEquals("last", teacher.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // Arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, LocalDate.now());

        // Act
        teacher.setFullName("");

        // Assert
        assertEquals("first", teacher.getFirstName());
        assertEquals("middle", teacher.getMiddleName());
        assertEquals("last", teacher.getLastName());
    }

    @Test
    void capitalizeIndividualNames() {
        // Arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, LocalDate.now());

        // Act
        teacher.setFirstName("harry");
        teacher.setMiddleName("james");
        teacher.setLastName("potter");

        // Assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());
    }

    @Test void capitalizeNamesWithCrazyCapitalization() {
        // Arrange
        Teacher teacher = new Teacher("first", "middle","last",  null, null, LocalDate.now());

        // Act
        teacher.setFirstName("hArRy");
        teacher.setMiddleName("jAmEs");
        teacher.setLastName("pOtTeR");

        // Assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());
    }
}