/*
    The controller class. This class talks to the view and the DAO
    to perform operations for the user.
*/

package dvdlibrary.controller;


/**
 *
 * @author 17202
 */
import dvdlibrary.dao.DVDLibraryDaoFI;
import dvdlibrary.dto.DVD;
import dvdlibrary.ui.DVDLibraryView;
import dvdlibrary.ui.UserIO;
import dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;


public class DVDLibraryController {
    UserIO uio = new UserIOConsoleImpl();
    private DVDLibraryDaoFI dao = new DVDLibraryDaoFI();
    private DVDLibraryView view = new DVDLibraryView(uio);
   
    boolean programRunning = true;
    //driver method that starts the program.
    public void run() {
      
        
        while (programRunning) {

            int choice = view.printMenuAndGet();
            
            switch (choice) {

                case 1:
                    addDVD();
                    break;
                case 2:
                    removeDVD();
                    break;
                case 3:
                    editDVD();
                    break;
                case 4:
                    showAllDVDs();
                    break;
                case 5:
                    displayDVDInfo();
                    break;
                case 6:
                    searchDVD();
                    break;
                case 7:
                    saveToFile();
                    break;
                case 8:
                    loadFromFile();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    errorMessage();

            }
            System.out.println();   
            System.out.println();
        }
    }

    /*
        These methods call operations from the view to get user requests, and
        use the dao to fufill such requests.
    */

    // error message for an invalid input
    private void errorMessage() {
        view.displayErrorInvalid();
    }

    //display for the user choosing to exit the program
    private void exit() {
        view.displayExitMessage();
        programRunning = false;
    }

    // method that gets details fromt he user and adds a DVD to the library
    private void addDVD() {
       view.displayAddDVDBanner();
       DVD newDVD = view.getNewDVDInfo();
       dao.addDVD(newDVD);
       view.displayAddSuccessBanner();
    }

    //method in charge of removing a user specified DVD fromt he library.
    private void removeDVD() {
        
        view.displayRemoveDVDBanner();
        String title = view.getDVDIDChoice();
        DVD tmp = dao.findDVD(title);
        
        if(tmp != null) dao.removeDVD(tmp); 
        
        view.displayRemoveResult(tmp);
        
    }

    //method to edit a specific DVD
    private void editDVD() {
        view.displayEditDVDBanner();
        DVD tmp = dao.findDVD(view.getDVDIDChoice());
        tmp = view.editDVD(tmp);
        dao.editDVD(tmp);
    }

    //displays all DVDs int he library
    private void showAllDVDs() {
        view.displayAllDVDBanner();
        List<DVD> dvdList = dao.listAllDVDs();
        view.displayAllDVD(dvdList);
    }

    //displays informaation of a specified DVD
    private void displayDVDInfo() {
       DVD tmp = dao.findDVD(view.getDVDIDChoice());
       view.listProperties(tmp);
    }


    //will report if a DVD is or is not in the library.
    private void searchDVD() {
        
        String title = view.getDVDIDChoice();
        DVD tmp = dao.findDVD(title);
        if(tmp != null) System.out.println(tmp.getTitle() + " is in the library.");
        
        else System.out.println(title + "Was not found.");
    }

    //method to load a library from a text file
    private void loadFromFile() {
        view.displayLoadMessage();
        dao.loadFromFile("DVDLibrary.txt");
    }

    //method to save current library to a text file
    private void saveToFile() {
        view.displayWriteMessage();
        dao.saveToFile("DVDLibrary.txt");
    }

}
