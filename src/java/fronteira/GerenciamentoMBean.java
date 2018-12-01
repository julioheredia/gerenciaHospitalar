/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import controle.ControladorMedicamento;
import controle.*;
import entidade.*;
import entidade.controller.*;
import entidade.util.web.CandidatoMedicoAvaliacao;
import entidade.util.web.RespostaMedicamentoSetor;
import entidade.util.web.RespostaPatrimonioSetor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.DualListModel;

/**
 *
 * @author julio
 */
@ManagedBean
@SessionScoped
public class GerenciamentoMBean {

    private UsuarioEntidade usuarioLogado;
    private Boolean isAdmin;
    private Boolean isFuncionario;
    private String runningPage;
    private ProfissionalEntidade profissional;
    private List<EquipeEntidade> equipeList;
    private List<SetorEntidade> setorList;
    private String tipoProfSelect;
    private MedicoEntidade medico;
    private EnfermeiroEntidade enfermeiro;
    private FarmaceuticoEntidade farmaceutico;
    private List<ProfissionalEntidade> profissionalList;
    private ClinicaEntidade clinica;
    private DualListModel<ConvenioSaudeEntidade> planoDualList;
    private DualListModel<ConvenioSaudeEntidade> planoDualListEdit;
    private DualListModel<ExameEntidade> exameDualList;
    private DualListModel<ExameEntidade> exameDualListEdit;
    private DualListModel<ClinicaEntidade> clinicaDualList;
    private DualListModel<ClinicaEntidade> clinicaDualListEdit;
    private List<ClinicaEntidade> clinicaList;
    private ConvenioSaudeEntidade plano;
    private List<ConvenioSaudeEntidade> planoList;
    private PatrimonioEntidade patrimonio;
    private List<PatrimonioEntidade> patrimonioList;
    private UsuarioEntidade usuario;
    private List<UsuarioEntidade> usuarioList;
    private String nomeProfissional;
    private Boolean searchNome;
    private Boolean searchType;
    private List<ProfissionalEntidade> profissionalListSearch;
    private MedicoCandidatoEntidade medicoCandidato;
    private List<SelectItem> especializacaoSelectList;
    private List<SelectItem> qualificacaoSelectList;
    private List<CandidatoMedicoAvaliacao> medicoAvaliacaoList;
    private CandidatoMedicoAvaliacao candidatoMedicoAvaliacao;
    private MedicamentoEntidade medicamento;
    private List<TipoMedicamento> tipoMedicamentoList;
    private List<MedicamentoEntidade> medicamentoList;
    private List<AreaHospitalar> areaHospitalarList;
    private SetorEntidade setor;
    private RespostaMedicamentoSetor requestMedicamentoSetor;
    private List<TipoPatrimonio> tipoPatrimonioList;
    private RespostaPatrimonioSetor requestPatrimonioSetor;

    public GerenciamentoMBean() {

        try {
            usuarioLogado = (UsuarioEntidade) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
            runningPage = "home.xhtml";

            if (usuarioLogado.getProfissional() != null) {
                isFuncionario = true;
            }
            if (usuarioLogado.getAdmin()) {
                isAdmin = true;
            }
            searchNome = false;
            searchType = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void vincularPatrimonioSetor() {
        ControladorPatrimonio cp = new ControladorPatrimonio();
        requestPatrimonioSetor = cp.vincularMedicamentoSetor(patrimonio, setor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", requestPatrimonioSetor.getTexto()));
    }

    public void vincularMedicamentoSetor() {
        ControladorMedicamento cm = new ControladorMedicamento();
        requestMedicamentoSetor = cm.vincularMedicamentoSetor(medicamento, setor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", requestMedicamentoSetor.getTexto()));
    }

    public void createMedicamento() {

        ControladorMedicamento cm = new ControladorMedicamento();
        Boolean resp = cm.createMedicamento(medicamento);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Medicamento salvo com sucesso"));
        }
    }

    public void contrataMedicoCandidato() {

        ControladorCandidatoMedico cc = new ControladorCandidatoMedico();
        Boolean resp = cc.contrataMedicoCandidato(candidatoMedicoAvaliacao);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Médico contratado!"));
        }
    }

    public void removeMedicoCandidato() {

        ControladorCandidatoMedico cc = new ControladorCandidatoMedico();
        Boolean resp = cc.removeMedicoCandidato(candidatoMedicoAvaliacao);
    }

    public void createMedicoCandidato() {

        ControladorCandidatoMedico cc = new ControladorCandidatoMedico();
        Boolean resp = cc.createMedicoCandidato(medicoCandidato);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Candidato salvo com sucesso"));
        }
    }

    public void editPlanoDeSaude() {

        plano.setClinicaList(clinicaDualListEdit.getTarget());
        plano.setExameList(exameDualListEdit.getTarget());

        ControladorPlanoSaude cp = new ControladorPlanoSaude();
        Boolean resp = cp.editConvenioSaude(plano);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Plano de Saúde salvo com sucesso"));
        }

    }

    public void editClinica() {

        clinica.setConvenioList(planoDualListEdit.getTarget());
        clinica.setExameList(exameDualListEdit.getTarget());

        ControladorClinica cc = new ControladorClinica();
        Boolean resp = cc.editClinica(clinica);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Clínica salvo com sucesso"));
        }

    }

    public void editUsuario() {

        ControladorUsuario uc = new ControladorUsuario();
        Boolean resp = uc.editUsuario(usuario);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuário salvo com sucesso"));
        }
    }

    public void removeUsuario() {

        ControladorUsuario uc = new ControladorUsuario();
        Boolean resp = uc.removeUsuario(usuario);
    }

    public void createUsuario() {

        ControladorUsuario uc = new ControladorUsuario();
        Boolean resp = uc.createUsuario(usuario);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Usuário salvo com sucesso"));
        }
    }

    public void editPatrimonio() {

        ControladorPatrimonio cp = new ControladorPatrimonio();
        Boolean resp = cp.editPatrimonio(patrimonio);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Patrimônio salvo com sucesso"));
        }
    }

    public void removePatrimonio() {

        ControladorPatrimonio cp = new ControladorPatrimonio();
        Boolean resp = cp.removePatrimonio(patrimonio);
    }

    public void createPatrimonio() {

        ControladorPatrimonio cp = new ControladorPatrimonio();
        Boolean resp = cp.createPatrimonio(patrimonio);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Patrimônio salvo com sucesso"));
        }
    }

    public void removePlano() {

        ControladorPlanoSaude cp = new ControladorPlanoSaude();
        Boolean resp = cp.removeConvenioSaude(plano);
    }

    public void createPlano() {

        plano.setClinicaList(clinicaDualList.getTarget());
        plano.setExameList(exameDualList.getTarget());

        ControladorPlanoSaude cps = new ControladorPlanoSaude();
        Boolean resp = cps.createConvenioSaude(plano);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Plano de Saúde salvo com sucesso"));
        }
    }

    public void removeClinica() {
        ControladorClinica cc = new ControladorClinica();
        Boolean resp = cc.removeClinica(clinica);
    }

    public void createClinica() {

        clinica.setConvenioList(planoDualList.getTarget());
        clinica.setExameList(exameDualList.getTarget());

        ControladorClinica cc = new ControladorClinica();
        Boolean resp = cc.createClinica(clinica);

        if (resp) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Clínica salvo com sucesso"));
        }
    }

    public void editProfissional() {


        ControladorProfissional cp = new ControladorProfissional();
        Boolean resp = cp.editProfissional(profissional, medico, enfermeiro, farmaceutico);

        if (resp) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Funcionário salvo com sucesso"));
        }
    }

    public void removeProfissional() {
        ControladorProfissional cp = new ControladorProfissional();
        Boolean resp = cp.removeProfissional(profissional);
    }

    public void createProfissional() {

        ControladorProfissional cp = new ControladorProfissional();
        Boolean resp = cp.createProfissional(profissional, medico, enfermeiro, farmaceutico, tipoProfSelect);

        if (resp) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Funcionário salvo com sucesso"));
        }
    }

    public void selectTipoProfessional() {

        try {
            if (tipoProfSelect.equals("M")) {
                profissional = new MedicoEntidade();
                medico = new MedicoEntidade();
            }
            if (tipoProfSelect.equals("E")) {
                profissional = new EnfermeiroEntidade();
                enfermeiro = new EnfermeiroEntidade();
            }
            if (tipoProfSelect.equals("F")) {
                profissional = new FarmaceuticoEntidade();
                farmaceutico = new FarmaceuticoEntidade();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pageDirectSelectMedicamento() {
        runningPage = "select_medicamento.xhtml";
    }

    public void pageDirectBindMedicamentoSetor() {
        runningPage = "bind_remedio_setor.xhtml";
    }

    public void pageDirectCreateMedicamento() {
        medicamento = new MedicamentoEntidade();
        runningPage = "create_medicamento.xhtml";
    }

    public void pageDirectEditUsuario() {
        runningPage = "edit_usuario.xhtml";
    }

    public void pageDirectSelectUsuario() {
        runningPage = "select_usuario.xhtml";
    }

    public void pageDirectCreateUsuario() {
        usuario = new UsuarioEntidade();
        runningPage = "create_usuario.xhtml";
    }

    public void pageDirectEditPatrimonio() {
        runningPage = "edit_patrimonio.xhtml";
    }

    public void pageDirectBindPatrimonioSetor() {
        runningPage = "bind_patrimonio_setor.xhtml";
    }

    public void pageDirectSelectPatrimonio() {
        runningPage = "select_patrimonio.xhtml";
    }

    public void pageDirectCreatePatrimonio() {
        patrimonio = new PatrimonioEntidade();
        runningPage = "create_patrimonio.xhtml";
    }

    public void pageDirectEditPlano() {

        clinica = null;
        runningPage = "edit_plano.xhtml";
    }

    public void pageDirectSelectPlano() {
        runningPage = "select_plano.xhtml";
    }

    public void pageDirectCreatePlano() {
        plano = new ConvenioSaudeEntidade();
        runningPage = "create_plano.xhtml";
    }

    public void pageDirectEditClinica() {

        plano = null;
        runningPage = "edit_clinica.xhtml";
    }

    public void pageDirectSelectClinica() {
        runningPage = "select_clinica.xhtml";
    }

    public void pageDirectCreateClinica() {
        clinica = new ClinicaEntidade();
        runningPage = "create_clinica.xhtml";
    }

    public void pageDirectSelectProfissional() {
        runningPage = "select_funcionario.xhtml";
    }

    public void pageDirectEditProfissional() {

        ProfissionalEntidade profissionalAux = profissional;
        ControladorProfissional cp = new ControladorProfissional();

        if (profissionalAux.getDiscriminator().equals("M")) {

            medico = cp.getMedico(profissionalAux.getId());
            enfermeiro = null;
            farmaceutico = null;
        }

        if (profissionalAux.getDiscriminator().equals("E")) {
            enfermeiro = cp.getEnfermeiro(profissionalAux.getId());
            medico = null;
            farmaceutico = null;
        }
        if (profissionalAux.getDiscriminator().equals("F")) {
            farmaceutico = cp.getFarmaceutico(profissionalAux.getId());
            medico = null;
            enfermeiro = null;
        }

        runningPage = "edit_funcionario.xhtml";
    }

    public void pageDirectRelatorioCandidatoMedico() {
        runningPage = "relatorio_candidato_medico.xhtml";
    }

    public void pageDirectSearchProfissional() {
        runningPage = "search_funcionario.xhtml";
    }

    public void pageDirectCreateCandidatoMedico() {

        medicoCandidato = new MedicoCandidatoEntidade();
        runningPage = "create_candidato_medico.xhtml";
    }

    public void pageDirectCreateProfissional() {
        runningPage = "create_funcionario.xhtml";
    }

    public UsuarioEntidade getUsuarioLogado() {
        return (UsuarioEntidade) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
    }

    public void setUsuarioLogado(UsuarioEntidade usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<SetorEntidade> getSetorList() {
        SetorEntidadeJpaController sc = new SetorEntidadeJpaController();
        return sc.findSetorEntidadeEntities();
    }

    public void setSetorList(List<SetorEntidade> setorList) {
        this.setorList = setorList;
    }

    public List<EquipeEntidade> getEquipeList() {
        EquipeEntidadeJpaController ec = new EquipeEntidadeJpaController();
        return ec.findEquipeEntidadeEntities();
    }

    public void setEquipeList(List<EquipeEntidade> equipeList) {
        this.equipeList = equipeList;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsFuncionario() {
        return isFuncionario;
    }

    public void setIsFuncionario(Boolean isFuncionario) {
        this.isFuncionario = isFuncionario;
    }

    public String getRunningPage() {
        return runningPage;
    }

    public void setRunningPage(String runningPage) {
        this.runningPage = runningPage;
    }

    public ProfissionalEntidade getProfissional() {
        return profissional;
    }

    public void setProfissional(ProfissionalEntidade profissional) {
        this.profissional = profissional;
    }

    public String getTipoProfSelect() {
        return tipoProfSelect;
    }

    public void setTipoProfSelect(String tipoProfSelect) {
        this.tipoProfSelect = tipoProfSelect;
    }

    public MedicoEntidade getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntidade medico) {
        this.medico = medico;
    }

    public EnfermeiroEntidade getEnfermeiro() {
        return enfermeiro;
    }

    public void setEnfermeiro(EnfermeiroEntidade enfermeiro) {
        this.enfermeiro = enfermeiro;
    }

    public FarmaceuticoEntidade getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(FarmaceuticoEntidade farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public List<ProfissionalEntidade> getProfissionalList() {
        ProfissionalEntidadeJpaController pc = new ProfissionalEntidadeJpaController();
        return pc.findProfissionalEntidadeEntities();
    }

    public void setProfissionalList(List<ProfissionalEntidade> profissionalList) {
        this.profissionalList = profissionalList;
    }

    public ClinicaEntidade getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaEntidade clinica) {
        this.clinica = clinica;
    }

    public DualListModel<ConvenioSaudeEntidade> getPlanoDualList() {

        List<ConvenioSaudeEntidade> convenioSelect = new ArrayList<ConvenioSaudeEntidade>();
        ConvenioSaudeEntidadeJpaController cc = new ConvenioSaudeEntidadeJpaController();
        List<ConvenioSaudeEntidade> convenioOpcao = cc.findConvenioSaudeEntidadeEntities();
        return new DualListModel<ConvenioSaudeEntidade>(convenioOpcao, convenioSelect);

    }

    public DualListModel<ConvenioSaudeEntidade> getPlanoDualListEdit() {
        ControladorDualList cdl = new ControladorDualList();
        return cdl.getDualListPlanoOfClinica(clinica);
    }

    public void setPlanoDualListEdit(DualListModel<ConvenioSaudeEntidade> planoDualListEdit) {
        this.planoDualListEdit = planoDualListEdit;
    }

    public void setPlanoDualList(DualListModel<ConvenioSaudeEntidade> planoDualList) {
        this.planoDualList = planoDualList;
    }

    public DualListModel<ExameEntidade> getExameDualList() {

        List<ExameEntidade> exameSelect = new ArrayList<ExameEntidade>();
        ExameEntidadeJpaController ec = new ExameEntidadeJpaController();
        List<ExameEntidade> exameOpcao = ec.findExameEntidadeEntities();
        return new DualListModel<ExameEntidade>(exameOpcao, exameSelect);

    }

    public DualListModel<ExameEntidade> getExameDualListEdit() {

        ControladorDualList cdl = new ControladorDualList();
        DualListModel<ExameEntidade> list = null;

        if (plano != null && clinica == null) {
            list = cdl.getDualListExameOfPlanoDeSaude(plano);
        }

        if (clinica != null && plano == null) {
            list = cdl.getDualListExameOfClinica(clinica);
        }

        return list;
    }

    public void setExameDualListEdit(DualListModel<ExameEntidade> exameDualListEdit) {
        this.exameDualListEdit = exameDualListEdit;
    }

    public void setExameDualList(DualListModel<ExameEntidade> exameDualList) {
        this.exameDualList = exameDualList;
    }

    public List<ClinicaEntidade> getClinicaList() {

        ClinicaEntidadeJpaController cc = new ClinicaEntidadeJpaController();
        return cc.findClinicaEntidadeEntities();
    }

    public void setClinicaList(List<ClinicaEntidade> clinicaList) {
        this.clinicaList = clinicaList;
    }

    public ConvenioSaudeEntidade getPlano() {
        return plano;
    }

    public void setPlano(ConvenioSaudeEntidade plano) {
        this.plano = plano;
    }

    public DualListModel<ClinicaEntidade> getClinicaDualList() {

        ClinicaEntidadeJpaController cc = new ClinicaEntidadeJpaController();
        List<ClinicaEntidade> clinicaSelect = new ArrayList<ClinicaEntidade>();
        List<ClinicaEntidade> clinicaOpcao = cc.findClinicaEntidadeEntities();
        return new DualListModel<ClinicaEntidade>(clinicaOpcao, clinicaSelect);

    }

    public DualListModel<ClinicaEntidade> getClinicaDualListEdit() {
        ControladorDualList cdl = new ControladorDualList();
        return cdl.getDualListClinicaOfPlanoDeSaude(plano);
    }

    public void setClinicaDualListEdit(DualListModel<ClinicaEntidade> clinicaDualListEdit) {
        this.clinicaDualListEdit = clinicaDualListEdit;
    }

    public void setClinicaDualList(DualListModel<ClinicaEntidade> clinicaDualList) {
        this.clinicaDualList = clinicaDualList;
    }

    public List<ConvenioSaudeEntidade> getPlanoList() {
        ConvenioSaudeEntidadeJpaController cc = new ConvenioSaudeEntidadeJpaController();
        return cc.findConvenioSaudeEntidadeEntities();
    }

    public void setPlanoList(List<ConvenioSaudeEntidade> planoList) {
        this.planoList = planoList;
    }

    public PatrimonioEntidade getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(PatrimonioEntidade patrimonio) {
        this.patrimonio = patrimonio;
    }

    public List<PatrimonioEntidade> getPatrimonioList() {
        PatrimonioEntidadeJpaController cp = new PatrimonioEntidadeJpaController();
        return cp.findPatrimonioEntidadeEntities();
    }

    public void setPatrimonioList(List<PatrimonioEntidade> patrimonioList) {
        this.patrimonioList = patrimonioList;
    }

    public UsuarioEntidade getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntidade usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioEntidade> getUsuarioList() {
        UsuarioEntidadeJpaController uj = new UsuarioEntidadeJpaController();
        return uj.findUsuarioEntidadeEntities();
    }

    public void setUsuarioList(List<UsuarioEntidade> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Boolean getSearchNome() {
        return searchNome;
    }

    public void setSearchNome(Boolean searchNome) {
        this.searchNome = searchNome;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public Boolean getSearchType() {
        return searchType;
    }

    public void setSearchType(Boolean searchType) {
        this.searchType = searchType;
    }

    public List<ProfissionalEntidade> getProfissionalListSearch() {

        ControladorProfissional cp = new ControladorProfissional();
        return cp.searchProfessional(nomeProfissional, searchNome, tipoProfSelect, searchType);
    }

    public void setProfissionalListSearch(List<ProfissionalEntidade> profissionalListSearch) {
        this.profissionalListSearch = profissionalListSearch;
    }

    public MedicoCandidatoEntidade getMedicoCandidato() {
        return medicoCandidato;
    }

    public void setMedicoCandidato(MedicoCandidatoEntidade medicoCandidato) {
        this.medicoCandidato = medicoCandidato;
    }

    public List<SelectItem> getEspecializacaoSelectList() {

        List<SelectItem> aux = new ArrayList<SelectItem>();
        for (Especializacao especializacao : Especializacao.getListEspecializacao()) {
            aux.add(new SelectItem(especializacao.toString(), especializacao.getNome()));
        }
        return aux;
    }

    public void setEspecializacaoSelectList(List<SelectItem> especializacaoSelectList) {
        this.especializacaoSelectList = especializacaoSelectList;
    }

    public List<SelectItem> getQualificacaoSelectList() {

        List<SelectItem> aux = new ArrayList<SelectItem>();
        for (Qualificacao qualificacao : Qualificacao.getListQualificacao()) {
            aux.add(new SelectItem(qualificacao.toString(), qualificacao.getNome()));
        }
        return aux;
    }

    public void setQualificacaoSelectList(List<SelectItem> qualificacaoSelectList) {
        this.qualificacaoSelectList = qualificacaoSelectList;
    }

    public List<CandidatoMedicoAvaliacao> getMedicoAvaliacaoList() {
        ControladorCandidatoMedico ccm = new ControladorCandidatoMedico();
        return ccm.relatorioMedicoCandidato();
    }

    public void setMedicoAvaliacaoList(List<CandidatoMedicoAvaliacao> medicoAvaliacaoList) {
        this.medicoAvaliacaoList = medicoAvaliacaoList;
    }

    public CandidatoMedicoAvaliacao getCandidatoMedicoAvaliacao() {
        return candidatoMedicoAvaliacao;
    }

    public void setCandidatoMedicoAvaliacao(CandidatoMedicoAvaliacao candidatoMedicoAvaliacao) {
        this.candidatoMedicoAvaliacao = candidatoMedicoAvaliacao;
    }

    public MedicamentoEntidade getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(MedicamentoEntidade medicamento) {
        this.medicamento = medicamento;
    }

    public List<TipoMedicamento> getTipoMedicamentoList() {
        return TipoMedicamento.getListTipoMedicamento();
    }

    public void setTipoMedicamentoList(List<TipoMedicamento> tipoMedicamentoList) {
        this.tipoMedicamentoList = tipoMedicamentoList;
    }

    public List<MedicamentoEntidade> getMedicamentoList() {
        MedicamentoEntidadeJpaController cm = new MedicamentoEntidadeJpaController();
        return cm.findMedicamentoEntidadeEntities();
    }

    public void setMedicamentoList(List<MedicamentoEntidade> medicamentoList) {
        this.medicamentoList = medicamentoList;
    }

    public List<AreaHospitalar> getAreaHospitalarList() {
        return AreaHospitalar.getListAreaHospitalar();
    }

    public void setAreaHospitalarList(List<AreaHospitalar> areaHospitalarList) {
        this.areaHospitalarList = areaHospitalarList;
    }

    public SetorEntidade getSetor() {
        return setor;
    }

    public void setSetor(SetorEntidade setor) {
        this.setor = setor;
    }

    public RespostaMedicamentoSetor getRequestMedicamentoSetor() {
        return requestMedicamentoSetor;
    }

    public void setRequestMedicamentoSetor(RespostaMedicamentoSetor requestMedicamentoSetor) {
        this.requestMedicamentoSetor = requestMedicamentoSetor;
    }

    public List<TipoPatrimonio> getTipoPatrimonioList() {
        return TipoPatrimonio.getListTipoPatrimonio();
    }

    public void setTipoPatrimonioList(List<TipoPatrimonio> tipoPatrimonioList) {
        this.tipoPatrimonioList = tipoPatrimonioList;
    }

    public RespostaPatrimonioSetor getRequestPatrimonioSetor() {
        return requestPatrimonioSetor;
    }

    public void setRequestPatrimonioSetor(RespostaPatrimonioSetor requestPatrimonioSetor) {
        this.requestPatrimonioSetor = requestPatrimonioSetor;
    }
}