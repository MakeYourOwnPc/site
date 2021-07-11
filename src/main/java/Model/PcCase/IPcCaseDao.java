package Model.PcCase;

import Model.Gpu.Gpu;

import java.util.ArrayList;

public interface IPcCaseDao<E extends Exception>{
    ArrayList<PcCase> doRetrieveAll(int limit, int offset) throws E;
    PcCase doRetrieveById(int id) throws E;
    ArrayList<PcCase> doRetrieveByName(String name,int limit, int offset) throws E;
    ArrayList<PcCase> doRetrieveByParameters(String name,String formFactor,int limit,int offset) throws E;
    boolean doSave(PcCase pcCase) throws E;
    boolean doUpdate(PcCase pcCase) throws E;
    boolean doUpdateStock(PcCase pcCase) throws E;
    boolean doDelete(int id) throws E;
}
