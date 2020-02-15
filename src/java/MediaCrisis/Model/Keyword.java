/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaCrisis.Model;

/**
 *
 * @author Admin
 */
public class Keyword {
    private int id;
    private String keyword;
    private String userId;

    public Keyword() {
    }

    
    public Keyword(int id, String keyword, String userId) {
        this.id = id;
        this.keyword = keyword;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Keyword{" + "id=" + id + ", keyword=" + keyword + ", userId=" + userId + '}';
    }
    
      
    
    
    
}
