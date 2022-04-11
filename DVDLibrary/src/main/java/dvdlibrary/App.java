/*
    the main class for the DVDLibrary application
*/
package dvdlibrary;

import dvdlibrary.controller.DVDLibraryController;


/**
 *
 * @author 17202
 */
public class App {
    
    public static void main(String[] args){
        DVDLibraryController con = new DVDLibraryController();
        con.run();
        
    }
    
}
