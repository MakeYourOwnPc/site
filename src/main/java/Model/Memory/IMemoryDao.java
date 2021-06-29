package Model.Memory;

import Model.Mobo.Mobo;

import java.util.ArrayList;

public interface IMemoryDao<E extends Exception> {
    ArrayList<Memory> doRetrieveAll(int limit, int offset) throws E;
    Memory doRetrieveById(int id) throws E;
    ArrayList<Memory> doRetrieveByName(String name,int limit, int offset)throws E;
    ArrayList<Memory> doRetrieveBySocket(String socket,int limit, int offset) throws E;
    ArrayList<Memory> doRetrieveByMType(Boolean mType,int limit, int offset) throws E;
    ArrayList<Memory> doRetrieveByParameters(String name,String socket,Boolean mType,int limit,int offset) throws E;
    boolean doDelete(int id) throws E;
    boolean doUpdate(Memory memory)throws E;
    boolean doUpdateStock(Memory memory)throws E;
    boolean doSave(Memory memory) throws E;
}
