package U3_Red.clase._04_SocketAltaUsuario;

import java.io.Serializable;

public class Usuario implements Serializable {

  private String username;
  private String password;
  private String ip;

  public Usuario(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }
}
