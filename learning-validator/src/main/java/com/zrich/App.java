package com.zrich;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class App {

    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        validate1(validator);
        validate2(validator);
        validateByGroup(validator);
        validateProperty(validator);
        validateValue(validator);


    }

    private static void validateByGroup(Validator validator) {
        Set<ConstraintViolation<Person>> constraintViolations =
                validator.validateValue(Person.class, "group", null, Intf1.class);
        assertEquals(1, constraintViolations.size());
        System.out.println("validate Intf1 |" + constraintViolations);
        constraintViolations = validator.validateValue(Person.class, "group", null, Intf2.class);
        assertEquals(0, constraintViolations.size());
        System.out.println("validate Intf2 |" + constraintViolations);
        constraintViolations =
                validator.validateValue(Person.class, "group", "test", Intf2.class, Intf1.class);
        assertEquals(1, constraintViolations.size());
        System.out.println("validate Intf1&Intf2 |" + constraintViolations);
    }

    private static void validateValue(Validator validator) {
        Set<ConstraintViolation<Person>> constraintViolations =
                validator.validateValue(Person.class, "name", null);
        assertEquals(1, constraintViolations.size());
        System.out.println(constraintViolations);
    }

    private static void validateProperty(Validator validator) {
        Person person = new Person(null, 101);
        Set<ConstraintViolation<Person>> constraintViolations =
                validator.validateProperty(person, "age");
        assertEquals(1, constraintViolations.size());
        System.out.println(constraintViolations);
    }

    private static void validate2(Validator validator) {
        Set<ConstraintViolation<Person>> constraintViolations =
                validator.validateValue(Person.class, "group", null, Intf1.class);
        assertEquals(1, constraintViolations.size());
        System.out.println("validate Intf1 |" + constraintViolations);
        constraintViolations = validator.validateValue(Person.class, "group", null, Intf2.class);
        assertEquals(0, constraintViolations.size());
        System.out.println("validate Intf2 |" + constraintViolations);
        constraintViolations =
                validator.validateValue(Person.class, "group", "test", Intf2.class, Intf1.class);
        assertEquals(1, constraintViolations.size());
        System.out.println("validate Intf1&Intf2 |" + constraintViolations);
    }

    private static void validate1(Validator validator) {
        Person person = new Person(null, 20);
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        assertEquals(1, constraintViolations.size());
        System.out.println(constraintViolations);
    }

}
