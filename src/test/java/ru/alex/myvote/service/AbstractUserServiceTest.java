package ru.alex.myvote.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.alex.myvote.model.Role;
import ru.alex.myvote.model.User;
import ru.alex.myvote.repository.UserRepository;
import ru.alex.myvote.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alex.myvote.UserTestData.*;

public abstract class AbstractUserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserRepository repository;

    @Test
    void create() throws Exception {
        User newUser = getNew();
        User created = service.create(new User(newUser));
        int newId = created.id();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "d@mail.ru", "newPass", Role.USER)));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER1_ID);
        Assertions.assertNull(repository.get(USER1_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    void get() throws Exception {
        User user = service.get(ADMIN1_ID);
        USER_MATCHER.assertMatch(user, ADMIN1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    void getByEmail() throws Exception {
        User user = service.getByEmail("d@mail.ru");
        USER_MATCHER.assertMatch(user, ADMIN1);
    }

    @Test
    void update() throws Exception {
        User updated = getUpdated();
        service.update(new User(updated));
        USER_MATCHER.assertMatch(service.get(USER1_ID), updated);
    }

    @Test
    void getAll() throws Exception {
        USER_MATCHER.assertMatch(service.getAll(), ADMIN1, USER1, USER2, USER3);
    }

    @Test
    void enable() {
        service.enable(USER1_ID, false);
        assertFalse(service.get(USER1_ID).isEnabled());
        service.enable(USER1_ID, true);
        assertTrue(service.get(USER1_ID).isEnabled());
    }
}