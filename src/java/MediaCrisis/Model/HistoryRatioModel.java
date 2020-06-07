/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Model;

import java.util.List;

/**
 *
 * @author letru
 */
public class HistoryRatioModel {
    List<String> listRatioStr;
    List<String> listDateStr;
    String keyword;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public List<String> getListRatioStr() {
        return listRatioStr;
    }

    public void setListRatioStr(List<String> listRatioStr) {
        this.listRatioStr = listRatioStr;
    }

    public List<String> getListDateStr() {
        return listDateStr;
    }

    public void setListDateStr(List<String> listDateStr) {
        this.listDateStr = listDateStr;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
}
