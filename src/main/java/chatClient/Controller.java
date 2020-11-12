package chatClient;

import chatLogic.Mensajes;
import chatProtocol.User;
import java.util.ArrayList;

public class Controller {
    View view;
    Model model;
    
    ServiceProxy localService;
    
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        localService = (ServiceProxy)ServiceProxy.instance();
        localService.setController(this);
        view.setController(this);
        view.setModel(model);
    }

    public void login() throws Exception{
        User u = new User(view.id.getText(),new String(view.clave.getPassword()),"");
        User logged=ServiceProxy.instance().login(u);
        model.setCurrentUser(logged);
        model.commit();
    }
    public void post(){
        String message=view.mensaje.getText();  
        String id = view.receptor;
        ServiceProxy.instance().post(message,id);
        model.commit();
    }
    
    public void logout(){
        try {
            ServiceProxy.instance().logout(model.getCurrentUser());
        }
        catch (Exception ex) {}
        model.setCurrentUser(null);
        model.setMessages(new ArrayList<Mensajes>());
        model.commit();
    }
        
    public void deliver(String message,String x){
        boolean bandera = false;
        if(model.getMessages()==null){
            Mensajes messi= new Mensajes();
            messi.setDestinatario(x);
            messi.getMensajes().add(message);
            model.getMessages().add(messi);
        }else{
            for (int i = 0; i < model.getMessages().size(); i++) {
                if(model.getMessages().get(i).getDestinatario().equals(x)){
                    model.getMessages().get(i).getMensajes().add(message);
                    bandera=true;
                }
            }
        }
        if(!bandera){
            Mensajes messi= new Mensajes();
            messi.setDestinatario(x);
            messi.getMensajes().add(message);
            model.getMessages().add(messi);
        }
        model.commit();    
    }    
    
}
