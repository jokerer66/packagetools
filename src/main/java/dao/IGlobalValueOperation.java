package dao;

import bean.GlobalValue;

import java.util.List;

public interface IGlobalValueOperation {

    public List<GlobalValue> selectGlobalValues();

    public GlobalValue selectGlobalValueByKeyID(int keyid);

    public GlobalValue selectGlobalValueByName(String keyname);

    public void addGlobalValue(GlobalValue globalValue);

    public void updateGlobalValue(GlobalValue globalValue);

    public void deleteGlobalValue(int keyid);



}
