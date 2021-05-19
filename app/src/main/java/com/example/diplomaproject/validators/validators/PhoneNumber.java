package com.example.diplomaproject.validators.validators;

import android.widget.EditText;

import com.example.diplomaproject.validators.interfaces.ErrorCallBack;

import java.util.HashMap;

public class PhoneNumber {

    public static String TAG = PhoneNumber.class.getSimpleName();
    private String value; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;
    private int maxLength = 12;
    private EditText mEditText;
    private boolean checkInputType = false;

    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String MAX_LENGTH = "MaxLength";
    public static String MIN_LENGTH = "MinLength";
    public static String IS_REQUIRED = "IsRequired";
    public static String EMPTY = "Empty";

    private ErrorCallBack errorCallBack;

    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public Boolean checkInputType() {
        return checkInputType;
    }

    public static class PhoneNumberBuilder {
        private String value; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;
        private int maxLength = 10;
        private int minLength = 10;
        private EditText mEditText;
        private boolean checkInputType = false;

        public PhoneNumberBuilder(String value) {
            this.value = value;
        }

        public PhoneNumberBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public PhoneNumberBuilder setMaxLength(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public PhoneNumberBuilder setMinLength(int minLength) {
            this.minLength = minLength;
            return this;
        }


        public PhoneNumberBuilder setEditText(EditText mEditText) {
            this.mEditText = mEditText;
            return this;
        }

        public PhoneNumberBuilder checkInputType(boolean checkInputType) {
            this.checkInputType = checkInputType;
            return this;
        }

        public HashMap<String, Boolean> build() {
            HashMap<String, Boolean> phoneNumberValidatorResp = new HashMap<String, Boolean>();

            if (this.isRequired) {
                phoneNumberValidatorResp.put(IS_REQUIRED, true);
            } else {
                phoneNumberValidatorResp.put(IS_REQUIRED, false);
            }
            if (this.value != null && !this.value.isEmpty()) {
                phoneNumberValidatorResp.put(EMPTY, false);
                if (this.value.length() >= minLength && this.value.length() <= maxLength) {
                    phoneNumberValidatorResp.put(SUCCESS, true);
                    phoneNumberValidatorResp.put(MAX_LENGTH, true);
                    phoneNumberValidatorResp.put(MIN_LENGTH, true);
                } else {
                    phoneNumberValidatorResp.put(SUCCESS, false);
                    phoneNumberValidatorResp.put(MAX_LENGTH, false);
                    phoneNumberValidatorResp.put(MIN_LENGTH, false);
                }
            } else {
                phoneNumberValidatorResp.put(SUCCESS, false);
                phoneNumberValidatorResp.put(EMPTY, true);
                phoneNumberValidatorResp.put(MAX_LENGTH, false);
                phoneNumberValidatorResp.put(MIN_LENGTH, false);
            }
            return phoneNumberValidatorResp;
        }

    }

    public PhoneNumber(ErrorCallBack back) {
        this.errorCallBack = back;
    }

    public boolean isValid(HashMap<String, Boolean> hashMap) {

        if (hashMap.get(PhoneNumber.SUCCESS)) {
            return true;
        } else {
            if (hashMap.get(PhoneNumber.IS_REQUIRED)) {
                if (!hashMap.get(PhoneNumber.EMPTY)) {
                    if (!hashMap.get(PhoneNumber.MIN_LENGTH) && !hashMap.get(PhoneNumber.MAX_LENGTH)) {
                        errorCallBack.onValidationError("Неверный номер телефона");
                        return false;
                    }
                } else {
                    errorCallBack.onValidationError("Номер телефона не может быть пустым.");
                    return false;
                }
            } else {
                if (!hashMap.get(PhoneNumber.EMPTY)) {
                    if (!hashMap.get(PhoneNumber.MIN_LENGTH) && !hashMap.get(PhoneNumber.MAX_LENGTH)) {
                        errorCallBack.onValidationError("Неверный номер телефона");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private PhoneNumber(PhoneNumberBuilder phoneNumberBuilder) {
        this.value = phoneNumberBuilder.value;
        this.isRequired = phoneNumberBuilder.isRequired;
        this.maxLength = phoneNumberBuilder.maxLength;
        this.mEditText = phoneNumberBuilder.mEditText;
        this.checkInputType = phoneNumberBuilder.checkInputType;
    }
}
