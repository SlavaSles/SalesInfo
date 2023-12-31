package dto.criteria;

public class Lastname extends Criteriya {
    private String lastName;

    public Lastname() {
    }

    public Lastname(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Lastname{" +
                "lastName='" + lastName + '\'' +
                '}';
    }
}
