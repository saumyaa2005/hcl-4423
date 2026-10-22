package org.abes.hrms.model;

import org.abes.hrms.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoEmployee {
    @Autowired
    private EmpRepo emprep;
    public int add(int a, int b){
        return a+b;
    }
    public double sqaureroot(){
        return emprep.sqaureroot(200);
    }
    public int gtn(){
        return emprep.gtn(10,20);
    }
}
