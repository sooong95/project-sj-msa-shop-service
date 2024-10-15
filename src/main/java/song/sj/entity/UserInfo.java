package song.sj.entity;

public interface UserInfo {

    String getEmail();

    String getPassword();

    void transPassword(String hashPassword);
}
