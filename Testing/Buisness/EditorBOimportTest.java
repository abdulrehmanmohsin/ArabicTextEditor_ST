package Buisness;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import bll.EditorBO;
import dal.IFacadeDAO;
import dto.Documents;

public class EditorBOimportTest {

    // ---------- Fake DAO for isolated business-layer testing ----------
    private IFacadeDAO fakeDb = new IFacadeDAO() {

        @Override
        public boolean createFileInDB(String name, String content) {
            return true;
        }

        @Override
        public List<Documents> getFilesFromDB() { return null; }

        @Override
        public boolean updateFileInDB(int id, String f, int p, String c){ return false; }

        @Override
        public boolean deleteFileInDB(int id){ return false; }

        @Override
        public String transliterateInDB(int id,String t){ return null; }

        @Override
        public Map<String,String> lemmatizeWords(String t){ return null; }

        @Override
        public Map<String,List<String>> extractPOS(String t){ return null; }

        @Override
        public Map<String,String> extractRoots(String t){ return null; }

        @Override
        public double performTFIDF(List<String> a,String b){ return 0; }

        @Override
        public Map<String,Double> performPMI(String c){ return null; }

        @Override
        public Map<String,Double> performPKL(String c){ return null; }

        @Override
        public Map<String,String> stemWords(String t){ return null; }

        @Override
        public Map<String,String> segmentWords(String t){ return null; }
    };

    @Test
    void testImportSuccess() throws Exception {

        // should import text file successfully when extension is valid
        EditorBO editor = new EditorBO(fakeDb);

        File temp = File.createTempFile("test", ".txt");
        FileWriter writer = new FileWriter(temp);
        writer.write("Hello world");
        writer.close();

        boolean result = editor.importTextFiles(temp, "sample.txt");

        assertTrue(result);
    }

    @Test
    void testInvalidExtension() throws Exception {

        // should return false when file extension is not supported
        EditorBO editor = new EditorBO(fakeDb);

        File temp = File.createTempFile("test", ".txt");
        FileWriter writer = new FileWriter(temp);
        writer.write("Hello world");
        writer.close();

        boolean result = editor.importTextFiles(temp, "sample.pdf");

        assertFalse(result);
    }

    @Test
    void testMissingFile() {

        // should return false if file cannot be read (exception path)
        EditorBO editor = new EditorBO(fakeDb);

        File missingFile = new File("file_that_does_not_exist.txt");

        boolean result =
                editor.importTextFiles(missingFile, "file_that_does_not_exist.txt");

        assertFalse(result);
    }
}