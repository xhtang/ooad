package hello.service;

import hello.ooad.Equipment;
import hello.ooad.Plan;
import hello.ooad.Record;

import java.util.List;

/**
 * Creator: DreamBoy
 * Date: 2018/6/25.
 */
public interface OoadService {
    int addEquipment(Equipment equipment);

    Equipment getEquipmentById(int id);

    Equipment getEquipmentByNum(String number);

    int addPlan(Plan plan);

    Plan getPlanById(int id);

    List getPlansByEquipmentId(int eid);

    List getPlansByDate(int time);

    int addRecord(Record record);

    int getHoursByENum(String number);

    int getHoursByEnumAndPlan(String number, int pid);

}
