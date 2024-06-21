package br.eletra.frontend.dto;

import java.util.Objects;

public class ModelDTO {

    private short id;
    private String modelName;
    private String category;

    public ModelDTO(short id, String modelName, String category) {
        this.id = id;
        this.modelName = modelName;
        this.category = category;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelDTO modelDTO = (ModelDTO) o;
        return id == modelDTO.id &&
                Objects.equals(modelName, modelDTO.modelName) &&
                Objects.equals(category, modelDTO.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelName, category);
    }

    @Override
    public String toString() {
        return modelName;
    }
}
