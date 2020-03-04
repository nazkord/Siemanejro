package com.siemanejro.siemanejroproject.communication;

public enum Client {

    SIEMANEJRO(new SiemanejroOkHttpCommunication());

    Client(SiemanejroCommunication siemanejroComm) {
        this.siemajeroComm = siemanejroComm;
    }

    private SiemanejroCommunication siemajeroComm;

    public SiemanejroCommunication get() {
        return siemajeroComm;
    }
}
