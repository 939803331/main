package com.ph.main.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;

public abstract class Story {
    private final String URL;

    private final String BOOK_NAME;

    private final String DOWNLOAD_URL;

    private final int START_INDEX;

    private final int END_INDEX;

    private Story(String url, String bookName, int start, int end) {
        this.URL = url;
        this.BOOK_NAME = bookName;
        DOWNLOAD_URL = "D:\\appStore\\temp\\" + BOOK_NAME + ".txt";
        this.START_INDEX = start;
        this.END_INDEX = end;
    }

    private void execute() throws IOException {
        System.out.println("开始下载 " + BOOK_NAME);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DOWNLOAD_URL, false))), true)) {

            for (int i = START_INDEX; i <= END_INDEX; i++) {

                Document document = Jsoup.connect(URL + i + ".html").get();
                String chapter = document.select("body > div > table:nth-child(4) > tbody > tr:nth-child(1) > td > h2 > font").html();
                String html = document.select("body > div > table:nth-child(5) > tbody > tr > td:nth-child(2) > p").html();
                String[] content = html.replace("&nbsp;", "").split("<br> <br> ");

                pw.println(chapter);
                for (String aContent : content) {
                    pw.println(aContent);
                }
                pw.println();
                pw.println();
                System.out.println(chapter + " 加载完毕...");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(BOOK_NAME + " 下载完成");
    }

    static class StoryEmpty extends Story {
        StoryEmpty(String url, String bookName, int start, int end) {
            super(url, bookName, start, end);
        }
    }

    static class Story1 extends Story {
        private static final String URL = "https://www.kanunu8.com/book4/10573/";

        private static final String BOOK_NAME = "死亡通知单2·宿命";

        private static final int START_INDEX = 186072;

        private static final int END_INDEX = 186100;

        public Story1() {
            this(URL, BOOK_NAME, START_INDEX, END_INDEX);
        }

        Story1(String url, String bookName, int start, int end) {
            super(url, bookName, start, end);
        }
    }

    static class Story2 extends Story {
        private static final String URL = "https://www.kanunu8.com/book4/10631/";

        private static final String BOOK_NAME = "最后一个道士1";

        private static final int START_INDEX = 187685;

        private static final int END_INDEX = 187704;

        public Story2() {
            this(URL, BOOK_NAME, START_INDEX, END_INDEX);
        }

        Story2(String url, String bookName, int start, int end) {
            super(url, bookName, start, end);
        }
    }

    static class Story3 extends Story {
        private static final String URL = "https://www.kanunu8.com/book4/10632/";

        private static final String BOOK_NAME = "最后一个道士2";

        private static final int START_INDEX = 187705;

        private static final int END_INDEX = 187725;

        public Story3() {
            this(URL, BOOK_NAME, START_INDEX, END_INDEX);
        }

        Story3(String url, String bookName, int start, int end) {
            super(url, bookName, start, end);
        }
    }

    static class Story4 extends Story {
        private static final String URL = "https://www.kanunu8.com/book4/10633/";

        private static final String BOOK_NAME = "最后一个道士3";

        private static final int START_INDEX = 187726;

        private static final int END_INDEX = 187745;

        public Story4() {
            this(URL, BOOK_NAME, START_INDEX, END_INDEX);
        }

        Story4(String url, String bookName, int start, int end) {
            super(url, bookName, start, end);
        }
    }

    public static void main(String[] args) throws IOException {
        Story story = new StoryEmpty("https://www.kanunu8.com/book4/10574/", "死亡通知单之离别曲·大结局", 186101, 186118);
        story.execute();
    }
}
