package chen.zucc.com.personalassistant.model;

import java.io.Serializable;

/**
 * Created by chenchongkang on 2017/7/12.
 */

public class ScheduleModel implements Serializable{
    private int id;
    private String Schedule_details;
    private String Schedule_beigntime;
    private String Schedule_endtime;
    private int Schedule_priority;
    private int Schedule_classes;
    private String Schedule_remark;
    private int Schedule_state;
    private String Schedule_picture;

    public void setId(int id) {
        this.id = id;
    }

    public void setSchedule_details(String schedule_details) {
        Schedule_details = schedule_details;
    }

    public void setSchedule_beigntime(String schedule_beigntime) {
        Schedule_beigntime = schedule_beigntime;
    }

    public void setSchedule_endtime(String schedule_endtime) {
        Schedule_endtime = schedule_endtime;
    }

    public void setSchedule_priority(int schedule_priority) {
        Schedule_priority = schedule_priority;
    }

    public void setSchedule_classes(int schedule_classes) {
        Schedule_classes = schedule_classes;
    }

    public void setSchedule_remark(String schedule_remark) {
        Schedule_remark = schedule_remark;
    }

    public void setSchedule_state(int schedule_state) {
        Schedule_state = schedule_state;
    }

    public void setSchedule_picture(String schedule_picture) {
        Schedule_picture = schedule_picture;
    }

    public int getId() {
        return id;
    }

    public String getSchedule_details() {
        return Schedule_details;
    }

    public String getSchedule_beigntime() {
        return Schedule_beigntime;
    }

    public String getSchedule_endtime() {
        return Schedule_endtime;
    }

    public int getSchedule_priority() {
        return Schedule_priority;
    }

    public int getSchedule_classes() {
        return Schedule_classes;
    }

    public String getSchedule_remark() {
        return Schedule_remark;
    }

    public int getSchedule_state() {
        return Schedule_state;
    }

    public String getSchedule_picture() {
        return Schedule_picture;
    }
}
