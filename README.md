<h1 align="center">Microserviços de avaliação de criação de trabalhos - Empresa de Software</h1>
<p align="center"><i>Repositório responsável por criar os trabalhos das squads.</i></p>

##  Sobre esse projeto
Este é um projeto que deve ser rodado após o Eureka Server estar rodando, para que se registre no discovery server.


## Indíce
<!--ts-->
   * [Como usar?](#como-usar)
   * [Endpoints](#endpoints)
   * [Creditos](#creditos)
<!--te-->
  
<h1>Como usar?</h1>
<p>O Eureka Server deve estar rodando, acesse-o <a href="https://github.com/ValterGabriell/bank-system-eureka-server">aqui</a>.</br>
<p>Clone ou baixe o repositório e start ele através de sua IDE de preferência rodando o método main da classe principal na pasta raíz da aplicação, feito isso, basta começar a usar :). O ideal é startar todos os outros microserviços antes de testar a aplicação.</p>
<p>Além disso, é fundamental ter um container do RabbitMQ no Docker rodando com usuario e senha padrao (guest, guest) para o microserviço poder enviar o código para a fila.</p>

1 -> <a href="https://github.com/ValterGabriell/bank-system-msaccount">Microserviço responsável por criar contas bancárias dos usuários</a></br>
2 -> <a href="https://github.com/ValterGabriell/bank-system-mscards">Microserviço responsável por criar cartões para os usuários</a></br>
3 -> <a href="https://github.com/ValterGabriell/bank-system-mscreditappraiser">Microserviço responsável verificar o crédito que o usuário terá e solicitar a emissão de cartão</a></br>
4 -> <a href="https://github.com/ValterGabriell/software-company-mslead">Microserviço responsável pela criação dos líderes das squads</a></br>
5 -> <a href="https://github.com/ValterGabriell/software-company-mscolaborators">Microserviço responsável pela criação dos colaboradores das squads</a></br>
6 -> <a href="https://github.com/ValterGabriell/software-company-msjobs">Microserviço responsável pela criação dos trabalhos dos colaboradores</a></br>
7 -> <a href="https://github.com/ValterGabriell/bank-system-gateway">Gateway para fazer o loadbalancer dos microserviços</a></br>
8 -> <a href="https://github.com/ValterGabriell/software-company-msshopping">Micro serviço responsável por gerenciar as compras dos clientes.</a></br>

  
<h1>Endpoints</h1>
<h3>BASE URL</h3>

```bash
http://localhost:8080/job/
``` 
<h1>POST</h1></br>

<h2>Criar trabalho</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/create</td>
    <td>/leadId</td>
    <td>/colaboratorId</td>
  </tr>
</table>


<h3>Request esperada</h3>

```bash

{
	"name":"desenvolver tal coisa",
	"description":"fazer desse jeito isso e aquilo",
	"finishDay":"2025-12-12"
}


```

<h3>Resposta esperada</h3></br>

```bash
{
	"message": "Trabalho para o colaborador com identificação 63856573232 criado. Líder responsável: 93856573232123",
	"jobId": "9d6c3a40-650e-47ad-a657-e5add272c674",
	"url": "http://host.docker.internal:65132/job?leadId=93856573232123&colaboratorId=63856573232&jobId=9d6c3a40-650e-47ad-a657-e5add272c674"
}
```

<h1>GET</h1></br>


<h2>Recuperar um colaborador por ID</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/</td>
    <td>jobId</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
{
	"id": "7d9f35db-daef-4289-bb0b-8f4a541bb978",
	"name": "desenvolver tal coisa",
	"description": "fazer desse jeito isso e aquilo",
	"wantDelete": false,
	"creationDay": "2023-05-08",
	"finishDay": "2025-12-12",
	"leadId": 93856573232123,
	"colaboratorId": 63856573232,
	"canceled": false,
	"finished": false
}
```



<h2>Recuperar todos os trabalhos de um colaborador por ID</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/</td>
    <td>colaboratorId</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
[
	{
		"id": "99b6dcbe-40bd-4265-a57e-a44bb138bd70",
		"name": "desenvolver tal coisa",
		"description": "fazer desse jeito isso e aquilo",
		"wantDelete": false,
		"creationDay": "2023-05-08",
		"finishDay": "2025-12-12",
		"leadId": 93856573232123,
		"colaboratorId": 63856573232,
		"finished": false,
		"canceled": false
	}
]
```

<h2>Recuperar todos os trabalhos criados por um líder por ID</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/</td>
    <td>leadId</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
[
	{
		"id": "99b6dcbe-40bd-4265-a57e-a44bb138bd70",
		"name": "desenvolver tal coisa",
		"description": "fazer desse jeito isso e aquilo",
		"wantDelete": false,
		"creationDay": "2023-05-08",
		"finishDay": "2025-12-12",
		"leadId": 93856573232123,
		"colaboratorId": 63856573232,
		"finished": false,
		"canceled": false
	}
]
```


<h2>Recuperar todos os trabalhos cancelados</h2>
<table>
  <tr>
    <th>Request</th>
  </tr>
  <tr>
    <td>/canceled</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
[
	{
		"id": "99b6dcbe-40bd-4265-a57e-a44bb138bd70",
		"name": "desenvolver tal coisa",
		"description": "fazer desse jeito isso e aquilo",
		"wantDelete": false,
		"creationDay": "2023-05-08",
		"finishDay": "2025-12-12",
		"leadId": 93856573232123,
		"colaboratorId": 63856573232,
		"finished": false,
		"canceled": true
	}
]
```

<h2>Recuperar todos os trabalhos não cancelados</h2>
<table>
  <tr>
    <th>Request</th>
  </tr>
  <tr>
    <td>/not-canceled</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
[
	{
		"id": "99b6dcbe-40bd-4265-a57e-a44bb138bd70",
		"name": "desenvolver tal coisa",
		"description": "fazer desse jeito isso e aquilo",
		"wantDelete": false,
		"creationDay": "2023-05-08",
		"finishDay": "2025-12-12",
		"leadId": 93856573232123,
		"colaboratorId": 63856573232,
		"finished": false,
		"canceled": false
	}
]
```

<h2>Recuperar todos os trabalhos abertos</h2>
<table>
  <tr>
    <th>Request</th>
  </tr>
  <tr>
    <td>/open</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
[
	{
		"id": "99b6dcbe-40bd-4265-a57e-a44bb138bd70",
		"name": "desenvolver tal coisa",
		"description": "fazer desse jeito isso e aquilo",
		"wantDelete": false,
		"creationDay": "2023-05-08",
		"finishDay": "2025-12-12",
		"leadId": 93856573232123,
		"colaboratorId": 63856573232,
		"finished": false,
		"canceled": false
	}
]
```


<h2>Recuperar todos os trabalhos finalizados</h2>
<table>
  <tr>
    <th>Request</th>
  </tr>
  <tr>
    <td>/finished</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
[
	{
		"id": "99b6dcbe-40bd-4265-a57e-a44bb138bd70",
		"name": "desenvolver tal coisa",
		"description": "fazer desse jeito isso e aquilo",
		"wantDelete": false,
		"creationDay": "2023-05-08",
		"finishDay": "2025-12-12",
		"leadId": 93856573232123,
		"colaboratorId": 63856573232,
		"finished": true,
		"canceled": false
	}
]
```


<h1>PUT</h1></br>

<h2>Atualizar a data final do trabalho</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/create</td>
    <td>/jobId</td>
    <td>/finishDate</td>
  </tr>
</table>


<h3>Resposta esperada</h3></br>

```bash
{
	"id": "5b4fb0f9-603c-4349-bd83-1d91a66c6edc",
	"name": "desenvolver tal coisa",
	"description": "fazer desse jeito isso e aquilo",
	"creationDay": "2023-05-08",
	"finishDay": "2026-12-12",
	"leadId": 93856573232123,
	"colaboratorId": 63856573232,
	"canceled": false,
	"finished": false
}
```


<h2>Atualizar um trabalho para cancelado</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/create</td>
    <td>/jobId</td>
  </tr>
</table>


<h3>Resposta esperada</h3></br>

```bash
{
	"id": "9d6c3a40-650e-47ad-a657-e5add272c674",
	"name": "desenvolver tal coisa",
	"description": "fazer desse jeito isso e aquilo",
	"creationDay": "2023-05-08",
	"finishDay": "2025-12-12",
	"leadId": 93856573232123,
	"colaboratorId": 63856573232,
	"canceled": true,
	"finished": false
}
```

<h2>Atualizar um trabalho para finalizado</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/create</td>
    <td>/jobId</td>
  </tr>
</table>


<h3>Resposta esperada</h3></br>

```bash
{
	"id": "9d6c3a40-650e-47ad-a657-e5add272c674",
	"name": "desenvolver tal coisa",
	"description": "fazer desse jeito isso e aquilo",
	"creationDay": "2023-05-08",
	"finishDay": "2025-12-12",
	"leadId": 93856573232123,
	"colaboratorId": 63856573232,
	"canceled": false,
	"finished": true
}
```


<h1>DELETE</h1></br>

<h2>Deletar um trabalho</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/create</td>
    <td>/jobId</td>
  </tr>
</table>


<h3>Resposta esperada</h3></br>

```bash
204 no content
```




<h1>Créditos</h1>

<a href="https://www.linkedin.com/in/valter-gabriel">
  <img style="border-radius: 50%;" src="https://user-images.githubusercontent.com/63808405/171045850-84caf881-ee10-4782-9016-ea1682c4731d.jpeg" width="100px;" alt=""/>
  <br />
  <sub><b>Valter Gabriel</b></sub></a> <a href="https://www.linkedin.com/in/valter-gabriel" title="Linkedin">🚀</ a>
 
Made by Valter Gabriel 👋🏽 Get in touch!

[![Linkedin Badge](https://img.shields.io/badge/-Gabriel-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/valter-gabriel/ )](https://www.linkedin.com/in/valter-gabriel/)

