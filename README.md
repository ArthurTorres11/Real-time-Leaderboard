# 🏆 RankUp - Sistema de Ranqueamento em Tempo Real

Um sistema completo de **leaderboard** com ranking de usuários atualizado em **tempo real**. Utiliza banco de dados relacional (MySQL) para persistência, e **Redis Sorted Set** para cálculo eficiente das posições e ranks dos usuários.

---

## 🚀 Visão Geral

Este projeto foi desenvolvido com o objetivo de aprender e aplicar conceitos de:

- Backend Java com Spring Boot
- Integração entre bancos SQL e NoSQL (Redis)
- Atualização de ranking de forma performática
- Gerenciamento de usuários e pontuações
- Arquitetura orientada a serviços (com Service e DTO)
- Persistência de histórico de pontuação
- Cálculo automático de Rank (TOP1, Diamante, Ouro, etc)

> 🔗 **Baseado no roadmap de backend:**  
> [https://roadmap.sh/backend](https://roadmap.sh/backend)

---

## 🧱 Tecnologias Utilizadas

| Camada       | Tecnologias                                               |
|--------------|-----------------------------------------------------------|
| Backend      | Java 21, Spring Boot 3.4, Spring Data JPA                 |
| Banco de Dados | MySQL (histórico e dados do usuário)                   |
| Cache / Realtime | Redis (ZSET - Sorted Set) para posição e rank         |
| Validações   | Jakarta Bean Validation (javax.validation)               |
| API REST     | Spring Web, Postman para testes                           |
| Containerização | Docker e Docker Compose para Redis e MySQL             |

---

## 📊 Funcionalidades

- ✅ Cadastro de usuários com validação
- ✅ Registro de pontuações com timestamp (histórico)
- ✅ Cálculo automático da pontuação total do usuário
- ✅ Redis usado para ordenar e classificar usuários por pontuação
- ✅ Rank atualizado automaticamente com base na posição (TOP1, DIAMANTE, etc)
- ✅ Histórico salvo em banco relacional
- ✅ Atualização manual de ranking via endpoint

---

## 🧠 Lógica de Rank

O rank é baseado na **posição** do usuário na leaderboard:

| Posição     | Rank       |
|-------------|------------|
| 1º lugar    | TOP1       |
| 2º - 10º    | DIAMANTE   |
| 11º - 50º   | OURO       |
| 51º - 100º  | PRATA      |
| 101+        | BRONZE     |

---

## 🔄 Endpoints Principais

| Método | Rota                                | Descrição                                     |
|--------|-------------------------------------|-----------------------------------------------|
| POST   | `/api/users`                        | Cadastra um novo usuário                      |
| GET    | `/api/users`                        | Lista todos os usuários                       |
| POST   | `/api/scores`                       | Registra um novo score e atualiza rank        |
| GET    | `/api/scores`                       | Lista o histórico de pontuação                |
| POST   | `/api/scores/atualizar-rankings`    | Atualiza todas as posições e ranks no MySQL   |

---

👨‍💻 Autor

Desenvolvido por Arthur Torres de Camargo de Oliveira

Estudante de Engenharia da Computação

🧭 Referência de Estudos

Este projeto segue como prática do roadmap.sh de Backend e explora fundamentos de:

Persistência de dados

Redis e estruturas de dados eficientes

Design de APIs REST

Camadas de serviço e boas práticas no Spring





