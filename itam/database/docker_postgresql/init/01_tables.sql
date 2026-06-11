CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'technician' CHECK (role IN ('admin', 'technician')),
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE asset (
    id BIGSERIAL PRIMARY KEY,
    asset_name VARCHAR(255) NOT NULL,
    asset_type VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    serial_number VARCHAR(255) NOT NULL UNIQUE,
    location VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL CHECK (status IN ('active','inactive','in_maintenance','retired')),
    acquisition_date DATE NOT NULL DEFAULT CURRENT_DATE,
    acquisition_value NUMERIC(12,2),
    observations TEXT,
    responsible_id BIGINT REFERENCES users(id) ON DELETE SET NULL
);
CREATE TABLE license (
    id BIGSERIAL PRIMARY KEY,
    software_name VARCHAR(255) NOT NULL,
    license_key VARCHAR(255),
    expiration_date DATE,
    status VARCHAR(50) NOT NULL DEFAULT 'active' CHECK (status IN ('active','expired','cancelled')),
    asset_id BIGINT REFERENCES asset(id),
    responsible_id BIGINT REFERENCES users(id)
);
CREATE TABLE maintenance (
    id BIGSERIAL PRIMARY KEY,
    asset_id BIGINT NOT NULL REFERENCES asset(id),
    scheduled_date DATE NOT NULL,
    type VARCHAR(50) NOT NULL CHECK (type IN ('preventive','corrective')),
    status VARCHAR(50) NOT NULL DEFAULT 'pending' CHECK (status IN ('pending','in_progress','completed','cancelled')),
    description TEXT,
    technician_id BIGINT REFERENCES users(id)
);
CREATE TABLE asset_assignment (
    id BIGSERIAL PRIMARY KEY,
    asset_id BIGINT NOT NULL REFERENCES asset(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    assigned_date DATE NOT NULL DEFAULT CURRENT_DATE,
    returned_date DATE,
    notes TEXT
);
