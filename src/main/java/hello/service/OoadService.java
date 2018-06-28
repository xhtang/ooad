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
    long addEquipment(Equipment equipment);

    Equipment getEquipmentById(long id);

    Equipment getEquipmentByNum(String number);

    long addPlan(Plan plan);

    Plan getPlanById(long id);

    List getPlansByEquipmentId(long eid);

    List getPlansByDate(long time);

    long addRecord(Record record);

    long getHoursByENum(String number);

    long getHoursByEnumAndPlan(String number, long pid);
}
