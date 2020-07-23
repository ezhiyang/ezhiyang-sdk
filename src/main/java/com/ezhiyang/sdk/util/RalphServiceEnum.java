package com.ezhiyang.sdk.util;

/**
 * 开放接口枚举
 * @author ZY
 */
public enum RalphServiceEnum {
  /**
   * 1-认证
   */
  PERSONAUTH(1, "ralph.personAuth", "认证", "name,certNo,mobile,accountType,accountNo"),
  /**
   * 2-提现提交
   */
  SUBMITWITHDRAW(2, "ralph.submitWithdraw", "提现提交",
      "[withdrawList,bizId,name,certNo,mobile,accountType,accountNo,amount]"),
  /**
   * 3-提现查询
   */
  QUEYRWITHDRAW(3, "ralph.queryWithdraw", "提现查询", "[queryList,bizId,taskNo]"),
  /**
   * 4-状态回调
   */
  CALLBACKSTATUS(4, "ralph.callbackStatus", "状态回调", "[statusList,bizId,status,taskNo]"),
  /**
   * 5-企业开通
   */
  OPENCOMPANY(5, "ralph.openCompany", "企业开通", "companyName,taxPayerId,industry,linkman,mobile"),
  /**
   * 6-开户信息异动回调
   */
  CALLBACKCOMPANY(6, "ralph.callbackCompany", "开户信息异动回调",
      "companyCode,companyName,payeeName,bankName,cardNo,taxAreaId,taxAreaName"),
  /**
   * 7-查询余额
   */
  QUERYBALANCE(7, "ralph.queryBalance", "查询余额", "companyCode,taxAreaId"),
  /**
   * 8-查询账户明细
   */
  QUERYACCOUNTDETAIL(8, "ralph.queryAccountDetail", "查询账户明细",
      "companyCode,taxAreaId,queryStartDate,queryEndDate"),

  /**
   * 9-额度查询
   */
  QUERYAVAILABLEAMOUNT(9, "ralph.queryAvailableAmount", "额度查询",
      "[queryList,certNo,name]"),
  /**
   * 10-活体认证
   */
  FACERECOGNITION(10, "ralph.faceRecognition", "活体认证",
      "name,certNo"),


  QUERYBANKRECEIPT(18, "ralph.queryBankReceipt", "查询结算回单", "bizId,taskNo");

  private Integer id;
  private String name;
  private String desc;
  private String signPropsIn;

  private RalphServiceEnum(Integer id, String name, String desc, String signPropsIn) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.signPropsIn = signPropsIn;
  }

  public static RalphServiceEnum getByName(String name) {
    if (PERSONAUTH.name.equals(name)) {
      return PERSONAUTH;
    } else if (SUBMITWITHDRAW.name.equals(name)) {
      return SUBMITWITHDRAW;
    } else if (QUEYRWITHDRAW.name.equals(name)) {
      return QUEYRWITHDRAW;
    } else if (CALLBACKSTATUS.name.equals(name)) {
      return CALLBACKSTATUS;
    } else if (OPENCOMPANY.name.equals(name)) {
      return OPENCOMPANY;
    } else if (CALLBACKCOMPANY.name.equals(name)) {
      return CALLBACKCOMPANY;
    } else if (QUERYBALANCE.name.equals(name)) {
      return QUERYBALANCE;
    } else if (QUERYACCOUNTDETAIL.name.equals(name)) {
      return QUERYACCOUNTDETAIL;
    } else if (QUERYAVAILABLEAMOUNT.name.equals(name)) {
      return QUERYAVAILABLEAMOUNT;
    } else if (FACERECOGNITION.name.equals(name)) {
      return FACERECOGNITION;
    } else if(QUERYBANKRECEIPT.name.equals(name)){
      return QUERYBANKRECEIPT;
    }
    return null;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  public String getSignPropsIn() {
    return signPropsIn;
  }

}
