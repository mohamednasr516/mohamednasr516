import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args){
        try {

            DatagramSocket serverSocket = new DatagramSocket(12004);
            while(true){
                byte[] reby = new byte[4096];
                byte[] rspy;
                DatagramPacket cp = new DatagramPacket(reby , reby.length);
                serverSocket.receive(cp);
                String input = new String(cp.getData()).trim();
                InetAddress cip = cp.getAddress();
                int cport = cp.getPort();
                if(input.toLowerCase().equals("what is tha weather"))
                {
                    String oto = new String(" The weather is sunny and you can go out ");
                    rspy = oto.getBytes();
                    DatagramPacket serpa = new DatagramPacket(rspy , rspy.length ,cip,cport);
                    serverSocket.send(serpa);

                }
                if(input.toLowerCase().equals("what is tha ip"))
                {
                    String oto = new  String(cip.toString());
                    rspy = oto.getBytes();
                    DatagramPacket serpa = new DatagramPacket(rspy , rspy.length ,cip,cport);
                    serverSocket.send(serpa);

                }
                if(input.toLowerCase().equals("what is the time"))
                {
                    Date date = new Date();
                    SimpleDateFormat sf =new SimpleDateFormat("hh:mm:ss a");

                    String oto= new  String(sf.format(date));
                    rspy = oto.getBytes();
                    DatagramPacket serpa = new DatagramPacket(rspy , rspy.length ,cip,cport);
                    serverSocket.send(serpa);
                }
                if(input.toLowerCase().equals("exit") || input.toLowerCase().equals("close"))
                {
                    break;
                }
                else {
                    String oto = new String("wrong question");
                    rspy = oto.getBytes();
                    DatagramPacket serpa = new DatagramPacket(rspy , rspy.length ,cip,cport);
                    serverSocket.send(serpa);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}