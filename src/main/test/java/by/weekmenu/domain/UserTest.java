package by.weekmenu.domain;

;
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

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    User user = new User("21313", "123123", "312213", "312121", role, account, userAdress, (long) 321312123);

    @Test
    public void test_UserIsValid() {


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
    public void test_invalidNullPassword() {

        user.setPassword(null);

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must have password", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


    }

    @Test
    public void test_invalidLastNameLessThan() {


        user.setLastName("s");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 255", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


    }

    @Test
    public void test_invalidLastNameMoreThan() {

        String str = StringUtils.repeat("12345678", 35);
        user.setLastName(str);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 255", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


    }

    @Test
    public void test_invalidBlankLastName() {

        user.setLastName("  ");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have lastname"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }

    @Test
    public void test_invalidNullLastName() {

        user.setLastName(null);

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must have lastname", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


    }

    @Test
    public void test_invalidUsernameLessThan() {


        user.setUsername("sa");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }


        @Test
        public void test_invalidUserNameMoreThan() {

            String str = StringUtils.repeat("12345678", 35);
            user.setUsername(str);
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
            assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
            assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


        }


    @Test
    public void test_blankName() {

        user.setUsername("");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have name"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }

    @Test
    public void test_NullName() {

        user.setUsername(null);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have name"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }

    @Test
    public void test_invalidFirstNameMoreThan() {

        String str = StringUtils.repeat("12345678", 35);
        user.setFirstName(str);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());


    }
    @Test
    public void test_invalidFirstNameLessThan() {

        String str = StringUtils.repeat("12345678", 35);
        user.setFirstName(str);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());


    }


    @Test
    public void test_blankFirstName() {

        user.setFirstName("");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have firstname"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }

    @Test
    public void test_emptyFirstName() {

        user.setFirstName("  ");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have firstname"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }

    @Test
    public void test_NullFirstName() {

        user.setFirstName(null);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<User> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have firstname"));
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());

    }


   /* @After
    public void tearDown() {
        validatorFactory.close();
    }*/

}
