package com.example.demo5;

import java.util.*;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import br.com.giulianabezerra.springbootjooq.public_.tables.CarAdvert;
import br.com.giulianabezerra.springbootjooq.public_.tables.records.CarAdvertRecord;

@Repository
public class CarAdvertRepository implements ICarAdvertRepository {
   
  private final DSLContext ctx;

  public CarAdvertRepository(DSLContext ctx) {
    this.ctx = ctx;
  }
  
  @Override
  public List<CarAdvertRecord> getAll(String sortBy) {
    return this.ctx
        .selectFrom(CarAdvert.CAR_ADVERT)
        .orderBy(org.jooq.impl.DSL.field(sortBy).asc())
        .fetchInto(CarAdvertRecord.class);
  }
  
  @Override
  public CarAdvertRecord findAdvertById(long id) {
    CarAdvertRecord record =   this.ctx
        .selectFrom(CarAdvert.CAR_ADVERT)
        .where(CarAdvert.CAR_ADVERT.ID.eq(id))
        .fetchOne();
     
    return record;
   }
  
  @Override
  public CarAdvertRecord create(String title, String fuelType, double price, boolean isNew, int mileage, LocalDateTime firstRegistration) {
    CarAdvertRecord createdCarRecord = ctx.newRecord(CarAdvert.CAR_ADVERT);
    createdCarRecord.setTitle(title);
    createdCarRecord.setFuelType(fuelType);
    createdCarRecord.setPrice(price);
    createdCarRecord.setIsNew(isNew);
    createdCarRecord.setMileage(mileage);
    createdCarRecord.setFirstRegistration(firstRegistration);
    
    int stored = createdCarRecord.store();

    if (stored == 1) {
        return createdCarRecord;
    } else {
      throw new RuntimeException("User not created");
    }
  }
   
  @Override
  public CarAdvertRecord edit(long id, String title, String fuelType, double price, boolean isNew, int mileage, LocalDateTime firstRegistration) {
    CarAdvertRecord existingCarRecord = this.findAdvertById(id);
    
    if(!existingCarRecord.getTitle().equals(title)){
        existingCarRecord.setTitle(title);
    }
    
    if(!existingCarRecord.getFuelType().equals(fuelType)){
        existingCarRecord.setFuelType(fuelType);
    }    
    
    if(!existingCarRecord.getPrice().equals(price)){
        existingCarRecord.setPrice(price);
    }
    
    if(!existingCarRecord.getIsNew().equals(isNew)){
        existingCarRecord.setIsNew(isNew);
    }
    if(!existingCarRecord.getMileage().equals(mileage)){
        existingCarRecord.setMileage(mileage);
    }
    if(!existingCarRecord.getFirstRegistration().equals(firstRegistration)){
        existingCarRecord.setFirstRegistration(firstRegistration);
    }
   
    int updated = existingCarRecord.store();

    if (updated == 1) {
        return existingCarRecord;
    } else {
        throw new RuntimeException("CarAdvert not updated");
    }
  }
  
  @Override
  public boolean remove(long id) {
    CarAdvertRecord advert = this.findAdvertById(id);
    
    if(advert==null){
        return false;
    }
    
    int delete = advert.delete();
    if(!(delete==1)){
        throw new RuntimeException("CarAdvert not removed");
    }
    
    return true;
   }
}
