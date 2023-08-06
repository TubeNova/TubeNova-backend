package TubeNova.app.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

//JSON에서 보낼 땐 숫자로 보내야 함
public enum Category {
    LIFESTYLE("LIFESTYLE"),
    MUSICNDANCE("MUSICNDANCE"),
    BEAUTYNFASHION("BEAUTYNFASHION"),
    FILMNANIMATION("FILMNANIMATION"),
    KIDS("KIDS"),
    GAME("GAME"),
    OUTDOOR("OUTDOOR"),
    SPORTS("SPORTS"),
    NEWSNPOLITIC("NEWSNPOLITIC"),
    GOVERNAGENCY("GOVERNAGENCY"),
    ENTERTAINMENT("ENTERTAINMENT"),
    FOOD("FOOD"),
    CELEBRITY("CELEBRITY"),
    SCIENCE("SCIENCE"),
    ANIMAL("ANIMAL"),
    HOBBY("HOBBY"),
    VEHICLE("VEHICLE"),
    ECONOMY("ECONOMY"),
    EDUCATION("EDUCATION"),
    UNTITLED("UNTITLED");

    //@Getter
    private final String value;

    Category(String value) {
        this.value = value;
    }
    @JsonCreator
    public static Category from(String value) {
        for (Category status : Category.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }


}
