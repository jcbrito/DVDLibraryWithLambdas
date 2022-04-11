/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dvdlibrary.ui;

import dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author 17202
 */
public class DVDLibraryView {
    
    final private UserIO io;
    
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    //main menu
    public int printMenuAndGet() {
        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD");
        io.print("4. List DVDs");
        io.print("5. View DVD");
        io.print("6. Search for DVD");
        io.print("7. Save Collection");
        io.print("8. Load Collection");
        io.print("0. Exit");

        return io.readInt("Please select from the above choices.", 0, 8);
    }

    //allow user to add a DVD 
    //provide info
    //return new DVD
    //
    //editDVD notes:
    //since we use this method for editDVD as well, we implemented functionality to work for both, however, a note may be necessary to not change the title of the DVD
    //
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        String date = io.readString("Please enter DVD release date (MM-DD-YYYY)"); //replace date with time?
        String rating = io.readString("Please enter DVD MPAA rating");
        String director = io.readString("Please enter DVD director name");
        String studio = io.readString("Please enter DVD studio name");
        String userNote = io.readString("Anything else you'd like to add?");

        DVD currDVD = new DVD(title, date, rating, director, studio, userNote);

        return currDVD;
    }

    //add DVD banner
    public void displayAddDVDBanner() {
        io.print("====  Add DVD ====");
    }

    //add DVD success banner
    public void displayAddSuccessBanner() {
        io.readString(
                "DVD successfully added to your collection.  Please hit enter to continue");
    }

    //allow user to remove a DVD
    //provide dvd title
    //return dvd to be deleted
    //remove DVD Banner
    public void displayRemoveDVDBanner() {
        io.print("==== Remove DVD ====");
    }

    //remove DVD result
    public void displayRemoveResult(DVD title) {
        if (title != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    //allow user to edit existing DVD
    public void displayEditDVDBanner() {
        io.print("==== Edit DVD ====");
    }
    //edit dvd 
    public DVD editDVD(DVD previous){
        //editDVD passes in a DVD to edit, first we notify the user of the DVD title that they're editing
        io.print("You chose to edit the DVD titled: " + previous.getTitle());
        //next we list the current fields of the DVD that they're editing
        io.print("Here are the current fields of that DVD:");
        listProperties(previous);
        //next we go through the steps for editing a DVD
        io.print("We're going to go through all properties of the specified DVD");
        io.print("Please type out changes as prompted");
        io.print("To leave a field the same, press Enter without typing anything"); //leaving a field empty will copy over the previous DVD field for that section
        DVD curr = getNewDVDInfo();
        if(curr.getReleaseDate().equals("")){
            curr.setReleaseDate(previous.getReleaseDate());
        }
        if(curr.getDirector().equals("")){
            curr.setDirector(previous.getDirector());
        }
        if(curr.getStudio().equals("")){
            curr.setStudio(previous.getStudio());
        }
        if(curr.getRatingString().equals("")){
            curr.ConvertRating(previous.getRatingString());
        }
        if(curr.getUserNote().equals("")){
            curr.setUserNote(previous.getUserNote());
        }
        return curr;
    }
    
    //display 1 DVD List of properties (used for edit)
    public void listProperties(DVD current){
        String DVDInfo = String.format("[Title]%s [Release Date]%s [Rating]%s [Director]%s [Studio]%s [Notes]%s ",
            current.getTitle(),
            current.getReleaseDate(),
            current.getRatingString(),
            current.getDirector(),
            current.getStudio(),
            current.getUserNote());
        io.print(DVDInfo);
        io.readString("Press Enter to continue.");
    }
    

    //allow user to list DVDs in collection
    public void displayAllDVDBanner() {
        io.print("==== Your DVDs ====");
    }
    //display dvd info method
    //
    // **** we could simplify the display all using new list properties method *****
    //
    // currently cannot use listProperties for this due to the 'Press Enter to continue' functionality
    //
    public void displayAllDVD(List<DVD> DVDList) {
        for(DVD currDVD : DVDList){
            String DVDInfo = String.format("[Title]%s [Release Date]%s [Rating]%s [Director]%s [Studio]%s [Notes]%s ",
                currDVD.getTitle(),
                currDVD.getReleaseDate(),
                currDVD.getRatingString(),
                currDVD.getDirector(),
                currDVD.getStudio(),
                currDVD.getUserNote());
            io.print(DVDInfo);
        }
        io.readString("Press Enter to continue.");
    }

    //allow user to display info for specified DVD
    //allow user to search for DVD by title
    public String getDVDIDChoice() {
        io.print("==== Search ====");
        return io.readString("Please enter DVD title");
    }

    //display 1 DVD Banner
    //if user only inputs a title, can use this for displayBanner over list properties
    public void displayDVDBanner(String title) {
        io.print("==== " + title + " ====");
    }
    
    

    //display exit message
    public void displayExitMessage() {
        io.print("Goodbye!");
    }

    //display unknown command
    public void displayUnknown() {
        io.print("Unknown Command");
    }
    
    //display error message invalid input
    public void displayErrorInvalid(){
        displayError("Invalid input...try again");
    }

    //display error message
    public void displayError(String errorMsg) {
        io.print("==== Error ====");
        io.print(errorMsg);
    }
    
    //display load from file message
    public void displayLoadMessage(){
        io.print("== Loading Collection ==");
    }
    
    //load confirm number of DVDs
    public void displayLoadConfirm(int numDVD){
        io.print("Loaded " + numDVD + " DVDs");
    }
    
    //display load from file message
    public void displayWriteMessage(){
        io.print("== Saving Collection ==");
    }
    
    //display write number DVDs
    public void displayWriteConfirm(int numDVD){
        io.print("Saved " + numDVD + " DVDs");
    }
    
}
