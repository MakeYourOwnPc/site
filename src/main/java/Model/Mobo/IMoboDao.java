package Model.Mobo;

import Model.Memory.Memory;

import java.util.ArrayList;

public interface IMoboDao<E extends Exception>{
    ArrayList<Mobo> doRetrieveAll() throws E;
    Mobo doRetrieveBySn(String sn) throws E;
    Mobo doRetrieveByName(String name)throws E;
    ArrayList<Mobo> doRetrieveByCpuSocket(String socket) throws E;
    ArrayList<Mobo> doRetrieveByRamSocket(String socket) throws E;
    ArrayList<Mobo> doRetrieveByFormFactor(String formFactor) throws E;
    ArrayList<Mobo> doRetrieveByAmountOfSlotNvme(int slot) throws E;
    ArrayList<Mobo> doRetrieveByAmountOfSlotSata(int slot) throws E;
    boolean doDeleteBySn(String sn) throws E;
    boolean doUpdate(Mobo mobo)throws E;
    boolean doUpdateStock(Mobo mobo)throws E;
    boolean doCheckStockBySn(String sn)throws E;
    boolean doSave(Mobo mobo) throws E;



}
