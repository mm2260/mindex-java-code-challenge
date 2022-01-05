package com.mindex.challenge.data;

import java.util.HashSet;
import java.util.List;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(Employee employee) {
        this.employee = employee;
        this.numberOfReports = calculateDirectReports(employee);
    }

    /**
     * Method to recursively calculate the total number of direct reports associated with an employee.
     * @param employee Employee object for which the total number of direct reports will be calculated.
     * @return returns the total number of direct reports for the employee instance.
     */
    private int calculateDirectReports(Employee employee) {
        HashSet<String> previouslySeenEmployeeIDs = new HashSet<>();
        previouslySeenEmployeeIDs.add(employee.getEmployeeId());

        List<Employee> directReports = employee.getDirectReports();
        if( directReports == null || directReports.isEmpty() ) {
            return 0;
        } else {
            return directReports.size() +
                    directReports.stream().mapToInt( report -> {
                        return calculateDirectReports(report, previouslySeenEmployeeIDs);
                    } ).sum();
        }
    }

    /**
     * Method overload for calculateDirectReports.
     * Adds HashSet for previously seen IDs in order to prevent infinite recursive loop.
     * @param employee Employee object for which the total number of direct reports will be calculated.
     * @param previouslySeenEmployeeIDs HashSet to check against.
     * @return returns the total number of direct reports for the employee instance.
     */
    private int calculateDirectReports(Employee employee, HashSet<String> previouslySeenEmployeeIDs) {
        if(previouslySeenEmployeeIDs.contains(employee.getEmployeeId())) {
            return 0;
        } else {
            previouslySeenEmployeeIDs.add(employee.getEmployeeId());

            List<Employee> directReports = employee.getDirectReports();
            if( directReports == null || directReports.isEmpty() ) {
                return 0;
            } else {
                return directReports.size() +
                        directReports.stream().mapToInt( report -> {
                            return calculateDirectReports(report, previouslySeenEmployeeIDs);
                        } ).sum();
            }
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

}
