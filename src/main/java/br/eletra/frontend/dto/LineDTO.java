package br.eletra.frontend.dto;

public class LineDTO {
    private short id;

    private String lineName;

    public LineDTO(LineDTO lineName) {
        this.lineName = String.valueOf(lineName);
    }

    public String toString() {
        return lineName;
    }

    public void setLineName(LineDTO selectedLine) {
        this.lineName = String.valueOf(selectedLine);
    }

    public String getLineName() {
        return this.lineName;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }
}