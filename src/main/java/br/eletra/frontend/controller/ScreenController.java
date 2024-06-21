package br.eletra.frontend.controller;

import br.eletra.frontend.dto.LineDTO;
import br.eletra.frontend.dto.ModelDTO;
import br.eletra.frontend.dto.CategoryDTO;
import br.eletra.frontend.services.CategoryServices;
import br.eletra.frontend.services.LineServices;
import br.eletra.frontend.services.ModelServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ScreenController implements Initializable {

    @FXML
    TitledPane titledPaneLines;

    @FXML
    ComboBox<LineDTO> comboBoxLines;

    @FXML
    TitledPane titledPaneModels;

    @FXML
    TreeView<Object> treeView;

    @FXML
    Accordion accordion;

    LineServices lineServices = new LineServices();
    CategoryServices categoryServices = new CategoryServices();
    ModelServices modelServices = new ModelServices();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);
        comboBoxSelect();
    }

    void comboBoxSelect() {
        List<LineDTO> lineList = lineServices.getAllLines();
        comboBoxLines.setItems(FXCollections.observableArrayList(lineList));
        comboBoxLines.valueProperty().addListener((observable, oldValue, newValue) -> openTreeView(newValue));
    }

    void openTreeView(LineDTO selectedLine) {
        titledPaneLines.setExpanded(false);
        titledPaneModels.setDisable(false);
        titledPaneModels.setExpanded(true);

        List<CategoryDTO> categoryList = categoryServices.getAllCategories(selectedLine);
        TreeItem<Object> rootItem = new TreeItem<>(new CategoryDTO(selectedLine.getLineName(), selectedLine.getId()));
        rootItem.setExpanded(true);

        categoryList.forEach(category -> {
            TreeItem<Object> categoryItem = new TreeItem<>(category);
            rootItem.getChildren().add(categoryItem);

            List<ModelDTO> modelList = modelServices.getAllModels(category);
            modelList.forEach(model -> categoryItem.getChildren().add(new TreeItem<>(model)));
        });

        treeView.setRoot(rootItem);
    }
}
