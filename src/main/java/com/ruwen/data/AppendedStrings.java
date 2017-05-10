package com.ruwen.data;

public class AppendedStrings {
//    Decompile with javap –c ClassName.class

    public String getBusinessKeys() {

        StringBuffer buffer = new StringBuffer();
        buffer.append("Company: ").append(getCompanyCode());
        buffer.append(", TN: ").append(getTrackingNumber());
        buffer.append(", DocCode: ").append(getDocumentCode());
        buffer.append(", ClientRole: ").append(getClientRole());

        return buffer.toString();
    }

    public String fooString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Company: ").append(getCompanyCode());
        builder.append(", TN: ").append(getTrackingNumber());
        builder.append(", DocCode: ").append(getDocumentCode());
        builder.append(", ClientRole: ").append(getClientRole());

        return builder.toString();
    }


    public String barString() {

        return "Company: " + getCompanyCode()
                + ", TN: " + getTrackingNumber()
                + ", DocCode: " + getDocumentCode()
                + ", ClientRole: " + getClientRole();
    }

    public String getCompanyCode() {
        return "companyCode";
    }

    public String getTrackingNumber() {
        return "trackingNumber";
    }

    public String getDocumentCode() {
        return "documentCode";
    }

    public String getClientRole() {
        return "clientRole";
    }
}
