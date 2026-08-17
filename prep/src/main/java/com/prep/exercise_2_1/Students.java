package com.prep.exercise_2_1;

import com.prep.exercise_2_2.Student;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

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

        List<String> firstNamesResult = students.getFirstNames();
        Set<String> distinctFirstNamesResult = students.getDistinctFirstNames();
        Set<String> distinctFirstNamesAlphabeticalResult = students.getDistinctFirstNamesInAlphabeticalOrder();
        Map<String, Integer> lastNameToGradeResult = students.getLastNameToGrade();
        Map<Student, Integer> studentToGradeResult = students.getStudentToGrade();

        Student newStudent = new Student("Mike", "Sanchez");

        firstNamesResult.add(newStudent.firstName());
        distinctFirstNamesResult.add(newStudent.firstName());
        distinctFirstNamesAlphabeticalResult.add(newStudent.firstName());
        lastNameToGradeResult.put(newStudent.lastName(), 100);
        studentToGradeResult.put(newStudent, 100);

        System.out.println("new student joins:");
        System.out.println(firstNamesResult);
        System.out.println(distinctFirstNamesResult);
        System.out.println(distinctFirstNamesAlphabeticalResult);
        System.out.println(lastNameToGradeResult);
        System.out.println(studentToGradeResult);

        Student studentToRemove = null;

        for (Student student : studentToGradeResult.keySet()) {
            if (student.toString()
                    .equals("Filip Pappas")) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            firstNamesResult.remove(studentToRemove.firstName());
            distinctFirstNamesResult.remove(studentToRemove.firstName());
            distinctFirstNamesAlphabeticalResult.remove(studentToRemove.firstName());
            lastNameToGradeResult.remove(studentToRemove.lastName());
            studentToGradeResult.remove(studentToRemove);
        }


        System.out.println("student leave:");
        System.out.println(firstNamesResult);
        System.out.println(distinctFirstNamesResult);
        System.out.println(distinctFirstNamesAlphabeticalResult);
        System.out.println(lastNameToGradeResult);
        System.out.println(studentToGradeResult);

        Student studentThatCheated = null;

        for (Student student : studentToGradeResult.keySet()) {
            if (student.toString()
                    .equals("Eryn Doe")) {
                studentThatCheated = student;
                break;
            }
        }

        if (studentThatCheated != null) {
            lastNameToGradeResult.put(studentThatCheated.firstName(), 0);
            studentToGradeResult.put(studentThatCheated, 0);
        }

        System.out.println("student cheated:");
        System.out.println(lastNameToGradeResult);
        System.out.println(studentToGradeResult);

        System.out.println("Highest: " + students.findHighestGrade());
        System.out.println("lowest: " + students.findLowestGrade());
        System.out.println(students.StudentWithHighestGrade());
        System.out.println(students.getFirstNameDuplicates());
    }

    /**
     * Map student as Student object to grade
     *
     * @return Map of students mapped to grade
     * @since 2.2
     */
    public Map<Student, Integer> getStudentToGrade() {

        Map<Student, Integer> studentGradeMap = new HashMap<>();

        for (int i = 0; i < lastNames.length; i++) {
            Student student = new Student(firstNames[i], lastNames[i]);
            studentGradeMap.put(student, grades[i]);
        }

        return studentGradeMap;
    }

    /**
     * Return only first names
     *
     * @return stringified Array of first names
     */
    public List<String> getFirstNames() {
        return new ArrayList<>(asList(firstNames));
    }

    /**
     * Return only unique first names
     *
     * @return Set of first names
     */
    public Set<String> getDistinctFirstNames() {
        return new HashSet<>(asList(firstNames));
    }

    /**
     * Return only unique first names in alphabetical order
     *
     * @return Set of first names sorted with TreeSet
     * @see TreeSet
     */
    public Set<String> getDistinctFirstNamesInAlphabeticalOrder() {
        return new TreeSet<>(asList(firstNames));
    }

    /**
     * Return last names mapped to grades
     * <p>
     * If there are some duplicate lastNames, only last one will be mapped
     *
     * @return Map of first names
     */
    public Map<String, Integer> getLastNameToGrade() {
        Map<String, Integer> lastNameToGrade = new HashMap<>();
        for (int i = 0; i < lastNames.length; i++) {
            lastNameToGrade.put(lastNames[i], grades[i]);
        }
        return lastNameToGrade;
    }

    /**
     * Helper function to get unique grades sorted from lowest to highest
     *
     * @return unique grades sorted from lowest to highest
     */
    public TreeSet<Integer> turnGradesIntoTreeSet() {
        Integer[] gradesIntegers = new Integer[grades.length];
        Arrays.setAll(gradesIntegers, i -> grades[i]);
        return new TreeSet<>((asList(gradesIntegers)));
    }

    public Integer findHighestGrade() {
        TreeSet<Integer> treeSetOfGrades = turnGradesIntoTreeSet();
        return treeSetOfGrades.last();
    }

    public Integer findLowestGrade() {
        TreeSet<Integer> treeSetOfGrades = turnGradesIntoTreeSet();
        return treeSetOfGrades.first();
    }

    /**
     * Return map entry with Student object with the highest grade
     */
    public Map.Entry<Student, Integer> StudentWithHighestGrade() {
        List<Map.Entry<Student, Integer>> list = new ArrayList<>(getStudentToGrade().entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list.get(list.size() - 1);
    }

    /**
     * Get list of names that belong to more than one student
     *
     * @return Set of duplicated names
     */
    public Set<String> getFirstNameDuplicates() {
        Set<String> elements = new HashSet<>();
        return Arrays.stream(firstNames)
                .filter(n -> !elements.add(n))
                .collect(Collectors.toSet());


    }

}
