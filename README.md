# API de Transferências

## Visão Geral
Esta é uma API RESTful que simula um sistema de pagamentos simplificado, permitindo transferências entre usuários comuns e lojistas, seguindo regras específicas de negócio.

## Tecnologias Utilizadas
- **Linguagem**: JAVA
- **Framework**:Spring Boot
- **Banco de Dados**: H2(em memória)

## Endpoints Principais

### POST /transfer
Realiza uma transferência entre usuários.

**Request:**
```json
{
  
  "sender": 1,
  "reciever": 3
  "value": 100.0,
}
```
## Regras de Negócio Implementadas

✅ **Validação de documentos e e-mails únicos**  
- CPF/CNPJ e e-mails devem ser únicos no sistema
- Impede cadastro duplicado com mesmo documento ou e-mail

💸 **Regras de transferência**  
- Usuários comuns podem:
  - Enviar dinheiro (para outros usuários ou lojistas)
  - Receber dinheiro de outros usuários
- Lojistas podem apenas:
  - Receber transferências
  - Não podem enviar dinheiro

💰 **Validação financeira**  
- Verificação obrigatória de saldo suficiente antes de transferir
- Bloqueio de transações sem fundos disponíveis

🔐 **Integrações externas**  
- Consulta a serviço autorizador externo (mock disponível)
- Envio de notificação ao recebedor via serviço externo

