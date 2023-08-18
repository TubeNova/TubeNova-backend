package TubeNova.app.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {
    LIFESTYLE("LIFESTYLE"), MUSICNDANCE("MUSICNDANCE"), BEAUTYNFASHION("BEAUTYNFASHION"), FILMNANIMATION("FILMNANIMATION"),
    KIDS("KIDS"), GAME("GAME"), OUTDOOR("OUTDOOR"), SPORTS("SPORTS"), NEWSNPOLITICS("NEWSNPOLITICS"), GOVERNAGENCY("GOVERNAGENCY"),
    ENTERTAINMENT("ENTERTAINMENT"), FOOD("FOOD"), CELEBRITY("CELEBRITY"), SCIENCE("SCIENCE"), ANIMAL("ANIMAL"), HOBBY("HOBBY"), VEHICLE("Vehicle"),
    ECONOMY("ECONOMY"), EDUCATION("EDUCATION"), UNTITLED("UNTITLED");
    
    private String category;

    Category(String category){
        this.category = category;
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
