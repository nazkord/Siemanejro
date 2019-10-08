package communication;

public enum Client {

    SIEMAJERO(new SiemajeroOkHttpCommunication());

    private Client(SiemajeroCommunication siemajeroComm) {
        this.siemajeroComm = siemajeroComm;
    }

    private SiemajeroCommunication siemajeroComm;

    public SiemajeroCommunication get() {
        return siemajeroComm;
    }
}
