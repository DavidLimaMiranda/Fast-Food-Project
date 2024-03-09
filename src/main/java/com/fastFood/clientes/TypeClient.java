package com.fastFood.clientes;

public enum TypeClient {
    CLIENT("client"),
    COMPANY("company");

    private String typeClient;

    TypeClient(String type) {

        this.typeClient = type;
    }

    public String getTypeClient() {
        return typeClient;
    }
}
