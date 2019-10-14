package communication;

public enum Client {

    SIEMAJERO(new SiemajeroOkHttpCommunication());

    Client(SiemajeroCommunication siemajeroComm) {
        this.siemajeroComm = siemajeroComm;
    }

    private SiemajeroCommunication siemajeroComm;

    public SiemajeroCommunication get() {
        return siemajeroComm;
    }
}
