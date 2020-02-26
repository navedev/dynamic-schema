/****************************************************************
 * Copyright (C) Lowe's Companies, Inc. All rights reserved. This file is for internal use only at
 * Lowe's Companies, Inc.
 ****************************************************************/
package com.lowes.dynamicschema.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FullResponse {

  @JsonProperty(value = "order_header_key")
  private String orderHeaderKey;

  @JsonProperty(value = "order_number")
  private String orderNo;

  @JsonProperty(value = "order_created_time")
  private String orderDate;

  @JsonProperty(value = "order_source")
  private String orderSource;

  @JsonProperty(value = "order_status")
  private String status;

  /**
   * Order/@Extn/@ExtnExternalCustomerID
   */
  @JsonProperty(value = "business")
  private String business;

  @JsonProperty(value = "order_purpose")
  private String orderPurpose;

  @JsonProperty(value = "customer_first_name")
  private String customerFirstName;

  @JsonProperty(value = "customer_last_name")
  private String customerLastName;

  @JsonProperty(value = "payment_methods")
  private PaymentMethods paymentMethods;

  @Data
  public static class PaymentMethods {

    @JsonProperty(value = "payment_method")
    private List<PaymentMethod> paymentMethod;
  }

  @Data
  public static class PaymentMethod {

    @JsonProperty(value = "display_card_number")
    private String displayCreditCardNo;

    @JsonProperty(value = "credit_card_type")
    private String creditCardType;

    @JsonProperty(value = "max_charge_limit")
    private String maxChargeLimit;

    @JsonProperty(value = "payment_type")
    private String paymentType;

    @JsonProperty(value = "payment_reference_1")
    private String paymentReference1;

  }

}
