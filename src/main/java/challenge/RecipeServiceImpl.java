package challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		if(recipeRepository.findById(id).isPresent())
			recipeRepository.save(recipe);
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
		return recipeRepository.findByIngredient(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {
		return null;
	}

	@Override
	public void like(String id, String userId) {

	}

	@Override
	public void unlike(String id, String userId) {

	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		return null;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {

	}

	@Override
	public void deleteComment(String id, String commentId) {

	}

}
