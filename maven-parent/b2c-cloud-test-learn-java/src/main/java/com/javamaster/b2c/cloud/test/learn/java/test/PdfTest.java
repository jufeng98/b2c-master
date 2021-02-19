package com.javamaster.b2c.cloud.test.learn.java.test;


import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.javamaster.b2c.cloud.test.learn.java.thinking.UnitTest;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created on 2018/12/26.<br/>
 *
 * @author yudong
 */
public class PdfTest {

    @Test
    @UnitTest
    public void test() throws Exception {
        URL url = new URL("file:/G:/yunpan/Effective Java.pdf");
        PDDocument document = PDDocument.load(url);
        List<PDPage> pages = document.getDocumentCatalog().getAllPages();
        System.out.println("pdf文件的总页数为:" + pages.size());
    }

    @Test
    public void test0() throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("F:\\IText.pdf"));
        document.open();
        document.add(new Paragraph("A Hello World PDF document."));
        document.close(); // no need to close PDFwriter?
    }

    @Test
    public void test1() throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document,
                new FileOutputStream("F:\\Chunk.pdf"));
        document.open();
        document.add(new Chunk("This is sentence 1. "));
        document.add(new Chunk("This is sentence 2. "));
        document.add(new Chunk("This is sentence 3. "));
        document.add(new Chunk("This is sentence 4. "));
        document.add(new Chunk("This is sentence 5. "));
        document.add(new Chunk("This is sentence 6. "));
        document.close();
    }

    @Test
    public void test2() throws Exception {

        Document document = new Document();
        PdfWriter.getInstance(document,
                new FileOutputStream("F:\\Phrase.pdf"));

        document.open();
        document.add(new Phrase("This is sentence 1. "));
        document.add(new Phrase("This is sentence 2. "));
        document.add(new Phrase("This is sentence 3. "));
        document.add(new Phrase("This is sentence 4. "));
        document.add(new Phrase("This is sentence 5. "));
        document.add(new Phrase("This is sentence 6. "));
        document.close();

    }

    @Test
    public void test3() throws Exception {
        Document document = new Document();

        PdfWriter.getInstance(document,
                new FileOutputStream("F:\\Phrase2.pdf"));

        document.open();
        Chunk chunk = new Chunk("This is a sentence ");

        Phrase phrase = new Phrase(50);

        phrase.add(chunk);
        phrase.add(chunk);
        phrase.add(chunk);
        phrase.add(chunk);
        phrase.add(chunk);
        phrase.add(chunk);

        document.add(phrase);
        document.close();
    }

    @Test
    public void test4() throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document,
                new FileOutputStream("F:\\Paragraph2.pdf"));
        document.open();
        Paragraph paragraph1 = new Paragraph();

        Paragraph paragraph2 = new Paragraph();

        paragraph2.setSpacingAfter(25);
        paragraph2.setSpacingBefore(25);
        paragraph2.setAlignment(Element.ALIGN_CENTER);
        paragraph2.setIndentationLeft(50);
        paragraph2.setIndentationRight(50);
        for (int i = 0; i < 10; i++) {
            Chunk chunk = new Chunk(
                    "This is a sentence which is long " + i + ". ");
            paragraph1.add(chunk);
            paragraph2.add(chunk);
        }

        document.add(paragraph1);
        document.add(paragraph2);
        document.close();

    }

    @Test
    public void test5() throws Exception {
        PdfReader pdfReader = new PdfReader("F:\\IText.pdf");
        PdfStamper pdfStamper = new PdfStamper(pdfReader,
                new FileOutputStream("F:\\IText-Stamped.pdf"));
        Image image = Image.getInstance(ResourceUtils.getFile("classpath:安琪拉.jpg").getAbsolutePath());
        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            PdfContentByte content = pdfStamper.getUnderContent(i);
            image.setAbsolutePosition(0f, 0f);
            content.addImage(image);
        }
        pdfStamper.close();
    }

    @Test
    public void test51() throws Exception {
        Document document = new Document();
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream("F:\\hello.pdf");
        PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
        File file = ResourceUtils.getFile("classpath:excel/certificate.jpg");
        Image image = Image.getInstance(file.getAbsolutePath());
        document.setPageSize(new Rectangle(image.getWidth(), image.getHeight()));
        document.setMargins(0, 0, 0, 0);
        document.open();
        document.add(image);
        String acquireMan = "jufeng98";
        String topics = "java tutorial";
        String acquireTime = "获得时间: "+DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        appendTextToDoc(writer, acquireMan, 250, 720);
        appendTextToDoc(writer, topics, 260, 600);
        appendTextToDoc(writer, acquireTime, 1000, 1030);
        document.close();
    }

    @SneakyThrows
    private void appendTextToDoc(PdfWriter writer, String txt, int x, int y) {
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        cb.beginText();
        cb.setFontAndSize(bf, 38);
        cb.setCharacterSpacing(5);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, txt, x, y, 0);
        cb.endText();
    }

    @Test
    public void test6() throws Exception {
        PdfReader pdfReader = new PdfReader(ResourceUtils.getFile("classpath:正则表达式入门.pdf").getAbsolutePath());
        System.out.println(pdfReader.getNumberOfPages());
        System.out.println(pdfReader.getInfo());

    }

}
