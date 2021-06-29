package Model.Cpu;

import java.util.ArrayList;

public interface ICpuDao<E extends Exception>{
    ArrayList<Cpu> doRetrieveAll(int limit, int offset) throws E;
    ArrayList<Cpu> doRetrieveBySocket(String cpuSocket,int limit, int offset) throws E;
    Cpu doRetrieveById(int id) throws E;
    ArrayList<Cpu> doRetrieveByName(String name,int limit, int offset) throws E;
    ArrayList<Cpu> doRetrieveByParameters(String name,String cpuSocket,Boolean integratedGpu,int limit,int offset) throws E;
    boolean doSave(Cpu cpu) throws E;
    boolean doUpdate(Cpu cpu) throws E;
    boolean doUpdateStock(Cpu cpu) throws E;
    boolean doDelete(int id) throws E;
}
