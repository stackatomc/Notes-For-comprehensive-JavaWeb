package bean;

import bean.note;
import dao.noteDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MayerFang
 * @file NoteBean
 * @Description
 * @date 2018/9/20
 */
public class NoteBean {
    public List<note> findAll(){
        try{
            noteDao noteDao=new noteDao();
            return noteDao.findAll();
        }catch(Exception e){
            System.out.println("could not find all notes");
            e.printStackTrace();
        }
        return new ArrayList<note>();
    }
}
