package api.models;

public class BookDetails {
    private Integer id;
    private String title;
    private String author;

    public BookDetails(String id, String title, String author) {
        if (id == null || id.trim().isBlank()) {
            this.id = null;
        } else {
            this.id =Integer.parseInt(id);
        }
        this.title = title;
        this.author = author;
    }

    // Getter for id
    public Integer getId() {
        return id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for author
    public String getAuthor() {
        return author;
    }

    // Setter for id
    public void setId(Integer id) {
        this.id = id;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
