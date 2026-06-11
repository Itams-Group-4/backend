-- Esta funcion sirve para cambiar cuando hay un mantenimiento cambiar automaticamente a en mantenimiento

CREATE OR REPLACE FUNCTION fn_asset_to_maintenance()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status IN ('pending', 'in_progress') THEN
        UPDATE asset SET status = 'in_maintenance'
        WHERE id = NEW.asset_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_asset_to_maintenance
AFTER INSERT ON maintenance
FOR EACH ROW EXECUTE FUNCTION fn_asset_to_maintenance();