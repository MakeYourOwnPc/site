package Model.Build;

import Model.Cpu.Cpu;
import Model.Gpu.Gpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.Psu.Psu;

import java.util.ArrayList;

public interface IBuildDao<E extends Exception>{
    ArrayList<BuildNames> doRetrieveAll(int limit,int offset) throws E;
    Build doRetrieveById(int id) throws E;
    BuildNames doRetrieveNamesById(int id) throws E;
    //ArrayList<BuildNames> doRetrieveByType(String type) throws E;
    ArrayList<BuildNames> doRetrieveSuggested() throws E;
    ArrayList<BuildNames> doRetrieveByMaker(String maker) throws E;
    ArrayList<BuildNames> doRetrieveByParameters(String mobo,String cpu,String gpu,String psu,String type,Boolean isSuggested,int limit,int offset) throws E;
    boolean doDelete(int id) throws E;
    boolean doUpdate(Build build)throws E;
    boolean doSave(Build build) throws E;


}
