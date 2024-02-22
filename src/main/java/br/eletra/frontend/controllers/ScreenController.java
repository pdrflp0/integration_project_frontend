package br.eletra.frontend.controllers;

import br.eletra.categories.Lines;
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

    // Mapa para armazenar os modelos por linha e subcategoria
    private final Map<String, Map<String, List<String>>> modelsPerLine = new HashMap<>();
    private final Lines lines = new Lines();

    @FXML
    public void initialize() {
        titledPaneModels.setDisable(true);

//        ObservableList<String> lines = FXCollections.observableArrayList(
//                "Cronos",
//                "Ares"
//        );

        comboBoxLines.setItems(lines.lines); // Adicionar as opções de linha ao ComboBox
        comboBoxLines.setOnAction(event -> handleLineSelection());   // Adicionar um listener para detectar a seleção da linha

        initializeModelsByLine(); // Inicializar os modelos para cada linha e subcategoria
    }

    // Método para lidar com a seleção de linha no ComboBox
    @FXML
    private void handleLineSelection() {
        String selectedLine = comboBoxLines.getValue();
        if (selectedLine != null) {
            titledPaneModels.setDisable(false);
            displayModels(selectedLine);
        }
    }

    // Inicializar os modelos para cada linha e subcategoria
    private void initializeModelsByLine() {
        Map<String, List<String>> modelsCronos = new LinkedHashMap<>();
        List<String> modelsCronosOld = new ArrayList<>();
        modelsCronosOld.add("Cronos 6001-A");
        modelsCronosOld.add("Cronos 6003");
        modelsCronosOld.add("Cronos 7023");
        modelsCronos.put("Cronos Old", modelsCronosOld);

        List<String> modelsCronosL = new ArrayList<>();
        modelsCronosL.add("Cronos 6021L");
        modelsCronosL.add("Cronos 7023L");
        modelsCronos.put("Cronos L", modelsCronosL);

        List<String> modelsCronosNG = new ArrayList<>();
        modelsCronosNG.add("Cronos 6001‑NG");
        modelsCronosNG.add("Cronos 6003‑NG");
        modelsCronosNG.add("Cronos 6021‑NG");
        modelsCronosNG.add("Cronos 6031‑NG");
        modelsCronosNG.add("Cronos 7021‑NG");
        modelsCronosNG.add("Cronos 7023‑NG");
        modelsCronos.put("Cronos-NG", modelsCronosNG);

        Map<String, List<String>> modelsAres = new LinkedHashMap<>();
        List<String> modelsAresTB = new ArrayList<>();
        modelsAresTB.add("Ares 7021");
        modelsAresTB.add("Ares 7031");
        modelsAresTB.add("Ares 7023");
        modelsAres.put("Ares TB", modelsAresTB);

        List<String> modelsAresTHS = new ArrayList<>();
        modelsAresTHS.add("Ares 8023 15");
        modelsAresTHS.add("Ares 8023 200");
        modelsAresTHS.add("Ares 8023 2,5");
        modelsAres.put("Ares THS", modelsAresTHS);

        modelsPerLine.put("Cronos", modelsCronos);
        modelsPerLine.put("Ares", modelsAres);
    }

    // Exibir os modelos correspondentes à linha selecionada
    private void displayModels(String selectedLine) {
        if (selectedLine != null) {
            Map<String, List<String>> subcategories = modelsPerLine.get(selectedLine);
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
    }

    // Método para expandir o TitledPane de Modelos
    private void expandModels() {
        titledPaneModels.setExpanded(true);
        accordion.setExpandedPane(accordion.getPanes().get(1)); // Índice 1 corresponde ao TitledPane de Modelos
    }
}
