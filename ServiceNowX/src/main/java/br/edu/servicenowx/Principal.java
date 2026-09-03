package br.edu.servicenowx;
import br.edu.servicenowx.modelo.*;
import br.edu.servicenowx.servico.*;
public class Principal {
    public static void main(String[] args){
        ServicoAplicacaoServiceNowX servico=new ServicoAplicacaoServiceNowX();
        servico.tecnicos.salvar("T1",new Tecnico("T1","Técnico 1","REDE"));
        servico.abrirChamado("INC001","usuario1","REDE","ALTA","2026-08-10T20:00:00");
        servico.abrirChamado("INC002","usuario2","REDE","CRITICA","2026-08-10T20:05:00");
        servico.atribuir("INC001"); servico.atribuir("INC002");
        servico.resolver("INC001"); servico.escalar("INC002");
        servico.registrarAlertaMonitoramento("SERVIDOR01","CPU 99%");
        servico.registrarAlertaMonitoramento("SERVIDOR01","CPU 100%");
        System.out.println("INC001="+servico.chamados.buscar("INC001").status);
        System.out.println("INC002_TECNICO="+servico.chamados.buscar("INC002").idTecnico);
        System.out.println("TECNICO_ATIVOS="+servico.tecnicos.buscar("T1").chamadosAtivos);
    }
}
