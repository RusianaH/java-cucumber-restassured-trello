package consts;

public enum Endpoint {
    GET_ALL_BOARDS("/1/members/{member}/boards"),
    GET_A_BOARD("/1/boards/{id}"),
    CREATE_A_BOARD("/1/boards"),
    DELETE_A_BOARD("/1/boards/{id}"),
    UPDATE_A_BOARD("/1/boards/{id}"),

    GET_ALL_CARDS("/1/lists/{list_id}/cards"),
    GET_CARD_URL("/1/cards/{id}"),
    CREATE_CARD_URL("/1/cards"),
    DELETE_CARD_URL("/1/cards/{id}"),
    UPDATE_CARD_ID("/1/cards/{id}");

    private final String url;

    Endpoint(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}