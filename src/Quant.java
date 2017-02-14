/**
 * Created by cottier on 13/02/2017.
 */
import com.jaunt.*;
import com.jaunt.component.*;
//import java.io.*;
import java.util.List;

public class Quant {

    private static int gcd(int a, int b)
    {
        while(a!=0 && b!=0) // until either one of them is 0
        {
            int c = b;
            b = a%b;
            a = c;
        }
        return a+b; // either one is 0, so return the non-zero value
    }

    public static void main(String[] args){
        try{
            UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
            userAgent.visit("http://www.quantbet.com/quiz/dev");                        //visit a url

            Elements strongs = userAgent.doc.findEach("<strong>"); //get child text of title element.
            List<Element> list = strongs.toList();

            int firstNumber = Integer.parseInt(list.get(0).innerHTML());
            int secondNumber = Integer.parseInt(list.get(1).innerHTML());
            int gcd = gcd(firstNumber,secondNumber);
            String submitter = Integer.toString(gcd);

            Form activeForm = userAgent.doc.getForm(0);
            System.out.println(firstNumber + " : " + secondNumber + " = " + submitter);
            activeForm.setTextField( "divisor",     //fill-out the form by applying a sequence of inputs
                    submitter
            );
            activeForm.submit();
            System.out.println(userAgent.doc.innerHTML());
        }
        catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
            System.err.println(e);
        }
    }
}
