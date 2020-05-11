package ru.alex.myvote.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.alex.myvote.AuthorizedUser;
import ru.alex.myvote.model.AbstractBaseEntity;
import ru.alex.myvote.model.Restaurant;
import ru.alex.myvote.model.User;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    public static int id = AbstractBaseEntity.START_SEQ;

    public static User user;

    public static Restaurant restaurant = null;

    public SecurityUtil() {
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int authUserId() {
        return get().getUserTo().id();
    }

    public static void setRestaurant(Restaurant restaurant) {
        SecurityUtil.restaurant = restaurant;
    }
}
