import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SaveFile {

    public static void saveToPDF(String filePath) {
        List<Map<String, String>> results = Serialization.getResults();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            addContentToPDF(document, results);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addContentToPDF(Document document, List<Map<String, String>> results) {
        results.forEach(cityResult -> {
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Chunk("Miasto: " + cityResult.get("name") + "\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            paragraph.add(new Chunk(cityResult.get("weather")));
            try {
                document.add(paragraph);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
    }

    public static void saveToXML(String filePath) {
        List<Map<String, String>> results = Serialization.getResults();

        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), buildXmlDocument(results));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> buildXmlDocument(List<Map<String, String>> results) {
        Map<String, Object> xmlDocument = new HashMap<>();
        xmlDocument.put("Weather", Map.of(
                "city", results.stream()
                        .map(result -> Map.of(
                                "name", result.get("name"),
                                "weather", result.get("weather")
                        ))
                        .collect(Collectors.toList())
        ));
        return xmlDocument;
    }

    public static void saveToJSON(String filePath) {
        List<Map<String, String>> results = Serialization.getResults();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
