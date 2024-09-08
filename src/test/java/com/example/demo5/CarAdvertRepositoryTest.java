package com.example.demo5;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import org.jooq.SelectConditionStep;
import br.com.giulianabezerra.springbootjooq.public_.tables.CarAdvert;
import br.com.giulianabezerra.springbootjooq.public_.tables.records.CarAdvertRecord;

class CarAdvertRepositoryTest {

    @Mock
    private DSLContext ctx;

    @InjectMocks
    private CarAdvertRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
/*
    @Test
    void testGetAll() {
        CarAdvertRecord record = new CarAdvertRecord();
        record.setTitle("Test Car");

        Result<CarAdvertRecord> result = mock(Result.class);
        when(result.into(CarAdvertRecord.class)).thenReturn(Collections.singletonList(record));
        when(ctx.selectFrom(CarAdvert.CAR_ADVERT).orderBy(any()).fetchInto(CarAdvertRecord.class)).thenReturn(result.into(CarAdvertRecord.class));

        List<CarAdvertRecord> records = repository.getAll("title");

        assertNotNull(records);
        assertEquals(1, records.size());
        assertEquals("Test Car", records.get(0).getTitle());
    }
*/
     
    @Test
    void testFindAdvertById() {
        
         long id = 1L;
        CarAdvertRecord record = new CarAdvertRecord();
        record.setId(id);
        record.setTitle("Test Car");
        record.setFuelType("Petrol");
        record.setPrice(20000.00);
        record.setIsNew(true);
        record.setMileage(10000);
        record.setFirstRegistration(LocalDateTime.of(2024, 1, 1, 0, 0));
          SelectConditionStep<?> conditionStep = mock(SelectConditionStep.class);
       
       // when(ctx.selectFrom(CarAdvert.CAR_ADVERT)).thenReturn(conditionStep);
   
        // Mock the `where` call to return the same `SelectConditionStep`
      //  when(conditionStep.where(CarAdvert.CAR_ADVERT.ID.eq(id))).thenReturn(conditionStep);
/*
        // Finally, mock the `fetchOne` call to return the `CarAdvertRecord`
        when(conditionStep.fetchOne()).thenReturn(record);

        // Now, when you call findAdvertById, it should return the mocked record
        CarAdvertRecord foundRecord = repository.findAdvertById(id);

        // Assertions
        assertNotNull(foundRecord, "The returned record should not be null");
        assertEquals(id, foundRecord.getId(), "The ID should match");
        assertEquals("Test Car", foundRecord.getTitle(), "The title should match");
*/
    }
  
    @Test
    void testCreate() {
        CarAdvertRecord record = mock(CarAdvertRecord.class);
        when(ctx.newRecord(CarAdvert.CAR_ADVERT)).thenReturn(record);
        when(record.store()).thenReturn(1);

        LocalDateTime now = LocalDateTime.now();
        CarAdvertRecord createdRecord = repository.create("Test Car", "Petrol", 10000, true, 0, now);

        verify(record).setTitle("Test Car");
        verify(record).setFuelType("Petrol");
        verify(record).setPrice(10000.0);
        verify(record).setIsNew(true);
        verify(record).setMileage(0);
        verify(record).setFirstRegistration(now);
        verify(record).store();

        assertNotNull(createdRecord);
    }
 
    @Test
    void testEdit() {
        long id = 1L;
        CarAdvertRecord record = new CarAdvertRecord();
        record.setId(id);
        record.setTitle("Old Title");

        when(ctx.selectFrom(CarAdvert.CAR_ADVERT).where(CarAdvert.CAR_ADVERT.ID.eq(id)).fetchOne()).thenReturn(record);
        when(record.store()).thenReturn(1);

        LocalDateTime now = LocalDateTime.now();
        CarAdvertRecord updatedRecord = repository.edit(id, "New Title", "Diesel", 12000, false, 100, now);

        assertNotNull(updatedRecord);
        assertEquals("New Title", updatedRecord.getTitle());
        assertEquals("Diesel", updatedRecord.getFuelType());
        assertEquals(12000, updatedRecord.getPrice());
        assertFalse(updatedRecord.getIsNew());
        assertEquals(100, updatedRecord.getMileage());
        assertEquals(now, updatedRecord.getFirstRegistration());
    }

    @Test
    void testRemove() {
        long id = 1L;
        CarAdvertRecord record = mock(CarAdvertRecord.class);
        when(ctx.selectFrom(CarAdvert.CAR_ADVERT).where(CarAdvert.CAR_ADVERT.ID.eq(id)).fetchOne()).thenReturn(record);
        when(record.delete()).thenReturn(1);

        boolean result = repository.remove(id);

        assertTrue(result);
        verify(record).delete();
    }

    @Test
    void testRemoveNotFound() {
        long id = 1L;
        when(ctx.selectFrom(CarAdvert.CAR_ADVERT).where(CarAdvert.CAR_ADVERT.ID.eq(id)).fetchOne()).thenReturn(null);

        boolean result = repository.remove(id);

        assertFalse(result);
    }

    @Test
    void testCreateFailure() {
        CarAdvertRecord record = mock(CarAdvertRecord.class);
        when(ctx.newRecord(CarAdvert.CAR_ADVERT)).thenReturn(record);
        when(record.store()).thenReturn(0);

        LocalDateTime now = LocalDateTime.now();
        assertThrows(RuntimeException.class, () -> {
            repository.create("Test Car", "Petrol", 10000, true, 0, now);
        });
    }

    @Test
    void testEditFailure() {
        long id = 1L;
        CarAdvertRecord record = mock(CarAdvertRecord.class);
        when(ctx.selectFrom(CarAdvert.CAR_ADVERT).where(CarAdvert.CAR_ADVERT.ID.eq(id)).fetchOne()).thenReturn(record);
        when(record.store()).thenReturn(0);

        LocalDateTime now = LocalDateTime.now();
        assertThrows(RuntimeException.class, () -> {
            repository.edit(id, "New Title", "Diesel", 12000, false, 100, now);
        });
    }

    @Test
    void testRemoveFailure() {
        long id = 1L;
        CarAdvertRecord record = mock(CarAdvertRecord.class);
        when(ctx.selectFrom(CarAdvert.CAR_ADVERT).where(CarAdvert.CAR_ADVERT.ID.eq(id)).fetchOne()).thenReturn(record);
        when(record.delete()).thenReturn(0);

        assertThrows(RuntimeException.class, () -> {
            repository.remove(id);
        });
    }
}

