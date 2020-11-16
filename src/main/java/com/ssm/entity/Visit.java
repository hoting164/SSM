package com.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@ToString
public class Visit {
    private int id;
    private  int empid;
    private String username;
    private int cusid;
    private String cusName;
    private  String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date date;


    public Visit() {}

//    public String getDate(){
//        String value = null;
//        //将Date类型的时间转换成指定格式的字符串
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        value = dateFormat.format(date);
//        System.out.println(value);
//        return value;
//
//    }
//    public void setDate(String date) {
//        //将字符串类型的日期转换成Date类型的指定格式的日期
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ParsePosition pos = new ParsePosition(0);//从第一个字符开始解析
//
//        this.date = f.parse(date,pos);/*对参数msg_create_date（String类型）从第一个字符开始解析（由pos），转换成java.util.Date类型，
//		而这个Date的格式为"yyyy-MM-dd"（因为SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");）*/
//
//    }
}
