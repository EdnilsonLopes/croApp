package com.cro.app.view.turma;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Turma;
import com.cro.app.model.enumerator.SerieEnum;
import com.cro.app.view.util.BeanForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class TurmaForm
  extends BeanForm<Turma> {

  public TurmaForm(TurmaViewLogic viewLogic) {
    super(viewLogic);
    VerticalLayout content = new VerticalLayout();
    content = new VerticalLayout();
    content.setSizeUndefined();
    add(content);

    //    content.add(createTabsLayout());
    content.add(createLayout());

    content.add(createButtonBar());
    inicializarBinder();
  }

  private Component createTabsLayout() {
    Tabs tabs = new Tabs(createTabGeral(), createTabAlunos());
    return tabs;
  }

  private Component createLayout() {
    VerticalLayout layout = new VerticalLayout();
    layout.setMargin(false);
    layout.setPadding(false);
    layout.setSpacing(false);
    layout = new VerticalLayout();
    layout.setSizeUndefined();
    HorizontalLayout hl = new HorizontalLayout();
    hl.setWidth("100%");
    hl.add(createSerieCbx(), createNomeEdit());
    layout.add(hl, createProfessorCbx(), createDescricaoEdit());
    AlunoTurmaGrid grid = new AlunoTurmaGrid();
    grid.setItems(DataService.get().getAlunoDAO().loadAll());
    grid.setHeight("100%");
    layout.add(grid);
    return layout;
  }

  private Tab createTabGeral() {
    Tab tabGeral = new Tab("Geral");
    VerticalLayout layout = new VerticalLayout();
    layout = new VerticalLayout();
    layout.setSizeUndefined();
    tabGeral.add(layout);
    HorizontalLayout hl = new HorizontalLayout();
    hl.setWidth("100%");
    hl.add(createSerieCbx(), createNomeEdit());
    layout.add(hl, createProfessorCbx(), createDescricaoEdit());
    return tabGeral;
  }

  private Tab createTabAlunos() {
    Tab tabAlunos = new Tab("Alunos");
    AlunoTurmaGrid grid = new AlunoTurmaGrid();
    grid.setItems(DataService.get().getAlunoDAO().loadAll());
    tabAlunos.add(grid);
    return tabAlunos;
  }

  @SuppressWarnings("unchecked")
  private Component createSerieCbx() {
    ComboBox<SerieEnum> cbxSerie =
      createComboBox("Serie", "serie", SerieEnum.getValues());
    cbxSerie.setWidth("100%");
    return cbxSerie;
  }

  private Component createProfessorCbx() {
    return createComboBox("Conselheiro",
                          "professor",
                          DataService.get().getProfessorDAO().loadAll());
  }

  private Component createNomeEdit() {
    TextField field = createTextField("Nome", "nome");
    field.setMaxLength(2);
    return field;
  }

  private Component createDescricaoEdit() {
    TextArea field = createTextArea("Descrição", "descricao");
    field.setWidth("100%");
    return field;
  }

}
