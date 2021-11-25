/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.learnbyproject.helper;

public class Validator {
    public static final int SUCCESS = 1;
    public static boolean checkName(String name) {
        return name.matches("[a-zA-Z\\s]{3,50}");
    }
    public static boolean checkEmailOrPhone(String emailOrPhone) {
        // check email first
        if (!emailOrPhone.matches("[a-z0-9-_]{3,30}@[a-z0-9-]{3,30}([.][a-z1-9]{2,5}){1,2}")) {
            if (!emailOrPhone.matches("\\d{9,11}")) {
                return false;
            }
        }
        return true;
    }
    /**
     *  At least 4 chars, max 8 chars
        Contains at least one digit
        Contains at least one upper alpha char
     */
    public static boolean checkPassword(String password) {
        return password.matches("(?=.*\\d+.*)(?=.*[A-Z]+.*)\\w{4,8}");
    }
}
