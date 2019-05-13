package by.weekmenu.domain;

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

public class RoleTest {

    private static Validator validator;
    private static ValidatorFactory validatorFactory;
    private Role role = new Role("USER", createUsers());
    private Account account = new Account(4, "IEEQWDS");
    private Set<UserAddress> userAdress = new HashSet<>();

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public Set<User> createUsers() {
        Account account = new Account(4, "IEEQWDS");
        Set<UserAddress> userAdress = new HashSet<>();
        User user = new User("123", "123", "123", "123", new Role("ssss"), account, userAdress, (long) 22223222);
        Set<User> users = new HashSet<>();
        users.add(user);
        return users;
    }

    @Test
    public void test_valid() {

        Set<ConstraintViolation<Role>> constraintViolations = validator.validate(role);
        assertEquals("Expected size of the ConstraintViolation set should be 0:", 0, constraintViolations.size());

    }

    @Test
    public void test_NullRole() {
       role.setName(null);
        Set<ConstraintViolation<Role>> constraintViolations = validator.validate(role);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<Role> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("role must have name"));
    }

    @Test
    public void test_EmptyRole() {
        role.setName("  ");
        Set<ConstraintViolation<Role>> constraintViolations = validator.validate(role);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<Role> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("role must have name"));
    }

    @Test
    public void test_BlankRole() {
        role.setName("");
        Set<ConstraintViolation<Role>> constraintViolations = validator.validate(role);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<Role> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("role must have name"));
    }
    
}
