package Model.PcCase;

import Model.Gpu.Gpu;

import java.util.ArrayList;

public interface IPcCaseDao<E extends Exception>{
    ArrayList<PcCase> doRetrieveAll() throws E;
    ArrayList<PcCase> doRetrieveByFormFactor() throws E;
    PcCase doRetrieveBySn(String sn) throws E;
    PcCase doRetrieveByName(String name) throws E;
    boolean doCheckStockBySn(String sn) throws E;
    boolean doSave(PcCase pcCase) throws E;
    boolean doUpdate(PcCase pcCase) throws E;
    boolean doUpdateStock(PcCase pcCase) throws E;
    boolean doDeleteBySn(String sn) throws E;
}
