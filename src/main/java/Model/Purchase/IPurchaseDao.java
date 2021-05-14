package Model.Purchase;

import Model.Build.Build;
import Model.Cpu.Cpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.Psu.Psu;

import java.util.ArrayList;

public interface IPurchaseDao<E extends Exception>{
    ArrayList<Purchase> doRetrieveAll() throws E;
    Purchase doRetrieveById(int id) throws E;
    ArrayList<Purchase> doRetrieveByType(String type)throws E;
    ArrayList<Purchase> doRetrieveByPower(int power) throws E;
    boolean doDelete(int id) throws E;
    boolean doUpdate(Purchase purchase)throws E;
    boolean doSave(Purchase purchase) throws E;
    ArrayList<Purchase> doRetrieveSuggested() throws E;
    ArrayList<Purchase> doRetrieveByMobo(Mobo mobo) throws E;
    ArrayList<Purchase> doRetrieveByCpu(Cpu cpu) throws E;
    ArrayList<Purchase> doRetrieveByMemory(Memory memory) throws E;
    ArrayList<Purchase> doRetrieveByEmail(String memory) throws E;


}
