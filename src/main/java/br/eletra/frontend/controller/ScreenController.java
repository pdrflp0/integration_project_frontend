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
    private TitledPane titledPaneLines;

    @FXML
    private ComboBox<LineDTO> comboBoxLines;

    @FXML
    private TitledPane titledPaneModels;

    @FXML
    private TreeView<LineDTO> treeView;

    @FXML
    private Accordion accordion;

    LineServices lineServices = new LineServices();
    CategoryServices categoryServices = new CategoryServices();
    ModelServices modelServices = new ModelServices();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accordion.setExpandedPane(titledPaneLines);
        titledPaneModels.setDisable(true);
        comboBoxSelect();
    }

    private void comboBoxSelect() {
        List<LineDTO> lineList = lineServices.getAllLines();
        comboBoxLines.setItems(FXCollections.observableArrayList(lineList));
        comboBoxLines.valueProperty().addListener(((observable, oldValue, newValue) -> openTreeView(newValue)));
    }

    private void openTreeView(LineDTO selectedLine) {
        titledPaneLines.setExpanded(false);
        titledPaneModels.setDisable(false);
        titledPaneModels.setExpanded(true);

        List<CategoryDTO> categoryList = CategoryServices.getAllCategories(selectedLine);
        TreeItem showTreeView = new TreeItem<>(selectedLine);
        showTreeView.setExpanded(true);

        categoryList.forEach((category) -> {
            TreeItem<CategoryDTO> categoryItem = new TreeItem<>(category);
            showTreeView.getChildren().add(categoryItem);

            List<ModelDTO> modelList = ModelServices.getAllModels(category);
            modelList.forEach((model) -> categoryItem.getChildren().add(new TreeItem(model)));
        });
        treeView.setRoot(showTreeView);
    }
}
