package org.tolinety.springrest.to;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.tolinety.springrest.model.Dish;
import org.tolinety.springrest.model.LunchMenu;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by tolin on 25.06.2017.
 */
@Data
public class LunchTo {

    private LocalDate registered;

    @NotEmpty
    private int[] disheIds;

    @JsonCreator
    public LunchTo(@JsonProperty("disheIds") int... disheIds) {
        this.disheIds = disheIds;
        registered = LocalDate.now();
    }

    public LunchTo(LocalDate registered, int[] disheIds) {
        this.registered = registered;
        this.disheIds = disheIds;
    }

    public static LunchMenu getFromTo(LunchTo lunchTo, List<Dish> dishes) {
        return new LunchMenu(null, lunchTo.getRegistered(), dishes);
    }
}
