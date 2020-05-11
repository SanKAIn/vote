package ru.alex.myvote.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.myvote.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    @Query(name = Dish.DELETE)
    int delete(@Param("id") int id, @Param("restId") int restaurantId);

    @Query(name = Dish.ALL_BY_REST)
    List<Dish> getAll(@Param("restId") int restaurantId);
}
