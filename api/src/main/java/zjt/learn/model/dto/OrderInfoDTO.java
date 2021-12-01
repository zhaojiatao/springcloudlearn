package zjt.learn.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/10/26 13:17
 * @ClassName: OrderInfoDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单编码
     */
    @ApiModelProperty(name = "orderSerial", value = "订单编码")
    private String orderSerial;

    /**
     * 租户 ID
     */
    @ApiModelProperty(name = "tenantId", value = "租户ID")
    private Long tenantId;

    /**
     * 采购商 ID
     */
    @ApiModelProperty(name = "purchaserId", value = "采购商ID")
    private Long purchaserId;

    /**
     * 实付价
     */
    @ApiModelProperty(name = "fee", value = "实付价")
    private BigDecimal fee;
    /**
     * 状态
     */
    @ApiModelProperty(name = "status", value = "状态")
    private Integer status;

    /**
     * 订单来源: 1目录采购, 2生产物料采购, 3人工采购
     *
     * @see
     */
    @ApiModelProperty(name = "orderSource", value = "订单来源: 1目录采购, 2生产物料采购, 3人工采购，7购物车， 8协议下单")
    private Integer orderSource;

    /**
     * 订单额外信息,持久化到数据库
     */
    protected String extraJson;

    /**
     * 订单额外信息,不持久化到数据库
     */
    protected Map<String, String> extra;
    /**
     * 订单tag信息,持久化到数据库
     */
    protected String tagsJson;
    /**
     * 订单tag信息,不持久化到数据库
     */
    protected Map<String, String> tags;

    /**
     * 订单创建时间
     */
    private Date createdAt;
    /**
     * 订单更新时间
     */
    private Date updatedAt;

    /**
     * 0，非删除
     */
    private Integer isDeleted;

    /**
     * 标签预留
     */
    private Long options;

    /**
     * 买家对象类型：0-用户，1-门店，2-公司
     */
    @ApiModelProperty(name = "", value = "买家对象类型：0-用户，1-门店，2-公司")
    private Integer buyerType;

    /**
     * 买家id
     */
    @ApiModelProperty(name = "", value = "买家id")
    private Long buyerId;

    /**
     * 卖家名称
     */
    @ApiModelProperty(name = "", value = "卖家名称")
    private String buyerName;

    /**
     * 卖家对象类型：0-用户，1-门店，2-公司
     */
    @ApiModelProperty(name = "", value = "卖家对象类型：0-用户，1-门店，2-公司")
    private Integer sellerType;

    /**
     * 卖家id
     */
    @ApiModelProperty(name = "", value = "卖家id")
    private Long sellerId;

    /**
     * 卖家名称
     */
    @ApiModelProperty(name = "", value = "卖家名称")
    private String sellerName;

    /**
     * 外部订单id
     */
    @ApiModelProperty(name = "", value = "外部订单id")
    private String outId;

    /**
     * 采购订单 ID
     */
    @ApiModelProperty(name = "", value = "采购订单 ID")
    private Long purchaserOrderId;

    /**
     * 采购订单编号
     */
    @ApiModelProperty(name = "", value = "采购订单编号")
    private String purchaseOrderSerial;

    /**
     * 订单类型
     *
     */
    @ApiModelProperty(name = "", value = "订单类型")
    private Integer type;

    /**
     * 批次号
     */
    @ApiModelProperty(name = "", value = "批次号")
    private String batchCode;

    /**
     * 原价
     */
    @ApiModelProperty(name = "", value = "原价")
    private BigDecimal originFee;

    /**
     * 运费
     */
    @ApiModelProperty(name = "", value = "运费")
    private BigDecimal shipFee;

    /**
     * 运费原始金额
     */
    @ApiModelProperty(name = "", value = "运费原始金额")
    private BigDecimal originShipFee;

    /**
     * 配送方式
     */
    @ApiModelProperty(name = "", value = "配送方式")
    private Integer shipmentType;

    /**
     * 收货人信息
     */
    @ApiModelProperty(name = "", value = "收货人信息")
    private String receiverInfos;

    /**
     * 期望到货时间
     */
    @ApiModelProperty(name = "", value = "期望到货时间")
    private Date expectArriveAt;

    /**
     * 到货仓库id
     */
    @ApiModelProperty(name = "", value = "到货仓库id")
    private Long warehouseId;

    /**
     * 地址标记
     */
    @ApiModelProperty(name = "", value = "地址标记")
    private String addressRemark;

    /**
     * 订单创建来源单据类型： 1-采购申请，2-采购单（支持补单），3-销售单
     */
    @ApiModelProperty(name = "", value = "订单创建来源单据类型： 1-采购申请，2-采购单（支持补单），3-销售单")
    private Integer createType;

    /**
     * 关联的来源单据id
     */
    @ApiModelProperty(name = "", value = "关联的来源单据id")
    private String createFrom;

    /**
     * 下单人ID
     */
    @ApiModelProperty(name = "", value = "下单人ID")
    private Long makeOrderUserId;

    /**
     * 实际操作下单的人的ID
     */
    @ApiModelProperty(name = "", value = "实际操作下单的人的ID")
    private Long operatorId;

    /**
     * 修改成当前版本的人
     */
    @ApiModelProperty(name = "", value = "修改成当前版本的人")
    private Long changeOrderUserId;

    /**
     * 买家备注
     */
    @ApiModelProperty(name = "", value = "买家备注")
    private String buyerNote;


    /**
     * 是否货到付款  1  是 其他  否
     */
    @ApiModelProperty(name = "", value = "是否货到付款  1  是  其他 否")
    private String cashOnDelivery;

    /**
     * 产品组编码
     */
    @ApiModelProperty(name = "", value = "产品组编码")
    private String itemGroupCode;

    /**
     * 产品组名称
     */
    @ApiModelProperty(name = "", value = "产品组名称")
    private String itemGroupName;

    /**
     * 协议编码
     */
    @ApiModelProperty(name = "", value = "协议编码")
    private String contractCode;

    /**
     * 协议名称
     */
    @ApiModelProperty(name = "", value = "协议名称")
    private String contractName;

    /**
     * 交易流程 id
     */
    @ApiModelProperty(name = "", value = "交易流程 id")
    private Integer flowId;

    /**
     * 审批流程 id
     */
    @ApiModelProperty(name = "", value = "审批流程 id")
    private String processDefinitionId;

    /**
     * 推荐人id
     */
    @ApiModelProperty(name = "", value = "推荐人id")
    private Long refererId;

    /**
     * 推荐人名称
     */
    @ApiModelProperty(name = "", value = "推荐人名称")
    private String refererName;

    /**
     * 优惠金额
     */
    @ApiModelProperty(name = "", value = "优惠金额")
    private BigDecimal discount;

    /**
     * 运费营销活动id
     */
    @ApiModelProperty(name = "", value = "运费营销活动id")
    private Long shipmentPromotionId;

    /**
     * 积分减免金额
     */
    @ApiModelProperty(name = "", value = "积分减免金额")
    private BigDecimal integral;

    /**
     * 余额减免金额
     */
    @ApiModelProperty(name = "", value = "余额减免金额")
    private BigDecimal balance;

    /**
     * 店铺级别的优惠id
     */
    @ApiModelProperty(name = "", value = "店铺级别的优惠id")
    private Long promotionId;

    /**
     * 支付类型, 1-在线支付 2-货到付款
     */
    @ApiModelProperty(name = "", value = "支付类型, 1-在线支付 2-货到付款")
    private Integer payType;

    /**
     * 订单渠道 1-手机 2-pc
     */
    @ApiModelProperty(name = "", value = "订单渠道 1-手机 2-pc")
    private Integer channel;

    /**
     * 是否申请过逆向流程
     */
    @ApiModelProperty(name = "", value = "是否申请过逆向流程")
    private Boolean hasRefund;

    /**
     * 是否已评价
     */
    @ApiModelProperty(name = "", value = "是否已评价")
    private Boolean commented;

    /**
     * 电商平台佣金费率, 万分之一
     */
    @ApiModelProperty(name = "commissionRate", value = "电商平台佣金费率, 万分之一")
    private BigDecimal commissionRate;

    /**
     * 分销抽佣费率, 万分之一
     */
    @ApiModelProperty(name = "distributionRate", value = "分销抽佣费率, 万分之一")
    private Integer distributionRate;

    /**
     * 改价金额
     */
    @ApiModelProperty(name = "diffFee", value = "改价金额")
    private BigDecimal diffFee;

    /**
     * 数据隔离字段
     */
    @ApiModelProperty(name = "isolation", value = "数据隔离字段")
    private String isolation;

    /**
     * 省份
     */
    @ApiModelProperty(name = "province", value = "省份")
    private String province;

    /**
     * 需支付定金
     */
    @ApiModelProperty(name = "deposit", value = "需支付定金")
    private BigDecimal deposit;

    /**
     * 定金协议id
     */
    @ApiModelProperty(name = "agreementId", value = "定金协议id")
    private Long agreementId;

    /**
     * 待支付时调整的尾差
     */
    private BigDecimal cashBalanceInit;

    /**
     * 订单支付完成时间
     */
    private Date paidAt;
    /**
     * 最优活动描述
     */
    private Long activityId;

    /**
     * 活动类型
     */
    private Integer activityType;

    /**
     * 经销商确认退货时间
     */
    private Date confirmReturnTime;

    /**
     * 满返活动描述
     */
    private String activityDesc;


    /**
     * 最优活动名称
     */
    private String activityName;






}
