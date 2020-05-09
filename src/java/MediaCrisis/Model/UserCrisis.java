/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Model;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class UserCrisis {

    List<Crisis> crisisList;
    String keyword;

    public UserCrisis() {

    }

    public List<Crisis> getCrisisList() {
        return crisisList;
    }

    public void setCrisisList(List<Crisis> crisisList) {
        this.crisisList = crisisList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "UserCrisis{" + "crisisList=" + crisisList + ", keyword=" + keyword + '}';
    }
    
    
}
