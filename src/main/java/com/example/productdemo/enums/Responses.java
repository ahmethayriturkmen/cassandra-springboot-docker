package com.example.productdemo.enums;

public enum Responses {

    SYSTEM_ERROR("01", "Sistem Hatası Meydana Geldi."),
    SAVE_OPERATION_SUCCESS("1", "Kayıt Başarıyla Eklendi."),

    SAVE_OBJECT_NULL("0","Ürün Boş Olamaz, Lütfen Tekrar Deneyiniz."),
    DELETE_OPERATION_SUCCESS("1", "Kayıt Başarıyla Silindi."),
    DELETE_OPERATION_FAILURE("0", "Kayıt Bulunamadığı İçin Silinemedi."),
    UPDATE_OPERATION_SUCCESS("1", "Kayıt Başarıyla Güncellendi."),

    UPDATE_OPERATION_NULL("1", "Ürün Boş Olamaz, Lütfen Tekrar Deneyiniz."),
    GET_OPERATION_SUCCESS("1", "Kayıt Bulundu."),
    GET_OPERATION_FAILURE("0", "Kayıt Bulunamadı, Lütfen Tekrar Deneyiniz.");

    private final String code;
    private final String description;

    Responses(String code, String description) {
        this.code = code;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}
