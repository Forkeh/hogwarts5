package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.common.Gender;

public record StudentRequestDTO(int id, String firstName, String middleName, String lastName, String name, String house,
                                Integer schoolYear, Boolean prefect, Gender gender) {
}
