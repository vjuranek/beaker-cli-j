package org.fedorahosted.beaker.util;

import java.util.ArrayList;
import java.util.List;

import org.fedorahosted.beaker.model.beakermodel.Job;
import org.fedorahosted.beaker.model.beakermodel.Recipe;
import org.fedorahosted.beaker.model.beakermodel.RecipeSet;
import org.fedorahosted.beaker.model.beakermodel.Task;

public class BeakerModelUtils {

    /**
     * 
     * @return {@link List} of {@link Task}s. Could be empty but never null.
     */
    public static List<Task> getJobTasks(Job job) {

        List<Task> tasks = new ArrayList<Task>();

        List<Object> recipeSets = job.getNotifyOrWhiteboardOrRecipeSet();
        for (Object recipeSet : recipeSets) {
            if (recipeSet instanceof RecipeSet) {
                for (Recipe recipe : ((RecipeSet) recipeSet).getRecipe()) {
                    for (Object task : recipe.getGuestrecipeOrAutopickOrKickstart()) {
                        if (task instanceof Task) {
                            tasks.add((Task) task);
                        }
                    }
                }
            }
        }

        return tasks;
    }

    public static Task getJobTaskById(Job job, int taskId) {

        if (job == null)
            throw new IllegalStateException();

        List<Object> recipeSets = job.getNotifyOrWhiteboardOrRecipeSet();
        for (Object recipeSet : recipeSets) {
            if (recipeSet instanceof RecipeSet) {
                for (Recipe recipe : ((RecipeSet) recipeSet).getRecipe()) {
                    for (Object task : recipe.getGuestrecipeOrAutopickOrKickstart()) {
                        if (task instanceof Task) {
                            if (String.valueOf(taskId).equals(((Task) task).getId()))
                                return (Task) task;
                        }
                    }
                }
            }
        }

        return null;
    }

    public static Task getJobTaskByName(Job job, String taskName) {

        if (job == null || taskName == null)
            throw new IllegalStateException();

        List<Object> recipeSets = job.getNotifyOrWhiteboardOrRecipeSet();
        for (Object recipeSet : recipeSets) {
            if (recipeSet instanceof RecipeSet) {
                for (Recipe recipe : ((RecipeSet) recipeSet).getRecipe()) {
                    for (Object task : recipe.getGuestrecipeOrAutopickOrKickstart()) {
                        if (task instanceof Task) {
                            if (String.valueOf(taskName).equals(((Task) task).getName()))
                                return (Task) task;
                        }
                    }
                }
            }
        }

        return null;
    }

}
