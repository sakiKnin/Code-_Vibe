CREATE TABLE IF NOT EXISTS car_advert (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    fuel_type VARCHAR(50) NOT NULL,
    price FLOAT NOT NULL,
    is_new BOOLEAN DEFAULT TRUE,
    mileage INTEGER NOT NULL,
    first_registration TIMESTAMP NOT NULL,
    crated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

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