package com.yc.reggie.dto;

import com.yc.reggie.entity.Setmeal;
import com.yc.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
