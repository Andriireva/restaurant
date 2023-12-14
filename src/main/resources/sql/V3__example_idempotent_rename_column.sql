DO
$$
    BEGIN
		ALTER TABLE IF EXISTS public.users RENAME not_exist TO name_column;
    EXCEPTION
        WHEN undefined_column THEN RAISE NOTICE 'column another_table.not_exist does not exist';
    END;
$$;