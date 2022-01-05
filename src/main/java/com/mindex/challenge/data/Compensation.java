package com.mindex.challenge.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Compensation {
    private static final Logger LOG = LoggerFactory.getLogger(Compensation.class);
    private static final String DATE_FORMAT = "MM-dd-yyyy";

    @Id
    private String employeeId;

    private Double salary;
    private Date effectiveDate;

    public Compensation() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
