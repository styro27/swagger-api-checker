package org.example.enums;

public enum PetStoreEndpoints {
    // Pet Endpoints
    ADD_PET("/pet"),
    UPDATE_PET("/pet"),
    FIND_PETS_BY_STATUS("/pet/findByStatus"),
    FIND_PET_BY_ID("/pet/{petId}"),
    UPDATE_PET_WITH_FORM("/pet/{petId}"),
    DELETE_PET("/pet/{petId}"),
    UPLOAD_PET_IMAGE("/pet/{petId}/uploadImage"),

    // Store Endpoints
    PLACE_ORDER("/store/order"),
    FIND_ORDER_BY_ID("/store/order/{orderId}"),
    DELETE_ORDER("/store/order/{orderId}"),
    GET_INVENTORY("/store/inventory"),

    // User Endpoints
    CREATE_USER("/user"),
    CREATE_USERS_WITH_LIST("/user/createWithList"),
    LOGIN_USER("/user/login"),
    LOGOUT_USER("/user/logout"),
    GET_USER_BY_NAME("/user/{username}"),
    UPDATE_USER("/user/{username}"),
    DELETE_USER("/user/{username}");

    private final String path;
    PetStoreEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public String buildUrl(Object... params) {
        String result = path;
        for (Object param : params) {
            result = result.replaceFirst("\\{\\w+}", param.toString());
        }
        return result;
    }
}
