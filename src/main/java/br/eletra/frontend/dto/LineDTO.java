package br.eletra.frontend.dto;

public class LineDTO {

    private short id;
    private String lineName;

    public LineDTO() {
    }

    public LineDTO(short id, String lineName) {
        this.id = id;
        this.lineName = lineName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return lineName;
    }
}
