package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
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
public class CompensationControllerServiceImplTest {

    private String compensationCreateUrl;
    private String compensationReadUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationCreateUrl = "http://localhost:" + port + "/compensation";
        compensationReadUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateRead() {

        final Employee johnLennon = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(johnLennon);
        testCompensation.setSalary(75000.5);
        testCompensation.setEffectiveDate("12-12-2021");

        // Create checks
        ResponseEntity createdCompensationResponse = restTemplate.postForEntity(compensationCreateUrl,
                testCompensation, Compensation.class);
        assertEquals(HttpStatus.OK, createdCompensationResponse.getStatusCode());

        Compensation createdCompensation = (Compensation)createdCompensationResponse.getBody();
        assertNotNull(createdCompensation);
        assertEquals(testCompensation.getEffectiveDate(), createdCompensation.getEffectiveDate());
        assertEquals(testCompensation.getSalary(),createdCompensation.getSalary());
        assertEmployeeEquivalence(testCompensation.getEmployee(),createdCompensation.getEmployee());

        // Read checks
        ResponseEntity readCompensationResponse = restTemplate.getForEntity(compensationReadUrl,
                Compensation.class, johnLennon.getEmployeeId());
        assertEquals(HttpStatus.OK, readCompensationResponse.getStatusCode());

        Compensation readCompensation = (Compensation)readCompensationResponse.getBody();
        assertNotNull(readCompensation);
        assertEquals(readCompensation.getEffectiveDate(), createdCompensation.getEffectiveDate());
        assertEquals(readCompensation.getSalary(),createdCompensation.getSalary());
        assertEmployeeEquivalence(readCompensation.getEmployee(),createdCompensation.getEmployee());
    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getDepartment(), actual.getDepartment());
        Assert.assertEquals(expected.getPosition(), actual.getPosition());
    }
}
