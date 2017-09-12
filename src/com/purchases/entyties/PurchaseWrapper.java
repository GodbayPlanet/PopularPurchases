package com.purchases.entyties;

public class PurchaseWrapper {

	private Purchase[] purchases;

    public Purchase[] getPurchases ()
    {
        return purchases;
    }

    public void setPurchases (Purchase[] purchases)
    {
        this.purchases = purchases;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [purchases = " + purchases + "]";
    }
}
