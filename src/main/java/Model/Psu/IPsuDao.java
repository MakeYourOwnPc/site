package Model.Psu;

import Model.Mobo.Mobo;

import java.util.ArrayList;

public interface IPsuDao<E extends Exception>{
    ArrayList<Psu> doRetrieveAll() throws E;
    Psu doRetrieveBySn(String sn) throws E;
    Psu doRetrieveByName(String name)throws E;
    ArrayList<Psu> doRetrieveByPower(int power) throws E;
    boolean doDelete(String sn) throws E;
    boolean doUpdate(Psu psu)throws E;
    boolean doUpdateStock(Psu psu)throws E;
    boolean doSave(Psu psu) throws E;
    boolean doCheckStockBySn(String sn)throws E;
}
