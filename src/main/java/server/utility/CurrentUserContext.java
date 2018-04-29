package server.utility;

import server.models.User;

public class CurrentUserContext {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Boolean isSeller() {
        if(this.currentUser.getType() == 1) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean isAdmin() {
        if(this.currentUser.getType() == 2) {
            return true;
        } else {
            return false;
        }
    }
}
