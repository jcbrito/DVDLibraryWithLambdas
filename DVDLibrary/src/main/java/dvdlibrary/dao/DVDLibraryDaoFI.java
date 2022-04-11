/*
    This is the dao implementation which handles operations 
    for our library.
*/

package dvdlibrary.dao;

/**
 *
 * @author 17202
 */
import java.util.HashMap;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;
import java.util.List;

import dvdlibrary.dto.DVD;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DVDLibraryDaoFI implements DVDLibraryDao {

    HashMap<String, DVD> hmap = new HashMap<String, DVD>();

    @Override
    public void addDVD(DVD dvd) {
        hmap.put(dvd.getTitle(), dvd);
    }

    @Override
    public void removeDVD(DVD dvd) {
        hmap.remove(dvd.getTitle());
    }

    @Override
    public void editDVD(DVD dvd) {
        hmap.replace(dvd.getTitle(), dvd);

    }

    @Override
    public List<DVD> listAllDVDs() {
        //list the keys

        List<DVD> list = new ArrayList<DVD>();
        for (DVD key : hmap.values()) {
            list.add(key);
        }
        return list;
    }

    @Override
    public void displayDVD(DVD dvd) {
        System.out.println(dvd);
    }

    @Override
    public DVD findDVD(String title) {
        return hmap.get(title);
    }

    
    /*
        reads records from the file and creates a hasmap that will
        serve as our library.
    */
    
    @Override
    public int loadFromFile(String fileName) {

        HashMap<String, DVD> loaded = new HashMap<>();
        int numberOfRecords = 0;
        try {

            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dvdField = data.split("::");

                DVD tmp = new DVD(dvdField[0], dvdField[1], dvdField[2], dvdField[3], dvdField[4], dvdField[5]);
                loaded.put(tmp.getTitle(), tmp);
                numberOfRecords++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        hmap = loaded;
        return numberOfRecords;
    }

    /*
        saves all the records in the hashmap to a file.
    */
    
    @Override
    public int saveToFile(String fileName) {

        int numberOfRecords = 0;
        try {
            PrintWriter myWriter = new PrintWriter(new File(fileName));
            for (DVD dvd : hmap.values()) {
              
                myWriter.write(dvd.createDVDRecord() + "\n");
                numberOfRecords++;
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return numberOfRecords;
    }
}
