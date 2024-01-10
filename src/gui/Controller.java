package gui;

import domain.Gene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    private Button Button;
    @FXML
    private TextField functionField;
    @FXML
    private TextField seqField;
    @FXML
    private ListView<String> listView;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textField;
    @FXML
    private CheckBox checkbox;




    @FXML
    protected void populateList() {
        try {
            ArrayList<Gene> genes = service.getAll();
            var sortedGenes = genes.stream().sorted(Comparator.comparing(report-> report.getOrganism())).collect(Collectors.toList());
            for (Gene gene : sortedGenes) {
                listView.getItems().add(gene.toString());
            }
        }catch (Exception exception) { exception.printStackTrace();}
    }

    @FXML
    protected void populateComboBox(){
        ArrayList<String> descriptions = new ArrayList<>();
        try {
            ArrayList<Gene> Genes = service.getAll();
            for(Gene Gene:Genes)
            {
                String desc = Gene.getOrganism();
                if(!descriptions.contains(desc)) descriptions.add(desc);
            }
            comboBox.getItems().addAll(descriptions);
        }
        catch (Exception exception) {exception.printStackTrace();}
    }

    @FXML
    void sortComboBox(ActionEvent event) {
        listView.getItems().clear();
        String filter_term = comboBox.getValue();
        try {
            ArrayList<Gene> Genes = service.getAll();
            var filteredGenes = Genes.stream().filter(w -> w.getOrganism().startsWith(filter_term)).collect(Collectors.toList());
            for (Gene gene : filteredGenes) {
                listView.getItems().add(gene.toString());
            }
        }catch (Exception exception) {exception.printStackTrace();}
    }


    @FXML
    void searchText(ActionEvent event) {
        listView.getItems().clear();
        String filter_term = comboBox.getValue();
        String filter_term2 = textField.getText();
        List<Gene> filteredGenesTwice;
        try {
            ArrayList<Gene> Genes = service.getAll();
            var filteredGenes = Genes.stream().filter(w -> w.getOrganism().startsWith(filter_term)).collect(Collectors.toList());
            if(checkbox.isSelected()){
                filteredGenesTwice = filteredGenes.stream().filter(w -> w.getName().contains(filter_term2)).collect(Collectors.toList());
            }else {
                filteredGenesTwice = filteredGenes.stream().filter(w -> w.getFunction().contains(filter_term2)).collect(Collectors.toList());
            }
            for(Gene gene : filteredGenesTwice){listView.getItems().add(gene.toString());}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateGene(ActionEvent event) {
//        functionField.setText("slapnuts");
//        seqField.setText("beetljuis");

        String[] descr = listView.getSelectionModel().getSelectedItem().split("'");
        String name = descr[1];
        listView.getItems().clear();

        String newfunc = functionField.getText();
        String newseq = seqField.getText();

        service.UpdateSchema(newfunc, newseq,name);
        populateList();
    }

    @FXML
    void getListViewValue(MouseEvent event) {
        //System.out.println("hello");
        String[] descr = listView.getSelectionModel().getSelectedItem().split("'");
        //System.out.println(descr[5]);
        functionField.setText(descr[5]);
        //System.out.println(descr[7]);
        seqField.setText(descr[7]);
    }

    @FXML
    public void initialize(){
        try {
//            service.createSchema();
//            service.AddinSchema();
            populateList();
            populateComboBox();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
