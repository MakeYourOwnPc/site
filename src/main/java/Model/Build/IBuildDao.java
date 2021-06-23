package Model.Build;

import Model.Cpu.Cpu;
import Model.Gpu.Gpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.Psu.Psu;

import java.util.ArrayList;

public interface IBuildDao<E extends Exception>{
    ArrayList<Build> doRetrieveAll(int limit,int offset) throws E;
    Build doRetrieveById(int id) throws E;
    ArrayList<Build> doRetrieveByType(String type,int limit,int offset)throws E;
    ArrayList<Build> doRetrieveByPower(int power,int limit,int offset) throws E;
    ArrayList<Build> doRetrieveSuggested(int limit,int offset) throws E;
    ArrayList<Build> doRetrieveByMobo(Mobo mobo,int limit,int offset) throws E;
    ArrayList<Build> doRetrieveByCpu(Cpu cpu,int limit,int offset) throws E;
    ArrayList<Build> doRetrieveByGpu(Gpu gpu, int limit, int offset) throws E;
    boolean doDelete(int id) throws E;
    boolean doUpdate(Build build)throws E;
    boolean doSave(Build build) throws E;


}