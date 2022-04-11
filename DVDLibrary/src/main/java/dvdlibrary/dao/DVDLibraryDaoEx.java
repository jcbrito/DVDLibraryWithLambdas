/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dvdlibrary.dao;

/**
 *
 * @author 17202
 */
public class DVDLibraryDaoEx extends Exception {
    public DVDLibraryDaoEx(String message) {
        super(message); //super: calls construction of super class (Exception)
    }
    
    public DVDLibraryDaoEx(String message, Throwable cause) {
        super(message, cause);
    }
}
