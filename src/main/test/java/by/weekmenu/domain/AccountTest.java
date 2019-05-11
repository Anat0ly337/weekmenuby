package by.weekmenu.domain;

import by.weekmenu.domain.Account;
import by.weekmenu.domain.Role;
import by.weekmenu.domain.User;
import by.weekmenu.domain.UserAddress;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {



    private static Validator validator;
    private static ValidatorFactory validatorFactory;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void createUsers(Account account) {
        User user = new User("123", "123", "123", "123", new Role("USER"), new Account(), new HashSet<UserAddress>(), (long) 2222222);
        Set<User> users = new HashSet<>();
        users.add(user);
        account.setUserList(users);
    }

    @Test
    public void test_shortname() {

        Account account = new Account(4, "xz");
        createUsers(account);
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<Account> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must be between 3 and 20"));

    }

    @Test
    public void test_longname() {
        String srt = StringUtils.repeat("aaaaa",5);

        Account account = new Account(4, srt);
        createUsers(account);
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<Account> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must be between 3 and 20"));

    }

    @Test
    public void test_blankname() {
        Set<User> users = new HashSet<>();

        Account account = new Account(4, "  ");
        createUsers(account);
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);

        assertEquals("Account must have name", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


    }


    @Test
    public void test_namenull() {
        Set<User> users = new HashSet<>();

        Account account = new Account(4, "");
        createUsers(account);
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
        assertEquals("Account must have name", constraintViolations.iterator().next().getMessage());

    }
    @Test
    public void test_emptyname() {
        Set<User> users = new HashSet<>();

        Account account = new Account(4, null);
        createUsers(account);
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
        assertEquals("Account must have name", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


    }

    @Test
    public void test_negativeCountOfMembers() {



        Account account = new Account(-2, "Ивановы");
        createUsers(account);
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
        assertEquals("must have positive or zero value", constraintViolations.iterator().next().getMessage());


    }

    @Test
    public void valid() {


        Account account = new Account(4, "Ивановы");
        createUsers(account);
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);
        assertEquals("Expected size of the ConstraintViolation set should be 0:", 0, constraintViolations.size());


    }

    /*@After
    public void tearDown() {
        validatorFactory.close();
    }*/




}


