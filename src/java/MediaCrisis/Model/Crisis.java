/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Model;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Crisis {

    private int id;
    private String type;
    private String DetectType;
    private String Content;
    private Double percentage;
    private String keyword;
    private String detectDate;

    public Crisis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetectType() {
        return DetectType;
    }

    public void setDetectType(String detectType) {
        DetectType = detectType;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDetectDate() {
        return detectDate;
    }

    public void setDetectDate(String detectDate) {
        this.detectDate = detectDate;
    }


    
    
}
