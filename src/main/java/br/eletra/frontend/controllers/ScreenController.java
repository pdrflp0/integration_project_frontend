package br.eletra.frontend.controllers;

import br.eletra.backend.categories.LinesInitializer;
import br.eletra.backend.categories.ModelsInitializer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.*;

public class ScreenController {

    @FXML
    private TitledPane titledPaneModels;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private ComboBox<String> comboBoxLines;

    @FXML
    private Accordion accordion;

    private final LinesInitializer linesInitializer = new LinesInitializer();
    private final ModelsInitializer modelsInitializer = new ModelsInitializer();

    public void initialize() {
        titledPaneModels.setDisable(true);

        comboBoxLines.setItems(linesInitializer.lines);
        comboBoxLines.setOnAction(event -> handleLineSelection());
    }

    private void handleLineSelection() {
        String selectedLine = comboBoxLines.getValue();
        if (selectedLine != null) {
            titledPaneModels.setDisable(false);
            displayModels(selectedLine);
        }
    }

    private void displayModels(String selectedLine) {
        Map<String, List<String>> subcategories = modelsInitializer.initializeModels().get(selectedLine);
        if (subcategories != null) {
            TreeItem<String> rootItem = new TreeItem<>(selectedLine);
            for (String subcategory : subcategories.keySet()) {
                TreeItem<String> subcategoryItem = new TreeItem<>(subcategory);
                List<String> models = subcategories.get(subcategory);
                for (String model : models) {
                    subcategoryItem.getChildren().add(new TreeItem<>(model));
                }
                rootItem.getChildren().add(subcategoryItem);
            }
            treeView.setRoot(rootItem);
            expandModels();
        }
    }

    private void expandModels() {
        titledPaneModels.setExpanded(true);
        accordion.setExpandedPane(accordion.getPanes().get(1)); // Index 1 corresponds to the Templates TitledPane
    }
}