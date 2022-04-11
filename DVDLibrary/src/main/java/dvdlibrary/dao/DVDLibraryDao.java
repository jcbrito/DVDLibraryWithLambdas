/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dvdlibrary.dao;

import dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author 17202
 */
public interface DVDLibraryDao {
    
    public void addDVD(DVD dvd);   
    
    public void removeDVD(DVD dvd);
    
    public void editDVD(DVD dvd);
    
    public List<DVD> listAllDVDs();
    
    public void displayDVD(DVD dvd);
    
    public DVD findDVD(String title);
    
    public int loadFromFile(String fileName);
    
    public int saveToFile(String fileName);
    
}
