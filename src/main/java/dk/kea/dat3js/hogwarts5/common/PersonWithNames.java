package dk.kea.dat3js.hogwarts5.common;

public interface PersonWithNames {
    String getFirstName();
    String getMiddleName();
    String getLastName();
    void setFirstName(String firstName);
    void setMiddleName(String middleName);
    void setLastName(String lastName);

    default String getFullName() {
        return getFirstName() + (getMiddleName() != null ? " " + getMiddleName() : "") + " " + getLastName();
    }

    default void setFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) return;

        int firstSpace = fullName.indexOf(' ');
        int secondSpace = fullName.lastIndexOf(' ');

        if (firstSpace == -1 || secondSpace == -1) {
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
            return;
        }

        int firstSpaceIndex = fullName.indexOf(' ');
        int lastSpaceIndex = fullName.lastIndexOf(' ');


        setFirstName((fullName.substring(0, firstSpaceIndex)));
        if (firstSpaceIndex == lastSpaceIndex) {
            setMiddleName(null);
            setLastName(fullName.substring(lastSpaceIndex + 1));
        } else {
            setMiddleName(fullName.substring(firstSpaceIndex + 1, lastSpaceIndex));
            setLastName(fullName.substring(lastSpaceIndex + 1));
        }
    }

    default String capitalize(String str) {
        if(str == null) return null;
        if (str.isEmpty()) return "";

        if(str.contains(" ")) {
            String[] words = str.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
            }
            return sb.toString().trim();
        }

        return (str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
    }
}
