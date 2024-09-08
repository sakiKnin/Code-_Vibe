package com.example.demo5;

import java.util.*;
import br.com.giulianabezerra.springbootjooq.public_.tables.records.CarAdvertRecord; 
import java.time.LocalDateTime;

public interface ICarAdvertRepository {     
    List<CarAdvertRecord> getAll(String sortBy);
    CarAdvertRecord findAdvertById(long id);
    CarAdvertRecord create(String title, String fuelType, double price, boolean isNew, int mileage, LocalDateTime firstRegistration);
    CarAdvertRecord edit(long id, String title, String fuelType, double price, boolean isNew, int mileage, LocalDateTime firstRegistration);
    boolean remove(long id);
}

