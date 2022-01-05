package com.mindex.challenge.data;

import java.util.HashSet;
import java.util.List;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(){
    }

    public ReportingStructure(Employee employee) {
        this.employee = employee;
        this.numberOfReports = calculateDirectReports(employee,null);
    }

    /**
     * Method to recursively calculate the total number of direct reports associated with an employee.
     * @param employee Employee object for which the total number of direct reports will be calculated.
     * @return returns the total number of direct reports for the employee instance.
     */
    private int calculateDirectReports(Employee employee, HashSet<String> previouslySeemIDs) {

        if(previouslySeemIDs == null){
            previouslySeemIDs = new HashSet<>();
        }

        if(previouslySeemIDs.contains(employee.getEmployeeId())){
            return 0;
        }else {
            previouslySeemIDs.add(employee.getEmployeeId());
        }

        List<Employee> directReports = employee.getDirectReports();

        if(directReports == null || directReports.isEmpty()){
            return 1;
        }

        HashSet<String> finalPreviouslySeemIDs = previouslySeemIDs;
        return directReports.size() + directReports.stream().mapToInt(r -> calculateDirectReports(r, finalPreviouslySeemIDs)).sum();
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

}
