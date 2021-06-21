package Model.Gpu;


import java.util.ArrayList;

public interface IGpuDao<E extends Exception>{
    ArrayList<Gpu> doRetrieveAll(int limit, int offset) throws E;
    Gpu doRetrieveById(int id) throws E;
    ArrayList<Gpu> doRetrieveByName(String name,int limit, int offset) throws E;
    boolean doSave(Gpu gpu) throws E;
    boolean doUpdate(Gpu gpu) throws E;
    boolean doUpdateStock(Gpu gpu) throws E;
    boolean doDelete(int id) throws E;
}
