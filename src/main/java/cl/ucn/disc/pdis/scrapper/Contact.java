package cl.ucn.disc.pdis.scrapper;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contactos")
public class Contact {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String position;
    @DatabaseField
    private String unit;
    @DatabaseField
    private String email;
    @DatabaseField
    private String phone;
    @DatabaseField
    private String office;
    @DatabaseField
    private String address;

    /**
     * ORMlite constructor
     */
    public Contact(){
        // ORM lite needs an no-arg constructor
    }

    /**
     * Constructor
    */
    public Contact(String id, String name, String position, String unit, String email, String phone, String office, String address) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.unit = unit;
        this.email = email;
        this.phone = phone;
        this.office = office;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", unit='" + unit + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", office='" + office + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
