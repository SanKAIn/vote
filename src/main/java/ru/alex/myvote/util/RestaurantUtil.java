package ru.alex.myvote.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.alex.myvote.model.Dish;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.model.User;
import ru.alex.myvote.service.RestaurantService;
import ru.alex.myvote.to.RestaurantTo;
import ru.alex.myvote.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public RestaurantUtil() {
    }

    public static List<RestaurantTo> getToS(Collection<Restaurant> restaurants){
        return restaurants.stream()
                .map(restaurant -> createTo(restaurant))
                .collect(Collectors.toList());
    }

    private static RestaurantTo createTo(Restaurant restaurant){
        return new RestaurantTo(restaurant.getId(), restaurant.getName(),
                restaurant.getMenu(), restaurant.getVote());
    }

    public static Restaurant restFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName(), Arrays.asList(restaurantTo.getMenu()), restaurantTo.getVotes());
    }

    public static String menuFromList(List<Dish> list) {
        return list.stream()
                .map(dish -> dish.getName() + "=" + dish.getCost()).collect(Collectors.joining(","));
    }

    public static List<Dish> getMenuList(String menu){
        String[] dish = menu.split(",");
        List<Dish> dishes = new ArrayList<>();
        for (String s : dish) {
            String[] cost = s.split("=");
            dishes.add(new Dish(cost[0].equals("*") ? "" : cost[0], cost[1].equals("*") ? 0 : Integer.parseInt(cost[1])));
        }
        return dishes;
    }

    public static void addVote(Restaurant restaurant, RestaurantService service){
        int vote = restaurant.getVote();
        vote++;
        restaurant.setVote(vote);
        service.update(restaurant);
    }

    public static void delVote(Restaurant restaurant, RestaurantService service){
        int vote = restaurant.getVote();
        vote--;
        restaurant.setVote(vote);
        service.update(restaurant);
    }

    public static ResponseEntity<String> toVote(int id, RestaurantService restaurantService){
        User user = SecurityUtil.user;
        Restaurant restaurant = restaurantService.getToUser(id);
        LocalDateTime dateTimeNow = LocalDateTime.now();
        LocalDateTime eleven = LocalDateTime.of(
                dateTimeNow.getYear(),
                dateTimeNow.getMonth(),
                dateTimeNow.getDayOfMonth(),
                11,00,0);
        if (dateTimeNow.isAfter(eleven)) return new ResponseEntity<>("Голосовать можно только до 11:00", HttpStatus.BAD_REQUEST);
        LocalDateTime userDate = user.getDateVote() == null ? LocalDateTime.now() : user.getDateVote();
        LocalDate dateUser = LocalDate.of(userDate.getYear(),userDate.getMonth(),userDate.getDayOfMonth());
        LocalDate todayDate = LocalDate.of(dateTimeNow.getYear(),dateTimeNow.getMonth(),dateTimeNow.getDayOfMonth());
        Integer userRest = user.getRestIdVote();
        if (userRest == null || dateUser.isBefore(todayDate)) {
            addVote(restaurant, restaurantService);
            user.setDateVote(dateTimeNow);
            user.setRestIdVote(restaurant.getId());
            return new ResponseEntity<>("Голос защитан", HttpStatus.OK);
        }
        if (dateUser.equals(todayDate)){
            if (userRest.intValue() == restaurant.getId().intValue()) {
                return new ResponseEntity<>("За этот ресторан голос уже защитан!", HttpStatus.OK);
            }
            Restaurant userRestaurant = restaurantService.getToUser(userRest);
            delVote(userRestaurant, restaurantService);
            restaurantService.update(userRestaurant);
            addVote(restaurant, restaurantService);
            user.setDateVote(dateTimeNow);
            user.setRestIdVote(restaurant.getId());
            return new ResponseEntity<>("Вы изменили предпочтение!?", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Что то еще", HttpStatus.NO_CONTENT);
    }
}
