package micro.spv.clientes.enumerators;

public enum PhoneClientCodeSaveEnum {
    PHONE_NUMBER_EXISTS((short)0),
    PHONE_SAVED((short)1),
    PHONE_NUMBER_DONT_EXISTS((short)2),
    PHONE_NUMBER_UPDATED((short)3);

    private short value;

    PhoneClientCodeSaveEnum(short value){
        this.value = value;
    }

    public short getValue(){
        return this.value;
    }

}
