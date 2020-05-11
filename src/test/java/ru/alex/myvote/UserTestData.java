package ru.alex.myvote;

import ru.alex.myvote.model.Role;
import ru.alex.myvote.model.User;
import ru.alex.myvote.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static ru.alex.myvote.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "meals", "password");

    public static final int USER1_ID = START_SEQ;
    public static final int USER2_ID = START_SEQ + 1;
    public static final int USER3_ID = START_SEQ + 2;
    public static final int ADMIN1_ID = START_SEQ + 3;
    public static final int ADMIN2_ID = START_SEQ + 4;

    public static final User USER1 = new User(USER1_ID, "User1", "a@mail.ru", "pass1", Role.USER);
    public static final User USER2 = new User(USER2_ID, "User2", "b@mail.ru", "pass2", Role.USER);
    public static final User USER3 = new User(USER3_ID, "User3", "c@mail.ru", "pass3", Role.USER);
    public static final User ADMIN1 = new User(ADMIN1_ID, "Admin1", "d@mail.ru","a_pas1",
            true, new Date(), Set.of(Role.ADMIN, Role.USER));

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass",
                true, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
