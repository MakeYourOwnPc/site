package Model.Country;

import java.util.ArrayList;

public interface ICountryDao<E extends Exception>{
    ArrayList<Country> doRetrieveAll() throws E;
    boolean doSave(Country country)throws E;
    boolean doDelete(String id)throws E;
    boolean doCheckByLabel(String label)throws E;
    boolean doCheckById(String id)throws E;
}
