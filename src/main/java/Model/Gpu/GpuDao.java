package Model.Gpu;


import java.util.ArrayList;

public interface GpuDao <E extends Exception>{
    ArrayList<Gpu> doRetrieveAll() throws E;
    Gpu doRetrieveBySn(String sn) throws E;
    Gpu doRetrieveByName(String name) throws E;
    boolean doCheckStockBySn(String sn) throws E;
    boolean doSave(Gpu gpu) throws E;
    boolean doUpdate(Gpu gpu) throws E;
    boolean doUpdateStock(Gpu gpu) throws E;
    boolean doDeleteBySn(String sn) throws E;
}
