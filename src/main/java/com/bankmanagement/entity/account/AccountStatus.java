package com.bankmanagement.entity.account;

/*
  ACTIVE – Account is fully operational.
  SUSPENDED – Temporarily disabled due to suspicious activity or user request.
  CLOSED – Permanently closed account; no transactions allowed.
  PENDING_APPROVAL – Account created but awaiting admin verification.
  DORMANT – Inactive for a long period (typically 12+ months) requires reactivation.
 */

import lombok.Getter;

@Getter
public enum AccountStatus {
  PENDING_APPROVAL("PENDING_APPROVAL"),
  ACTIVE("ACTIVE"),
  DORMANT("DORMANT"),
  SUSPENDED("SUSPENDED"),
  CLOSED("CLOSED");

  private final String value;

  AccountStatus(String value) {
    this.value = value;
  }
}
