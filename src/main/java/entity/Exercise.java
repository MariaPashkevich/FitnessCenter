package entity;

public class Exercise {

    private int exerciseId;
    private String name;
    private String description;
    private String link;

    public Exercise(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Exercise() {
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        if (exerciseId != exercise.exerciseId) return false;
        if (name != null ? !name.equals(exercise.name) : exercise.name != null) return false;
        if (description != null ? !description.equals(exercise.description) : exercise.description != null)
            return false;
        return link != null ? link.equals(exercise.link) : exercise.link == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (exerciseId ^ (exerciseId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
