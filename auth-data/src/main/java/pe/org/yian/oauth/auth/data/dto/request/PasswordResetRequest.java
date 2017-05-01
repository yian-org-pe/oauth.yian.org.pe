package pe.org.yian.oauth.auth.data.dto.request;

/**
 * Created by jaxkodex on 30/04/17.
 */
public class PasswordResetRequest {
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
