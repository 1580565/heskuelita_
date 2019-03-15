package com.capgemini.heskuelita.data.entity;



import javax.persistence.*;


@Entity (name =  "User")
@Table (name = "users")
public class UserAnnotation {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @Column (name="id", nullable=false, unique=true)
    private int id;

    @Column(name="user_name", length=22, nullable=false)
    private String us_nom;

    @Column(name="password", length=22, nullable=false)
    private String us_pw;

    @Column(name="email", length = 22, nullable=false)
    private String us_email;

    @Column(name="nombre", length = 22, nullable=false)
    private String nombre;

    @Column(name="apellido", length = 22, nullable=false)
    private String apellido;

    @Column(name="fnac", length = 22, nullable=false)
    private String fnac;

    @Column(name="gen", length = 22, nullable=false)
    private String gen;

    @Column(name="doc", length = 22, nullable=false)
    private String doc;


    public UserAnnotation() {

        super ();
    }

    public UserAnnotation(String name, String password, String email, String us_nombre, String us_apellido, String us_fnac, String us_gen, String us_doc) {
        this.us_nom = name;
        this.us_pw = password;
        this.us_email = email;
        this.nombre = us_nombre;
        this.apellido = us_apellido;
        this.fnac = us_fnac;
        this.gen = us_gen;
        this.doc = us_doc;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return us_nom;
    }

    public void setName(String name) {
        this.us_nom = name;
    }

    public String getPassword() {
        return us_pw;
    }

    public void setPassword(String password) {
        this.us_pw = password;
    }

    public String getEmail() {
        return us_email;
    }

    public void setEmail(String email) {
        this.us_email = email;
    }

    public String getUs_nombre() {
        return nombre;
    }

    public void setUs_nombre(String us_nombre) {
        this.nombre = us_nombre;
    }

    public String getUs_apellido() {
        return apellido;
    }

    public void setUs_apellido(String us_apellido) {
        this.apellido = us_apellido;
    }

    public String getUs_fnac() {
        return fnac;
    }

    public void setUs_fnac(String us_fnac) {
        this.fnac = us_fnac;
    }

    public String getUs_gen() {
        return gen;
    }

    public void setUs_gen(String us_gen) {
        this.gen = us_gen;
    }

    public String getUs_doc() {
        return doc;
    }

    public void setUs_doc(String us_doc) {
        this.doc = us_doc;
    }
}
