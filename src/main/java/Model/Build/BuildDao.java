package Model.Build;

import Model.Cpu.Cpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.Psu.Psu;

import java.util.ArrayList;

public interface BuildDao<E extends Exception>{
    ArrayList<Build> doRetrieveAll() throws E;
    Build doRetrieveById(int id) throws E;
    ArrayList<Build> doRetrieveByType(String type)throws E;
    ArrayList<Build> doRetrieveByPower(int power) throws E;
    boolean doDeleteById(int id) throws E;
    boolean doUpdate(Build build)throws E;
    boolean doSave(Build build) throws E;
    ArrayList<Build> doRetrieveSuggested() throws E;
    ArrayList<Build> doRetrieveByMobo(Mobo mobo) throws E;
    ArrayList<Build> doRetrieveByCpu(Cpu cpu) throws E;
    ArrayList<Build> doRetrieveByMemory(Memory memory) throws E;

}
