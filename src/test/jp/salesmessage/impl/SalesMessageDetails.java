package test.jp.salesmessage.impl;

import test.jp.salesmessage.saleslog.SalesMessageLog;

/**
 
 *      This class processes each product sales and appends them in a sales log.
 *      Uses a message parser to parse the incoming messages and collects the
 *      product information and prepares a product object for sale processing.
 *      Ignores processing of any invalid messages and processes until it reaches the
 *      log report limit.
 */
public class SalesMessageDetails {
    
    public SalesMessageLog salesMessageLog;    
    private PriceAdjustmentDetails priceAdjustmentDetails;
    private ProductDetials productDetials;
   
    public SalesMessageDetails() {
    	salesMessageLog = new SalesMessageLog();
    }

    // Process notices and appends product information to the relevant product in the
    	// SalesMessageLog.
    // @return [Boolean] false on empty string and invalid message and true on the rest.
    public boolean salesMessageProcess(String saleNotice) {

        // It helps to parse the incoming messages and obtain product sale information.               
        MessageReadAndParserDetails messageReadAndParserDetails = new MessageReadAndParserDetails(saleNotice);

        // Get the product type e.g 'apple'
        String productType = messageReadAndParserDetails.getProductType();

        // Check if product type is empty return false and do nothing.
        if (productType.isEmpty()) {
            return false;
        }

        //Returns an existing product else returns a new Product object
        this.productDetials = salesMessageLog.getProduct(productType);

        // Prepare the productDetials details for adjustment
        this.priceAdjustmentDetails = new PriceAdjustmentDetails(productDetials);

        // Set the productDetials details from the parsed message
        this.productDetials.setProductQuantity(messageReadAndParserDetails.getProductQuantity());
        this.productDetials.setTotalQuantity(messageReadAndParserDetails.getProductQuantity());
        this.productDetials.setProductPrice(messageReadAndParserDetails.getProductPrice());
        this.productDetials.setAdjustmentOperator(messageReadAndParserDetails.getOperatorType());

        // Set the total value of the productDetials.
        setProductTotalPrice();

        // Set the sale log reports
        salesMessageLog.setSalesReports(saleNotice);

        // Update the productDetials with the new details
        salesMessageLog.updateProduct(productDetials);

        return true;
    }

    // Set or Append Total productDetials price based on any adjustment if given.
    // Also appends the log for adjustments made.
    private void setProductTotalPrice() {
        double adjustedPrice;
        double productValue;

        if (!productDetials.getAdjustmentOperator().isEmpty()) {
            adjustedPrice = priceAdjustmentDetails.getAdjustedPrice();
            salesMessageLog.setAdjustmentReports(priceAdjustmentDetails.adjustmentReport());
            productDetials.setTotalPrice(adjustedPrice);
        } else {
            productValue = productDetials.calculatePrice(productDetials.getProductQuantity(), productDetials.getProductPrice());
            productDetials.appendTotalPrice(productValue);
        }
    }

}