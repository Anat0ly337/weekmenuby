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

public class UserAddressTest {

    private static Validator validator;
    private static ValidatorFactory validatorFactory;
    private User user = new User("21313", "123123", "312213", "312121", new Role("admin"), new Account(1, "dsad"), new HashSet<UserAddress>(), (long) 321312123);
    private UserAddress userAddress = new UserAddress("Partizan", "77777", "minsk", "1A", "dsaa", 1, "sdaads", "sadsad", user);


    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void test_invalidHouseNull() {
        userAddress.setHouse(null);
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("must have house", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void test_invalidHouseBlank() {
        userAddress.setHouse("");
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("must have house", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testInvalidHouseEmpty() {
        userAddress.setHouse("  ");
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("must have house", constraintViolations.iterator().next().getMessage());
        assertEquals("Expected size of the ConstraintViolation should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testNullFloor() {
        userAddress.setFloor(null);
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("Expected size of the ConstraintViolation set should be 0", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidFloor() {
        userAddress.setFloor(-2);
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("Expected size of the ConstraintViolation set should be 1", 1, constraintViolations.size());
    }

    @Test
    public void testSmallCity() {
        userAddress.setCity("22");
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidBlankCity() {
        userAddress.setCity("");
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<UserAddress> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have city"));
    }

    @Test
    public void testInvalidNullCity() {
        userAddress.setCity(null);
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<UserAddress> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have city"));
    }

    @Test
    public void testInvalidEmptykCity() {
        userAddress.setCity("  ");
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        List<String> messages = constraintViolations.stream()
                .map((ConstraintViolation<UserAddress> violation) -> violation.getMessage())
                .collect(Collectors.toList());
        assertTrue(messages.contains("must have city"));
    }

    @Test
    public void testHugeDistrict() {
        userAddress.setDistrict("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testHugeRegion() {
        userAddress.setRegion("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testHugeCity() {
        userAddress.setCity("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("must be between 3 and 20", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testHugeComment() {
        String str = StringUtils.repeat("comment", 150);
        userAddress.setComment(str);
        Set<ConstraintViolation<UserAddress>> constraintViolations = validator.validate(userAddress);
        assertEquals("more than 1000", constraintViolations.iterator().next().getMessage());
    }

    @After
    public void tearDown() {
        validatorFactory.close();
    }

}
