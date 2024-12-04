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
}
