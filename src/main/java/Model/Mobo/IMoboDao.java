package Model.Mobo;

import Model.Memory.Memory;

import java.util.ArrayList;

public interface IMoboDao<E extends Exception>{
    ArrayList<Mobo> doRetrieveAll(int limit, int offset) throws E;
    Mobo doRetrieveById(int id) throws E;
    ArrayList<Mobo> doRetrieveByName(String name,int limit, int offset)throws E;
    ArrayList<Mobo> doRetrieveByCpuSocket(String socket,int limit, int offset) throws E;
    ArrayList<Mobo> doRetrieveByRamSocket(String socket,int limit, int offset) throws E;
    ArrayList<Mobo> doRetrieveByFormFactor(String formFactor,int limit, int offset) throws E;
    ArrayList<Mobo> doRetrieveByAmountOfSlotNvme(int slot,int limit, int offset) throws E;
    ArrayList<Mobo> doRetrieveByAmountOfSlotRam(int slot,int limit, int offset) throws E;
    ArrayList<Mobo> doRetrieveByAmountOfSlotSata(int slot,int limit, int offset) throws E;
    ArrayList<Mobo> doRetrieveByParameters(String name,String ramSocket,String cpuSocket,String formFactor,int nvmeSlot,int sataSlot,int ramSlot,int limit, int offset) throws E;
    boolean doDelete(int id) throws E;
    boolean doUpdate(Mobo mobo)throws E;
    boolean doUpdateStock(Mobo mobo)throws E;
    boolean doSave(Mobo mobo) throws E;



}
