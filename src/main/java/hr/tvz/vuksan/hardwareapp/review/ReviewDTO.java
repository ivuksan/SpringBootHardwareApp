package hr.tvz.vuksan.hardwareapp.review;

public class ReviewDTO {

    private final String title;
    private final String description;
    private final int rating;

    public ReviewDTO(String title, String description, int rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
