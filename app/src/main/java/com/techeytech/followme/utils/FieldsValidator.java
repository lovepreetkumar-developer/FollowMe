package com.techeytech.followme.utils;

import android.graphics.Color;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * Created by jatin on 12/1/2016.
 */

public class FieldsValidator {

    // Regular Expression
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    public static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d][A-Za-z\\d!@#$%^&*()_+]{7,19}$";
    public static final String PASSWORD_REGEX_NEW = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$";
    public static final String AT_LEAST_ONE_DIGIT_AND_UPPER_ALPHA_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
    public static final String AT_LEAST_ONE_SYMBOL_AND_UPPER_ALPHA_REGEX = "^(?=.*[$@$!%*#?&])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";

    // Error Messages
    public static final String REQUIRED_MSG = "Required";
    public static final String EMAIL_MSG = "Email address is required and in correct format";
    public static final String PHONE_MSG = "###-#######";
    public static final String PASSWORD_MSG = "Password must be of 9 character and include atleast one small and one capital letter and number";
    public static final String TEXT_LIMIT = "Nickname cannot be greater than 18";

    public FieldsValidator() {
    }

    // call this method when you need to check email validation
    public boolean isEmailAddress(EditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    public boolean isValidPassword(EditText editText, boolean required) {
        if (editText.getText().toString().length() < 8) {
            editText.setError(PASSWORD_MSG);
            return false;
        } else {
            return isValid(editText, PASSWORD_REGEX_NEW, PASSWORD_MSG, required);
        }
    }

    // call this method when you need to check phone number validation
    public boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public boolean isValid(EditText editText, String regex, String errMsg, boolean required) {
        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);
        // text required and editText is blank, so return false
        if (required && !hasText(editText)) return false;
        // pattern doesn't match so returning false

        if (!Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }
        ;
        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public boolean hasText(TextView textview) {
        String text = textview.getText().toString().trim();
        textview.setError(null);
        // length 0 means there is no text
        if (text.length() == 0) {
            textview.setError(REQUIRED_MSG);
            return false;
        }
        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public boolean hasText(EditText editText, String errMsg) {
        String text = editText.getText().toString().trim();
        editText.setError(null);
        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(errMsg);

            return false;
        }
        return true;
    }

    public boolean isTextWithInRange(EditText editText, int minLimit, int maxLimit, String error) {
        String text = editText.getText().toString().trim();
        editText.setError(null);
        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }
        if (text.length() >= minLimit && text.length() <= maxLimit) {
            return true;
        } else {
            editText.setError(error);
            return false;
        }
    }

    public boolean isMatching(EditText editText1, EditText editText2) {
        if (editText1.getText().toString().equalsIgnoreCase(editText2.getText().toString())) {
            return true;
        } else {
            editText2.setError("Old password and confirmation password don't match");
            return false;
        }
    }


    // return true if the input field is valid, based on the parameter passed
    public boolean haveAtLeastOneDigitAndUpperAlpha(EditText editText, String regex, String errMsg, boolean required) {
        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);
        // text required and editText is blank, so return false
        if (required && !hasText(editText)) return false;
        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }
        ;
        return true;
    }


    // return true if the input field is valid, based on the parameter passed
    public boolean haveAtLeastOneSymbolAndUpperAlpha(EditText editText, String regex, String errMsg, boolean required) {
        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);
        // text required and editText is blank, so return false
        if (required && !hasText(editText)) return false;
        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }
        ;
        return true;
    }


    private synchronized void clearError(final EditText view) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setError(null);
            }
        }, 1000);


    }

    public synchronized boolean validateEmail(EditText view, String message) {
        if (message == null || message.isEmpty())
            message = "Please enter a valid email";

        ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
        ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
                view.getText().toString()).matches()) {
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        return false;
    }


    public synchronized boolean validateNotEmpty(final TextView view, String message) {
        if (message == null || message.isEmpty())
            message = "This field should not be empty";
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
        ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
        if (view.getText().toString().isEmpty()) {
            view.requestFocus();
            view.setError(ssbuilder);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setError(null);
                }
            }, 1000);

            return true;
        }
        return false;
    }

    public synchronized boolean validateNotEmpty(EditText view, String message) {
        if (message == null || message.isEmpty() || message.equalsIgnoreCase(""))
            message = "This field should not be empty";
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
        ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
        if (view.getText().toString().isEmpty()) {
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        return false;
    }

    public synchronized boolean validateLength(EditText view, int minLength, int maxLength,
                                               String message) {
        if (view.getText().toString().equals("")
                || view.getText().toString().length() < minLength) {
            if (message == null || message.isEmpty())
                message = "Input should be more than " + minLength
                        + " characters.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(
                    message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        if (view.getText().toString().length() > maxLength) {
            if (message == null || message.isEmpty())
                message = "Input should be less than " + maxLength
                        + " characters.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(
                    message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        return false;
    }

    public synchronized boolean validateLength(TextView view, int minLength, int maxLength,
                                               String message) {
        if (view.getText().toString().equals("")
                || view.getText().toString().length() < minLength) {
            if (message == null || message.isEmpty())
                message = "Input should be more than " + minLength
                        + " characters.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(
                    message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.setError(ssbuilder);
            return true;
        }
        if (view.getText().toString().length() > maxLength) {
            if (message == null || message.isEmpty())
                message = "Input should be less than " + maxLength
                        + " characters.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(
                    message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.setError(ssbuilder);
            return true;
        }
        return false;
    }


    public synchronized boolean validateValidCharacters(EditText view, Pattern pattern,
                                                        String message) {
        if (!pattern.matcher(view.getText().toString()).matches()) {
            if (message == null || message.isEmpty())
                message = "This input does not fit the requiered pattern.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(
                    message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        return false;
    }

    public synchronized boolean validateUsernameWithoutSpace(EditText view,
                                                             String message) {
        if (view.getText().toString().toString().contains(" ")) {
            if (message == null || message.isEmpty())
                message = "This input does not fit the requiered pattern.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        return false;
    }

    public synchronized boolean validateUsernameSpace(EditText view, String message) {
        if (view.getText().toString().toString().trim().equalsIgnoreCase("")) {
            if (message == null || message.isEmpty())
                message = "This field should not be empty.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        return false;
    }

    public synchronized boolean validatedateofbirth(EditText view, String message) {
        if (view.getText().toString().toString().trim().equalsIgnoreCase("")) {
            if (message == null || message.isEmpty())
                message = "This field should not be empty.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        return false;
    }

    public synchronized boolean validateGender(Spinner view, String message) {
        View spinnerview = view.getSelectedView();
        TextView selectedTextView = (TextView) spinnerview;
        String gender = (String) view.getSelectedItem();
        if (gender.equalsIgnoreCase("sex")) {
            if (message == null || message.isEmpty())
                message = "Please choose Gender.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            //((TextView) spinnerview).setError(ssbuilder);
            selectedTextView.setError(ssbuilder);
            return true;

        }
        return false;
    }

    public synchronized boolean validatePhoneNumberSpace(EditText view, String message) {
        if (view.getText().toString().toString().trim().equalsIgnoreCase("")) {
            if (message == null || message.isEmpty())
                message = "This field should not be empty.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        if ((view.getText().toString().toString().length() < 10 || view.getText().toString().toString().length() > 13)) {
            message = "Please enter the right phone number.";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            view.requestFocus();
            view.setError(ssbuilder);
            clearError(view);
            return true;
        }
        return false;
    }

    public synchronized boolean validatePasswordMatch(EditText password, EditText confirmPassword, String message) {
        if (!password.getText().toString().equalsIgnoreCase(confirmPassword.getText().toString())) {
            if (message == null || message.isEmpty())
                message = "Password Mismatch";
            ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.WHITE);
            SpannableStringBuilder ssbuilder = new SpannableStringBuilder(
                    message);
            ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
            confirmPassword.requestFocus();
            confirmPassword.setError(ssbuilder);
            clearError(confirmPassword);
            return false;
        }

        return true;

    }
}