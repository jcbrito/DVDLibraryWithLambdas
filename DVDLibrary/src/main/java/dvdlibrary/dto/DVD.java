/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dvdlibrary.dto;

import java.util.Objects;

/**
 *
 * @author 17202
 */
//import java.util.Date; //for release date
public class DVD {

    public enum MPAA {
        G, PG, PG13, R, NC17, M, GP, X //enum class for MPAA Ratings
    }

    //data fields attributes
    //added final keyword for title to make sure we don't get repeat DVDs
    private final String title;
    private String releaseDate;
    private MPAA rating;
    private String directorName;
    private String studio;
    private String userNote;

    //constructors
    public DVD(String title) {
        this.title = title;
    }

    //creates a delimeted string representation of the DVD for writing to a file.
    public String createDVDRecord() {

        return title + "::" + releaseDate + "::" + getRatingString() + "::" + directorName + "::" + studio + "::" + userNote;
    }
    //constructor for simple DVD implementation with just title

    //to sstring method for DVD object 
    @Override
    public String toString() {//overriding the toString() method  
        return "Tilte = " + title + " Release Date = " + releaseDate + " Rating = " + rating + " Director Name = " + directorName + " Status = " + studio + " UserNote = " + userNote;
    }

    //constructor for full DVD implementation with all fields
    public DVD(String title, String releaseDate, String rating, String directorName, String studio, String userNote) {
        //set all fields from input except rating
        this.title = title;
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        this.studio = studio;
        this.userNote = userNote;
        //use rating conversion for enum type 
        ConvertRating(rating);
    }

    //getters and setters
    //title getter
    public String getTitle() {
        return this.title;
    }

    //releaseDate getter
    public String getReleaseDate() {
        return this.releaseDate;
    }

    //releaseDate setter
    public void setReleaseDate(String newdate) {
        this.releaseDate = newdate;
    }

    //directorName getter
    public String getDirector() {
        return this.directorName;
    }

    //directorName setter
    public void setDirector(String name) {
        this.directorName = name;
    }

    //Studio getter
    public String getStudio() {
        return this.studio;
    }

    //Studio setter
    public void setStudio(String name) {
        this.studio = name;
    }

    //userNote getter
    public String getUserNote() {
        return this.userNote;
    }

    //userNote setter
    public void setUserNote(String note) {
        this.userNote = note;
    }

    //conversion methods
    //method for converting MPAA enums
    public void ConvertRating(String rating) { //conversion from string input to MPAA enum class for rating
        //use if else statement chain since dealing with strings
        //***maybe a way to make this easier? switch?***
        if (rating.equals("G")) {
            this.rating = MPAA.G;
        } else if (rating.equals("PG")) {
            this.rating = MPAA.PG;
        } else if (rating.equals("PG13")) {
            this.rating = MPAA.PG13;
        } else if (rating.equals("R")) {
            this.rating = MPAA.R;
        } else if (rating.equals("NC17")) {
            this.rating = MPAA.NC17;
        } else if (rating.equals("M")) {
            this.rating = MPAA.M;
        } else if (rating.equals("GP")) {
            this.rating = MPAA.GP;
        } else {
            this.rating = MPAA.X; //possibly we should use an alternate else statement for an invalid input
        }
    }

    //method for converting MPAA enum ratings to strings for output
    public String getRatingString() {
        //create MPAA enum for currect DVD enum
        //create empty string for return value
        MPAA temp = this.rating;
        String ret = "";
        //switch statement with MPAA enum class cases
        switch (temp) {
            case G:
                ret = "G";
                break;
            case PG:
                ret = "PG";
                break;
            case PG13:
                ret = "PG13";
                break;
            case R:
                ret = "R";
                break;
            case NC17:
                ret = "NC17";
                break;
            case M:
                ret = "M";
                break;
            case GP:
                ret = "GP";
                break;
            case X:
                ret = "X";
                break;
            default: //not sure what to do for default
                break;
        }
        //return conversion
        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.releaseDate);
        hash = 67 * hash + Objects.hashCode(getRatingString());
        hash = 67 * hash + Objects.hashCode(this.directorName);
        hash = 67 * hash + Objects.hashCode(this.studio);
        hash = 67 * hash + Objects.hashCode(this.userNote);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userNote, other.userNote)) {
            return false;
        }
        return this.rating == other.rating;
    }

}
