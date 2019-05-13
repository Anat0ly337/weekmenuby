package by.weekmenu.domain;


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

public class UserTest {
    private static Validator validator;
    private static ValidatorFactory validatorFactory;

    private Role role = new Role("USER");
    private Account account = new Account(4, "IEEQWDS");
    private Set<UserAddress> userAdress = new HashSet<>();
    private User user = new User("21313", "123123", "312213", "312121", role, account, userAdress, (long) 321312123);


    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void testUserIsValid() {
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("Expected size of the ConstraintViolation should be without exceptions:", 0, constraintViolations.size());
    }

    @Test
    public void test_invalidPasswordeLessThan() {
        user.setPassword("23");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 255", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }

    @Test
    public void test_invalidPasswordeMoreThan() {
        String str = StringUtils.repeat("12345678", 35);
        user.setPassword(str);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 255", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void test_invalidBlankPassword() {
        user.setPassword("  ");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have password"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testInvalidNullPassword() {
        user.setPassword(null);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must have password", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testInvalidLastNameLessThan() {
        user.setLastName("s");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 255", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


    }

    @Test
    public void testInvalidLastNameMoreThan() {
        String str = StringUtils.repeat("12345678", 35);
        user.setLastName(str);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 255", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testInvalidBlankLastName() {
        user.setLastName("  ");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have lastname"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testInvalidNullLastName() {
        user.setLastName(null);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must have lastname", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testInvalidUsernameLessThan() {
        user.setUsername("sa");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }


        @Test
        public void testInvalidUserNameMoreThan() {
            String str = StringUtils.repeat("12345678", 35);
            user.setUsername(str);
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
            assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
            assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
        }


    @Test
    public void testBlankName() {
        user.setUsername("");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have name"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testNullName() {
        user.setUsername(null);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have name"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testInvalidFirstNameMoreThan() {
        String str = StringUtils.repeat("12345678", 35);
        user.setFirstName(str);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidFirstNameLessThan() {
        String str = StringUtils.repeat("12345678", 35);
        user.setFirstName(str);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }


    @Test
    public void testBlankFirstName() {

        user.setFirstName("");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have firstname"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }

    @Test
    public void testEmptyFirstName() {
        user.setFirstName("  ");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have firstname"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testNullFirstName() {
        user.setFirstName(null);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have firstname"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }


    @After
    public void tearDown() {
        validatorFactory.close();
    }

}
