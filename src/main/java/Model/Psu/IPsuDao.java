package Model.Psu;

import Model.Mobo.Mobo;

import java.util.ArrayList;

public interface IPsuDao<E extends Exception>{
    ArrayList<Psu> doRetrieveAll(int limit, int offset) throws E;
    Psu doRetrieveById(int id) throws E;
    ArrayList<Psu> doRetrieveByName(String name,int limit, int offset)throws E;
    ArrayList<Psu> doRetrieveByPower(int power,int limit, int offset) throws E;
    boolean doDelete(int id) throws E;
    boolean doUpdate(Psu psu)throws E;
    boolean doUpdateStock(Psu psu)throws E;
    boolean doSave(Psu psu) throws E;
}
