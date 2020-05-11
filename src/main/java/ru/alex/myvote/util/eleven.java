package ru.alex.myvote.util;

import ru.alex.myvote.model.History;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.repository.crud.HistoryRepositoryImpl;
import ru.alex.myvote.repository.crud.RestaurantRepositoryImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.alex.myvote.util.RestaurantUtil.menuFromList;

public class eleven {

    public eleven(RestaurantRepositoryImpl repository, HistoryRepositoryImpl historyRepository) {
        LocalTime rubicon = LocalTime.of(11, 00);
        int minute = 5;
        long milliseconds = minute * 60000;
        new Thread(() -> {
            while (true){
                try{
                    LocalTime time = LocalTime.now();
                    if (time.isAfter(rubicon) && time.isBefore(rubicon.plusMinutes(minute))){
                        List<Restaurant> restaurants = repository.getAll();
                        for (Restaurant r: restaurants) {
                            historyRepository.save(new History(r.getName(), LocalDate.now(), r.getVote(), menuFromList(r.getMenu())));
                        }
                    }
                    Thread.sleep(milliseconds);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
