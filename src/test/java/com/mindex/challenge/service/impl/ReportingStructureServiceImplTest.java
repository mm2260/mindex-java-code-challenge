package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeUrl;
    private String reportingStructureUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureUrl = "http://localhost:" + port + "/employee/{id}/reporting";
    }

    @Test
    public void testRead() {

        final Employee johnLennon = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        ReportingStructure testReportingStructure = new ReportingStructure(johnLennon);

        // Read checks
        ResponseEntity readReportingStructureResponse = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, johnLennon.getEmployeeId());
        assertEquals(HttpStatus.OK, readReportingStructureResponse.getStatusCode());
        ReportingStructure readReportingStructure = (ReportingStructure)readReportingStructureResponse.getBody();
        assertNotNull(readReportingStructure);

        assertEmployeeEquivalence(readReportingStructure.getEmployee(), johnLennon);
        assertEquals(readReportingStructure.getNumberOfReports(), 4);
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getDepartment(), actual.getDepartment());
        Assert.assertEquals(expected.getPosition(), actual.getPosition());
    }
}