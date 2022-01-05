package com.mindex.challenge.service.impl;

/*
@Author: Mindex
@Author: Mohammed Mehboob (mm2260@rit.edu)
 */


import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Override
    public ReportingStructure create(Employee employee) {
        LOG.debug("Creating reporting structure for employee [{}]", employee);
        return new ReportingStructure(employee);
    }
}
