package Model.Purchase;

import Model.Build.Build;
import Model.Cpu.Cpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.Psu.Psu;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IPurchaseDao<E extends Exception>{
    ArrayList<Purchase> doRetrieveAll(int limit, int offset) throws E;
    ArrayList<Purchase> doRetrieveByEmail(String email,int limit, int offset) throws E;
    ArrayList<Purchase> doRetrieveByParameters(String email, Date startingDate,Date endingDate, int limit, int offset) throws E;
    Purchase doRetrieveById(int id) throws E;
    boolean doDelete(int id) throws E;
    boolean doSave(Purchase purchase) throws E;
}
