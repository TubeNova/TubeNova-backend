package TubeNova.app.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {
    LIFESTYLE("LifeStyle"), MUSICNDANCE("MusicAndDance"), BEAUTYNFASHION("BeautyAndFashion"), FILMNANIMATION("FilmAndAnimation"),
    KIDS("Kids"), GAME("Game"), OUTDOOR("Outdoor"), SPORTS("Sports"), NEWSNPOLITICS("NewsAndPolitics"), GOVERNAGENCY("GovernmentAgency"),
    ENTERTAINMENT("Entertainment"), FOOD("FOOD"), CELEBRITY("Celebrity"), SCIENCE("Science"), ANIMAL("Animal"), HOBBY("Hobby"), VEHICLE("Vehicle"),
    ECONOMY("Economy"), EDUCATION("Education"), UNTITLED("Untitled");

    private String category;

    Category(String category){
        this.category = category;
    }

    public static List<Category> toCategories(List<String> strCategories){
        List<Category> categories = new ArrayList<>();
        for(String category : strCategories){
            Category[] arr = Category.values();
            Category ctgr = Arrays.stream(arr)
                    .filter(o -> (o.category.equals(category))).findAny()
                    .orElseThrow(()->new IllegalArgumentException("올바르지 않은 카테고리 입니다."));
            categories.add(ctgr);
        }
        return categories;
    }
    public static Category toCategory(String str){
        Category[] arr = Category.values();
        Category ctgr = Arrays.stream(arr).filter(o->(o.category.equals(str))).findAny()
                .orElseThrow(()->new IllegalArgumentException("올바르지 않은 카테고리입니다."));
        return ctgr;
    }
    public static List<String> toStringList(List<Category> categories){
        List<String> categoryList = new ArrayList<>();
        for(Category category : categories){
            String str = category.category;
            categoryList.add(str);
        }
        return categoryList;
    }
}
