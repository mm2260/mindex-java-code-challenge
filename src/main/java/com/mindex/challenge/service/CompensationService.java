package com.mindex.challenge.service;

/*
@Author: Mindex
@Author: Mohammed Mehboob (mm2260@rit.edu)
 */

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String id);
}
