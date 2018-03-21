package data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Swomfire on 21-Mar-18.
 */

public class Request {
    @SerializedName("seq")
    @Expose
    private Integer seq;
    @SerializedName("receiptSeq")
    @Expose
    private Integer receiptSeq;
    @SerializedName("itemSeq")
    @Expose
    private Integer itemSeq;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("tableId")
    @Expose
    private String tableId;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getReceiptSeq() {
        return receiptSeq;
    }

    public void setReceiptSeq(Integer receiptSeq) {
        this.receiptSeq = receiptSeq;
    }

    public Integer getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(Integer itemSeq) {
        this.itemSeq = itemSeq;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

}
