package challenge;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Classe para mapear a receita no MongoDB
 *
 */

@Document(collection = "recipe")
public class Recipe {

    @Id
    private String id;
    private String title;
    private String description;
    private List<String> likes;
    private List<String> ingredients;
    private List<RecipeComment> comments;

    public Recipe(){};




    public void addLike(String id){ this.likes.add(id);}

    public void removeLike(String id){ this.likes.remove(likes.indexOf(id));}

    public void addComments(RecipeComment comment){ this.comments.add(comment);}

    public void updateComments(RecipeComment comment){

        this.comments.get(comments.indexOf(comment)).setComment(comment.getComment());
    }

    public void removeComments(RecipeComment comment){
        this.comments.remove(comments.indexOf(comment));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeComment> getComments() {
        return comments;
    }

    public void setComments(List<RecipeComment> comments) {
        this.comments = comments;
    }
}
