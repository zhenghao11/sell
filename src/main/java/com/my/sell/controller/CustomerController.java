package com.my.sell.controller;

import com.my.sell.viewObject.Food;
import com.my.sell.viewObject.FoodType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * created by hzheng on 2017/9/16.
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController{

    @RequestMapping(value = "/index")
    public Object index(Model model){
        // 家常菜
        List<Food> foodList = new ArrayList<Food>();
        Food food1 = new Food();
        food1.setId(1);
        food1.setName("宫保鸡丁");
        food1.setPrice(new BigDecimal(25));
        food1.setDescription("描述:宫保鸡丁");
        food1.setStock(1000);
        foodList.add(food1);
        //
        Food food2 = new Food();
        food2.setId(2);
        food2.setName("糖醋里脊");
        food2.setPrice(new BigDecimal(35));
        food2.setDescription("描述:糖醋里脊");
        food2.setStock(1000);
        foodList.add(food2);
        //
        FoodType foodType1 = new FoodType();
        foodType1.setFoodList(foodList);
        foodType1.setId(1);
        foodType1.setName("家常菜");
        //酒水饮料
        List<Food> foodList1 = new ArrayList<Food>();
        Food food3 = new Food();
        food3.setId(3);
        food3.setName("樱桃味可乐");
        food3.setPrice(new BigDecimal(5));
        food3.setDescription("描述:樱桃味可乐");
        food3.setStock(1000);
        foodList1.add(food3);
        //
        FoodType foodType2 = new FoodType();
        foodType2.setFoodList(foodList1);
        foodType2.setId(2);
        foodType2.setName("酒水饮料");
        //
        List<FoodType> foodTypeList = new ArrayList<FoodType>();
        foodTypeList.add(foodType1);
        foodTypeList.add(foodType2);
        model.addAttribute("foodTypeList",foodTypeList);
    //    return foodTypeList;
        return "common/customer";
    }


}
