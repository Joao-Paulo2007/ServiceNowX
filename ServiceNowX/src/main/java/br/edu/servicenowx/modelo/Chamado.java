package br.edu.servicenowx.modelo;
import java.util.*;
public class Chamado {
    public String id;
    public String solicitante;
    public String categoria;
    public String prioridade;
    public String status="ABERTO";
    public String idTecnico;
    public String abertoEm;
    public String prazoEm;
    public List<String> historico=new ArrayList<>();
    public Chamado(String id,String solicitante,String categoria,String prioridade,String abertoEm){this.id=id;this.solicitante=solicitante;this.categoria=categoria;this.prioridade=prioridade;this.abertoEm=abertoEm;}
}
