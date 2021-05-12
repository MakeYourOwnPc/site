package Model.Cpu;

import java.util.ArrayList;

public interface CpuDao<E extends Exception>{
    ArrayList<Cpu> doRetrieveAll() throws E;
    ArrayList<Cpu> doRetrieveBySocket(String cpusocket) throws E;
    Cpu doRetrieveBySn(String sn) throws E;
    Cpu doRetrieveByName(String name) throws E;
    boolean doCheckStockBySn(String sn) throws E;
    boolean doSave(Cpu cpu) throws E;
    boolean doUpdate(Cpu cpu) throws E;
    boolean doUpdateStock(Cpu cpu) throws E;
    boolean doDeleteBySn(String name) throws E;

}
