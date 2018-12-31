public class Account {
    private Utente utente;
    private int id;
    private String nome;
    private String email;
    private String password;

    //costrutore


    public Account(Utente utente, int id, String nome, String email, String password) {
        this.utente = utente;
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    //Get&Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //Fine Get&Set




}

