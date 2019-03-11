package challenge;

import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String>{


    List<Recipe> findByIngredients(String ingredient);

    //List<Recipe> findByTitleLike(String query);

    List<Recipe> findByTitleLikeOrDescriptionLike(String searchTitle, String searchDescription);
}
