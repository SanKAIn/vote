package ru.alex.myvote.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.myvote.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query(name = Restaurant.DELETE)
    int delete(@Param("id") int id, @Param("adminId") int adminId);

    List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r WHERE r.admin=?1")
    List<Restaurant> getAllAdmin(int adminId);

}
