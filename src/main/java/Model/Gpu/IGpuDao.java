package Model.Gpu;


import java.util.ArrayList;

public interface IGpuDao<E extends Exception>{
    ArrayList<Gpu> doRetrieveAll() throws E;
    Gpu doRetrieveBySn(String sn) throws E;
    Gpu doRetrieveByName(String name) throws E;
    boolean doSave(Gpu gpu) throws E;
    boolean doUpdate(Gpu gpu) throws E;
    boolean doUpdateStock(Gpu gpu) throws E;
    boolean doDelete(String sn) throws E;
}
