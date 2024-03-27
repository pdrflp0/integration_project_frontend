package br.eletra.frontend.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.net.URL;
import java.util.*;
import br.eletra.backend.configuration.HibernateConfiguration;
import br.eletra.backend.dao.LineDAO;
import br.eletra.backend.dao.CategoryDAO;
import br.eletra.backend.dao.ModelDAO;
import br.eletra.backend.entity.LineEntity;
import br.eletra.backend.entity.ModelEntity;
import br.eletra.backend.entity.CategoryEntity;

public class ScreenController implements Initializable {

    @FXML
    private TitledPane titledPaneModels;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private ComboBox<String> comboBoxLines;

    @FXML
    private Accordion accordion;

    private final Session session = HibernateConfiguration.getSessionFactory().openSession();
    private LineDAO lineDAO;
    private CategoryDAO categoryDAO;
    private ModelDAO modelDAO;

    public ScreenController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titledPaneModels.setDisable(true);

        try {
            lineDAO = new LineDAO(session);
            categoryDAO = new CategoryDAO(session);
            modelDAO = new ModelDAO(session);

            List<LineEntity> lines = lineDAO.getAllLines();
            ObservableList<String> lineNames = FXCollections.observableArrayList();
            for (LineEntity line : lines) {
                lineNames.add(line.getName());
            }
            comboBoxLines.setItems(lineNames);

            comboBoxLines.setOnAction(event -> handleLineSelection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLineSelection() {
        String selectedLine = comboBoxLines.getValue();
        if (selectedLine != null) {
            titledPaneModels.setDisable(false);
            displayModels(selectedLine);
            expandModels();
        }
    }


    private void displayModels(String selectedLine) {
        try {
            LineEntity line = lineDAO.getLineByName(selectedLine);
            if (line != null) {
                List<CategoryEntity> categories = categoryDAO.getCategoriesByLine(line);
                TreeItem<String> rootItem = new TreeItem<>(selectedLine);

                for (CategoryEntity category : categories) {
                    TreeItem<String> categoryItem = new TreeItem<>(category.getName());
                    List<ModelEntity> models = modelDAO.getModelsByCategory(category);
                    for (ModelEntity model : models) {
                        categoryItem.getChildren().add(new TreeItem<>(model.getName()));
                    }
                    rootItem.getChildren().add(categoryItem);
                }

                treeView.setRoot(rootItem);
            } else {
                System.out.println("Linha não encontrada: " + selectedLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void expandModels() {
        titledPaneModels.setExpanded(true);
        accordion.setExpandedPane(accordion.getPanes().get(1));
    }
}
