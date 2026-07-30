package com.prep.exercise_2_1;

import com.prep.exercise_2_2.Student;

import java.util.*;

/**
 * Print students names and their grades in different formats
 */
public class Students {
    private final String[] firstNames;
    private final String[] lastNames;
    private final int[] grades;

    public Students(String[] firstNames, String[] lastNames, int[] grades) {
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.grades = grades;
    }

    public static void main(String[] args) {
        String[] firstNames = {"Anuket", "Eryn", "Filip", "Raquel", "Eryn"};
        String[] lastNames = {"Hassan", "Doe", "Pappas", "Hassan", "Jones"};
        int[] grades = {22, 45, 35, 23, 41};
        Students students = new Students(firstNames, lastNames, grades);
        System.out.println(students.getFirstNames());
        System.out.println(students.getDistinctFirstNames());
        System.out.println(students.getDistinctFirstNamesInAlphabeticalOrder());
        System.out.println(students.getLastNameToGrade());
        System.out.println(students.getStudentToGrade());
    }

    public Map<Student, Integer> getStudentToGrade() {

        Map<Student, Integer> studentGradeMap = new HashMap<>();

        for (int i = 0; i < lastNames.length; i++) {
            Student student = new Student(firstNames[i], lastNames[i]);
            studentGradeMap.put(student, grades[i]);
        }

        return studentGradeMap;
    }

    /**
     * Print only first names
     *
     * @return stringified Array of first names
     */
    public String getFirstNames() {
        return Arrays.toString(firstNames);
    }

    /**
     * Print only unique first names
     *
     * @return Set of first names
     */
    public Set<String> getDistinctFirstNames() {
        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.addAll(List.of(firstNames));
        return uniqueNames;
    }

    /**
     * Print only unique first names in an Alphabetical order
     *
     * @return Set of first names sorted with TreeSet
     * @see TreeSet
     */
    public Set<String> getDistinctFirstNamesInAlphabeticalOrder() {
        Set<String> treeSet = new TreeSet();
        treeSet.addAll(List.of(firstNames));
        return treeSet;
    }

    /**
     * Print last names mapped to grades
     * <p>
     * If there are some duplicate lastNames, only last one will be mapped
     *
     * @return Set of first names
     */
    public Map<String, Integer> getLastNameToGrade() {
        Map<String, Integer> lastNameToGrade = new HashMap<>();
        for (int i = 0; i < lastNames.length; i++) {
            lastNameToGrade.put(lastNames[i], grades[i]);
        }
        return lastNameToGrade;
    }
}
