import java.util.Scanner;

public class Main {


    public static void main (String[] args) throws Exception {

        Serialization serialization = new Serialization(new HttpRequest());

        while(true) {
            System.out.println("w - wprowadz miasto, z - zakoncz i zapisz");
            Scanner scanner = new Scanner(System.in);

            String decision = scanner.nextLine();
            if (decision.equals("w")) {
                String city = scanner.nextLine();
                if (Serialization.CheckCity(city)) {
                    String result = serialization.ModifyResponse(city);
                    System.out.println(result);
                }
            }
            else if (decision.equals("z")) {
                System.out.println("p = PDF, x = XML, j = JSON");
                String format = scanner.nextLine();
                switch (format) {
                    case "p" -> SaveFile.saveToPDF("E:\\ApiProject\\output.pdf");
                    case "x" -> SaveFile.saveToXML("E:\\ApiProject\\output.xml");
                    case "j" -> SaveFile.saveToJSON("E:\\ApiProject\\output.json");
                }
                break;
            }
            else {
                break;
            }
        }
    }
}
