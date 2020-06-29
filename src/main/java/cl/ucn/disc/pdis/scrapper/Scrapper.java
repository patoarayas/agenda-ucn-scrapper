/*
 * The MIT License (MIT)
 * Copyright (c) 2020 Patricio Araya
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the “Software”), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.pdis.scrapper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class Scrapper {

    /** Logger */
    public static Logger log = LoggerFactory.getLogger(Scrapper.class);

    /**
     * Main method
     * @param args .
     */
    public static void main(String[] args){

        // Setup DB
        String dbUrl = "jdbc:sqlite:contactos.db";
        ConnectionSource connectionSource = null;
        Dao<Contact,Integer> contactDao = null;

        try {
            connectionSource = new JdbcConnectionSource(dbUrl);
            log.debug("DB conection open");
            // Create the DAO
            contactDao = DaoManager.createDao(connectionSource, Contact.class);
            // Create table;
            TableUtils.createTableIfNotExists(connectionSource, Contact.class);

        } catch (SQLException e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            log.error("Error creating a DB connection: "+ e.getMessage());
        } finally {
            try {
                connectionSource.close();
            } catch (IOException e) {
                log.error("Error closing DB connection: "+e.getMessage());
            }
        }


        // URL to agenda ucn
        final String url = "http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=";
        int id = 21;
        int lastId = 29730;

        // For every id get contact info
        for(int i = id; id <= lastId;id++){
            log.info("Getting contact id: "+id);
            Contact contact = getContactInfo(id);
            if(contact != null){
                try{
                    contactDao.create(contact);
                } catch (SQLException e){
                    log.error("Error inserting contact info:"+ e.getMessage());
                }
            }

            try {
                Random random = new Random();
                int delay = 1000 + random.nextInt(500);
                Thread.sleep(delay);
                log.info("Delay of :"+ delay);
            } catch (InterruptedException e){
                log.debug("Delay was interrupted: "+e.getMessage());
            }

        }

    }

    private static Contact getContactInfo(Integer id){
        final String url = "http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=";
        Contact newContact = null;
        try{
            Document doc = Jsoup.connect(url+id).get();
            String name =  doc.getElementById("lblNombre").text();
            String position =  doc.getElementById("lblCargo").text();
            String unit =  doc.getElementById("lblUnidad").text();
            String email =  doc.getElementById("lblEmail").text();
            String phone =  doc.getElementById("lblTelefono").text();
            String office =  doc.getElementById("lblOficina").text();
            String address =  doc.getElementById("lblDireccion").text();

            if(!name.isEmpty()){
                newContact = new Contact(id.toString(),name,position,unit,email,phone,office,address);
                log.debug("CONTACT: "+ newContact.toString());
            }


        } catch (IOException e) {
            log.error("Error retrieving contact info: "+e.getMessage());
        }

        return newContact;

    }




    
}
