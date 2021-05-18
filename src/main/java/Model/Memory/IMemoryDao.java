package Model.Memory;

import Model.Mobo.Mobo;

import java.util.ArrayList;

public interface IMemoryDao<E extends Exception> {
    ArrayList<Memory> doRetrieveAll() throws E;
    Memory doRetrieveBySn(String sn) throws E;
    Memory doRetrieveByName(String name)throws E;
    ArrayList<Memory> doRetrieveBySocket(String socket) throws E;
    boolean doDelete(String sn) throws E;
    boolean doUpdate(Memory memory)throws E;
    boolean doUpdateStock(Memory memory)throws E;
    boolean doSave(Memory memory) throws E;
    boolean doCheckStockBySn(String sn)throws E;

}
