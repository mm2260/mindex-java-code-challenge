package com.mindex.challenge.data;

/*
@Author: Mindex
@Author: Mohammed Mehboob (mm2260@rit.edu)
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Compensation {
    private static final Logger LOG = LoggerFactory.getLogger(Compensation.class);
    private static final String DATE_FORMAT = "MM-dd-yyyy";

    private Employee employee;
    private Double salary;
    private Date effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEffectiveDate() {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(this.effectiveDate);
    }

    public void setEffectiveDate(String effectiveDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            this.effectiveDate = formatter.parse(effectiveDate);
        } catch (ParseException e) {
            LOG.debug("[compensation] Error parsing date: [{}]", effectiveDate);
        }
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

}
