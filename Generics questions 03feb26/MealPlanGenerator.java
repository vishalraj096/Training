import java.util.*;

interface MealPlan {
    String name();
}

class VegetarianMeal implements MealPlan {
    public String name() {
        return "Vegetarian";
    }
}

class VeganMeal implements MealPlan {
    public String name() {
        return "Vegan";
    }
}

class KetoMeal implements MealPlan {
    public String name() {
        return "Keto";
    }
}

class HighProteinMeal implements MealPlan {
    public String name() {
        return "High-Protein";
    }
}

class Meal<T extends MealPlan> {
    private final T plan;

    public Meal(T plan) {
        this.plan = plan;
    }

    public T getPlan() {
        return plan;
    }

    public String toString() {
        return plan.name();
    }

    public static <T extends MealPlan> boolean validate(T plan) {
        return plan != null;
    }

    public static <T extends MealPlan> Meal<T> generate(T plan) {
        return new Meal<>(plan);
    }
}

public class MealPlanGenerator {
    public static void main(String[] args) {
        VeganMeal vegan = new VeganMeal();
        VegetarianMeal vegetarian = new VegetarianMeal();
        KetoMeal keto = new KetoMeal();
        List<Meal<?>> meals = new ArrayList<>();
        if (Meal.validate(vegan))
            meals.add(Meal.generate(vegan));
        if (Meal.validate(vegetarian))
            meals.add(Meal.generate(vegetarian));
        if (Meal.validate(keto))
            meals.add(Meal.generate(keto));
        for (Meal<?> m : meals)
            System.out.println(m);
    }
}