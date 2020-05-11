package ru.alex.myvote.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.service.RestaurantService;
import ru.alex.myvote.to.RestaurantTo;
import ru.alex.myvote.util.RestaurantUtil;
import ru.alex.myvote.web.SecurityUtil;

import java.util.List;

import static ru.alex.myvote.util.ValidationUtil.assureIdConsistent;
import static ru.alex.myvote.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {} for admin {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int adminId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for admin {}", id, adminId);
        service.delete(id, adminId);
    }

    public List<RestaurantTo> getAll() {
        List<Restaurant> list = service.getAll();
        return RestaurantUtil.getToS(list);
    }

    public List<RestaurantTo> getAllAdmin() {
        int adminId = SecurityUtil.authUserId();
        log.info("getAll for admin {}", adminId);
        List<Restaurant> list = service.getAllAdmin(adminId);
        return RestaurantUtil.getToS(list);
    }

    public void update(Restaurant restaurant, int id) {
        int adminId = SecurityUtil.authUserId();
        assureIdConsistent(restaurant, id);
        log.info("update {} for admin {}", restaurant, adminId);
        service.update(restaurant, adminId);
    }

    public Restaurant create(Restaurant restaurant) {
        int adminId = SecurityUtil.authUserId();
        checkNew(restaurant);
        log.info("create {} for admin {}", restaurant, adminId);
        return service.create(restaurant, adminId);
    }
    @Transactional
    public ResponseEntity<String> vote(int id) {
        return RestaurantUtil.toVote(id, service);
    }
}
