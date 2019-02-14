/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safecare.spring;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import safecare.dao.StudentDao;
import safecare.enitity.StudentInfo;

/**
 *
 * @author RE-2084
 */
@Controller
public class controller {

    @Autowired
    StudentDao sDao;

    @RequestMapping(value = "/test.htm")
    public @ResponseBody
    String getHi() {
        System.out.println("Point hit");
        return "Hi";
    }
/* insert and update */ 
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public @ResponseBody
    String insert(StudentInfo sInfoDB) {
        Gson ob = new Gson();

        if (sInfoDB.getId() == 0) {
            sDao.save(sInfoDB);
        } else {
            sDao.update(sInfoDB);
        }
        String s = ob.toJson(sInfoDB);

        return s;
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

/* Display */
    @RequestMapping(value = "display")
    public @ResponseBody
    String display() {

        System.out.println(" Display");
        List<StudentInfo> listdetails = sDao.display();
        return new Gson().toJson(listdetails);
    }
/* Delete */

    @RequestMapping(value = "delete")
    public @ResponseBody
    String delete(@RequestParam("id") Integer id) {
        StudentInfo sd = sDao.getId(id);
        sDao.delete(sd);
        return "succesful";
    }
/*  Edit */
    @RequestMapping(value = "edit")
    public @ResponseBody
    String edit(@RequestParam("id") Integer eid) {
        StudentInfo sd = sDao.getId(eid);
        return new Gson().toJson(sd);
    }
/*  Data table */
    @RequestMapping(value = "table")
    public @ResponseBody
    String display1() {

        List<StudentDao> list = sDao.displayTable();

        Map ma = new HashMap();
        ma.put("aaData", list);

        ma.put("iTotalRecords", 100);
        ma.put("iTotalDisplayRecords", 100);
        System.out.println(ma);
        return new Gson().toJson(ma);
    }

    @RequestMapping(value = "registration")
    public String test2() {
        return "registration";
    }

    @ModelAttribute
    public void setAccessControlResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

}
