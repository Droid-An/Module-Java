package com.prep.exercise_2_2;


public record Student(String firstName, String lastName) {

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
