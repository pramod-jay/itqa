package api.models;

public class BookDetails {
    private int id;
    private String title;
    private String author;

    public BookDetails(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
