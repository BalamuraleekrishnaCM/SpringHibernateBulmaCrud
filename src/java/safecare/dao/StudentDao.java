/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package safecare.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import safecare.enitity.StudentInfo;

/**
 *
 * @author RE-2084
 */
@Transactional
@Repository
public class StudentDao {

    @Autowired
    SessionFactory sessionFactory;


public List display() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentInfo.class);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("name"), "name")
                .add(Projections.property("email"), "email")
                .add(Projections.property("phone"), "phone")
                .add(Projections.property("id"), "id"));
        criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return criteria.list();
    }

    public void save(StudentInfo stdinfo) {
        sessionFactory.getCurrentSession().save(stdinfo);
    };
    public StudentInfo getId(Integer id) {
         return (StudentInfo)sessionFactory.getCurrentSession().get(StudentInfo.class, id);
    }
     public void delete(StudentInfo sd){
        
        sessionFactory.getCurrentSession().delete(sd);
    }
      public void update(StudentInfo sd){
        
        sessionFactory.getCurrentSession().update(sd);
    }
      
public List displayTable() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentInfo.class);
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("name"), "name")
                .add(Projections.property("email"), "email")
                .add(Projections.property("phone"), "phone")
                );
        criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return criteria.list();
    }
    public List displaysql(String searchName, Integer iDisplayLength, Integer iSortCol_0, String sSortDir_0) {

        String sql = "SELECT * FROM sample";

        String sort = "";
        String search = " ";
//   searching

//   if(!searchName.isEmpty()||!sSortDir_0.isEmpty()){
        if (!searchName.isEmpty()) {
            search = " where name LIKE '" + searchName + "%' "
                    + "OR phone LIKE '" + searchName + "%'"
                    + "OR password LIKE '" + searchName + "%' ";
        }
        if (iSortCol_0.equals(0)) {
            sort = "ORDER BY name ";
        } else if (iSortCol_0.equals(1)) {
            sort = "ORDER BY phone ";
        } else {
            sort = "ORDER BY email ";
        }

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql + search + sort + sSortDir_0);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setMaxResults(iDisplayLength);
        return query.list();
    }

    public List<StudentInfo> display1() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StudentInfo.class);
        return criteria.list();
    }

}
