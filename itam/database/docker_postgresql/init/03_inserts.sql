INSERT INTO users (id, name, email, password, role, created_at) VALUES
(1, 'Carlos Lopez', 'carlos.lopez@itam.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin', '2025-01-10 08:00:00'),
(2, 'Ana Martinez', 'ana.martinez@itam.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'technician', '2025-01-15 09:30:00'),
(3, 'Luis Garcia', 'luis.garcia@itam.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'technician', '2025-02-01 10:00:00'),
(4, 'Sofia Ramirez', 'sofia.ramirez@itam.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'technician', '2025-02-20 11:15:00'),
(5, 'Pedro Hernandez', 'pedro.hernandez@itam.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'technician', '2025-03-05 14:20:00');

INSERT INTO asset (
    id,
    asset_name,
    asset_type,
    model,
    serial_number,
    location,
    status,
    acquisition_date,
    acquisition_value,
    observations,
    responsible_id
)
VALUES
(1, 'Laptop Dell Latitude 5420', 'Laptop', 'Latitude 5420', 'DLL-5420-001', 'Oficina Central', 'active', '2023-05-12', 1200.00, 'Equipo administrativo', 2),
(2, 'Laptop HP ProBook 450', 'Laptop', 'ProBook 450 G8', 'HP-450-002', 'Oficina Central', 'in_maintenance', '2023-06-20', 1100.00, 'Presenta lentitud', 3),
(3, 'Servidor Principal', 'Server', 'PowerEdge R740', 'SRV-740-003', 'Data Center', 'active', '2022-09-10', 8500.00, 'Servidor de producción', 1),
(4, 'Impresora Epson L6270', 'Printer', 'L6270', 'EPS-6270-004', 'Recepción', 'inactive', '2024-01-15', 450.00, 'Sin uso actualmente', 4),
(5, 'Switch Cisco 2960', 'Network', 'Catalyst 2960', 'CIS-2960-005', 'Data Center', 'active', '2023-03-11', 1800.00, 'Core de red', 1),
(6, 'Laptop Lenovo ThinkPad T14', 'Laptop', 'T14 Gen 3', 'LNV-T14-006', 'Finanzas', 'active', '2024-04-02', 1450.00, 'Asignada a finanzas', 5),
(7, 'Monitor Samsung 27', 'Monitor', 'S27R350', 'SAM-027-007', 'RRHH', 'retired', '2021-02-15', 220.00, 'Pantalla dañada', 4),
(8, 'PC Escritorio OptiPlex', 'Desktop', 'OptiPlex 7090', 'DLL-7090-008', 'Desarrollo', 'active', '2024-05-10', 980.00, 'Equipo de desarrollo', 2);

INSERT INTO license (
    id,
    software_name,
    license_key,
    expiration_date,
    status,
    asset_id,
    responsible_id
)
VALUES
(1, 'Microsoft Office 365', 'OFF-365-AAA111', '2027-01-10', 'active', 1, 2),
(2, 'Windows 11 Pro', 'WIN11-BBB222', '2028-05-12', 'active', 1, 2),
(3, 'Adobe Acrobat Pro', 'ADO-CCC333', '2025-04-30', 'expired', 2, 3),
(4, 'VMware ESXi', 'VWM-DDD444', '2027-12-31', 'active', 3, 1),
(5, 'Windows Server 2022', 'WSR-EEE555', '2028-09-10', 'active', 3, 1),
(6, 'Cisco Packet Tracer', 'CPT-FFF666', '2026-12-31', 'active', 5, 1),
(7, 'Microsoft SQL Server', 'SQL-GGG777', '2025-12-31', 'active', 8, 2),
(8, 'Power BI Pro', 'PBI-HHH888', '2025-03-01', 'expired', 6, 5);

INSERT INTO maintenance (
    id,
    asset_id,
    scheduled_date,
    type,
    status,
    description,
    technician_id
)
VALUES
(1, 2, '2025-06-15', 'corrective', 'pending', 'Cambio de disco SSD', 2),
(2, 3, '2025-07-10', 'preventive', 'completed', 'Limpieza interna y actualización firmware', 3),
(3, 5, '2025-06-20', 'preventive', 'in_progress', 'Revisión de puertos y conexiones', 4),
(4, 1, '2025-05-10', 'corrective', 'completed', 'Reemplazo de batería', 2),
(5, 8, '2025-08-01', 'preventive', 'pending', 'Mantenimiento general', 3),
(6, 4, '2025-04-15', 'corrective', 'cancelled', 'Falla reportada no reproducible', 5);

INSERT INTO asset_assignment (
    id,
    asset_id,
    user_id,
    assigned_date,
    returned_date,
    notes
)
VALUES
(1, 1, 2, '2025-01-10', NULL, 'Equipo principal de trabajo'),
(2, 2, 3, '2025-02-01', NULL, 'Pendiente de mantenimiento'),
(3, 3, 1, '2024-09-15', NULL, 'Administrador del servidor'),
(4, 4, 4, '2025-01-20', '2025-03-10', 'Equipo retirado temporalmente'),
(5, 5, 1, '2024-10-05', NULL, 'Responsable de infraestructura'),
(6, 6, 5, '2025-04-03', NULL, 'Uso departamento finanzas'),
(7, 8, 2, '2025-05-15', NULL, 'Equipo para desarrollo backend'),
(8, 1, 3, '2024-08-01', '2024-12-15', 'Asignación histórica');

SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('asset_id_seq', (SELECT MAX(id) FROM asset));
SELECT setval('license_id_seq', (SELECT MAX(id) FROM license));
SELECT setval('maintenance_id_seq', (SELECT MAX(id) FROM maintenance));
SELECT setval('asset_assignment_id_seq', (SELECT MAX(id) FROM asset_assignment));
