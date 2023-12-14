-- renaming users. naem -> name
ALTER TABLE IF EXISTS public.users
    RENAME naem TO name;
