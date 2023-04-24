package edu.guilford;

import java.util.Random;

import org.h2.security.AES;

public class generator {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String favoriteColor;
    private int luckyNumber;
    private String petName;

    public generator(String firstName, String lastName, String email, String favoriteColor, int luckyNumber, String petName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.favoriteColor = favoriteColor;
        this.luckyNumber = luckyNumber;
        this.petName = petName;
        this.password = generatePassword();
        this.encryptedPassword = encryptPassword(this.password);
    }

    public generator(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public int getLuckyNumber() {
        return luckyNumber;
    }

    public void setLuckyNumber(int luckyNumber) {
        this.luckyNumber = luckyNumber;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    private String generatePassword() {
        StringBuilder sb = new StringBuilder();

        // Add characters from first name
        for (int i = 0; i < Math.min(firstName.length(), 2); i++) {
            sb.append(firstName.charAt(i));
        }

        // Add characters from last name
        for (int i = 0; i < Math.min(lastName.length(), 2); i++) {
            sb.append(lastName.charAt(i));
        }

        // Add lucky number
        sb.append(luckyNumber);

        // Add favorite color
        sb.append(favoriteColor);

        // Add pet name
        sb.append(petName);

        // Add random characters
        Random rand = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_+-=[]{}|;':\",./<>?";
        for (int i = 0; i < 8; i++) {
            sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
        }

        String password = sb.toString();
        System.out.println("Generated Password: " + password);
        return password;
    }

    /**
     * @param password
     * @return
     */
    private String encryptPassword(String password) {
        String secretKey = "ThisIsASecretKey"; // can be any string
        return AES.encrypt(password, secretKey);
    }

    public void decryptPassword() {
        String secretKey = "ThisIsASecretKey"; // must be the same secret key used to encrypt the password
        final String decryptedPassword = AES.decrypt(encryptedPassword, secretKey);
        System.out.println("Decrypted Password: " + decryptedPassword);
    }
}

