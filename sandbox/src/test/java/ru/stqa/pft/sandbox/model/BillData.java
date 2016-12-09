package ru.stqa.pft.sandbox.model;


import org.hibernate.annotations.Type;
import javax.persistence.*;

@Table(name = "Rebill")
public class BillData {

    @Column(name = "rebill_id")
    @Type(type = "int")
    private String billId;

    public BillData withBillId(String billId) {
        this.billId = billId;
        return this;
    }

    public String getBillId() {
        return billId;
    }

    @Override
    public String toString() {
        return "BillData{" +
                "billId='" + billId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillData billData = (BillData) o;

        return billId != null ? billId.equals(billData.billId) : billData.billId == null;
    }

    @Override
    public int hashCode() {
        return billId != null ? billId.hashCode() : 0;
    }
}
