package server.utility;

import server.models.Bruger;

public class CurrentUserContext {
    private Bruger currentUser;

    public Bruger getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Bruger currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean isAdmin() {
        if(this.currentUser.getType() == 1) {
            return true;
        } else {
            return false;
        }
    }
}
