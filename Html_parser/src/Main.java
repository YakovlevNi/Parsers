import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Document doc = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ссылку на страницу по типу"
                + "\n" + "https://www.perekrestok.ru/cat/c/202/cipsy"
                + "\n\n" + "После введения ссылки нажмите Enter");

        String input = scanner.nextLine();
        doc = Jsoup.connect(input).get();
        Elements picLink = doc.select("span"); //Тип ключа в начале строчки
        // System.out.println(picLink);
        for (Element element : picLink) {
            if (element.hasText()) {
                element.absUrl("product-card__link-text"); //Ссылка на конкретный ключ для поиска
                String meme = element.text().replaceAll("\\d+\\W", "").replaceAll("\\n", "") //Меме - строчка буфера
                        .replaceAll("\\d+", "").replaceAll("-", "").replaceAll(",", "");
                //System.out.println(meme);
                FileWriter writer = new FileWriter("ParsedFile.txt", true);
                writer.write(meme + "\n");
                writer.flush();
                // https://www.perekrestok.ru/cat/c/202/cipsy
            }

        }
        System.out.println("\n\n" + "Парсинг выполнен, в папке с программой появился файл ParsedFile.txt, в нем хранятся данные." + "\n" + "Обязательно вытащите файл из папки");
    }
}





