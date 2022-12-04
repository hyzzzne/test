package com.javalab.javaconfig.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {
    private String name;
    private int age;
    private List<Car> carList = new ArrayList<Car>();
    private Map<String, Car> carMap = new HashMap<String, Car>();
    
    public Person(String name, int age) {    
        this.name = name;
        this.age = age;
        carList.add(new Car("소나타", "현대", 200L));
        carList.add(new Car("아이오닉", "현대", 300L));
        carList.add(new Car("뷰소닉", "기아", 400L));
        
        carMap.put("Benz", new Car("SClass", "Benz", 500L));
        carMap.put("BMW", new Car("BClass", "BMW", 500L));        
    }

}