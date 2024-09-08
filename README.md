# Code-_Vibe
Programming ability test for Core Vibe

***********************************************************************************************************************

1. Podizanje baze:

  Otvorimo terminal:
  >>>docker-compose up

  Otvorimo novi termnial te se u njemu prijavimo na bazu.
  (username: postgres, password: postgres)
  >>>psql -h localhost -p 5432 -U postgres -W

  Ovdije izvršimo DDL za kreiranje tablice te triggera:

  CREATE TABLE IF NOT EXISTS car_advert (id BIGSERIAL PRIMARY KEY,title VARCHAR(255) NOT NULL,fuel_type VARCHAR(50) NOT NULL,price FLOAT NOT NULL,is_new BOOLEAN DEFAULT TRUE,mileage INTEGER NOT   NULL,first_registration TIMESTAMP NOT NULL,crated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

  CREATE OR REPLACE FUNCTION update_updated_at_column()
  RETURNS TRIGGER AS $$
  BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
  END;
  $$ LANGUAGE plpgsql;

  CREATE TRIGGER update_timestamp
  BEFORE UPDATE ON car_advert
  FOR EACH ROW
  EXECUTE FUNCTION update_updated_at_column();

**********************************************************************************************************************

2. Podizanje aplikacije

    Otvorimo terminal te se pozicioniramo u demo5 te izvršimo (MAC OS) 
   
      >>>./gradlew build 
      
   kako bi se izgenerirla .jar datoteka.
     
     Nakon toga pokrenemo aplikaciju:
     >>>java -jar build/libs/demo5-0.0.1-SNAPSHOT.jar

   Swaggere OpenAPI se nalazi na : 
                http://localhost:8080/webjars/swagger-ui/index.html

   Basic autorizacija: username: user, password: password

>>
>>>
>>>>
>>>>>