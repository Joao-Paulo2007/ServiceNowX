# Fluxo do usuário

```mermaid
flowchart LR
    A[Solicitante informa numero do chamado] --> B{Chamado existe?}
    B -- não --> E[Exibe mensagem: chamado nao encontrado]
    B -- sim --> C[Sistema busca status atual do chamado]
    C --> D[Exibe status, tecnico responsavel e ultima atualizacao]
```

- **Caminho principal:** chamado existe → status é encontrado e exibido.
- **Caminho alternativo:** número de chamado inexistente → mensagem de erro.
- **Início:** solicitante informa o número do chamado (ex.: `INC001`).
- **Fim:** solicitante entende a situação atual do atendimento.

---