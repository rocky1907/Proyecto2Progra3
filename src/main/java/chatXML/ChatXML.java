package chatXML;
import chatLogic.Mensajes;
import chatLogic.ServiceUsuariosContactos;
import chatProtocol.User;
import com.sun.xml.internal.ws.util.Pool;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
public class ChatXML {
   public void crearContactosXML(ServiceUsuariosContactos service,User s) throws JAXBException, IOException{
       JAXBContext context = JAXBContext.newInstance(ServiceUsuariosContactos.class);
            Marshaller marsha = context.createMarshaller();
            marsha.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            //marsha.marshal(ServiceUsuariosContactos.instance(), System.out);
            marsha.marshal(service, new FileWriter("C:\\Users\\DELL\\Documents\\Ciclo IV 2020\\Programacion III\\Proyecto 2\\XML\\Contactos\\"+s.getId()+".xml"));
            
   } 
   
   public ServiceUsuariosContactos cargarContactosXML(User s) throws JAXBException{
       JAXBContext context = JAXBContext.newInstance(ServiceUsuariosContactos.class);
       Unmarshaller unmar = context.createUnmarshaller();
       ServiceUsuariosContactos s1 = null;
       s1=(ServiceUsuariosContactos) unmar.unmarshal(new File("C:\\Users\\DELL\\Documents\\Ciclo IV 2020\\Programacion III\\Proyecto 2\\XML\\Contactos\\"+s.getId()+".xml"));
       return s1;
   }
   
   public void crearMensajesXML(Mensajes li,User s) throws JAXBException, IOException{
        JAXBContext context = JAXBContext.newInstance(Mensajes.class);
        Marshaller marsha = context.createMarshaller();
        marsha.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        //marsha.marshal(ServiceUsuariosContactos.instance(), System.out);
        marsha.marshal(li, new FileWriter("C:\\Users\\DELL\\Documents\\Ciclo IV 2020\\Programacion III\\Proyecto 2\\XML\\Mensajes\\"+s.getId()+"-"+li.getDestinatario()+".xml"));

   }
   public List<Mensajes> cargarMensajesXML(User s) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(Mensajes.class);
        Unmarshaller unmar = context.createUnmarshaller();
        List<Mensajes> list = new ArrayList<>();
        for (int i = 0; i < ServiceUsuariosContactos.instance().getContactos().size(); i++) {
            Mensajes m1 = new Mensajes();
                m1 =(Mensajes) unmar.unmarshal(new File("C:\\Users\\DELL\\Documents\\Ciclo IV 2020\\Programacion III\\Proyecto 2\\XML\\Mensajes\\"
               +s.getId()+"-"+ServiceUsuariosContactos.instance().getContactos().get(i).getId()+".xml"));
        list.add(m1);
        }
        return list;
   }

    public ChatXML() {
    }
}
