--PROCEDURES

--procedure para mudar o estado do jogador que entra
CREATE procedure entrada @pessoa int, @jogo int
as
begin try
	update pessoaJogo
	set estadoEmJogo = 2 where idPessoa = @pessoa and idjogo = @jogo --passa o estado da pessoa para em campo
	end try
BEGIN CATCH  
    SELECT ERROR_MESSAGE() AS ErrorMessage;  
END CATCH;

GO

--procedure para mudar o estado do jogador que sai
CREATE procedure saida @pessoa int, @jogo int
as
update pessoaJogo
set estadoEmJogo = 3 where idPessoa = @pessoa and idjogo = @jogo --muda o estado da pessoa para equipa técnica no jogo
															--ao mudar para equipa tecnica a pessoa continua a poder ser expulsa e levar cartoes
															-- no entanto nao pode voltar a entrar
GO

--procedure para mudar o estudo do jogador expulso
CREATE procedure expulso @pessoa int, @jogo int
as
update pessoaJogo
set estadoEmJogo = 0 where idPessoa = @pessoa and idJogo = @jogo --muda o estado da pessoa para expulso no jogo respetivo

GO

--SCALAR FUNCTIONS

--funcao para verificar se o golo ou auto golo marcados foi por um jogador em campo
CREATE function confirmaGolo (@jogador int, @jogo int)
returns bit
as
begin
	declare @estadoEmCampo int
	--verifica o estado em jogo do jogador no jogo atual
	select @estadoemCampo = estadoEmJogo from pessoaJogo as pj where pj.idPessoa = @jogador and idjogo = @jogo
	--se o jogador nao estiver em campo retorna 0
	if @estadoEmCampo = 0 OR @estadoEmCampo = 1 OR @estadoEmCampo = 3
		return 0

return 1
end

GO

--funcao para contar golos de uma equipa num jogo
CREATE function contarGolosJogo(@jogo int, @equipa int)
returns int
begin
declare @golos int

select @golos =	count(id) from evento where idJogo = @jogo and idequipa = @equipa and idTipoEvento = 1

return @golos
end

GO

--funcao para contar golos sofridos de uma equipa num jogo
CREATE function func_ContarGolosSofridosJogo(@jogo int, @equipa int)
returns int
begin
declare @golos int

select @golos =	count(id) from evento where idJogo = @jogo  and idequipa <> @equipa and idTipoEvento = 1

return @golos
end

GO

--funcao auxiliar do trigger repetidos para verificar jogos casa fora repetidos
CREATE function func_getRepetidos(@equipa1 int,@equipa2 int, @casafora int)
returns bit
as
begin
declare @count int,
		@idjogo int
declare cursor_repetidos cursor
--queremos ir verificar os jogos todos da equipa 2 onde esta jogou no lado contrario da equipa 1
for Select idJogo from jogoEquipa 
where idequipa = @equipa2 and casaFora <> @casafora

Open cursor_repetidos
FETCH NEXT FROM cursor_repetidos
into @idjogo
while @@fetch_status = 0 
begin
	--se existir algum jogo entre as equipas nao pode haver um segundo
	if exists(select top 1 1 from jogoEquipa where idjogo= @idjogo and idEquipa = @equipa1 )
	begin
		return  1
	end

FETCH NEXT FROM cursor_repetidos
	into @idjogo
end
close cursor_repetidos
deallocate cursor_repetidos
return 0
end

GO

--Funcao para contar os pontos de uma equipa mediante o seu resultado
create function funcPontosEquipa (@vencedor char(1))
returns int
as begin
declare @pontos int

if @vencedor like 'V'
	set @pontos = 3
if @vencedor like 'D'
	set @pontos = 0
if @vencedor like 'E'
	set @pontos = 1

return @pontos
end

GO

--funcao que conta os golos de duas equipas e verifica o resultado, recebe um jogo e uma equipa e vai buscar a equipa contraria
CREATE function verResultadoEquipaJogo (@jogo int, @equipa int)
returns char(1)
as
begin
--conta os golos da equipa1
declare @golosEquipa int =  dbo.contarGolosJogo(@jogo, @equipa)
--vai buscar a equipa contraria da 1 no jogo escolhido
declare @equipa2 int = (Select idequipa from jogoEquipa where idjogo = @jogo and idEquipa <> @equipa)
--conta os golos da equipa 2
declare @golosEquipa2 int = dbo.contarGolosJogo(@jogo, @equipa2)

--verifica quem ganhou
if @golosEquipa > @golosEquipa2
	return 'V'
else if @golosEquipa < @golosequipa2
	return 'D'

	--se nenhuma ganhou e empate
return 'E'

end

GO

--TABLE FUNCTIONS

--funcao que constroi as linhas de resultados para a funcao func_retornaResultado
CREATE function func_retornaResultado (@jogo int)
returns @tabelaResultados table (liga int, jornada int, equipa1 int, equipaCasa nvarchar(50), resultado1 int, 
equipa2 int,equipaFora nvarchar(50), resultado2 int)

as
begin
declare @equipa1 int, @resultado1 int, @equipa2 int, @resultado2 int, @liga int, @jornada int
declare @equipaCasa nvarchar(50), @equipaFora nvarchar(50)

--liga do jogo escolhido para ser possivel filtrar por ligas
Select @liga = liga, @jornada = jornada from jogo where id = @jogo

--vai buscar as equipas casa e fora do jogo e os golos que estas marcaram
set @equipa1 = (Select idEquipa from jogoEquipa where idJogo = @jogo and casaFora = 0)
set @equipa2 = (Select idEquipa from jogoEquipa where idJogo = @jogo and casaFora = 1)
set @resultado1 = dbo.contarGolosJogo(@jogo,@equipa1)
set @resultado2 = dbo.contarGolosJogo(@jogo,@equipa2)

--nomes das equipas para apresentar em vez do id
Select @equipaCasa = nome from equipa where id = @equipa1
Select @equipaFora = nome from equipa where id = @equipa2

insert into @tabelaResultados(liga, jornada, equipa1, equipaCasa, resultado1, equipa2, equipaFora, resultado2)
values(@liga, @jornada, @equipa1, @equipaCasa, @resultado1, @equipa2, @equipaFora, @resultado2)

return 
end

GO

--retorna os dados de um jogador num jogo para a funcao func_dadosJogadorTodos
CREATE function retornaDadosJogador (@jogo int , @jogador int)
returns table
as

return select  @jogo as 'idJogo',@jogador as 'idjogador',
(Select liga from jogo where id = @jogo) as 'Liga',
(Select jornada from jogo where id = @jogo) as 'Jornada',
(Select nome from pessoa where id = @jogador) as 'jogador',
(select count(idTipoEvento) from evento where idjogo = @jogo and idPessoa = @jogador and idTipoEvento = 1) as 'Golos',
(select count(idTipoEvento) from evento where idjogo = @jogo and idPessoa = @jogador and idTipoEvento = 2) as 'Golos anulados',
(select count(idTipoEvento) from evento where idjogo = @jogo and idPessoa = @jogador and idTipoEvento = 3) as 'amarelo',
(select count(idTipoEvento) from evento where idjogo = @jogo and idPessoa = @jogador and idTipoEvento = 4) as 'amarelo duplos', 
(select count(idTipoEvento) from evento where idjogo = @jogo and idPessoa = @jogador and idTipoEvento = 5) as 'vermelho'

GO

--retorna os dados de uma equipa num jogo para a funcao func_retornaDadosJogo
CREATE function retornaDadosJogo (@jogo int , @equipa1 int)
returns table
as

return 
	select  @jogo as 'idJogo',@equipa1 as 'idEquipa',
	(Select liga from jogo where id = @jogo) as 'liga',
	(Select jornada from jogo where id = @jogo) as 'jornada',
	(Select nome from equipa where id = @equipa1) as 'equipa',
	dbo.contarGolosJogo(@jogo,@equipa1) as 'golos',
	dbo.func_ContarGolosSofridosJogo(@jogo,@equipa1) as 'golos Sofridos',
	(select count(idTipoEvento) from evento where idjogo = @jogo and idequipa = @equipa1 and idTipoEvento = 3) as 'amarelos',
	(select count(idTipoEvento) from evento where idjogo = @jogo and idequipa = @equipa1 and idTipoEvento = 4) as 'Duplo Amarelos', 
	(select count(idTipoEvento) from evento where idjogo = @jogo and idequipa = @equipa1 and idTipoEvento = 5) as 'vermelhos',
	(select dbo.verResultadoEquipaJogo(@jogo, @equipa1)) as 'resultado',
	(select dbo.funcPontosEquipa(dbo.verResultadoEquipaJogo(@jogo, @equipa1))) as 'pontos',
	(Select casaFora from jogoEquipa where idJogo = @jogo and idEquipa = @equipa1) as 'casaFora'

--funcao que retorna todos os dados dos jogadores escolhidos em determinados jogos, podemos escolher o jogo, jogador ou tudo
CREATE function func_dadosJogadorTodos (@idjogo int= -1,@idPessoa int = -1)

--dados a retornar
returns @tabelaDadosJogadores TABLE(idJogo int, idJogador int, liga int, jornada int, 
jogador nvarchar (50), golos int, golosanulados int, amarelo int, amareloduplos int, vermelho int)
as
begin

declare cursor_dados cursor
	--jogador e jogo a procurar se -1 retorna tudo 
for Select idJogo, idPessoa from pessoaJogo
		where (@idJogo = -1 or idJogo = @idJogo) and (@idPessoa = -1 or idpessoa = @idPessoa) 

Open cursor_dados

FETCH NEXT FROM cursor_dados
into @idjogo,@idPessoa

while @@fetch_status = 0
begin
--inserir os dados na tabela que sao retornados pela funcao que vai buscar os dados
	insert into @tabelaDadosJogadores (idJogo, idJogador, liga, jornada, jogador, 
		golos, golosanulados, amarelo, amareloduplos, vermelho) 
	select idJogo,idJogador, liga, jornada, jogador,golos, [golos anulados],
		amarelo,[amarelo duplos],vermelho from retornaDadosJogador(@idJogo,@idPessoa)
	FETCH NEXT FROM cursor_dados
		into @idjogo,@idPessoa
end

close cursor_dados
deallocate cursor_dados
return 
end

GO

--funcao que returna todos os dados de uma equipa escolhida num jogo
CREATE function func_dadosJogosTodos (@idJogo int = -1, @idEquipa int = -1)
returns @tabelaDadosJogos TABLE(idJogo int, idEquipa int, idLiga int, idJornada int, 
equipa nvarchar (60), golos int, golosSofridos int, amarelos int, amareloduplos int, vermelhos int,resultado char(1), pontos int, casaFora Bit)
as
begin

declare cursor_dados cursor
--vai buscar os jogos e equipas escolhidas -1 retorna tudo
for Select idJogo, idEquipa from jogoEquipa 
where (@idJogo = -1 or idJogo = @idJogo) and (@idEquipa = -1 or idEquipa = @idEquipa) 
order by idJogo

Open cursor_dados
FETCH NEXT FROM cursor_dados
into @idjogo,@idEquipa

while @@fetch_status = 0 
begin
	insert into @tabelaDadosJogos 
		(idJogo, idEquipa, idliga, idjornada, 
			equipa,golos, golosSofridos, amarelos, amareloduplos, vermelhos,resultado,pontos, casaFora) 
	select 
		idJogo,idequipa, liga, jornada, equipa,golos, [golos sofridos], amarelos,[duplo amarelos],
		vermelhos,resultado,pontos, casaFora
	from dbo.retornaDadosJogo(@idJogo,@idEquipa)
		
	FETCH NEXT FROM cursor_dados
	into @idjogo,@idEquipa
end
close cursor_dados
deallocate cursor_dados
return 
end

GO

--funcao que retorna o resultado de um jogo numa linha
CREATE function func_resultadoTodos (@idJogo int = -1)
returns @tabelaResultados TABLE(liga int, jornada int, idEquipa1 int, equipaCasa nvarchar (60), golosCasa int, 
idEquipa2 int, equipaFora nvarchar (60), golosFora int)
as
begin

declare cursor_dados cursor
--retorna o jogo selecionado -1 retorna os jogos todos
for Select idJogo from jogoEquipa 
where (@idJogo = -1 or idJogo = @idJogo) 
order by idJogo

Open cursor_dados
FETCH NEXT FROM cursor_dados
into @idjogo

while @@fetch_status = 0 
begin
	insert into @tabelaResultados 
		(liga, jornada, idEquipa1, equipaCasa, golosCasa, 
idEquipa2, equipaFora, golosFora)
	select 
		liga, jornada, equipa1, equipaCasa, resultado1, equipa2, equipaFora, resultado2
	from func_retornaResultado(@idJogo)
		
	FETCH NEXT FROM cursor_dados
	into @idjogo
end
close cursor_dados
deallocate cursor_dados
return 
end

GO

--TRIGGERS

--Altera o estado da pessoa mediante o tipo de evento
CREATE Trigger verificaEvento
on evento
for insert
as
begin
	declare @flag bit
	declare @evento int, @pessoa int, @jogo int, @estadoJogo int, @id int

	--Tabela temporaria para permitir varios inserts
	select *
	into #inserted_data 
	from inserted

	--Enquanto houver dados na tabela temporaria
	while ( exists(select id from #inserted_data) )
		Begin
		
		select @id = id, @evento = idTipoEvento, @pessoa = idPessoa, @jogo = idJogo from #inserted_data

		--o estado no jogo da pessoa inseriada (expulso, em campo, banco ou equipa tecnica
		select @estadoJogo = estadoEmJogo from pessoaJogo where idPessoa = @pessoa and idJogo = @jogo

		--Se o evento for golo ou auto golo vamos verificar que nao foi marcado por alguem que nao esteja em campo
		if @evento = 1 OR @evento = 2
			set @flag = dbo.confirmaGolo(@pessoa, @jogo)
		--se o evento for vermelho ou duplo amarelo
		if @evento = 5 OR @evento = 4
			begin
			--verifica se ja se encontra expulso
			if @estadoJogo = 0
				begin
				set @flag = 0
				end
			else
			--se nao estiver expulso, muda o estado para expulso
				begin
					exec dbo.expulso @pessoa, @jogo
				end
			end
		--vamos verificar que o jogador que entra se encontra no banco
		if @evento = 6
			begin
			--se o estado for diferente de banco
			if @estadoJogo <> 1
				begin
				set @flag = 0
				end
			else
			--se estiver no banco entra
				begin
					exec dbo.entrada @pessoa, @jogo
				end
			end
		--a mesma coisa para saida
		if @evento = 7
			begin
			--se nao estiver em campo
			if @estadoJogo <> 2
				begin
				set @flag = 0
				end
			else
			--se estiver em campo sai
				begin
					exec dbo.saida @pessoa, @jogo
				end
			end

		--se a flag estiver a 0 significa que alguma condicao nao foi cumprida
		if @flag = 0
			begin
				;throw 51000,'Impossivel inserir',1
			end

		--apagar o id atual
		delete from #inserted_data
		where id = @id
		--fim do while
		end
end

GO

--Impede a insercao de jogos duplicados Casa e Fora
CREATE trigger tg_jogoEquipa
on jogoEquipa
instead of insert, update
as
declare @idjogo int,
		@equipa1 int,
		@equipa2 int,
		@casafora bit,
		@resultado bit,
		@flag bit = 1

select @idjogo = idjogo, @equipa1 = idequipa,@casafora=casaFora from inserted

declare cursor_duplicados cursor

for Select idEquipa from jogoEquipa 
where idJogo = @idjogo

Open cursor_duplicados
FETCH NEXT FROM cursor_duplicados
into @equipa2
while @@fetch_status = 0 
begin

	--funcao que retorna se existiram mais que um jogos entre as equipas
	set @resultado = dbo.func_getRepetidos(@equipa1,@equipa2, @casafora);
	

	if (@resultado = 1)
	begin
	--	se nao der para inserir queremos eliminar o jogo anterior para nao ficar com jogos com uma equipa
		delete from jogoEquipa where idJogo = @idjogo
		--flag para avisar que algo deu errado no fim
		set @flag = 0
	--Queremos receber mensagem de erro
		RAISERROR (15600,-1,-1, 'excluirEquipa'); 
	end
	
	FETCH NEXT FROM cursor_duplicados
	into @Equipa2
end
close cursor_duplicados
deallocate cursor_duplicados

--se tudo estiver correto insere a flag so esta a 1 se nao tiver entrado no delete
if(@flag=1)
insert into jogoEquipa values (@idjogo,@equipa1,@casafora)

GO

--Trigger para defenir o estado de jogo inicial das pessoas
CREATE Trigger setEstadoJogadorInicio
on pessoaJogo
after insert
as
begin

    update pessoaJogo
    set estadoEmJogo =
    case
        when funcaoJogo = 1 then 2 --se a funcao inicial for titular o estado defenido é campo
        when funcaoJogo = 2 then 1 --se a funcao inicial for suplente o estado defenido é banco
        when funcaoJogo = 3 then 3 --se o estado inicial for Equipa Técnica o estado defenido é equipa técnica
    end
end

GO

--Trigger para verificar que a pessoa nao assina por 2 clubes ao mesmo tempo (Figo)
create trigger trg_verificaContrato
on pessoaEquipa
instead of insert
as

declare @jogador int, @equipa int, @dataInicio date, @dataFim date, @funcao int
select @jogador = idPessoa, @equipa = idEquipa, @dataInicio = datainicio, 
		@dataFim = dataFim, @funcao = funcao from inserted

--Se existir um registo nulo significa que não ha contrato, esta linha é redundante uma vez que nao pode ser null
if exists(select top 1 1 from pessoaEquipa where idPessoa = @jogador and datainicio is null)	
	begin
		insert into pessoaEquipa(idPessoa, idEquipa,dataInicio, dataFim, funcao)
			values(@jogador, @equipa, @dataInicio, @dataFim, @funcao)
	end
--nao existe nenhum contrato sem data de fim ou seja nao tem contrato atual
else if not exists(select top 1 1 from pessoaEquipa where idPessoa = @jogador and datafim is null)
	begin
		insert into pessoaEquipa(idPessoa, idEquipa,dataInicio, dataFim, funcao)
			values(@jogador, @equipa, @dataInicio, @dataFim, @funcao)
	end
else
	begin
		RAISERROR (15600,-1,-1, 'errorContract'); 
	end

GO

--VIEWS

CREATE view classificacao
as
select distinct * from func_dadosJogosTodos()

GO

CREATE view jogadoresEquipas
as
  select equipa.nome as equipa, pessoa.nome as jogador, pessoa.id as idPessoa, pais.nome as nacionalidade from equipa
  inner join pessoaEquipa
  on  equipa.id = pessoaEquipa.idEquipa
  inner join pessoa 
  on pessoaEquipa.idPessoa = pessoa.id
  inner join pais
  on pessoa.nacionalidade = pais.id

GO

--view para ver informacao completa de um jogo liga, joranda, data, equipa,estadio e se jogou em casa ou fora
create view jogo_JogoEquipa as
select  j.liga,j.jornada,j.dia as 'data', jogoEquipa.idEquipa,
jogoEquipa.idJogo, jogoEquipa.casaFora, equipa.nome as clube, estadio.nome as estadio
from jogo j
inner join jogoEquipa on j.id = jogoEquipa.idJogo
inner join estadio on j.idEstadio = estadio.id
inner join equipa on jogoEquipa.idEquipa = equipa.id

GO

--view para ver informacao de uma cidade
Create view vcidadePais
as
select C.id as 'cidade_id', C.nome as 'cidade_nome', P.id as 'pais_id', P.nome as 'pais_nome' from Cidade C
inner join  pais  P on
C.pais = P.id

GO

--view para ver informacao da equipa e seu estadio
create view vEquipaEstadio
as
Select Eq.id as 'id_equipa' , Eq.nome as 'nome_equipa', Es.id as 'id_estadio', Es.nome as 'nome_estadio', Es.cidade  from equipa Eq
inner join estadio Es on
Eq.estadio = Es.id

GO

--view para ver informacao de uma equipa pontos,golos, golos sofrido, amarelos e expulsoes, possibilidade de filtrar por liga jornada etc
CREATE view view_DadosEquipas
as
select idliga, idjornada, idEquipa, equipa, sum(pontos) as pontuacao ,  sum(golos) as golos, sum(golosSofridos) as [Golos Sofridos],sum(amarelos) as amarelos, 
	sum(amareloduplos + vermelhos) as Expulsoes
		from func_dadosJogosTodos(-1,-1) group by idliga, idjornada, equipa, idequipa

GO

--view para ver as estatisticas de um jogador, possibilidade de filtrar por liga, jornada, jogador etc
create view view_DadosJogadores
as
select liga, jornada, idjogador,jogador, sum(golos) as golos, sum(golosanulados) as [golos anulados], sum(amarelo) as amarelos, sum(vermelho) as vermelho  
from func_dadosJogadorTodos(-1,-1) group by liga, jornada, idjogador, jogador

GO

--view para ver as estatisticas de um jogador, possibilidade de filtrar por liga, jogador etc
create view view_melhorMarcador
as
select liga, idjogador, jogador, sum(golos) as golos from func_dadosJogadorTodos(-1,-1)  group by liga, idjogador, jogador

GO

create view vPessoasSemFuncoes
as
select pessoa.id AS 'idPessoa', nome as 'nomePessoa' from pessoa 
where pessoa.id not in (select idPessoa from pessoaEquipa) and pessoa.id not in(Select idPessoa from arbitro)
GO
--ADDONS

CREATE procedure proc_estatisticasEquipas
as
begin
declare @idJogo int, @idEquipa int
DECLARE @tabelaDadosJogos TABLE(idJogo int, idEquipa int, equipa varchar (60), golos int, amarelo int, amareloduplos int, vermelho int)
declare cursor_dados cursor
for Select idJogo, idEquipa from pessoaJogo order by idJogo

Open cursor_dados

FETCH NEXT FROM cursor_dados
into @idjogo,@idEquipa
while @@fetch_status = 0
begin

insert into @tabelaDadosJogos (idJogo, idEquipa, equipa, golos, amarelo, amareloduplos, vermelho) 
select idJogo,[id equipa],equipa,golos,amarelo,[amarelo duplos],vermelho from retornaDadosJogo(@idJogo,@idEquipa)

FETCH NEXT FROM cursor_dados
into @idjogo,@idEquipa
end
close cursor_dados
deallocate cursor_dados
return select * from @tabelaDadosJogos

end

GO

create procedure proc_VerificaDuplicados @liga int,@equipaCasa int, @equipaFora int,@jogo int
as 
Begin
declare @idJogo int,
		@flag1 int = 0,
		@flag2 int =0,
		@count int

declare cursor_liga_duplicados cursor

for Select id from jogo 
where @liga = liga

Open cursor_liga_duplicados
FETCH NEXT FROM cursor_liga_duplicados
into @idjogo
while @@fetch_status = 0 
begin
	
	select TOP 1 @flag1=1 from jogoEquipa where idJogo = @idjogo and idEquipa =  @equipaCasa
	select TOP 1 @flag2=1 from jogoEquipa where idJogo = @idjogo and idEquipa = @equipaFora

	if(@flag1 = 1 and @flag2 = 1)
	begin
	set	@count = @count + 1
	end

	if(@count > 2)
	begin
	;THROW 50001,'Já existem dois jogos das mesmas equipas na liga', 1;
	end
	else
		begin
		insert into jogoEquipa values(@jogo,@equipaCasa,0)
		insert into jogoEquipa values(@jogo,@equipaFora,1)
		end

	FETCH NEXT FROM cursor_dados
	into @idjogo
end
close cursor_dados
deallocate cursor_dados

end
GO