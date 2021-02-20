package demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;

public class LuceneTester {
    static String dataDir;
    Indexing indexer;

    public static void main(String[] args) throws ParseException {
        dataDir = "";
        LuceneTester tester;
        try {
            tester = new LuceneTester();
            tester.createIndex();
            System.out.println("Enter Name and Query --> ");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            String q = sc.nextLine();
            System.out.println("name " + name + " Q : " + q);
            tester.search(name, q);
            //for getting specific Topic's data for specific user call the method below and comment createIndex and search method.
            //tester.getSearchedTopic(q, name);
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createIndex() throws IOException {
        Scanner sc = new Scanner(System.in);
        String indexDir = "../lucene/indexDirectory";
        System.out.println("Enter a path --> ");
        dataDir = sc.nextLine();
        Path indexPath = Paths.get(indexDir);
        Indexing indexer = new Indexing(indexPath);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed + " File(s) indexed, time taken: " + (endTime - startTime) + " ms");
    }

    @SuppressWarnings("unchecked")
    private void search(String name, String searchQuery) throws IOException, ParseException {
        String indexDirectoryPath = "../lucene/indexDirectory";
        Path indexPath = Paths.get(indexDirectoryPath);
        Searcher searcher = new Searcher(indexPath);
        long startTime = System.currentTimeMillis();
        System.out.println("Searchquery : " + searchQuery);
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();
        System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
        List<DocFile> documents = new ArrayList<DocFile>();
        int i = 1;
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            String file = doc.get(LuceneConstants.FILE_PATH);
            System.out.println("file : " + file);
            DocFile document = new DocFile();
            document.setPath(file);
            document.setRank(i);
            document.setScore(scoreDoc.score);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            document.setModification_time(sdf.format((new File(file)).lastModified()));
            documents.add(document);
            i++;
        }
        // displaying files informations
        // JSONObject users = new JSONObject();
        JSONArray array = new JSONArray();
        for (DocFile df : documents) {
            System.out.println("Path : - " + df.getPath());
            System.out.println("Score : " + df.getScore());
            System.out.println("Rank : " + df.getRank());
            System.out.println("Last Modified : " + df.getModification_time());
            JSONObject topics = new JSONObject();
            topics.put("Path", df.getPath().replace("\\\\", "\\"));
            topics.put("Score", df.getScore());
            topics.put("Rank", df.getRank());
            topics.put("Last Modified", df.getModification_time());
            array.add(topics);
            System.out.println(" Json data " + array);
            String content = readFile(df.getPath(), StandardCharsets.US_ASCII);
            File directory = new File(name);
            directory.mkdir();
            File file = new File(directory.getPath(), searchQuery.trim() + ".json");
            System.out.println(" file created and path is " + file.getAbsolutePath());
            try {
                FileWriter writer = new FileWriter(file.getAbsolutePath());
                writer.write(array.toJSONString());
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (df.getPath().endsWith(".html")) {
                org.jsoup.nodes.Document docc = Jsoup.parse(content);
                String title = docc.title();
                System.out.println("Html Title : " + title);
            }
            System.out.println("----------------------------------------");
        }
        searcher.close();
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    JSONArray getSearchedTopic(String topicName, String userName) {
        JSONParser jsonParser = new JSONParser();
        JSONArray array = new JSONArray();
        try {
            File dir = new File(userName);
            System.out.println("Directory exist " + dir.isDirectory());
            File file = new File(dir, topicName.trim() + ".json");
            System.out.println("File exist " + file.isFile());
            FileReader reader = new FileReader(file.getPath());
            Object obj = jsonParser.parse(reader);
            array = (JSONArray) obj;
            System.out.println(array.toJSONString());
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return array;
    }

}