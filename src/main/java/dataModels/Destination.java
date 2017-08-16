package dataModels;


public class Destination {
    private int id;
    private String location;

    public Destination(String location) {
        this.location = location;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Destination that = (Destination) o;

        if (id != that.id) return false;
        return location.equals(that.location);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + location.hashCode();
        return result;
    }
}
