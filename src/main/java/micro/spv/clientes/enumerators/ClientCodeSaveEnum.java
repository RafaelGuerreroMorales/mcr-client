package micro.spv.clientes.enumerators;

public enum ClientCodeSaveEnum {
    CLIENT_EXIST((short) 0),
    CLIENT_CORRECT((short) 1),
    CLIENT_DONT_EXISTS((short)2),
    CLIENT_UPDATED((short)3);


    short value;
    ClientCodeSaveEnum(short value){
        this.value = value;
    }

    public short getValue(){
        return this.value;
    }
}
