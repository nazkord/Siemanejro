package com.siemanejro.siemanejroproject.communication;

public enum Client {

    SIEMAJERO(new SiemanejroOkHttpCommunication());

    Client(SiemajeroCommunication siemajeroComm) {
        this.siemajeroComm = siemajeroComm;
    }

    private SiemajeroCommunication siemajeroComm;

    public SiemajeroCommunication get() {
        return siemajeroComm;
    }
}
