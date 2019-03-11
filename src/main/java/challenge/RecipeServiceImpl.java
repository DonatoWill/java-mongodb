package challenge;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository recipeRepository;

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		Optional<Recipe> opRecipe = recipeRepository.findById(id);

		if(opRecipe.isPresent()) {
			Recipe foundRecipe = opRecipe.get();
			foundRecipe.setDescription(recipe.getDescription());
			foundRecipe.setTitle(recipe.getTitle());
			foundRecipe.setIngredients(recipe.getIngredients());
			recipeRepository.save(foundRecipe);
		}
	}

	@Override
	public void delete(String id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public Recipe get(String id) {
		return recipeRepository.findById(id).get();
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return recipeRepository.findByIngredients(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {

		BasicQuery query =
				new BasicQuery("{$regex : '" + search + "'}");


		List<Recipe> recipes = recipeRepository.findByTitleLikeOrDescriptionLike(search, search);

		return recipes;
	}

	@Override
	public void like(String id, String userId) {
		Recipe recipe = recipeRepository.findById(id).get();
		recipe.addLike(id);
		recipeRepository.save(recipe);


	}

	@Override
	public void unlike(String id, String userId) {
		Recipe recipe = recipeRepository.findById(id).get();
		recipe.removeLike(id);
		recipeRepository.save(recipe);

	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {

		Recipe recipe = recipeRepository.findById(id).get();
		comment.setId(new ObjectId().toString());
		recipe.addComments(comment);
		recipeRepository.save(recipe);

		return comment;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Recipe recipe = recipeRepository.findById(id).get();
		recipe.updateComments(comment);
		recipeRepository.save(recipe);
	}

	@Override
	public void deleteComment(String id, String commentId) {
		Recipe recipe = recipeRepository.findById(id).get();
		RecipeComment recipeComment = recipe.getComments().stream().filter(c -> c.getComment() == commentId).findFirst().get();
		recipe.removeComments(recipeComment);
		recipeRepository.save(recipe);

	}

}
