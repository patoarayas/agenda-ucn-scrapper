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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "contactos")
public class Contact {

    @DatabaseField(id = true)
    private Integer id;
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
    public Contact(Integer id, String name, String position, String unit, String email, String phone, String office, String address) {
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
