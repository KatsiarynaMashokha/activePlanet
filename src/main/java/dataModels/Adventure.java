package dataModels;

/**
 * Created by Guest on 8/16/17.
 */
public class Adventure {
    private int destinationPoint; //required to instantiate Adventure class
    private int adventureId; //CREATED by database and given later
    private String category;
    private String title;
    private String description;
    private String duration;
    private String peak;
    private float rating;

    public Adventure(String category, String title, String description, String duration, String peak, int destinationPoint) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.peak = peak;
        this.destinationPoint = destinationPoint;
    }

    //GETTERS
    public int getDestinationPoint() {
        return destinationPoint;
    }

    public int getAdventureId() {
        return adventureId;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public String getPeak() {
        return peak;
    }

    public float getRating() {
        return rating;
    }

    //SETTERS
    public void setDestinationPoint(int destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public void setAdventureId(int adventureId) {
        this.adventureId = adventureId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPeak(String peak) {
        this.peak = peak;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adventure adventure = (Adventure) o;

        if (destinationPoint != adventure.destinationPoint) return false;
        if (adventureId != adventure.adventureId) return false;
        if (!category.equals(adventure.category)) return false;
        if (!title.equals(adventure.title)) return false;
        return description.equals(adventure.description);
    }

    @Override
    public int hashCode() {
        int result = destinationPoint;
        result = 31 * result + adventureId;
        result = 31 * result + category.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
