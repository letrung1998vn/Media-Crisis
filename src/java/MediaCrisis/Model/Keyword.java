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
    private boolean available;
    private int log_version;
    private double normal_rate;

    public Keyword() {
    }

    public Keyword(int id, String keyword, String userId, boolean available, int log_version, double normal_rate) {
        this.id = id;
        this.keyword = keyword;
        this.userId = userId;
        this.available = available;
        this.log_version = log_version;
        this.normal_rate = normal_rate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getLog_version() {
        return log_version;
    }

    public void setLog_version(int log_version) {
        this.log_version = log_version;
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

    public double getNormal_rate() {
        return normal_rate;
    }

    public void setNormal_rate(double normal_rate) {
        this.normal_rate = normal_rate;
    }

    @Override
    public String toString() {
        return "Keyword{" + "id=" + id + ", keyword=" + keyword + ", userId=" + userId + ", available=" + available + ", log_version=" + log_version + ", normal_rate=" + normal_rate + '}';
    }

    
}
