package org.abes.hrms.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo {
    double sqaureroot(double d);
    String empData();
    int gtn(int a, int b);
}
