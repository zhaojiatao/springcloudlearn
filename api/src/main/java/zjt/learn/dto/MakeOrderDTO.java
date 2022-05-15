package zjt.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2022/5/12 9:47
 * @ClassName: MakeOrderDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MakeOrderDTO implements Serializable {
    private static final long serialVersionUID = 1314078847059335812L;

    private Long id;

    /**
     * 订单编号(支持输入数字、字母)
     * ^表示开头
     * $表示结尾
     * +表示若干
     */
    private String orderNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户真实姓名
     */
    private String userRealName;


    /**
     * 订单总金额 单位元，小数点两位精确到分
     */
    private BigDecimal orderAmount;


    private String orderSourceCode;

    private String orderSourceDesc;

    private String orderStatusCode;

    private String orderStatusDesc;

    private String orderPlatformCode;

    private String orderPlatformDesc;


    /**
     * 收货人姓名
     */
    private String shippingName;

    /**
     * 收货人联系方式
     */
    private String shippingContact;
    /**
     * 收货人地址
     */
    private String shippingAddress;


    /**
     * 下单时间
     */
    private String makeOrderAt;

    /**
     * 发票类型
     */
    private String invoiceType;
    /**
     * 发票抬头
     */
    private String invoiceLookedUp;
    /**
     * 发票内容
     */
    private String invoiceContent;
    /**
     * 纳税人识别号
     */
    private String taxpayerIdentificationNumber;



}
