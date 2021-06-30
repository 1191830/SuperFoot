create database superfoot
go 
use superfoot

go

create table liga(
ano	 int		  PRIMARY KEY,
nome nvarchar(20)
)

GO

create table jornada(
jornada int          not null	  constraint CK_jogo_jornada check (jornada between 1 and 38),	 
liga    int			 not null	  constraint FK_jornada_liga references liga(ano),
								  constraint PK_jornada_id   PRIMARY KEY (jornada,liga)
)

go

create table pais(
id   int		  identity(1,1) constraint PK_pais_id PRIMARY KEY,
nome nvarchar(30) not null default 'Sem nome',
)

go

create table cidade(
id   int		  identity(1,1) constraint PK_cidade_id   PRIMARY KEY,
nome nvarchar(30) not null      default 'Sem nome',
pais int		  constraint FK_cidade_pais references pais(id)
)

go

create table estadio(
id     int			identity(1,1) constraint PK_estadio_id     PRIMARY KEY,
nome   nvarchar(20)	not null default 'Sem nome',
cidade int			constraint FK_estadio_cidade references cidade(id),
)

go

create table equipa(
id      int			 identity(1,1)   constraint PK_equipa_id      PRIMARY KEY,
nome    nvarchar(15)				 constraint NN_equipa_nome    not null default 'Sem nome',
estadio int							 constraint FK_equipa_estadio references estadio(id)
)

go

create table funcaoPessoa(
id     int          constraint PK_funcaoPessoa_id     PRIMARY KEY,
funcao nvarchar(15)	constraint UK_funcaoPessoa_funcao unique
					not null default '' 					              
)

go

Create table pessoa(
id            int          identity(1,1) constraint PK_pessoa_id   PRIMARY KEY,
nome          nvarchar(50)               not null default 'Zé Ninguém',
nacionalidade int						 constraint FK_pessoa_pais references pais(id),
)

go

create table pessoaEquipa(
id				int  identity(1,1) constraint PK_pessoaEquipa        PRIMARY KEY,
idPessoa		int  not null      constraint FK_pessoaEquipa_pessoa references pessoa(id),
idEquipa		int  not null      constraint FK_pessoaJogo_equipa   references equipa(id),
numeroFavorito  int,
dataInicio      date not null,
dataFim			date,
funcao			int                constraint FK_pessoaEquipa_funcao references funcaoPessoa(id)
)

go

create table parteJogo(
id    int          constraint PK_parteJogo      PRIMARY KEY,
parte nvarchar(30) constraint UK_parteJogo_nome unique not null
)

go

create table arbitro(
id            int          identity(1,1)                constraint PK_arbitro_id   PRIMARY KEY,
idPessoa      int		   constraint FK_arbitro_pessoa references pessoa(id)
							constraint UK_arbitro_pessoa unique not null)

go

create table jogo(
id        int  identity(1,1) constraint PK_jogo primary key,
liga	  int  not null,
jornada   int  not null,
idEstadio int  not null      constraint FK_jogo_estadio references estadio(id),			   
arbitro   int  not null      constraint FK_jogo_arbitro references arbitro(id),
dia       datetime not null,
						     constraint FK_jogo_jornada Foreign Key (jornada,liga) references jornada(jornada, liga) )

go

create table jogoEquipa(
idJogo   int not null    references jogo(id),
idEquipa int not null    references equipa(id),
casaFora BIT not null,
			 constraint PK_jogoEquipa PRIMARY KEY (idJogo, idEquipa),
			 constraint UK_unique_casaFora Unique (idJogo, casaFora))
			
go

create table funcaoJogo(
id   int          constraint PK_funcaoJogo PRIMARY KEY,
nome nvarchar(20) not null)

go

create table estadoEmJogo(
id         int          constraint PK_estadoEmJogo_id PRIMARY KEY,
estadoJogo nvarchar(15) not null
)

go

create table pessoaJogo(
idPessoa     int references pessoa(id),
idEquipa     int not null,
idJogo       int not null,
funcaoJogo   int not null constraint FK_pessoaJogo_funcao	  references funcaoJogo(id),
estadoEmJogo int not null constraint FK_pessoaJogo_estado	  references estadoEmJogo(id)
						  constraint PK_pessoaJogo            PRIMARY KEY (idPessoa, idEquipa, idJogo),
						  constraint FK_pessoaJogo_jogoEquipa foreign key (idJogo, idEquipa) 
					references jogoEquipa(idJogo,idEquipa)
		   )

go

create table tipoEvento(
id   int          constraint PK_tipoEvento_id   PRIMARY KEY,
tipo nvarchar(20) not null)

go

create table evento(
id           int identity(1,1) PRIMARY KEY,
idJogo       int not null, 
idEquipa     int not null, 
idPessoa     int not null ,
idTipoEvento int constraint FK_evento_tipoEvento	  references tipoEvento(id) not null ,
minuto       int not null	constraint CK_evento_minuto check (minuto >= 0) default 1,
parte		 int constraint FK_evento_parteJogo		  references parteJogo(id)  
			 not null default 1,
			 constraint FK_evento_jogoEquipa Foreign Key (idPessoa, idEquipa, idJogo)
				references pessoaJogo(idPessoa, idEquipa, idJogo))