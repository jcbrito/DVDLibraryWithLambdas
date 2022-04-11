package dvdlibrary.dao;

import dvdlibrary.dto.DVD;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan B
 */
public class DVDLibraryDaoFITest {

    DVDLibraryDao dao;

    public DVDLibraryDaoFITest() {
    }

    @BeforeEach
    public void setUp() {

        dao = new DVDLibraryDaoFI();

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddGetDVD() {

        String title = "Movie 1";
        String releaseDate = "11-12-1999";
        String rating = "G";
        String director = "director 1";
        String studio = "studio 1";
        String note = "Good";

        DVD dvd = new DVD(title, releaseDate, rating, director, studio, note);

        dao.addDVD(dvd);

        DVD retrievedDVD = dao.findDVD(title);

        // Check the data is equal
        assertEquals(dvd.getTitle(),
                retrievedDVD.getTitle(),
                "Checking DVD title.");
        assertEquals(dvd.getReleaseDate(),
                retrievedDVD.getReleaseDate(),
                "Checking DVD Release Date.");
        assertEquals(dvd.getRatingString(),
                retrievedDVD.getRatingString(),
                "Checking DVD Rating.");
        assertEquals(dvd.getDirector(),
                retrievedDVD.getDirector(),
                "Checking DVD Director.");
        assertEquals(dvd.getStudio(),
                retrievedDVD.getStudio(),
                "Checking DVD Studio.");
        assertEquals(dvd.getUserNote(),
                retrievedDVD.getUserNote(),
                "Checking DVD User Notes.");

    }

    @Test
    public void testRemoveDVD() {

        String title = "Movie 1";
        String releaseDate = "11-12-1999";
        String rating = "G";
        String director = "director 1";
        String studio = "studio 1";
        String note = "Good";

        String title2 = "Movie 2";
        String releaseDate2 = "11-12-2005";
        String rating2 = "R";
        String director2 = "director 2";
        String studio2 = "studio 2";
        String note2 = "Better";

        DVD dvd = new DVD(title, releaseDate, rating, director, studio, note);
        DVD dvd2 = new DVD(title2, releaseDate2, rating2, director2, studio2, note2);

        dao.addDVD(dvd);
        dao.addDVD(dvd2);

        dao.removeDVD(dvd2);
        assertEquals(1, dao.listAllDVDs().size(), "The library should only have 1 DVD.");

        assertFalse(dao.findDVD(title2) != null, "The library should NOT include " + title2);
        assertFalse(dao.findDVD(title) == null, "The library should include " + title);

        dao.removeDVD(dvd);

        assertEquals(0, dao.listAllDVDs().size(), "The library should  have no DVDs.");

        assertFalse(dao.findDVD(title2) != null, "The library should NOT include " + title2);
        assertFalse(dao.findDVD(title) != null, "The library should NOT include " + title);

    }

    @Test
    public void testListAllDVD() {

        String title = "Movie 1";
        String releaseDate = "11-12-1999";
        String rating = "G";
        String director = "director 1";
        String studio = "studio 1";
        String note = "Good";

        String title2 = "Movie 2";
        String releaseDate2 = "11-12-2005";
        String rating2 = "R";
        String director2 = "director 2";
        String studio2 = "studio 2";
        String note2 = "Better";

        DVD dvd = new DVD(title, releaseDate, rating, director, studio, note);
        DVD dvd2 = new DVD(title2, releaseDate2, rating2, director2, studio2, note2);
        
        //check the list is empty
        assertEquals(0, dao.listAllDVDs().size(), "The size should be 0 since we started with an empty library.");

        //add one dvd
        dao.addDVD(dvd);
        //expect a list of size 1
        assertEquals(1, dao.listAllDVDs().size(), "The size should be 1 since we added one DVD.");
        
        //add second dvd
        dao.addDVD(dvd2);
        //expected list of size 2
        assertEquals(2, dao.listAllDVDs().size(), "The size should be 2 since we added another DVD.");

    }
    
    @Test
    public void testWriteToFile(){
        
        String title = "Movie 1";
        String releaseDate = "11-12-1999";
        String rating = "G";
        String director = "director 1";
        String studio = "studio 1";
        String note = "Good";

        String title2 = "Movie 2";
        String releaseDate2 = "11-12-2005";
        String rating2 = "R";
        String director2 = "director 2";
        String studio2 = "studio 2";
        String note2 = "Better";

        DVD dvd = new DVD(title, releaseDate, rating, director, studio, note);
        DVD dvd2 = new DVD(title2, releaseDate2, rating2, director2, studio2, note2);
        
        dao.addDVD(dvd);
        dao.addDVD(dvd2);
        
        int records = dao.saveToFile("testDVD.txt");
        
        assertEquals(2, records, "Two DVDs should of been written out tot he disk.");
        
    }
    
      public void testLoadFile(){
        
        String title = "Movie 1";
        String releaseDate = "11-12-1999";
        String rating = "G";
        String director = "director 1";
        String studio = "studio 1";
        String note = "Good";

        String title2 = "Movie 2";
        String releaseDate2 = "11-12-2005";
        String rating2 = "R";
        String director2 = "director 2";
        String studio2 = "studio 2";
        String note2 = "Better";

        DVD dvd = new DVD(title, releaseDate, rating, director, studio, note);
        DVD dvd2 = new DVD(title2, releaseDate2, rating2, director2, studio2, note2);
        
        dao.addDVD(dvd);
        dao.addDVD(dvd2);
        
               
        //delete all records
        dao.removeDVD(dvd);
        dao.removeDVD(dvd2);
        
        assertEquals(0, dao.listAllDVDs().size(), "The in memeory library should be empty.");
        
        //the records should be there from the previous write test. (check testDVD.txt file)
        int records = dao.loadFromFile("testDVD.txt");
        
        assertEquals(2, records, "Two records should of been read fromt eh file.");
        assertEquals(2, dao.listAllDVDs().size(), "We should have a total of 2 records in the library.");
              
    }
    
}
