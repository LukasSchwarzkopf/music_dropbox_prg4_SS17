package sample.logic_model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileTypeTest {

    @Test
    public void find() throws Exception {
        FileType testfiletype = FileType.create("mp3");
        FileType testfiletype1 = FileType.find(testfiletype.m_id);
        assertEquals(testfiletype1.m_title, testfiletype.m_title);
    }

    @Test
    public void find_by_title() throws Exception {
        FileType testfiletype = FileType.create("mp3");
        FileType testfiletype1 = FileType.find_by_title("mp3");
        assertEquals(testfiletype1.m_id, testfiletype.m_id);
    }

    @Test
    public void all() throws Exception {

    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
        for(FileType type : FileType.all()){
            type.delete();
        }
    }

    @Test
    public void create() throws Exception {
        FileType testfiletype = FileType.create("mp3");

    }

}