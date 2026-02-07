package models;

public enum DocumentType {
    PASSPORT,
    ID_CARD;

    public static DocumentType fromString(String value) {
        return DocumentType.valueOf(value.toUpperCase());
    }
}


