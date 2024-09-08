package com.example.demo5;

import java.util.*;
import com.example.demo5.model.CarAdvertDTO;
import java.util.stream.Collectors;
import br.com.giulianabezerra.springbootjooq.public_.tables.records.CarAdvertRecord;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public class CarAdvertMapper {
 
  public static Mono<CarAdvertDTO> toMonoCarAdvertDTO(CarAdvertRecord carAdvertRecord) {
    return Mono.just(new CarAdvertDTO(
        carAdvertRecord.getId(),
        carAdvertRecord.getTitle(),
        carAdvertRecord.getFuelType(),
        carAdvertRecord.getPrice(),
        carAdvertRecord.getIsNew(),
        carAdvertRecord.getMileage(),
        carAdvertRecord.getFirstRegistration()
    ));
  }
  
   public static CarAdvertDTO toCarAdvertDTO(CarAdvertRecord carAdvertRecord) {
    return new CarAdvertDTO(
        carAdvertRecord.getId(),
        carAdvertRecord.getTitle(),
        carAdvertRecord.getFuelType(),
        carAdvertRecord.getPrice(),
        carAdvertRecord.getIsNew(),
        carAdvertRecord.getMileage(),
        carAdvertRecord.getFirstRegistration()
    );
  }
 
   public static Flux<CarAdvertDTO> toCarAdvertDTOList(List<CarAdvertRecord> carAdvertRecords) {
        return Flux.fromIterable(carAdvertRecords.stream()
            .map(CarAdvertMapper::toCarAdvertDTO)
            .collect(Collectors.toList())
        );
    }
 
}
