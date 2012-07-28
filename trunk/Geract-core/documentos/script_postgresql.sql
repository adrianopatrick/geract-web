set search_path to geract;

CREATE FUNCTION calcula_nota_entrega() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
	media numeric(4,2);
	nota_nao_atribuida integer;
	status_entrega integer;
	prova_bimestre integer;
	equipe integer;
	aluno integer;
	existe integer;
BEGIN
	if new.pontuacao is not null then
	
		select sum(a.pontuacao)/count(entrega_id) into media
		from geract.atividades a
		where a.entrega_id = new.entrega_id;

		select count(*) into nota_nao_atribuida
		from geract.atividades a
		where pontuacao is null and a.entrega_id = new.entrega_id;

		if nota_nao_atribuida = 0 then
			status_entrega = 3;
		else
			status_entrega = 2;
		end if;

		update geract.entregas
		set data_realizada = current_date,
		status_entrega_id = status_entrega,
		nota = media
		where entrega_id = new.entrega_id;

		if  new.pontuacao<>0 then
			select  equipe_id into equipe
			from geract.entregas
			where entrega_id = new.entrega_id;

			if current_date <= '2012-10-01'::date then
				prova_bimestre = 1;
			else
				prova_bimestre = 2;
			end if;

			for aluno in select aluno_id from geract.alunos where equipe_id = equipe loop

				select count(*) into existe 
				from geract.notas
				where aluno_id = aluno and entrega_id = new.entrega_id and equipe_id = equipe;

				if existe = 0 then
					insert into geract.notas (nota_id, data_cadastro, prova, aluno_id, entrega_id, equipe_id)
					values (nextval('geract.seq_notas'), now(), prova_bimestre, aluno, new.entrega_id, equipe);
				end if;
				
			end loop;
		end if;
		
	end if;
	return new;
END;
$$;

CREATE TRIGGER calcula_nota_entrega
  AFTER UPDATE
  ON geract.atividades
  FOR EACH ROW
  EXECUTE PROCEDURE geract.calcula_nota_entrega();

--
-- TOC entry 1839 (class 0 OID 1043657)
-- Dependencies: 147
-- Data for Name: status_atividade; Type: TABLE DATA; Schema: geract; Owner: patrick
--

INSERT INTO status_atividade VALUES (1, 'Cadastrada');
INSERT INTO status_atividade VALUES (2, 'Conclusao');
INSERT INTO status_atividade VALUES (3, 'Pendente');


--
-- TOC entry 1840 (class 0 OID 1043662)
-- Dependencies: 148
-- Data for Name: status_entrega; Type: TABLE DATA; Schema: geract; Owner: patrick
--

INSERT INTO status_entrega VALUES (1, 'Cadastrada');
INSERT INTO status_entrega VALUES (3, 'Finalizada');
INSERT INTO status_entrega VALUES (2, 'Parcialmente Finalizada');
