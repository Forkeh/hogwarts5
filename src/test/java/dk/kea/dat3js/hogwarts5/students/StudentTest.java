package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
        // Arrange
        Student student = new Student("Harry", "James", "Potter", null, 1, false);

        // Act
        String fullName = student.getFullName();

        // Assert
        assertEquals("Harry James Potter", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // Arrange
        Student student = new Student("Cho", null, "Chang", null, 1, false);

        // Act
        String fullName = student.getFullName();

        // Assert
        assertEquals("Cho Chang", fullName);
    }

    @Test
    void setFullNameWithMiddleName() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 5, false);

        // Act
        student.setFullName("Ron Bilius Weasley");

        // Assert
        assertEquals("Ron", student.getFirstName());
        assertEquals("Bilius", student.getMiddleName());
        assertEquals("Weasley", student.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 5, false);

        // Act
        student.setFullName("Hermione Granger");

        // Assert
        assertEquals("Hermione", student.getFirstName());
        assertNull(student.getMiddleName());
        assertEquals("Granger", student.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 5, false);

        // Act
        student.setFullName("Albus Percival Wulfric Brian Dumbledore");

        // Assert
        assertEquals("Albus", student.getFirstName());
        assertEquals("Percival Wulfric Brian", student.getMiddleName());
        assertEquals("Dumbledore", student.getLastName());
    }

    @Test
    void setFullNameWithoutLastName() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 5, false);

        // Act
        student.setFullName("Severus");

        // Assert
        assertEquals("Severus", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithNull() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 5, false);

        // Act
        student.setFullName(null);

        // Assert
        assertEquals("first", student.getFirstName());
        assertEquals("middle", student.getMiddleName());
        assertEquals("last", student.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 5, false);

        // Act
        student.setFullName("");

        // Assert
        assertEquals("first", student.getFirstName());
        assertEquals("middle", student.getMiddleName());
        assertEquals("last", student.getLastName());
    }

    @Test
    void capitalizeIndividualNames() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 1, false);

        // Act
        student.setFirstName("harry");
        student.setMiddleName("james");
        student.setLastName("potter");

        // Assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void capitalizeNamesWithCrazyCapitalization() {
        // Arrange
        Student student = new Student("first", "middle", "last", null, 1, false);

        // Act
        student.setFirstName("hArRy");
        student.setMiddleName("jAmEs");
        student.setLastName("pOtTeR");

        // Assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }
}