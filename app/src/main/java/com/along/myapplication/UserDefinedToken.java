package com.along.myapplication;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.MultiAutoCompleteTextView;

/**
 * Created by Administrator on 2016/3/30.
 */
public class UserDefinedToken implements MultiAutoCompleteTextView.Tokenizer {

    private char userDefTokenizer = ',';

    public UserDefinedToken() {
    }

    public UserDefinedToken(char token) {
        userDefTokenizer = token;
    }

    public int findTokenEnd(CharSequence text, int cursor) {
        int i = cursor;
        int len = text.length();
        while (i < len) {
            if (text.charAt(i) == userDefTokenizer) {
                return i;
            } else {
                i++;
            }
        }
        return len;

    }

    public int findTokenStart(CharSequence text, int cursor) {
        int i = cursor;
        while (i > 0 && text.charAt(i - 1) != userDefTokenizer) {
            i--;
        }
        while (i < cursor && text.charAt(i) == ' ') {
            i++;
        }
        return i;
    }

    public CharSequence terminateToken(CharSequence text) {
        int i = text.length();
        while (i > 0 && text.charAt(i - 1) == ' ') {
            i--;
        }
        if (i > 0 && text.charAt(i - 1) == userDefTokenizer) {
            return text;
        } else {
            if (text instanceof Spanned) {
                SpannableString sp = new SpannableString(text
                        + String.valueOf(userDefTokenizer));
                TextUtils.copySpansFrom((Spanned) text, 0, text.length(),
                        Object.class, sp, 0);
                return sp;
            } else {
                return text + String.valueOf(userDefTokenizer);
            }
        }
    }

    public void setToken(char token) {
        userDefTokenizer = token;
    }

    public char getToken() {
        return userDefTokenizer;
    }
}