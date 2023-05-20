package micro.spv.clientes.enumerators;

public enum ClientCodeSaveEnum {
    CLIENT_EXIST((short) 0),
    CLIENT_CORRECT((short) 1);


    short value;
    ClientCodeSaveEnum(short value){
        this.value = value;
    }

    public short getValue(){
        return this.value;
    }
}
