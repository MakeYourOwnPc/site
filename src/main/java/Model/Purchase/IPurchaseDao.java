package Model.Purchase;

import Model.Build.Build;
import Model.Cpu.Cpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.Psu.Psu;

import java.util.ArrayList;

public interface IPurchaseDao<E extends Exception>{
    ArrayList<Purchase> doRetrieveAll(int limit, int offset) throws E;
    Purchase doRetrieveById(int id) throws E;
    ArrayList<Purchase> doRetrieveByType(String type, int limit, int offset)throws E;
    boolean doDelete(int id) throws E;
    boolean doUpdate(Purchase purchase)throws E;
    boolean doSave(Purchase purchase) throws E;
    ArrayList<Purchase> doRetrieveSuggested(int limit, int offset) throws E;
    ArrayList<Purchase> doRetrieveByMobo(String name,int limit, int offset) throws E;
    ArrayList<Purchase> doRetrieveByCpu(String name, int limit, int offset) throws E;
    ArrayList<Purchase> doRetrieveByEmail(String email,int limit, int offset) throws E;

}
