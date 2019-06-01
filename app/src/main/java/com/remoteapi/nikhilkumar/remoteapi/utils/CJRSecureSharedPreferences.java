package com.remoteapi.nikhilkumar.remoteapi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Base64;

import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;




public class CJRSecureSharedPreferences implements SharedPreferences {
        private SharedPreferences mSharedPreferences;
        private Context mContext;

        public CJRSecureSharedPreferences(Context context) {
            if (context != null) {
                this.mContext = context;
                this.mSharedPreferences = context.getSharedPreferences("app_pref", 0);
            }

        }

        public CJRSecureSharedPreferences(Context context, String prefsName) {
            if (context != null) {
                this.mContext = context;
                this.mSharedPreferences = context.getSharedPreferences(prefsName, 0);
            }

        }

        public CJRSecureSharedPreferences.Editor edit() {
            return new CJRSecureSharedPreferences.Editor();
        }

        public Map<String, ?> getAll() {
            throw new UnsupportedOperationException();
        }

        public boolean getBoolean(String key, boolean defValue) {
            String value = this.mSharedPreferences.getString(key, (String)null);
            if (value != null && !value.equalsIgnoreCase("null")) {
                String decrypytValue = this.decrypt(value);
                return decrypytValue != null ? Boolean.parseBoolean(decrypytValue) : defValue;
            } else {
                return defValue;
            }
        }

        public float getFloat(String key, float defValue) {
            String value = this.mSharedPreferences.getString(key, (String)null);
            if (value != null && !value.equalsIgnoreCase("null")) {
                String decrypytValue = this.decrypt(value);
                return decrypytValue != null ? Float.parseFloat(decrypytValue) : defValue;
            } else {
                return defValue;
            }
        }

        public int getInt(String key, int defValue) {
            String value = this.mSharedPreferences.getString(key, (String)null);
            if (value != null && !value.equalsIgnoreCase("null")) {
                String decrypytValue = this.decrypt(value);
                return decrypytValue != null ? Integer.parseInt(decrypytValue) : defValue;
            } else {
                return defValue;
            }
        }

        public long getLong(String key, long defValue) {
            String value = this.mSharedPreferences.getString(key, (String)null);
            if (value != null && !value.equalsIgnoreCase("null")) {
                String decrypytValue = this.decrypt(value);
                return decrypytValue != null ? Long.parseLong(decrypytValue) : defValue;
            } else {
                return defValue;
            }
        }

        public String getString(String key, String defValue) {
            if (this.mSharedPreferences == null) {
                return null;
            } else {
                String value = this.mSharedPreferences.getString(key, (String)null);
                return value != null && !value.equalsIgnoreCase("null") ? this.decrypt(value) : defValue;
            }
        }

        public boolean contains(String key) {
            return this.mSharedPreferences.contains(key);
        }

        public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
            this.mSharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }

        public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
            this.mSharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }

        private String encrypt(String toEncrypt) {
            try {
                SecretKeySpec skeySpec = new SecretKeySpec(this.generateSecretKey(), "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(1, skeySpec);
                byte[] encryptedBytes = cipher.doFinal(toEncrypt.getBytes());
                return Base64.encodeToString(encryptedBytes, 8);
            } catch (Exception var5) {
                var5.printStackTrace();
                return null;
            }
        }

        private String decrypt(String encryptedText) {
            try {
                SecretKeySpec skeySpec = new SecretKeySpec(this.generateSecretKey(), "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(2, skeySpec);
                byte[] toDecrypt = Base64.decode(encryptedText, 8);
                return new String(cipher.doFinal(toDecrypt));
            } catch (Exception var5) {
                var5.printStackTrace();
                return null;
            }
        }

        public Set<String> getStringSet(String arg0, Set<String> arg1) {
            if (this.mSharedPreferences == null) {
                return null;
            } else {
                Set<String> value = this.mSharedPreferences.getStringSet(arg0, arg1);
                return value != null ? value : null;
            }
        }

        private byte[] generateSecretKey() {
            String androidId = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
            int len = androidId.length();
            if (len > 16) {
                androidId = androidId.substring(0, 16);
            } else if (len < 16) {
                for(int diff = 16 - len; diff > 0; --diff) {
                    androidId = "0" + androidId;
                }
            }

            char[] cipher1 = new char[]{'p', '@', 'y', '!', 'm', 'k', 'e', 'Y', '4', 'o', 'n', 'E', '9', '7', 'p', 'r'};
            char[] cipher2 = androidId.toCharArray();
            char[] key = new char[16];

            for(int i = 0; i < 16; ++i) {
                key[i] = (char)(cipher1[i] ^ cipher2[i]);
            }

            return (new String(key)).getBytes();
        }


        public class Editor implements android.content.SharedPreferences.Editor {
            protected android.content.SharedPreferences.Editor mEditor;

            public Editor() {
                this.mEditor = CJRSecureSharedPreferences.this.mSharedPreferences.edit();
            }

            public CJRSecureSharedPreferences.Editor putBoolean(String key, boolean value) {
                this.mEditor.putString(key, CJRSecureSharedPreferences.this.encrypt(Boolean.toString(value)));
                return this;
            }

            public CJRSecureSharedPreferences.Editor putFloat(String key, float value) {
                this.mEditor.putString(key, CJRSecureSharedPreferences.this.encrypt(Float.toString(value)));
                return this;
            }

            public CJRSecureSharedPreferences.Editor putInt(String key, int value) {
                this.mEditor.putString(key, CJRSecureSharedPreferences.this.encrypt(Integer.toString(value)));
                return this;
            }

            public CJRSecureSharedPreferences.Editor putLong(String key, long value) {
                this.mEditor.putString(key, CJRSecureSharedPreferences.this.encrypt(Long.toString(value)));
                return this;
            }

            public CJRSecureSharedPreferences.Editor putString(String key, String value) {
                if (value != null) {
                    this.mEditor.putString(key, CJRSecureSharedPreferences.this.encrypt(value));
                } else {
                    this.mEditor.putString(key, value);
                }

                return this;
            }

            public void apply() {
                this.mEditor.apply();
            }

            public CJRSecureSharedPreferences.Editor clear() {
                this.mEditor.clear();
                return this;
            }

            public boolean commit() {
                return this.mEditor.commit();
            }

            public CJRSecureSharedPreferences.Editor remove(String key) {
                this.mEditor.remove(key);
                this.mEditor.apply();
                return this;
            }

            public android.content.SharedPreferences.Editor putStringSet(String arg0, Set<String> arg1) {
                return null;
            }
        }
    }
