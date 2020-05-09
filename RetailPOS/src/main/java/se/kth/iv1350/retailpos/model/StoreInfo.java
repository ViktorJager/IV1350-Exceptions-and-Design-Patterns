package se.kth.iv1350.retailpos.model;

/**
 * Information about the retail store, used by the receipt.
 */
class StoreInfo {
    private String storeName = "Retail Store X";
    private String storeAddress = "UnknownStreet 13, 75441, Uppsala";
    
    
    /**
     * Get the value of storeName
     *
     * @return the value of storeName
     */
    public String getStoreName() {
        return storeName;
    }
    
    /**
     * Get the value of storeAddress
     *
     * @return the value of storeAddress
     */
    public String getStoreAddress() {
        return storeAddress;
    }
}
