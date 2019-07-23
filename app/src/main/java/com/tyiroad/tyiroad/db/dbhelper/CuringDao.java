package com.tyiroad.tyiroad.db.dbhelper;




import java.util.ArrayList;
import java.util.List;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.ConstructionInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.ConstructionUploadInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.DiseaseBaseInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.DisposalSchemeInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.FilterBHInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.FilterLxInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.GcldInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.GyfwInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.InspectionPropertyContentInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.InspectionPropertyInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.LogRecordInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.MonitoringInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.QualityInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.RoadLineInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.RoadSectionInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.TypeOfInvestigation;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.UnitInfo;
import com.tyiroad.tyiroad.db.dbhelper.dbInfo.XclxInfo;

/**
 * 数据库访问接口类
 */
public interface CuringDao {

    /**
     * 释放各个数据查询对象
     */
    void release();

    /**
     * 添加施工维修记录 单个
     * @param info
     */
    int addSgwx(ConstructionInfo info);

    /**
     * 添加施工维修记录 多个
     * @param infoList
     */
    int addSgwx(ArrayList<ConstructionInfo> infoList);

    /**
     * 查询所有施工维修记录
     */
    List<ConstructionInfo> querySgwx();

    /**
     * 根据病害id查询施工记录
     * @param bhid
     * @return
     */
    ConstructionInfo querySgwxByBhid(String bhid);

    /**
     * 根据 路线id 病害id 查询施工维修记录  时间倒序
     */
    List<ConstructionInfo> querySgwxByLxidAndBhidDaoXu(String lxid, String bhid);

    /**
     * 根据 路线id 病害id 查询施工维修记录  时间正序
     */
    List<ConstructionInfo> querySgwxByLxidAndBhidZhengXu(String lxid, String bhid);

    /**
     * 当前病害id是否在施工维修表里存在
     * @param bhid
     * @return
     */
    boolean isExitByBhid(String bhid);

    /**
     * 修改施工维修记录
     * @param info
     * @return
     */
    int updateSgwx(ConstructionInfo info);

    /***
     * 根据id删除一条施工记录
     * @param sgwxId
     */
    int deleteSgwxById(int sgwxId);

    /**
     * 添加施工维修记录 带上传表
     * @param info
     */
    int addSgwxUpload(ConstructionUploadInfo info);

    /**
     * 查询所有施工维修记录 带上传表
     */
    List<ConstructionUploadInfo> querySgwxUpload();

    /**
     * 根据病害id查询施工记录带上传表
     * @param bhid
     * @return
     */
    ConstructionUploadInfo querySgwxUploadByBhid(String bhid);

    /**
     * 当前病害id是否在施工维修表里存在 待上传表
     * @param bhid
     * @return
     */
    boolean isExitByBhidInUpload(String bhid);

    /**
     * 修改施工维修记录 带上传表
     * @param info
     * @return
     */
    int updateSgwxUpload(ConstructionUploadInfo info);

    /***
     * 根据id删除一条施工记录 带上传表
     * @param sgwxId
     */
    int deleteSgwxUploadById(int sgwxId);




    /**
     * 添加病害基本信息 单个
     * @param info
     */
    int addBingHaiBase(DiseaseBaseInfo info);

    /**
     * 添加病害基本信息 多个
     * @param infoList
     */
    int addBingHaiBase(ArrayList<DiseaseBaseInfo> infoList);

    /**
     * 修改病害基本信息
     * @param info
     */
    int updateBingHaiBase(DiseaseBaseInfo info);

    /**
     * 查询最后一条病害基本信息的（也就是最新添加的一条）id
     * @return
     */
    int queryBingHaiBaseIdForLast();

    /**
     * 根据id查询病害基本信息
     * @param baseid
     * @return
     */
    DiseaseBaseInfo queryBingHaiBaseById(int baseid);

    /**
     * 查询所有采集病害基本信息
     * @return
     */
    List<DiseaseBaseInfo> queryAllBingHaiBase(String gydwid);

    /**
     * 通过时间 路线 路段 合同 清单 查询病害基本信息 倒序
     * @return
     */
    List<DiseaseBaseInfo> queryBingHaiBaseByLdAndSjDaoXu(long start, long end, String lxid, String ldid, String htid, String qdid);

    /**
     * 通过时间 路线 路段 合同 清单 查询病害基本信息 正序
     * @return
     */
    List<DiseaseBaseInfo> queryBingHaiBaseByLdAndSjZhengXu(long start, long end, String lxid, String ldid, String htid, String qdid);

    /**
     * 根据单位id 路线id 病害id 起始时间 查询病害基本信息
     * @return
     */
    List<DiseaseBaseInfo> queryBingHaiBaseByDwLxBh(String dwid, String lxid, String bhid, long start, long end);

    /**
     * 删除一条病害基本信息
     * @param bhbaseId
     */
    int deleteBingHaiBaseById(int bhbaseId);





    /**
     * 增加处置方案
     * @param infoList
     */
    int addCzfa(ArrayList<DisposalSchemeInfo> infoList);

    /**
     * 根据病害名称查询处置方案
     * @return
     */
    DisposalSchemeInfo queryCzfaByBhmc(String mcStr);

    /**
     * 根据处置方案名称或病害名称模糊查询处置方案
     * @return
     */
    List<DisposalSchemeInfo> queryCzfaByMcLike(String mcStr);

    /**
     * 根据处置方案名称或病害名称模糊查询处置方案 并且根据清单id进行查询
     * @return
     */
    List<DisposalSchemeInfo> queryCzfaByMcLikeAndQdid(String mcStr, String qdid);

    /**
     * 根据清单id进行查询 查询获取第一条数据
     * @return
     */
    DisposalSchemeInfo queryCzfaByQdidForFirst(String qdid);

    /**
     * 删除所有处置方案
     */
    int deleteAllCzfa();

    /**
     * 添加巡查性质内容
     * @param infoList
     */
    int addXcxznr(ArrayList<InspectionPropertyContentInfo> infoList);

    /**
     * 通过巡查性质名称查询所有巡查性质内容
     * @param
     * @return
     */
    List<InspectionPropertyContentInfo> queryXcxznrByXcxzmc();

    /**
     * 根据巡查性质内容的id查询巡查性质内容
     * @param xcxznrid
     * @return
     */
    InspectionPropertyContentInfo queryXcxznrById(String xcxznrid);

    /**
     * 删除所有巡查性质内容
     */
    int deleteAllXcxznr();

    /**
     * 添加巡查性质
     * @param infoList
     */
    int addXcxz(ArrayList<InspectionPropertyInfo> infoList);

    /**
     * 查询所有巡查性质
     * @return
     */
    List<InspectionPropertyInfo> queryXcxz();

    /**
     * 删除所有巡查性质
     */
    int deleteAllXcxz();

    /**
     * 添加日志记录 单个
     * @param info
     */
    int addRzjl(LogRecordInfo info);
    /**
     * 添加质量检验表单个
     * @param info
     */
    int addQuality(QualityInfo info);
    /**
     * 添加过程监控表单个
     * @param info
     */
    int addMonitoring(MonitoringInfo info);
    /**
     * 添加日志记录 多个
     * @param infoList
     */
    int addRzjl(ArrayList<LogRecordInfo> infoList);

    /**
     * 修改日志记录
     * @param info
     * @return
     */
    int updateRzjl(LogRecordInfo info);
    /**
     * 修改过程监控
     * @param info
     * @return
     */
    int updateMonitoring(MonitoringInfo info);
    /**
     * 修改质量检验
     * @param info
     * @return
     */
    int updateQuality(QualityInfo info);
    /**
     * 根据日志记录id查询单个日志
     * @param rzjlid
     * @return
     */
    LogRecordInfo queryRzjlById(int rzjlid);
    /**
     * 根据id查询过程监控
     * @param rzjlid
     * @return
     */
    MonitoringInfo queryMonitoring(int rzjlid);
    /**
     * 根据id查询质量检验
     * @param rzjlid
     * @return
     */
    QualityInfo queryQuality(int rzjlid);
    /**
     * 查询所有日志记录
     * @return
     */
    List<LogRecordInfo> queryAllRzjl();
    /**
     * 查询所有质量检验
     * @return
     */
    List<QualityInfo> queryAllQuality();
    /**
     * 查询所有过程监控
     * @return
     */
    List<MonitoringInfo> queryAllMonitoring();
    /**
     * 按时间查询日志记录 倒序
     * @return
     */
    List<LogRecordInfo> queryRzjlByTimeDaoXu(long start, long end);

    /**
     * 按时间查询日志记录 正序
     * @return
     */
    List<LogRecordInfo> queryRzjlByTimeZhengXu(long start, long end);

    /**
     * 根据id删除一条日志记录
     * @param rzjlId
     */
    int deleteRzjlById(int rzjlId);
    /**
     * 根据id删除一条质量检验
     * @param rzjlId
     */
    int deleteQualityId(int rzjlId);
    /**
     * 根据id删除一条过程监控
     * @param rzjlId
     */
    int deleteMonitoring(int rzjlId);
    /**
     * 添加路线
     * @param infoList
     */
    int addLx(ArrayList<RoadLineInfo> infoList);

    /**
     * 根据路线id查询路线
     */
    RoadLineInfo queryLxByLxid(String lxid);

    /**
     * 根据路段id查询路线
     */
    List<RoadLineInfo> queryLxByLdid(String ldid);

    /**
     * 根据路线名称查询路线
     */
    RoadLineInfo queryLxByLxmc(String lxmc);

    /**
     * 查询所有路线
     * @return
     */
    List<RoadLineInfo> queryAllLx();

    /**
     * 删除所有路线
     */
    int deleteAllLx();

    /**
     * 添加路段
     * @param infoList
     */
    int addLd(ArrayList<RoadSectionInfo> infoList);

    /**
     * 根据路段id查询路段
     * @param ldid
     * @return
     */
    RoadSectionInfo queryLdByLdid(String ldid);
    RoadSectionInfo queryLdByLdmc(String ldmc);
    /**
     * 新巡查路线ID查询
     * @param lxid
     * @return
     */
    XclxInfo queryXclxid(String lxid);
    /**
     * 通过路线id查询所对应的路段
     * @return
     */
    List<RoadSectionInfo> queryLdByLxid(String lxid);

    /**
     * 通过管养单位id查询所对应的路段
     * @return
     */
    List<RoadSectionInfo> queryLdByGydwid(String gydwid);
    /**
     * 新巡查路线
     * @return
     */
    List<XclxInfo> queryAllXclx();
    /**
     * 删除所有路段
     */
    int deleteAllLd();

    /**
     * 添加管养单位
     * @param infoList
     */
    int addGydw(ArrayList<UnitInfo> infoList);

    /**
     * 根据qydwid查询单个管养单位
     * @param gydwid
     * @return
     */
    UnitInfo queryGydwById(String gydwid);

    /**
     * 根据父级id查询子管养单位
     * @param parentId
     * @return
     */
    List<UnitInfo> queryGydwByParentId(String parentId);

    /**
     * 删除所有管养单位
     */
    int deleteAllGydw();

    /**
     * 添加调查类型
     * @param infoList
     */
    int addInvestigation(ArrayList<TypeOfInvestigation> infoList);

    /**
     * 根据id查询调查类型
     * @return
     */
    TypeOfInvestigation queryInvestigationById(String typeId);
    TypeOfInvestigation queryInvestigationByMc(String mc);
    /**
     * 查询所有调查类型
     * @return
     */
    List<TypeOfInvestigation> queryAllInvestigation();
//    List<TypeOfInvestigation> queryAllInvestigation();
//    Test
    /**
     * 删除所有调查类型
     */
    int deleteAllDcLx();

    /**
     * 添加工程路段
     */
    int addGcld(ArrayList<GcldInfo> infoList);
    /**
     * 添加管养范围表
     */
    int addGyfw(ArrayList<GyfwInfo> infoList);
    /**
     * 添加路线ID表
     */
    int addXclx(ArrayList<XclxInfo> infoList);
    /**
     * 根据gcldid查询工程路段
     */
    GcldInfo queryGcld(String gcldid);

    /**
     * 根据gcldid查询工程路段
     */
    List<GcldInfo> queryGcldByGcldid(String gcldid);

    /**
     * 根据gcldid查询工程路段
     * @param lxid
     * @param zh 桩号
     * @return
     */
    List<GcldInfo> queryGcldByLxidAndZh(String lxid, String zh);

    /**
     * 删除所有工程路段
     */
    int deleteAllGcld();

    /**
     * 删除所有离线包基础数据表里的数据
     */
    void deleteAllBaseTableData();

    /**
     * 通过路线名称和病害id查询施工记录
     * @param lxmc
     * @param bhid
     * @return
     */
    List<ConstructionInfo> querySgxxBylxidAndBhid(String lxmc, String bhid);

    /**

     * 通过路线名称和病害id查询施工记录待上传表
     * @param lxmc
     * @param bhid
     * @return
     */
    List<ConstructionUploadInfo> querySgxxUploadBylxidAndBhid(String lxmc, String bhid);

    /**
     * 添加筛选病害信息
     * @param listInfo
     * @return
     */
    int addFilterBHInfo(ArrayList<FilterBHInfo> listInfo);

    /**
     * 查询所有筛选病害信息
     * @return
     */
    List<FilterBHInfo> queryAllFilterBHInfo();

    /**
     * 删除所有筛选病害信息
     * @return
     */
    int deleteAllBHInfo();
    /**
     * 删除所有日志信息
     * @return
     */
    int deleteAllRzInfo();
    /**
     * 删除所有过程监控
     * @return
     */
    int deleteAllMonitoring();
    /**
     * 删除新巡查路线
     * @return
     */
    int deleteAllXclx();
    /**
     * 删除所有质量检验
     * @return
     */
    int deleteAllQualityInfo();
    /**
     * 删除所有病害信息
     * @return
     */
    int deleteAllBhInfo();
    /**
     * 删除所有带施工表
     * @return
     */
    int deleteAllDSgInfo();
    /**
     * 删除所有施工表
     * @return
     */
    int deleteAllSgInfo();
    /**
     * 添加筛选路线信息
     * @param listInfo
     * @return
     */
    int addFilterLXInfo(ArrayList<FilterLxInfo> listInfo);

    /**
     * 查询所有筛选路线信息
     * @return
     */
    List<FilterLxInfo> queryAllFilterLXInfo();

    /**
     * 删除所有筛选路线信息
     * @return
     */
    int deleteAllLXInfo();
    /**
     * 查询所有病害类型去重
     * @return
     */
    List<DisposalSchemeInfo> queryAllBHLXInfo();
    //查询病害名称
    List<DisposalSchemeInfo> queryBH(String bhlx);
    List<DisposalSchemeInfo> queryCZFA(String czfa, String bhlx);
    List<DisposalSchemeInfo> queryGCXM(String gcxm, String bhlx);

}
