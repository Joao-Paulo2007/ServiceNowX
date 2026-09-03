# ServiceNowX — Projeto Semestral

O **ServiceNowX** simula uma central de serviços técnicos com chamados, técnicos, prioridades, SLA, notificações, monitoramento e integrações externas.


## Estrutura principal
- `modelo`: entidades e dados do domínio;
- `servico`: fluxo principal da aplicação e regras atuais;
- `repositorio`: persistência simplificada em memória;
- `legado`: integrações externas simuladas;
- `docs`: documentação, atividades, evidências, ADRs, diagramas e anexos.

## Execução
Requer Java 17.

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out br.edu.servicenowx.Principal
```

No Windows, pode ser utilizado o script `scripts/compile.ps1`.
