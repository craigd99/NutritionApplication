package org.meicode.nutritionapplication.pojo;

public class Item {

    private String itemName;
    private String carbohydrates;
    private String barcodeID;

    public Item (String itemName, String carbohydrates, String barcodeID){
        this.itemName = itemName;
        this.carbohydrates = carbohydrates;
        this.barcodeID = barcodeID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getBarcodeID() {
        return barcodeID;
    }

    public void setBarcodeID(String barcodeID) {
        this.barcodeID = barcodeID;
    }
}
