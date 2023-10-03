package dto.criteria;

public class Lastname extends Criteria {
    String lastName;

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
}
