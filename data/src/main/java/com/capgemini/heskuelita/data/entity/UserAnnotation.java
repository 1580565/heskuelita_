package com.capgemini.heskuelita.data.entity;



import javax.persistence.*;


@Entity (name =  "Usuario")
@Table (name = "usuarios")
public class UserAnnotation {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "us_seq")
    @SequenceGenerator(name = "us_seq", sequenceName = "us_seq")
    @Column (name="id", nullable=false, unique=true)
    private int id;

    @Column(name="usuario", length=22, nullable=false)
    private String us_nom;

    @Column(name="clave", length=22, nullable=false)
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

    public UserAnnotation(String us_nom, String us_pw, String us_email, String nombre, String apellido, String fnac, String gen, String doc) {
        this.us_nom = us_nom;
        this.us_pw = us_pw;
        this.us_email = us_email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fnac = fnac;
        this.gen = gen;
        this.doc = doc;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUs() {
        return us_nom;
    }

    public void setUs(String us_nom) {
        this.us_nom = us_nom;
    }

    public String getPw() {
        return us_pw;
    }

    public void setPw(String us_pw) {
        this.us_pw = us_pw;
    }

    public String getEmail() {
        return us_email;
    }

    public void setEmail(String us_email) {
        this.us_email = us_email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFnac() {
        return fnac;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
}
