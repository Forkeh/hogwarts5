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
        int lastSpace = fullName.lastIndexOf(' ');

        if (firstSpace == -1 || lastSpace == -1) {
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
            return;
        }

        setFirstName((fullName.substring(0, firstSpace)));
        if (firstSpace == lastSpace) {
            setMiddleName(null);
            setLastName(fullName.substring(lastSpace + 1));
        } else {
            setMiddleName(fullName.substring(firstSpace + 1, lastSpace));
            setLastName(fullName.substring(lastSpace + 1));
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
