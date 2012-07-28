create database geract;
create schema geract;
drop schema public;

create or replace function geract.calcula_nota_entrega() returns trigger as
$BODY$
declare
	media double precision;
	nota_nao_atribuida integer;
	status_entrega integer;
BEGIN
	if new.pontuacao is not null then
	
		select sum(a.pontuacao)/count(entrega_id) into media
		from geract.atividades a
		where a.entrega_id = new.entrega_id;

		select count(*) into nota_nao_atribuida
		from geract.atividades a
		where pontuacao is null and a.entrega_id = new.entrega_id;

		if nota_nao_atribuida = 0 then
			status_entrega = 2;
		else
			status_entrega = 3;
		end if;

		update geract.entregas
		set data_realizada = current_date,
		status_entrega_id = status_entrega,
		nota = media
		where entrega_id = new.entrega_id;
		
	end if;
	return new;
END;
$BODY$
language plpgsql;


create trigger calcula_nota_entrega after update on atividades
for each row execute procedure calcula_nota_entrega();
