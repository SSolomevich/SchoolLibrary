package com.company;

/**
 * Created by 15 on 19.01.2018.
 */
public class Publication {

    private Integer numberPublication;

    private String typePublication;

    private String namePublication;

    private Boolean availablePublication;

    public Publication(Integer numberPublication, String typePublication, String namePublication, Boolean availablePublication) {
        this.numberPublication = numberPublication;
        this.typePublication = typePublication;
        this.namePublication = namePublication;
        this.availablePublication = availablePublication;
    }

    public Integer getNumberPublication() {
        return numberPublication;
    }

    public void setNumberPublication(Integer numberPublication) {
        this.numberPublication = numberPublication;
    }

    public String getTypePublication() {
        return typePublication;
    }

    public void setTypePublication(String typePublication) {
        this.typePublication = typePublication;
    }

    public String getNamePublication() {
        return namePublication;
    }

    public void setNamePublication(String namePublication) {
        this.namePublication = namePublication;
    }

    public Boolean getAvailablePublication() {
        return availablePublication;
    }

    public void setAvailablePublication(Boolean availablePublication) {
        this.availablePublication = availablePublication;
    }


}
