-- Insertar clientes
INSERT INTO cliente (id, nombre, email, documento) VALUES
('CLI001', 'Juan Pérez', 'juan.perez@email.com', '12345678'),
('CLI002', 'María García', 'maria.garcia@email.com', '87654321'),
('CLI003', 'Carlos López', 'carlos.lopez@email.com', '11223344');

-- Insertar cuentas
INSERT INTO cuenta (cuenta_id, cliente_id, numero_cuenta, saldo, estado) VALUES
('CTA001', 'CLI001', '0001-234567', 1000.00, 'ACTIVO'),
('CTA002', 'CLI002', '0001-234568', 2500.00, 'ACTIVO'),
('CTA003', 'CLI003', '0001-234569', 500.00, 'ACTIVO');

-- Insertar transacción de ejemplo
INSERT INTO transaccion
(transaccion_id, cuenta_origen_id, cuenta_destino_id, monto, comision, tipo, estado, descripcion)
VALUES
('TXN001', 'CTA001', 'CTA002', 100.00, 5.00, 'TRANSFERENCIA', 'COMPLETADA', 'Transferencia de prueba');