package spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HtmlDownloader htmlDownloader = context.getBean("htmlDownloader", HtmlDownloader.class);

        String content;
        int max = 2;

        htmlDownloader.download("http://localhost:8000");
        Thread.sleep(15000);
        htmlDownloader.download("http://localhost:8000");

//        Thread[] threads = new Thread[50];
//        for (int i = 0; i < threads.length; i++) {
//            threads[i] = new ExecThread(htmlDownloader);
//            threads[i].start();
//        }
//
//        for (int i = 0; i < threads.length; i++) {
//            threads[i].join();
//        }

        context.close();
    }

    public static class ExecThread extends Thread {

        HtmlDownloader htmlDownloader;

        public ExecThread(HtmlDownloader htmlDownloader) {
            this.htmlDownloader = htmlDownloader;
        }

        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    String content = htmlDownloader.download("http://localhost:8000");

                }
                Thread.sleep(30000);
                for (int i = 0; i < 10; i++) {
                    String content = htmlDownloader.download("http://localhost:8000");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

