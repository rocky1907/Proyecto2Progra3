package chatClient;

import chatLogic.Mensajes;
import chatProtocol.User;
import java.util.ArrayList;
import java.util.List;

public class Model extends java.util.Observable {
    User currentUser;
    List<Mensajes> messages;

    public Model() {
       currentUser = null;
       messages=new ArrayList<>();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Mensajes> getMessages() {
        return messages;
    }

    public void setMessages(List<Mensajes> messages) {
        this.messages = messages;
    }

    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }
    
    public void commit(){
        this.setChanged();
        this.notifyObservers();        
    } 
}
