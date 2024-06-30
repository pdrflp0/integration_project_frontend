package br.eletra.frontend.controller;

import br.eletra.frontend.dto.CategoryDTO;
import br.eletra.frontend.dto.LineDTO;
import br.eletra.frontend.dto.ModelDTO;
import br.eletra.frontend.services.CategoryServices;
import br.eletra.frontend.services.LineServices;
import br.eletra.frontend.services.ModelServices;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ScreenControllerTest extends ApplicationTest {

    private ScreenController controller;

    @Before
    public void setUp() {
        controller = Mockito.spy(ScreenController.class);
        controller.titledPaneLines = new TitledPane();
        controller.comboBoxLines = new ComboBox<>();
        controller.titledPaneModels = new TitledPane();
        controller.treeView = new TreeView<>();
        controller.accordion = new Accordion();
        controller.lineServices = Mockito.mock(LineServices.class);
        controller.categoryServices = Mockito.mock(CategoryServices.class);
        controller.modelServices = Mockito.mock(ModelServices.class);

        controller.accordion.getPanes().addAll(controller.titledPaneLines, controller.titledPaneModels);
        controller.titledPaneLines.setContent(controller.comboBoxLines);
        controller.titledPaneModels.setContent(controller.treeView);
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testInitializeController() {
        // Given
        Mockito.doNothing().when(controller).comboBoxSelect();

        // When
        controller.initialize(null, null);

        // Then
        assertEquals(controller.titledPaneLines, controller.accordion.getExpandedPane());
        assertTrue(controller.titledPaneModels.isDisable());
        verify(controller).comboBoxSelect();
    }

    @Test
    public void testComboBoxSelect01() {
        // Given
        List<LineDTO> mockList = new ArrayList<>();
        LineDTO mockLine = new LineDTO((short) 1, "Ares");
        Mockito.when(controller.lineServices.getAllLines()).thenReturn(mockList);

        // When
        controller.comboBoxSelect();
        controller.openTreeView(mockLine);

        // Then
        assertEquals(FXCollections.observableArrayList(mockList), controller.comboBoxLines.getItems());
        verify(controller.lineServices).getAllLines();
    }

    @Test
    public void testComboBoxSelect02() {
        // Given
        List<LineDTO> mockList = new ArrayList<>();
        mockList.add(new LineDTO((short) 1, "Ares"));
        mockList.add(new LineDTO((short) 2, "Cronos"));
        Mockito.when(controller.lineServices.getAllLines()).thenReturn(mockList);
        controller.comboBoxSelect();
        controller.comboBoxLines.setValue(controller.comboBoxLines.getItems().get(0));

        // When
        controller.openTreeView(mockList.get(0));

        // Then
        verify(controller, times(2)).openTreeView(controller.comboBoxLines.getItems().get(0));
    }

    @Test
    public void testOpenTreeView01() {
        // Given
        LineDTO mockLine = new LineDTO((short) 1, "Ares");
        CategoryDTO mockCategory = new CategoryDTO("Ares TB", (short) 1);
        ModelDTO mockModel = new ModelDTO((short) 1, "Ares 7021", "");
        List<CategoryDTO> mockCategoryList = new ArrayList<>();
        mockCategoryList.add(mockCategory);
        List<ModelDTO> mockModelList = new ArrayList<>();
        mockModelList.add(mockModel);

        // Mock the service calls
        when(controller.categoryServices.getAllCategories(mockLine)).thenReturn(mockCategoryList);
        when(controller.modelServices.getAllModels(mockCategory)).thenReturn(mockModelList);

        // When
        controller.openTreeView(mockLine);

        // Then
        TreeItem<Object> rootItem = controller.treeView.getRoot();
        assertNotNull(rootItem);
        assertEquals(new CategoryDTO(mockLine.getLineName(), mockLine.getId()), rootItem.getValue());

        // Check first child category
        TreeItem<Object> categoryItem = rootItem.getChildren().get(0);
        assertNotNull(categoryItem);
        assertEquals(mockCategory, categoryItem.getValue());

        // Check first child model of the category
        TreeItem<Object> modelItem = categoryItem.getChildren().get(0);
        assertNotNull(modelItem);
        assertEquals(mockModel, modelItem.getValue());

        assertFalse(controller.titledPaneLines.isExpanded());
        assertFalse(controller.titledPaneModels.isDisable());
        assertTrue(controller.titledPaneModels.isExpanded());
    }

    @Test
    public void testOpenTreeView02() {
        // Given
        LineDTO mockLine = new LineDTO((short) 2, "Cronos");
        CategoryDTO mockCategory = new CategoryDTO("Cronos Old", (short) 3);
        ModelDTO mockModel = new ModelDTO((short) 1, "Cronos 6001-A", "");
        List<CategoryDTO> mockCategoryList = new ArrayList<>();
        mockCategoryList.add(mockCategory);
        List<ModelDTO> mockModelList = new ArrayList<>();
        mockModelList.add(mockModel);

        // Mock the service calls
        when(controller.categoryServices.getAllCategories(mockLine)).thenReturn(mockCategoryList);
        when(controller.modelServices.getAllModels(mockCategory)).thenReturn(mockModelList);

        // When
        controller.openTreeView(mockLine);

        // Then
        TreeItem<Object> rootItem = controller.treeView.getRoot();
        assertNotNull(rootItem);
        assertEquals(new CategoryDTO(mockLine.getLineName(), mockLine.getId()), rootItem.getValue());

        // Check the number of categories
        List<TreeItem<Object>> categoryItems = rootItem.getChildren();
        assertEquals(1, categoryItems.size());

        // Check the first category and its model
        TreeItem<Object> categoryItem = categoryItems.get(0);
        assertNotNull(categoryItem);
        assertEquals(mockCategory, categoryItem.getValue());

        List<TreeItem<Object>> modelItems = categoryItem.getChildren();
        assertEquals(1, modelItems.size());

        TreeItem<Object> modelItem = modelItems.get(0);
        assertNotNull(modelItem);
        assertEquals(mockModel, modelItem.getValue());

        assertFalse(controller.titledPaneLines.isExpanded());
        assertFalse(controller.titledPaneModels.isDisable());
        assertTrue(controller.titledPaneModels.isExpanded());

        assertEquals(2, controller.treeView.getExpandedItemCount());
    }
}
